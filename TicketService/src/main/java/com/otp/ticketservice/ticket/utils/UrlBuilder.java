package com.otp.ticketservice.ticket.utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@UtilityClass
public class UrlBuilder {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String baseUrl = dotenv.get("API_BASE_URL");

    public static String buildUrl(String endpoint) {
        return baseUrl + endpoint;
    }

    public static String buildUrl(String endpoint, Map<String, String> queryParams) {
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

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding URL parameter value", e);
        }
    }
}
