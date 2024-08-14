package com.example.weatherprojectcdi.interfaces;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.servlet.http.*;

public interface ILocationBodyProcessor {
    LocationDto getLocationFromRequest(HttpServletRequest request, HttpRequestHandler httpRequestHandler) throws BadRequestException;

}
