package com.forsrc.gwt.client.application.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gwt.material.design.client.constants.HideOn;

@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME) 
public @interface TableColumn {

    String columnName();
    boolean numeric() default false;
    HideOn hideOn() default HideOn.NONE;

}
