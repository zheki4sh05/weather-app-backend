package com.example.weatherprojectcdi.service;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.dto.api.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;

import java.io.*;
import java.net.http.*;
import java.util.*;

@Named("weatherService")
@ApplicationScoped
public class WeatherServiceImpl implements IWeatherService, Serializable {

    private final String SERVICE_API_KEY = "d1a151c70a3c897f2630de0b0b101e9f";

    private Optional<ForecastDto> makeRequest(String path) throws BadRequestException {

        HttpRequestHandler httpRequestHandler = HttpRequestHandler.builder().path(path).build();

        HttpResponse<String> response =  httpRequestHandler.get();

        int resultCode = response.statusCode();

        switch (resultCode){
            case 200:{
                return Optional.ofNullable(doMapping(response.body()));
            }
            case 404:{
                throw new BadRequestException("City not found!");
            }
        }
        return Optional.empty();

    }
    @Override
    public Optional<ForecastDto> getForecastByCityName(String name) throws BadRequestException {

        String path = buildApiUrl(name);

        return makeRequest(path);

    }

    private String buildApiUrl(String countryName){
        StringBuilder stringBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?q=");
        stringBuilder.append(countryName);
        stringBuilder.append("&appid=");
        stringBuilder.append(SERVICE_API_KEY);
        stringBuilder.append("&units=metric");
        return stringBuilder.toString();

    }
    private String buildApiUrl(String lon, String lat){
        StringBuilder stringBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?lat=");
        stringBuilder.append(lat);
        stringBuilder.append("&lon=");
        stringBuilder.append(lon);
        stringBuilder.append("&appid=");
        stringBuilder.append(SERVICE_API_KEY);
        stringBuilder.append("&units=metric");
        return stringBuilder.toString();

    }

    private ForecastDto doMapping(String data){
        ObjectMapper objectMapper = new ObjectMapper();

        ForecastDto forecastDto = null;
        try {
            forecastDto = objectMapper.readValue(data, ForecastDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return forecastDto;
    }


    @Override
    public List<ForecastDto> getForecastByAll(List<LocationDto> locationsDtoList) {

        List<ForecastDto> forecastDtoList = new ArrayList<>();

        locationsDtoList.forEach(item->{
            forecastDtoList.add(getForecast(item));
        });

        return forecastDtoList;

    }

    private ForecastDto getForecast(LocationDto locationDto)  {

        String path = buildApiUrl(String.valueOf(locationDto.getLon()), String.valueOf(locationDto.getLat()));

        ForecastDto forecastDto = null;
        try {
            forecastDto = makeRequest(path).orElse(new ForecastDto());
            forecastDto.setLocationId(locationDto.getId());
        } catch (BadRequestException e) {
            return new ForecastDto();
        }

        return forecastDto;

    }

}
