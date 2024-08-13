package com.example.weatherprojectcdi.util.servlet;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.servlet.http.*;

import java.util.*;

public class BodyProcessor implements IAuthBodyRequestProcessor {

    public BodyProcessor() {

    }

    private UserDTO getFromReqBody(HttpServletRequest request ,HttpRequestHandler httpRequestHandler) throws BadRequestException {

        Optional<Object> obj = httpRequestHandler.parseRequestBody(request, UserDTO.class);

        return obj.map(o -> (UserDTO) o).orElseGet(UserDTO::new);

    }

    private UserDTO checkIfBodyIsNotEmpty(UserDTO userDTO) throws BadRequestException{

        if(userDTO.isNotEmpty())
            return userDTO;

        throw new BadRequestException("Not correct request body!");

    }

    @Override
    public UserDTO getUserFromRequest(HttpServletRequest request , HttpRequestHandler httpRequestHandler) throws BadRequestException {
        return checkIfBodyIsNotEmpty(getFromReqBody(request, httpRequestHandler));
    }
}
