package com.otp.ticketservice.api.controller;

import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {

    private final CoreServiceInterface coreService;

    public ApiController(CoreServiceInterface coreService) {
        this.coreService = coreService;
    }
}
