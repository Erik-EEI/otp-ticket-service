package com.otp.partner.controller.event;

import com.otp.partner.dto.DetailedEventDTO;
import com.otp.partner.dto.response.ApiResponse;
import com.otp.partner.dto.EventDTO;
import com.otp.partner.dto.EventSeatsDTO;
import com.otp.partner.dto.SeatDTO;
import com.otp.partner.entity.Event;
import com.otp.partner.entity.Seat;
import com.otp.partner.mapper.EventMapper;
import com.otp.partner.service.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class EventController {

    private final EventService eventService;
    private final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("getEvents")
    public ResponseEntity<ApiResponse> getEvents() {
        LOGGER.info("GET REQUEST at /getEvents endpoint");
        List<Event> events = eventService.getAllEvents();

        List<EventDTO> eventDTOS = EventMapper.mapToEventDTOList( events );

        return new ResponseEntity<>(new ApiResponse(eventDTOS,true), HttpStatus.OK);
    }

    @GetMapping("getEvent")
    public ResponseEntity<ApiResponse> getEventById(
            @RequestParam(required = true) Long eventId,
            @RequestParam(defaultValue = "false") boolean detailed
    ) {
        LOGGER.info("GET REQUEST at /getEvent endpoint");
        Event event = eventService.getEventById(eventId);

        if(detailed){
            DetailedEventDTO detailedEvent = EventMapper.mapToDetailedEventDTO( event );

            return new ResponseEntity<>(new ApiResponse(detailedEvent,true), HttpStatus.OK);
        }

        EventSeatsDTO responseDTO = EventMapper.mapToEventSeatsDTO( event );

        return new ResponseEntity<>(new ApiResponse(responseDTO,true), HttpStatus.OK);
    }
}
