package com.accelex.sample.exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreationDTO {
	@NotNull(message = "Registration of the vehicle can't be null")
	@NotBlank(message = "Registration of the vehicle cannot be blank")
	@Size(min = 5, max = 15, message = "Registration of the vehicle must be between 5 and 15 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Registration of the vehicle should contain only letters, digits, and underscores")
	String registration;
	
	@NotNull(message = "Drivers licence number can't be null")
	@NotBlank(message = "Drivers licence number can't be blank")
	@Size(min = 3, max = 50, message = "Drivers licence number must be between 3 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Drivers licence number should contain only letters, digits, and underscores")
	String driverLicenceNumber;

	public String getRegistration() {
		// TODO Auto-generated method stub
		return registration;
	}

	public String getDriverLicenceNumber() {
		// TODO Auto-generated method stub
		return driverLicenceNumber;
	}

	public void setRegistration(String registration) {
		// TODO Auto-generated method stub
		this.registration = registration;
	}

	public void setDriverLicenceNumber(String driverLicenceNumebr) {
		// TODO Auto-generated method stub
		this.driverLicenceNumber = driverLicenceNumebr;
	}
	
	@Override
	public String toString() {
	    return "Rental{" +
	           "driverLicenceNumber=" + driverLicenceNumber +
	           ", registration=" + registration;
	}

}
