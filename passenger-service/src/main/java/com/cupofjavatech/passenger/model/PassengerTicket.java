package com.cupofjavatech.passenger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PASSENGER_TICKET")
public class PassengerTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PASSENGER_ID")
	private Integer passengerId;

	@Column(name = "TICKET_ID")
	private Integer ticketId;

	public PassengerTicket() {
		super();
	}

	public PassengerTicket(Integer passengerId, Integer ticketId) {
		super();
		this.passengerId = passengerId;
		this.ticketId = ticketId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

}
