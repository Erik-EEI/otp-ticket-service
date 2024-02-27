package com.otp.partner.controller.event;

import com.otp.partner.dto.EventDTO;
import com.otp.partner.dto.EventSeatsDTO;
import com.otp.partner.dto.SeatDTO;
import com.otp.partner.entity.Event;
import com.otp.partner.entity.Seat;
import com.otp.partner.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("getEvents")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventService.getAllEvents();

        List<EventDTO> eventDTOS = events
                .stream()
                .map((event) -> new EventDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getLocation(),
                        event.getStartTimestamp(),
                        event.getEndTimestamp()))
                .toList();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("getEvent/{eventId}")
    public ResponseEntity<Event> getEventById(@RequestParam(required = true) Long eventId) {
        Event event = eventService.getEventById(eventId);
        List<Seat> seats = event.getSeats();

        EventSeatsDTO responseDTO = new EventSeatsDTO(
                event.getId(),
                seats.stream()
                        .map(seat -> new SeatDTO(
                                seat.getId(),
                                seat.getSeatName(),
                                seat.getPrice(),
                                seat.getCurrency(),
                                seat.isReserved()
                        ))
                        .toList()
                );

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
