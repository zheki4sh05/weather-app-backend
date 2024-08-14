package com.example.weatherprojectcdi.util;

import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.exception.*;
import jakarta.inject.*;
import jakarta.servlet.http.*;
import lombok.*;

import javax.swing.text.html.*;
import java.net.*;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;

@Named("RequestHandler")
public class HttpRequestHandler {
    public HttpRequestHandler() {

    }

    public HttpResponse<String> get(String path){

        try {

            HttpRequest newRequest = createHttpRequest(path);

            return makeRequest(newRequest);

        } catch (URISyntaxException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private HttpResponse<String> makeRequest(HttpRequest newRequest) throws ExecutionException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                    .build();
            CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(newRequest, HttpResponse.BodyHandlers.ofString());
           // httpClient.close();
            return response.get();


//        CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
//                .build()
//                .sendAsync(newRequest, HttpResponse.BodyHandlers.ofString());


    }

    private HttpRequest createHttpRequest(String path) throws URISyntaxException {

        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .GET()
                .build();
    }
    public static Optional<Cookie> getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw  new RuntimeException();
        }
        return Arrays.stream(cookies)
                .filter(item -> item.getName().equals(UrlPath.COOKIE_NAME)).findFirst();
    }

    public <T> Optional<Object> parseRequestBody(HttpServletRequest request, Class<T> clazz){

            return JsonMapper.mapFrom(request, clazz);


        }



}
