package com.github.imanx.qlroidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by Farshid Roohi.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

/*

use when different key request field
example :
    request key :  id_person
    variable name class ; idPerson

 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD , ElementType.TYPE})
public @interface SerializedField {
    String value() default "";
}