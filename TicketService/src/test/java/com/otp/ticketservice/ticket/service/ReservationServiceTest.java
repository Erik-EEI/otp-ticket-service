package com.otp.ticketservice.ticket.service;

import static org.junit.jupiter.api.Assertions.*;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.dao.ReservationDAO;
import com.otp.ticketservice.ticket.exceptions.UnexpectedResponseFromPartnerException;
import com.otp.ticketservice.ticket.service.ReservationService;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {
    @Mock
    private ReservationDAO reservationDAO;
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationService = new ReservationService(reservationDAO);
    }

    @Test
    public void testMakeReservation_Success() {
        String sampleReservationResponse = "{\"reservationId\":1,\"success\":true}";
        HttpResponse mockResponse = mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn(sampleReservationResponse);
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        when(reservationDAO.makeReservation(any(PaymentDataDTO.class))).thenReturn(mockResponse);

        HttpResponse<String> result = reservationService.makeReservation(new PaymentDataDTO(1L, 2L,"C001","sample_token"));

        verify(reservationDAO, times(1)).makeReservation(any(PaymentDataDTO.class));
        assertEquals(mockResponse, result);
    }

    @Test
    public void testMakeReservation_UnexpectedResponse() {
        HttpResponse mockResponse = mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn("Unexpected response");
        Mockito.when(mockResponse.statusCode()).thenReturn(500);
        when(reservationDAO.makeReservation(any(PaymentDataDTO.class))).thenReturn(mockResponse);

        assertThrows(UnexpectedResponseFromPartnerException.class, () -> {
            reservationService.makeReservation(new PaymentDataDTO(1L, 2L,"C001","sample_token"));
        });

        verify(reservationDAO, times(1)).makeReservation(any(PaymentDataDTO.class));
    }
}