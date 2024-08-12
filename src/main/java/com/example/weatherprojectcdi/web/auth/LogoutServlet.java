package com.example.weatherprojectcdi.web.auth;

import com.example.weatherprojectcdi.interfaces.*;
import com.example.weatherprojectcdi.util.*;
import jakarta.inject.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet(name = "logoutServlet", value = UrlPath.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Inject
    private ISessionService sessionService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<Cookie> authCookie = HttpRequestHandler.getSessionCookie(request);
        authCookie.ifPresent(cookie -> sessionService.delete(cookie.getValue()));

    }

}
