package com.example.weatherprojectcdi.interfaces;


import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.dto.api.*;
import com.example.weatherprojectcdi.exception.*;

import java.util.*;

public interface IWeatherService {

     Optional<ForecastDto> getForecastByCityName(String name) throws BadRequestException;

     List<ForecastDto> getForecastByAll(List<LocationDto> locationsDtoList);


    Optional<LongForecastDto> getLongForecastByCityLonLat(String name);
}
