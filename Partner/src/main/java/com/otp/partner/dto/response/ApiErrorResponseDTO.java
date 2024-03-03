package com.otp.partner.dto.response;

public record ApiErrorResponseDTO(String message, int errorCode, boolean success) {
}
