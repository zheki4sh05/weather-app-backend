package com.example.weatherprojectcdi.filter;

import jakarta.enterprise.context.*;
import jakarta.interceptor.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.ws.rs.container.*;

import java.io.*;

@WebFilter(filterName = "CORS", urlPatterns = "/*")
public class CORSFilter implements Filter {



    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("cors");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content

        HttpServletResponse resp = (HttpServletResponse) servletResponse;



        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST,DELETE");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");




        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }

        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }

}