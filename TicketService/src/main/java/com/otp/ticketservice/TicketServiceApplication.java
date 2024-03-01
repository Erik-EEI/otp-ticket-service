package com.otp.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TicketServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

}
