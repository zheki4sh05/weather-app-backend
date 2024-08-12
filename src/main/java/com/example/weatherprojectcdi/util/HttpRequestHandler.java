package com.example.weatherprojectcdi.util;

import jakarta.servlet.http.*;
import lombok.*;

import java.net.*;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;

@Builder
public class HttpRequestHandler {

    private final String path;

    public HttpResponse<String> get(){

        try {

            HttpRequest newRequest = createHttpRequest();

            return makeRequest(newRequest);

        } catch (URISyntaxException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private HttpResponse<String> makeRequest(HttpRequest newRequest) throws ExecutionException, InterruptedException {

        CompletableFuture<HttpResponse<String>> response = HttpClient.newBuilder()
                .build()
                .sendAsync(newRequest, HttpResponse.BodyHandlers.ofString());

        return response.get();

    }

    private HttpRequest createHttpRequest() throws URISyntaxException {

        return HttpRequest.newBuilder()
                .uri(new URI(path))
                .GET()
                .build();
    }
    public static Optional<Cookie> getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(item -> item.getName().equals(UrlPath.COOKIE_NAME)).findFirst();
    }


}
