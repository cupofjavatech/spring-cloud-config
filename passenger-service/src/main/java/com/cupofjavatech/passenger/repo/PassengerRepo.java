package com.cupofjavatech.passenger.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cupofjavatech.passenger.model.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Integer> {

	@Query("SELECT p FROM Passenger p WHERE p.passportNo = :passportNo")
	public Passenger findByPassportNo(String passportNo);

	@Query(value = "SELECT new com.cupofjavatech.passenger.model.Passenger(p.id, p.name, p.passportNo, p.age, p.nationality) "
			+ " from Passenger p, PassengerTicket pt WHERE pt.ticketId = :ticketId AND pt.passengerId = p.id")
	public List<Passenger> findByTicketId(Integer ticketId);

	@Query(value = "select p from Passenger p WHERE p.passportNo in (:passportList)")
	public List<Passenger> findListByPassportNo(List<String> passportList);

}
