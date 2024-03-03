package com.otp.partner.controller.event;

import com.otp.partner.dto.DetailedEventDTO;
import com.otp.partner.dto.response.ApiErrorResponseDTO;
import com.otp.partner.dto.response.ApiResponseDTO;
import com.otp.partner.dto.EventDTO;
import com.otp.partner.dto.EventSeatsDTO;
import com.otp.partner.entity.Event;
import com.otp.partner.mapper.EventMapper;
import com.otp.partner.service.event.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Event Controller", description = "Controller for handling event related operations")
public class EventController {

    private final EventService eventService;
    private final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all events",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid api secret or key",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))})
    })
    @GetMapping("getEvents")
    public ResponseEntity<ApiResponseDTO> getEvents() {
        LOGGER.info("GET REQUEST at /getEvents endpoint");
        List<Event> events = eventService.getAllEvents();

        List<EventDTO> eventDTOS = EventMapper.mapToEventDTOList( events );

        return new ResponseEntity<>(new ApiResponseDTO(eventDTOS,true), HttpStatus.OK);
    }

    @Operation(summary = "Get a specific event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned a specific event",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid api secret or key",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Event or seat not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))})
    })
    @GetMapping("getEvent")
    public ResponseEntity<ApiResponseDTO> getEventById(
            @Parameter(in = ParameterIn.DEFAULT, required = true, description = "Id of the event to get from module", example = "1")
            @RequestParam(required = true) Long eventId,
            @Parameter(in = ParameterIn.DEFAULT, required = true, description = "If its value is true, the response will contain every detail of the event, not just the id and seats")
            @RequestParam(defaultValue = "false") boolean detailed
    ) {
        LOGGER.info("GET REQUEST at /getEvent endpoint");
        Event event = eventService.getEventById(eventId);

        if(detailed){
            DetailedEventDTO detailedEvent = EventMapper.mapToDetailedEventDTO( event );

            return new ResponseEntity<>(new ApiResponseDTO(detailedEvent,true), HttpStatus.OK);
        }

        EventSeatsDTO responseDTO = EventMapper.mapToEventSeatsDTO( event );

        return new ResponseEntity<>(new ApiResponseDTO(responseDTO,true), HttpStatus.OK);
    }
}
