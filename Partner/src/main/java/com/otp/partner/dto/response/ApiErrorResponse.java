package com.otp.partner.dto.response;

public record ApiErrorResponse(String message,int errorCode, boolean success) {
}
