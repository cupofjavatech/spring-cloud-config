package com.cupofjavatech.passenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cupofjavatech.passenger.exception.CustomException;
import com.cupofjavatech.passenger.model.Passenger;
import com.cupofjavatech.passenger.model.PassengerTicket;
import com.cupofjavatech.passenger.repo.PassengerRepo;
import com.cupofjavatech.passenger.repo.PassengerTicketRepo;

import jakarta.transaction.Transactional;

@Service
public class PassengerService {

	private PassengerRepo passengerRepo;
	private PassengerTicketRepo passengerTicketRepo;

	public PassengerService(PassengerRepo passengerRepo, PassengerTicketRepo passengerTicketRepo) {
		this.passengerRepo = passengerRepo;
		this.passengerTicketRepo = passengerTicketRepo;
	}

	@Transactional
	public Passenger savePassenger(Passenger passenger) {
		return passengerRepo.save(passenger);
	}

	@Transactional
	public Passenger updatePassenger(Passenger passenger) {

		if (passenger == null || Strings.isBlank(passenger.getPassportNo())) {
			throw new CustomException("Record Not Found", null, HttpStatus.NOT_FOUND);
		}

		Passenger entity = passengerRepo.findByPassportNo(passenger.getPassportNo());
		if (entity != null) {
			if (passenger.getAge() > 0) {
				entity.setAge(passenger.getAge());
			}

			if (!Strings.isEmpty(passenger.getName())) {
				entity.setName(passenger.getName());
			}

			if (!Strings.isEmpty(passenger.getNationality())) {
				entity.setNationality(passenger.getNationality());
			}

			return passengerRepo.save(entity);

		} else {
			throw new CustomException("Record Not Found", null, HttpStatus.NOT_FOUND);
		}

	}

	public Passenger findById(Integer id) {
		Optional<Passenger> optional = passengerRepo.findById(id);
		if (optional.isEmpty()) {
			throw new CustomException("Record Not Found", null, HttpStatus.NOT_FOUND);
		}

		return optional.get();
	}

	public List<Passenger> findByTicketId(Integer id) {
		List<Passenger> list = passengerRepo.findByTicketId(id);
		if (list == null || list.isEmpty()) {
			throw new CustomException("Record Not Found", null, HttpStatus.NOT_FOUND);
		}
		return list;
	}

	@Transactional
	public List<Passenger> addTicketToPassenger(List<Passenger> listPassenger, Integer ticketId) {

		List<String> list = listPassenger.stream().filter(p -> Strings.isNotBlank(p.getPassportNo()))
				.map(p -> p.getPassportNo()).collect(Collectors.toList());

		if (list.size() != listPassenger.size()) {
			throw new CustomException("Invalid Passenger Detail", null, HttpStatus.BAD_REQUEST);
		}

		List<Passenger> entityList = passengerRepo.findListByPassportNo(list);
		if (entityList == null || entityList.isEmpty() || entityList.size() != listPassenger.size()) {
			throw new CustomException("Invalid Passenger Detail", null, HttpStatus.BAD_REQUEST);
		}

		List<PassengerTicket> listPT = new ArrayList<>();
		entityList.stream().forEach(p -> {
			listPT.add(new PassengerTicket(p.getId(), ticketId));
		});

		passengerTicketRepo.saveAll(listPT);

		return entityList;
	}

}
