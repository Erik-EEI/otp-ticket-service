package com.otp.partner.service.event;

import com.otp.partner.entity.Event;
import com.otp.partner.exception.EventNotFoundException;
import com.otp.partner.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.eventService = new EventService(eventRepository);
    }

    @Test
    void testGetAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertEquals(events.size(), result.size());
        assertEquals(events, result);
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetEventById() {
        Long eventId = 1L;
        Event event = new Event();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(eventId);

        assertEquals(event, result);
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    void testGetEventByIdNotFound() {
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.getEventById(eventId));

        verify(eventRepository, times(1)).findById(eventId);
    }
}