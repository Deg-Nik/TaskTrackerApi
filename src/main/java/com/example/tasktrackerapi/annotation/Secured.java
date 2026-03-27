package com.example.tasktrackerapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Nikolai Degtiarev
 * created : 24.03.26
 *
 *
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    /**
     * Требуемая роль
     */
    String role() default "USER";
}
