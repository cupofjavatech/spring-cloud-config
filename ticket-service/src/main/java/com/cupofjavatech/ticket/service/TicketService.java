package com.cupofjavatech.ticket.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cupofjavatech.ticket.exception.CustomException;
import com.cupofjavatech.ticket.feign.FlightFeignClient;
import com.cupofjavatech.ticket.feign.PassengerFeignClient;
import com.cupofjavatech.ticket.model.Flight;
import com.cupofjavatech.ticket.model.Passenger;
import com.cupofjavatech.ticket.model.Ticket;
import com.cupofjavatech.ticket.model.TicketDto;
import com.cupofjavatech.ticket.repo.TicketRepo;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

	private TicketRepo ticketRepo;
	private PassengerFeignClient passengerFeignClient;
	private FlightFeignClient flightFeignClient;

	public TicketService(TicketRepo ticketRepo, PassengerFeignClient passengerFeignClient,
			FlightFeignClient flightFeignClient) {
		this.ticketRepo = ticketRepo;
		this.passengerFeignClient = passengerFeignClient;
		this.flightFeignClient = flightFeignClient;
	}

	public TicketDto getTicketDetail(Integer ticketNumber) {
		Optional<Ticket> ticketOptional = null;
		try {
			ticketOptional = ticketRepo.findById(ticketNumber);
		} catch (Exception e) {
			throw new CustomException("Failed to fech Ticket Details", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ticketOptional == null || ticketOptional.isEmpty()) {
			throw new CustomException("Ticket Not Found", null, HttpStatus.BAD_REQUEST);
		}

		Ticket ticketEntity = ticketOptional.get();
		TicketDto ticketDto = new TicketDto();
		ticketDto.setTicketNumber(ticketEntity.getId());

		try {
			ResponseEntity<List<Passenger>> passengerResponse = passengerFeignClient
					.findByTicketId(ticketOptional.get().getId());
			// passengerList = passengerResponse.getBody();
			ticketDto.setListPassenger(passengerResponse.getBody());
			ResponseEntity<Flight> flightResponse = flightFeignClient
					.getFlightByFlightNumber(ticketEntity.getFlightNumber());

			ticketDto.setFlight(flightResponse.getBody());
		} catch (Exception e) {
			throw new CustomException("Failed to get Passenger/Flight Details", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ticketDto;
	}

	@Transactional
	public TicketDto createTicket(@RequestBody TicketDto ticketDto) {

		if (ticketDto.getListPassenger().stream().filter(p -> Strings.isBlank(p.getPassportNo())).findAny()
				.isPresent()) {
			throw new CustomException("Passenger Details Not Valid", null, HttpStatus.BAD_REQUEST);
		}

		TicketDto response = new TicketDto();

		try {
			ResponseEntity<Flight> flightRes = flightFeignClient
					.getFlightByFlightNumber(ticketDto.getFlight().getFlightNo());
			response.setFlight(flightRes.getBody());
			if (response.getFlight() == null) {
				throw new CustomException("Invalid Flight Number", null, HttpStatus.BAD_REQUEST);
			}

			Ticket ticketEntity = new Ticket();
			ticketEntity.setFlightNumber(ticketDto.getFlight().getFlightNo());
			ticketEntity = ticketRepo.save(ticketEntity);

			ResponseEntity<List<Passenger>> passengerReponse = passengerFeignClient
					.addTicketToPassenger(ticketEntity.getId(), ticketDto.getListPassenger());

			if (passengerReponse.getBody() == null) {
				throw new CustomException("Invalid Passenger Details ", null, HttpStatus.BAD_REQUEST);
			}

			response.setListPassenger(passengerReponse.getBody());
			response.setTicketNumber(ticketEntity.getId());

		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException("Invalid Flight Number", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
