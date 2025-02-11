package com.cupofjavatech.flight.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FLIGHT")
@Getter
@Setter
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FLIGHT_NO", nullable = false, length = 5)
	private String flightNo;

	@Column(name = "CAPACITY", nullable = false, length = 4)
	private int capacity;

	@Column(name = "FROM_STATION", nullable = false)
	private String fromStation;

	@Column(name = "TO_STATION", nullable = false)
	private String toStation;

	@Column(name = "FROM_CODE")
	private String fromCode;

	@Column(name = "TO_CODE")
	private String toCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEPARTURE")
	private Date departure;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ARRIVAL")
	private Date arrival;

	@Column(name = "PRICE", precision = 2)
	private Double price;

}
