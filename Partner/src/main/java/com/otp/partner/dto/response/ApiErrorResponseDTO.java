package com.otp.partner.dto.response;

/**
 * Data Transfer Object (DTO) representing an API error response.
 */
public record ApiErrorResponseDTO(String message, int errorCode, boolean success) {
}
