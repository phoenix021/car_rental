package com.accelex.sample.exercise.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.accelex.sample.exercise.dto.RentalDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customer_id;
	private String firstName;
	private String lastName;
	private String driverLicenceNumber;
	private LocalDate birthDate;
    @OneToMany(mappedBy = "customer")
    private Set<Rental> rentals = new HashSet<>();

	public Customer(String firstName, String lastName, String driverLicenceNumber, LocalDate birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.driverLicenceNumber = driverLicenceNumber;
		this.birthDate = birthDate;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		// TODO Auto-generated method stub
		this.driverLicenceNumber =  driverLicenceNumber;
	}

	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		this.lastName = lastName;
	}

	public void setFirstName(String firstName2) {
		// TODO Auto-generated method stub
		this.firstName = firstName;
	}

	public void setBirthDate(LocalDate birthDate) {
		// TODO Auto-generated method stub
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	public LocalDate getBirthDate() {
		// TODO Auto-generated method stub
		return this.birthDate;
	}

	public String getDriverLicenceNumber() {
		// TODO Auto-generated method stub
		return this.driverLicenceNumber;
	}

	public Set<Rental> getRentals() {
		// TODO Auto-generated method stub
		return rentals;
	}

}