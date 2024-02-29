package com.otp.ticketservice.ticket.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@UtilityClass
public class HttpRequestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public HttpResponse<String> getRequest(String targetURL) {
        try {
            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(targetURL))
                    .header("accept", "application/json")
                    .header("x-api-key", "partner-key")
                    .header("x-api-secret", "partner-secret")
                    .GET()
                    .build();

            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new RuntimeException("Error sending HTTP request", e);
        }
    }

    public HttpResponse<String> postRequest(String targetURL, Map<String, String> requestBody) {
        try {
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);

            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(targetURL))
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "partner-key")
                    .header("x-api-secret", "partner-secret")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                    .build();

            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            throw new RuntimeException("Error sending HTTP POST request", e);// TODO Implement custom exception
        }
    }
}
