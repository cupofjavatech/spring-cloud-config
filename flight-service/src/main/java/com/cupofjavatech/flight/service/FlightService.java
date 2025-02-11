package com.cupofjavatech.flight.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cupofjavatech.flight.exception.CustomException;
import com.cupofjavatech.flight.model.Flight;
import com.cupofjavatech.flight.repo.FlightRepo;
import com.cupofjavatech.flight.util.DateUtil;

import jakarta.transaction.Transactional;

@Service
public class FlightService {

	Logger LOG = LoggerFactory.getLogger(FlightService.class);

	private FlightRepo flightRepo;

	public FlightService(FlightRepo flightRepo) {
		this.flightRepo = flightRepo;
	}

	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}

	public Flight getFlightByFlightNumber(String flightNumber) {
		Flight flight = flightRepo.findByFlightNo(flightNumber);
		if (flight == null) {
			throw new CustomException("No Record Found", null, HttpStatus.NOT_FOUND);
		}

		return flight;
	}

	@Transactional
	public Flight addFlight(Flight flight) {

		if (flight == null || Strings.isBlank(flight.getFlightNo()) || Strings.isBlank(flight.getFromStation())
				|| Strings.isBlank(flight.getToStation())) {
			throw new CustomException("Bad Request", null, HttpStatus.BAD_REQUEST);
		}
		return flightRepo.save(flight);

	}

	public List<Flight> getFlightFromTo(String from, String to, String date) {

		try {
			List<Flight> list = flightRepo.findFromTo(from, to);
			if (list != null && !list.isEmpty() && Strings.isNotBlank(date)) {
				Date dt = DateUtil.getDateDDMMYY(date);

				return list.stream().filter(f -> {
					try {
						return DateUtil.removeTime(f.getDeparture()).compareTo(dt) == 0;
					} catch (ParseException e) {
						LOG.error(e.getMessage(), e);
						return false;
					}
				}).collect(Collectors.toList());
			}
			return list != null ? list : new ArrayList<>();
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			return new ArrayList<>();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ArrayList<>();
		}

	}

	@Transactional
	public Flight updateFlight(Flight flight, String flightNumber) {
		Flight flightEntity = getFlightByFlightNumber(flightNumber);

		if (flight.getCapacity() > 0) {
			flightEntity.setCapacity(flight.getCapacity());
		}

		if (flight.getDeparture() != null) {
			flightEntity.setDeparture(flight.getDeparture());
		}

		if (flight.getArrival() != null) {
			flightEntity.setArrival(flight.getArrival());
		}

		if (flight.getPrice() != null) {
			flightEntity.setPrice(flight.getPrice());
		}
		try {
			return flightRepo.save(flightEntity);
		} catch (Exception e) {
			throw new CustomException(e.getLocalizedMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
