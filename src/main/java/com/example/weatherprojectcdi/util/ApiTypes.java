package com.example.weatherprojectcdi.util;

public enum ApiTypes {

    FORECAST("forecast"),
    WEATHER("weather");

    private String value;

    ApiTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
