package com.otp.ticketservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ticket Service API", version = "1.0", description = "Ticket service consisting of API Module, Ticket module, Core module"))
public class TicketServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

}
