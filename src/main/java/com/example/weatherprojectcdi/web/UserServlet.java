package com.example.weatherprojectcdi.web;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.dto.api.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.inject.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {



    @Inject
    private IWeatherService weatherService;
    @Inject
    private ISessionService sessionService;
    @Inject
    private IUserService userService;

    @Inject
    private ILocationBodyProcessor locationBodyProcessor;

    @Inject
    private HttpRequestHandler httpRequestHandler;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = sessionService.findUserByCookie(HttpRequestHandler.getSessionCookie(request));

        List<LocationDto> locationsDtoList = userService.findLocationsByUser(user);

        List<ForecastDto> forecastDtoList = weatherService.getForecastByAll(locationsDtoList);

        try {
            response.getWriter().write(JsonMapper.mapTo(forecastDtoList));
        } catch (InternalServerErrorException e) {
            response.setStatus(500);
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            LocationDto locationDto = locationBodyProcessor.getLocationFromRequest(request, httpRequestHandler);

            userService.save(locationDto, request);

        }catch (NumberFormatException | BadRequestException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (InternalServerErrorException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Long id = Long.valueOf(request.getParameter("id"));

            userService.delete(id);

        }catch (NumberFormatException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
