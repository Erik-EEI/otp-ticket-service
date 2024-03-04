package com.otp.ticketservice.ticket.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for building URLs with optional query parameters.
 */
@Component
public class UrlBuilder {

    @Value("${api.base.url}")
    private String baseUrl;

    /**
     * Builds a URL without any query parameters.
     * @param endpoint the endpoint to append to the base URL
     * @return the complete URL
     */
    public String buildUrl(String endpoint) {
        return baseUrl + endpoint;
    }

    /**
     * Builds a URL with query parameters.
     * @param endpoint the endpoint to append to the base URL
     * @param queryParams the map of query parameters
     * @return the complete URL with query parameters
     */
    public String buildUrl(String endpoint, Map<String, String> queryParams) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl + endpoint);

        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(encodeValue(entry.getValue()))
                        .append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }

    /**
     * Encodes the value of a query parameter.
     * @param value the value to encode
     * @return the encoded value
     */
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding URL parameter value", e);
        }
    }
}
