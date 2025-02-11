package com.cupofjavatech.ticket.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flight {

//	private int id;

	private String flightNo;

	private int capacity;

	private String fromStation;

	private String toStation;

	private String fromCode;

	private String toCode;

	private Date departure;

	private Date arrival;

	private Double price;

}
