package com.otp.ticketservice.ticket.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UtilityClass
public class HttpRequestUtil {
    public String getRequest(String targetURL){ //TODO Handle with custom exceptions
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(targetURL))
                    .header("accept", "application/json") //TODO Handle api secret
                    .header("x-api-key", "partner-key")
                    .header("x-api-secret", "partner-secret")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
