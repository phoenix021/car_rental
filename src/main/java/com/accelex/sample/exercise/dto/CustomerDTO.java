package com.accelex.sample.exercise.dto;

import java.util.ArrayList;
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
	List<RentalDTO> rentals = new ArrayList<RentalDTO>(0);

	public CustomerDTO(String firstName, String lastName, String driverLicenceNumber, String birthDate,
			List<RentalDTO> rentals) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.driverLicenceNumber = driverLicenceNumber;
		this.birthDate = birthDate;
		this.rentals = rentals;
	}

	public String getBirthDate() {
		// TODO Auto-generated method stub
		return birthDate;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	public String getDriverLicenceNumber() {
		// TODO Auto-generated method stub
		return driverLicenceNumber;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}
}

