package com.cupofjavatech.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TicketMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketMsApplication.class, args);
	}

}
