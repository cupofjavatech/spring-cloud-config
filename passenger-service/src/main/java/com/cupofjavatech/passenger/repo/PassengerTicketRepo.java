package com.cupofjavatech.passenger.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cupofjavatech.passenger.model.PassengerTicket;

public interface PassengerTicketRepo extends JpaRepository<PassengerTicket, Integer> {

}
