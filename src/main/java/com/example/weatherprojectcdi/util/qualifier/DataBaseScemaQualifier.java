package com.example.weatherprojectcdi.util.qualifier;

import jakarta.inject.*;

import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface DataBaseScemaQualifier {}
