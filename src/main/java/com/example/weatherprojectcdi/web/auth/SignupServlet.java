package com.example.weatherprojectcdi.web.auth;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;
import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.inject.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

import static com.example.weatherprojectcdi.util.UrlPath.*;

@WebServlet(name = "signupServlet", value = UrlPath.REGISTRATION)
public class SignupServlet extends HttpServlet{

    @Inject
    private IAuthorizationService authorizationService;

    @Inject
    private ISessionService sessionService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {




    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            try {

                UserDTO userDTO = getFromReqBody(request);

              authorizationService.create(userDTO);

              User user = authorizationService.find(userDTO).orElseThrow();

                UUID uuid = sessionService.createByUser(user);

                Cookie cookie = new Cookie(COOKIE_NAME, uuid.toString());

                response.addCookie(cookie);
                response.getWriter().write("");

            } catch (NotUniqueEmail e) {
               response.sendError(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write(e.getMessage());
            } catch (InternalServerErrorException e) {
                response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
                response.getWriter().write(e.getMessage());
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }


    }

    private UserDTO getFromReqBody(HttpServletRequest request) throws BadRequestException {

        Optional<Object> userDTO = JsonMapper.mapFrom(request, UserDTO.class);

        if(userDTO.isEmpty() || !((UserDTO)userDTO.get()).isNotEmpty())
            throw new BadRequestException();

       return (UserDTO) userDTO.get() ;

    }


}
