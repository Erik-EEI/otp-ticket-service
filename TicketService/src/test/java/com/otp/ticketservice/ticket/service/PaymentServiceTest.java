package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.service.PaymentService;
import com.otp.ticketservice.ticket.service.ReservationService;
import com.otp.ticketservice.ticket.utils.SeatHandler;
import com.otp.ticketservice.ticket.utils.TimestampHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private CoreServiceInterface coreService;

    @Mock
    private EventServiceInterface eventService;

    @Mock
    private ReservationService reservationService;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.paymentService = new PaymentService(coreService,eventService,reservationService);
    }

    @Test
    void testPayForReservation_Success() {
        String sampleReservationResponse = "{\"reservationId\":1,\"success\":true}";
        PaymentDataDTO paymentData = new PaymentDataDTO(1L, 2L, "CH001", "Token");
        EventDataDTO eventDataDTO = new EventDataDTO(1L);
        DetailedEventDTO detailedEvent = createSampleDetailedEvent();
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(eventService.getDetailedEvent(eventDataDTO)).thenReturn(detailedEvent);
        when(reservationService.makeReservation(paymentData)).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(sampleReservationResponse);

        PaymentResponseDTO result = paymentService.payForReservation(paymentData);

        verify(eventService, times(1)).getDetailedEvent(eventDataDTO);
        verify(reservationService, times(1)).makeReservation(paymentData);
    }


    @Test
    void testPayForReservation_Failure() {
        PaymentDataDTO paymentData = new PaymentDataDTO(1L, 3L, "CH001", "Token");
        EventDataDTO eventDataDTO = new EventDataDTO(1L);
        DetailedEventDTO detailedEvent = createSampleDetailedEvent();
        when(eventService.getDetailedEvent(eventDataDTO)).thenReturn(detailedEvent);

        assertThrows(CanNotReserveOccupiedSeatException.class,() -> paymentService.payForReservation(paymentData));

        verify(eventService, times(1)).getDetailedEvent(eventDataDTO);
    }

    private DetailedEventDTO createSampleDetailedEvent() {
        List<SeatDTO> seats = List.of(new SeatDTO(2, "S2", 1000, "HUF", false),new SeatDTO(3, "S2", 1000, "HUF", true));
        return new DetailedEventDTO(1L, "Sample Event", "Sample Location", "1577836800", "1577844000", seats);
    }
}
