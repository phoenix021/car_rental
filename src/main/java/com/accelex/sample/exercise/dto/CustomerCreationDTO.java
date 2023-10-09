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
public class CustomerCreationDTO {
	@NotNull(message = "First name can't be null")
	@NotBlank(message = "First name cannot be blank")
	@Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
	private String firstName;

	@NotNull(message = "Last name can't be null")
	@NotBlank(message = "Last name cannot be blank")
	@Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
	private String lastName;

	@NotNull(message = "Drivers licence number can't be null")
	@NotBlank(message = "Drivers licence number can't be blank")
	@Size(min = 3, max = 50, message = "Drivers licence number must be between 3 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Drivers licence number should contain only letters, digits, and underscores")
	private String driverLicenceNumber;

	@NotNull(message = "Birth date can't be null")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in the format yyyy-MM-dd")
	private String birthDate;
}
