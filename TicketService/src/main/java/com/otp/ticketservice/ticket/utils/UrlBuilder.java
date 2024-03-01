package com.otp.ticketservice.ticket.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class UrlBuilder {

    @Value("${api.base.url}")
    private String baseUrl;

    public String buildUrl(String endpoint) {
        return baseUrl + endpoint;
    }

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

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding URL parameter value", e);
        }
    }
}
