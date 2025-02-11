package com.cupofjavatech.ticket.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupofjavatech.ticket.model.TicketDto;
import com.cupofjavatech.ticket.service.TicketService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	private TicketService ticketService;

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("active-profile")
	public String getActiveProfile() {
		return "profile: " + profile + "\n" +
			   "dbUrl : " + dbUrl + "\n" +
				"dbUsername : " + dbUsername;
	}
	
	@GetMapping("/ticket-details/{ticketNumber}")
	public ResponseEntity<TicketDto> getTicketDetail(@PathVariable Integer ticketNumber) {
		Preconditions.checkArgument(ticketNumber != null && ticketNumber != 0, "Invalid Value");
		return ResponseEntity.ok(ticketService.getTicketDetail(ticketNumber));
	}

	@PostMapping("/create")
	public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
		Preconditions.checkArgument(ticketDto != null, "Invalid Ticket Details");
		Preconditions.checkArgument(ticketDto.getFlight() != null, "Invalid Flight Number");
		Preconditions.checkArgument(Strings.isNotEmpty(ticketDto.getFlight().getFlightNo()), "Invalid Flight Number");
		Preconditions.checkArgument(ticketDto.getListPassenger() != null && !ticketDto.getListPassenger().isEmpty(),
				"Invalid Passenger Details");
		return ResponseEntity.ok(ticketService.createTicket(ticketDto));
	}
}
