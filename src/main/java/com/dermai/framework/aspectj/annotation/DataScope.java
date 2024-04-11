package com.dermai.framework.aspectj.annotation;

import java.lang.annotation.*;

/**
 * @author Shaobo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    String value() default "";
}
