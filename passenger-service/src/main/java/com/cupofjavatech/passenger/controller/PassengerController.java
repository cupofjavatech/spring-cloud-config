package com.cupofjavatech.passenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupofjavatech.passenger.model.Passenger;
import com.cupofjavatech.passenger.service.PassengerService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	private PassengerService passengerService;

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	PassengerController(PassengerService passengerService) {
		this.passengerService = passengerService;
	}

	@GetMapping("active-profile")
	public String getActiveProfile() {
		return "profile: " + profile + "\n" +
			   "dbUrl : " + dbUrl + "\n" +
				"dbUsername : " + dbUsername;
	}

	@PostMapping("/add")
	public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
		Preconditions.checkArgument(passenger != null, "Invalid Passenger Details");
		Preconditions.checkArgument(
				passenger.getName() != null && passenger.getNationality() != null && passenger.getPassportNo() != null,
				"Invalid Passenger Details");
		return ResponseEntity.ok(passengerService.savePassenger(passenger));
	}

	@PostMapping("/update")
	public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger) {
		Preconditions.checkArgument(passenger != null, "Invalid Passenger Details");
		Preconditions.checkArgument(passenger.getPassportNo() != null, "Invalid Passenger Details");
		return ResponseEntity.ok(passengerService.updatePassenger(passenger));
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Passenger> getPassenger(@PathVariable("id") Integer id) {
		Preconditions.checkArgument(id != null && !id.equals(0), "Invalid Passenger Details");
		return ResponseEntity.ok(passengerService.findById(id));
	}

	@GetMapping("/ticket/{id}")
	public ResponseEntity<List<Passenger>> findByTicketId(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(passengerService.findByTicketId(id));
	}

	@PostMapping("/add-ticket-passenger/{ticketId}")
	public ResponseEntity<List<Passenger>> addTicketToPassenger(@PathVariable("ticketId") Integer ticketId,
			@RequestBody List<Passenger> passengerList) {
		Preconditions.checkArgument(ticketId != null, "Ticket Id Should not be null");
		Preconditions.checkArgument(passengerList != null && !passengerList.isEmpty(), "Invalid Passegner Details");
		return ResponseEntity.ok(passengerService.addTicketToPassenger(passengerList, ticketId));
	}

}
