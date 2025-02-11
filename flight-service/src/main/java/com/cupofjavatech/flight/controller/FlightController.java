package com.cupofjavatech.flight.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cupofjavatech.flight.model.Flight;
import com.cupofjavatech.flight.service.FlightService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping("/flight")
public class FlightController {

	private FlightService flightService;
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;

	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping("active-profile")
	public String getActiveProfile() {
		return "profile: " + profile + "\n" +
			   "dbUrl : " + dbUrl + "\n" +
				"dbUsername : " + dbUsername;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> list = flightService.getAllFlights();
		return list != null ? ResponseEntity.ok(list) : ResponseEntity.ok(new ArrayList<>());
	}

	@GetMapping("/flightnumber/{flightNumber}")
	public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber) {
		Preconditions.checkArgument(Strings.isNotBlank(flightNumber), "Invalid Value");
		return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
	}

	@GetMapping("/from/{from}/to/{to}/date/{date}")
	public ResponseEntity<List<Flight>> getFlightFromTo(@PathVariable("from") String from,
			@PathVariable("to") String to, @PathVariable("date") String date) {
		Preconditions.checkArgument(Strings.isNotBlank(from), "Invalid From Value");
		Preconditions.checkArgument(Strings.isNotBlank(to), "Invalid To value");

		return ResponseEntity.ok(flightService.getFlightFromTo(from, to, date));
	}

	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
		Preconditions.checkArgument(flight != null, "Invalid Flight Value");
		Preconditions.checkArgument(flight.getFlightNo() != null, "Invalid Flight Number");
		Preconditions.checkArgument(flight.getFromStation() != null, "Invalid Station Name");
		Preconditions.checkArgument(flight.getToStation() != null, "Invalid Station Name");
		return ResponseEntity.ok(flightService.addFlight(flight));
	}

	@PostMapping("/update/{flightNumber}")
	public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight,
			@PathVariable("flightNumber") String flightNumber) {
		Preconditions.checkArgument(flight != null, "Invalid Flight Value");
		Preconditions.checkArgument(flightNumber != null, "Invalid Flight Id");
		return ResponseEntity.ok(flightService.updateFlight(flight, flightNumber));
	}
}
