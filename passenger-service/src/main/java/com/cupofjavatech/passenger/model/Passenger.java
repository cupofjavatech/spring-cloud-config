package com.cupofjavatech.passenger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NAME", nullable = false)
	String name;

	@Column(name = "PASSPORT_NO")
	String passportNo;

	@Column(name = "AGE", length = 3)
	int age;

	@Column(name = "NATIONALITY")
	String nationality;

	public Passenger() {
	}

	public Passenger(int id) {
		this.id = id;
	}

	public Passenger(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Passenger(int id, String name, String passportNo, int age, String nationality) {
		this.id = id;
		this.name = name;
		this.passportNo = passportNo;
		this.age = age;
		this.nationality = nationality;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNationality() {
		return nationality;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

}
