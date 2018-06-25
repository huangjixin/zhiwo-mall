package com.fulan.application.util.util;

import java.lang.annotation.*;

/**
 * @Description: 主键注解
 * @author: guiyang
 * @date: 2017/11/18 11:32
 */

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface IdAnnon {
    /**
     * 是否是id注解
     * @return
     */
    boolean isOnlyId() default true;

}