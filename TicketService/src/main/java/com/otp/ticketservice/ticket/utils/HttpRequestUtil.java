package com.otp.ticketservice.ticket.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@UtilityClass
public class HttpRequestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public <T> T getRequest(String targetURL, Class<T> responseType) {
        try {
            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(targetURL))
                    .header("accept", "application/json")
                    .header("x-api-key", "partner-key")
                    .header("x-api-secret", "partner-secret")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), responseType);
        } catch (Exception e) {
            throw new RuntimeException("Error sending HTTP request", e);
        }
    }
}
