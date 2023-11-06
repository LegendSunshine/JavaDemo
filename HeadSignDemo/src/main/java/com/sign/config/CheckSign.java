package com.sign.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author legend
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME )
public @interface CheckSign {
}
