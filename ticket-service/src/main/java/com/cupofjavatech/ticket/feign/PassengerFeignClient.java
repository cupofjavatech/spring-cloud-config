package com.cupofjavatech.ticket.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cupofjavatech.ticket.model.Passenger;

@FeignClient(value="passengerFeignClient", url="http://localhost:8200/")
//@FeignClient("passenger-service")
public interface PassengerFeignClient {

	@GetMapping("passenger/ticket/{id}")
	public ResponseEntity<List<Passenger>> findByTicketId(@PathVariable Integer id);

	@PostMapping("passenger/add-ticket-passenger/{ticketId}")
	public ResponseEntity<List<Passenger>> addTicketToPassenger(@PathVariable("ticketId") Integer ticketId,
			@RequestBody List<Passenger> passengerList);
}