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

    private final String actionSearch = "search";
    private final String actionMore = "more";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        String cityName = request.getParameter("city");

        switch(action){
            case actionSearch:{

                makeSearchAction(response,cityName);

                break;
            }
            case actionMore:{

                 makeMoreSearchAction(response,cityName);

                break;
            }
            default:{
                response.getWriter().write("Uncorrect request missing action param");
                response.setStatus(400);
            }

        }




    }

    private void makeMoreSearchAction(HttpServletResponse response, String name) throws IOException {

        try {
            LongForecastDto forecastListDto  = weatherService.getLongForecastByCityLonLat(name).orElseThrow(InternalServerErrorException::new);
            response.getWriter().write(JsonMapper.mapTo(forecastListDto));
        } catch (InternalServerErrorException | IOException e) {
            response.setStatus(500);
        }

    }

    private void makeSearchAction( HttpServletResponse response, String cityName) throws IOException {

        try {
            ForecastDto forecastDto  = weatherService.getForecastByCityName(cityName).orElseThrow(InternalServerErrorException::new);
            response.getWriter().write(JsonMapper.mapTo(forecastDto));
        } catch (BadRequestException e) {

            response.getWriter().write(e.getMessage());
            response.setStatus(400);
        } catch (InternalServerErrorException | IOException e) {
            response.setStatus(500);
        }


    }


}
