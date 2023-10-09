package com.accelex.sample.exercise.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRentalDTO {
	private String firstName;
	private String lastName;
	private String driverLicenceNumber;
	private LocalDate birthDate;
	private List<RentalDTO> rentals;
}
