package com.cupofjavatech.ticket.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cupofjavatech.ticket.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

//	@Query("SELECT t FROM Ticket where ticketNumber = :ticketNumber")
//	public Ticket findbyTicketNumber(String ticketNumber);

	public List<Ticket> findByFlightNumber(String flightNumber);

}
