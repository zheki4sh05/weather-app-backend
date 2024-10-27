package com.example.weatherprojectcdi.util;

import lombok.*;

@Builder
public class OpenWeatherUrlHandler {

    private String countryName;

    private String lon;

    private String lat;

    private String unit;

    private String type;

    private final String SERVICE_API_KEY = "d1a151c70a3c897f2630de0b0b101e9f";

//    private String buildApiUrl(String countryName){
//        StringBuilder stringBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?q=");
//        stringBuilder.append(countryName);
//        stringBuilder.append("&appid=");
//        stringBuilder.append(SERVICE_API_KEY);
//        stringBuilder.append("&units=metric");
//        return stringBuilder.toString();
//
//    }
//    private String buildApiUrl(String lon, String lat){
//        StringBuilder stringBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?lat=");
//        stringBuilder.append(lat);
//        stringBuilder.append("&lon=");
//        stringBuilder.append(lon);
//        stringBuilder.append("&appid=");
//        stringBuilder.append(SERVICE_API_KEY);
//        stringBuilder.append("&units=metric");
//        return stringBuilder.toString();
//
//    }

    public String buildApiUrl(){

        StringBuilder stringBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/"+type+"?");

        if(countryName!=null){
            stringBuilder.append("q=");
            stringBuilder.append(countryName);

        }else{
            stringBuilder.append("&lat=");
            stringBuilder.append(lat);
            stringBuilder.append("&lon=");
            stringBuilder.append(lon);
        }

        stringBuilder.append("&appid=");
        stringBuilder.append(SERVICE_API_KEY);
        stringBuilder.append("&units=");
        stringBuilder.append(unit);

        return stringBuilder.toString();


    }



}
