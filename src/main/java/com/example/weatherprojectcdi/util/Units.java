package com.example.weatherprojectcdi.util;

public enum Units {

    METRIC("metric");

    private String value;

    Units(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
