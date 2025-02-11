package com.cupofjavatech.ticket.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
	private Integer ticketNumber;
	private List<Passenger> listPassenger;
	private Flight flight;

}
