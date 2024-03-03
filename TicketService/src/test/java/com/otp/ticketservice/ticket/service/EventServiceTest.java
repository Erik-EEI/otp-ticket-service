package com.otp.ticketservice.ticket.service;

import static org.junit.jupiter.api.Assertions.*;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dao.EventDAO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.exceptions.ExternalSystemNotAvailableException;
import com.otp.ticketservice.ticket.exceptions.UnexpectedResponseFromPartnerException;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpResponse;
import java.util.Collections;

import static org.mockito.Mockito.*;

class EventServiceTest {
    @Mock
    private EventDAO eventDAO;
    @Mock
    private HttpResponse mockResponse;
    private EventServiceInterface eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.eventService = new EventService(eventDAO);
    }

    @Test
    public void testGetAllEvents_Success() {
        String sampleEventResponse = "{\"data\":[{\"eventId\":1,\"title\":\"Szilveszteri zártkörű rendezvény\",\"location\":\"Greenwich\",\"startTimeStamp\":\"1577836800\",\"endTimeStamp\":\"1577844000\"}],\"success\":true}";
        Mockito.when(mockResponse.body()).thenReturn(sampleEventResponse);
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        when(eventDAO.getAllEvents()).thenReturn(mockResponse);

        assertDoesNotThrow(() -> HttpResponseExceptionHandler.checkForException(mockResponse));
        EventListDTO result = eventService.getAllEvents();


        verify(eventDAO, times(1)).getAllEvents();
        assertNotEquals(result.data(), Collections.emptyList());
    }

    @Test
    public void testGetAllEvents_ExceptionThrown() {
        Mockito.when(mockResponse.body()).thenReturn("");
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        when(eventDAO.getAllEvents()).thenReturn(mockResponse);

        doThrow(ExternalSystemNotAvailableException.class).when(eventDAO).getAllEvents();

        assertThrows(Exception.class, () -> {
            HttpResponseExceptionHandler.checkForException(mockResponse);
            eventService.getAllEvents();
        });

        verify(eventDAO, times(1)).getAllEvents();
    }
    @Test
    public void testGetEvent_Success() {
        String sampleEventResponse = "{\"data\":{\"eventId\":1,\"seats\":[{\"id\":1,\"seatName\":\"S1\",\"price\":1000,\"currency\":\"HUF\",\"reserved\":true}]},\"success\":true}";
        HttpResponse mockResponse = mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn(sampleEventResponse);
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        when(eventDAO.getEvent(any(EventDataDTO.class))).thenReturn(mockResponse);

        assertDoesNotThrow(() -> HttpResponseExceptionHandler.checkForException(mockResponse));
        EventSeatsDTO result = eventService.getEvent(new EventDataDTO(1L));


        verify(eventDAO, times(1)).getEvent(any(EventDataDTO.class));
        assertNotEquals(result.seats(), Collections.emptyList());
    }

    @Test
    public void testGetEvent_UnexpectedResponse() {
        String unexpectedResponse = "{\"unexpected\":\"response\"}";
        HttpResponse mockResponse = mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn(unexpectedResponse);
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        when(eventDAO.getEvent(any(EventDataDTO.class))).thenReturn(mockResponse);

        assertThrows(UnexpectedResponseFromPartnerException.class, () -> {
            eventService.getEvent(new EventDataDTO(1L));
        });
        
        verify(eventDAO, times(1)).getEvent(any(EventDataDTO.class));
    }


}