package com.accelex.sample.exercise.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private String firstName;
	private String lastName;
	private String driverLicenceNumber;
	private String birthDate;
	List<RentalDTO> rentals;
}

