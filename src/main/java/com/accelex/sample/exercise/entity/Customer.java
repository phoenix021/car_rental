package com.accelex.sample.exercise.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

}