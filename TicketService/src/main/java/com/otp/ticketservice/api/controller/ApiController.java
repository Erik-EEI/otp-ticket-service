package com.otp.ticketservice.api.controller;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApiController {

    private final CoreServiceInterface coreService;

    public ApiController(CoreServiceInterface coreService) {
        this.coreService = coreService;
    }

    @GetMapping("getEvents")
    public ResponseEntity<PartnerResponseDTO> getEvents() {

        return new ResponseEntity<>(new PartnerResponseDTO(), HttpStatus.OK);
    }

    @GetMapping("getEvent")
    public ResponseEntity<PartnerResponseDTO> getEventById(@RequestParam(required = true) Long eventId) {
        EventDataDTO eventData = new EventDataDTO(eventId);
        
        return new ResponseEntity<>(new PartnerResponseDTO(), HttpStatus.OK);
    }

    @PostMapping("pay")
    public ResponseEntity<PartnerResponseDTO> getEventById(
            @RequestParam(required = true) Long eventId,
            @RequestParam(required = true) Long seatId,
            @RequestParam(required = true) Long cardId,
            @RequestHeader(required = true) String userToken
    ) {
        coreService.validateUserToken(userToken);

        PaymentDataDTO paymentData = new PaymentDataDTO(eventId,seatId,cardId);


        return new ResponseEntity<>(new PartnerResponseDTO(), HttpStatus.OK);
    }
}
