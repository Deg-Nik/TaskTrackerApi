package com.example.tasktrackerapi.aspect;

import com.example.tasktrackerapi.enums.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : Nikolai Degtiarev
 * created : 24.03.26
 *
 *
 **/
@Aspect
@Component
@Slf4j
public class LoginAspect {
    /**
     * Pointcut: все методы в пакете service
     */
    @Pointcut("execution(* com.example.tasktrackerapi.service.*.*(..))")
    public void serviceMethods() {}

    /**
     * @Before - логирование ВХОДА в метод
     *
     * Выполняется ДО вызова метода
     */
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("-> Calling method: {}", methodName);
        log.debug("-> Arguments: {}", Arrays.toString(args));
    }

    /**
     * AfterReturning - логирование УСПЕШНОГО выполнения
     *
     * Выполняется ТОЛЬКО если метод завершился БЕЗ исключений
     */
    @AfterReturning(
            pointcut = "serviceMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        log.info("<- Method {} completed successfully", methodName);

        // Логируем результат только для простых типов
        if (result != null) {
            if (result instanceof Number || result instanceof String) {
                log.debug("<- Result: {}", result);
            } else if (result instanceof java.util.List) {
                log.debug("<- Result: List of {} items", ((java.util.List<?>) result).size());
            } else {
                log.debug("<- Result: {}", result.getClass().getSimpleName());
            }
        }
    }

    /**
     * @After - логирование завершения (всегда)
     *
     * Выполняется ПОСЛЕ метода (даже при исключении)
     */
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.debug("Method {} finished", methodName);
    }
}
