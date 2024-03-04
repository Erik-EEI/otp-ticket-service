package com.otp.ticketservice.ticket.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.ticket.exceptions.ExternalSystemNotAvailableException;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Utility class for making HTTP requests.
 */
@UtilityClass
public class HttpRequestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Sends a GET request to the specified target URL with default headers.
     * @param targetURL the target URL for the GET request
     * @return the HTTP response received from the server
     * @throws ExternalSystemNotAvailableException if an error occurs during the request
     */
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
            throw new ExternalSystemNotAvailableException();
        }
    }

    /**
     * Sends a POST request to the specified target URL with default headers and the provided request body.
     * @param targetURL the target URL for the POST request
     * @param requestBody the request body to be sent in JSON format
     * @return the HTTP response received from the server
     * @throws ExternalSystemNotAvailableException if an error occurs during the request
     */
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
            throw new ExternalSystemNotAvailableException();
        }
    }
}
