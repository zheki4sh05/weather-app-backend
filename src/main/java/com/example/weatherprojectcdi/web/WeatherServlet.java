package com.example.weatherprojectcdi.web;

import com.example.weatherprojectcdi.dto.api.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.inject.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;


@WebServlet(UrlPath.WEATHER)
public class WeatherServlet extends HttpServlet{

    @Inject
   private IWeatherService weatherService;



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String cityName = request.getParameter("city");

        try {
            ForecastDto forecastDto  = weatherService.getForecastByCityName(cityName).orElseThrow(InternalServerErrorException::new);
            response.getWriter().write(JsonMapper.mapTo(forecastDto));
        } catch (BadRequestException e) {

            response.getWriter().write(e.getMessage());
           response.setStatus(400);
        } catch (InternalServerErrorException e) {
            response.setStatus(500);
        }


    }




}
