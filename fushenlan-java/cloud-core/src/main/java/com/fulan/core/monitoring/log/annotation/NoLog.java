package com.fulan.core.monitoring.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 用在类、接口或者方法上，用于排除切面报文记录
         *
         * @author c_xiaofang-001
        * 2017-10-31
        *
        */
@Target(value = { ElementType.TYPE,ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLog {

}
