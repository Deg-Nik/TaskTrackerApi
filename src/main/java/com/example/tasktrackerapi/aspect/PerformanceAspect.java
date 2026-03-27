package com.example.tasktrackerapi.aspect;

import com.example.tasktrackerapi.annotation.Monitored;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : Nikolai Degtiarev
 * created : 24.03.26
 *
 * Мониторинг производительности
 **/
@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    /**
     * Pointcut: методы с аннотацией @Monitored
     */
    @Pointcut("@annotation(monitored)")
    public void monitoredMethods(Monitored monitored) {}

    /**
     * @Around - замер времени выполнения
     *
     * Выполняется ВОКРУГ метода (до и после)
     */
    public Object measureExecutionTime(
            ProceedingJoinPoint joinPoint,
            Monitored monitored
    ) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String operation = monitored.value().isEmpty() ? methodName : monitored.value();
        long threshold = monitored.threshold();

        // До вызова метода
        long startTime = System.currentTimeMillis();
        log.debug("Starting measurement: {}", operation);

        Object result = null;
        try {
            // Вызов оригинального метода
            result = joinPoint.proceed();
            return  result;
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            // Предупреждение если превышен порог
            if (duration > threshold)
                log.warn("SLOW: {} took {}ms (threshold: {}ms)", operation, duration, threshold);
            else
                log.info("{} took {}ms", operation, duration);
        }
    }

    /**
     * Замер времени для ВСЕХ методов service
     */
    public Object measureAllService(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;

        if (duration > 100)
            log.info("Service method {} took {}ms", methodName, duration);

        return result;
    }
}