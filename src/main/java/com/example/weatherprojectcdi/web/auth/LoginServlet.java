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

@WebServlet(name = "loginServlet", value = UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

    @Inject
    private ISessionService sessionService;
    @Inject
    private  IAuthorizationService authorizationService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        UserDTO userDTO = UserDTO.builder().email(req.getParameter("email")).password(req.getParameter("password")).build();

        try {
            User user = authorizationService.find(userDTO).orElseThrow(UserNotFoundException::new);

            UUID uuid = sessionService.createByUser(user);

            Cookie cookie = new Cookie(COOKIE_NAME, uuid.toString());

            resp.addCookie(cookie);
            resp.setStatus(200);

        } catch (UserNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (InternalServerErrorException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
