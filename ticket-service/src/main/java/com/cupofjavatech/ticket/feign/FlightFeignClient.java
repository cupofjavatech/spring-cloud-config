package com.cupofjavatech.ticket.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cupofjavatech.ticket.model.Flight;

@FeignClient(value="flightFeignClient", url="http://localhost:8100/")
//@FeignClient("flight-service")
public interface FlightFeignClient {

	@GetMapping("flight/flightnumber/{flightNumber}")
	public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber);

}