package com.accelex.sample.exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: remove either this one or VehicleDTO, they are currently same
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreationDTO {
	@NotNull(message = "Make year of the vehicle can't be null")
	@NotBlank(message = "Make year of the vehicle cannot be blank")
	@Size(min = 4, max = 4, message = "Make year of the vehicle must have 4 characters")
	@Pattern(regexp = "^[0-9]+$", message = "Make year of the vehicle should contain only digits")
	private String year;

	@NotNull(message = "Brand of the vehicle can't be null")
	@NotBlank(message = "Brand of the vehicle cannot be blank")
	@Size(min = 3, max = 50, message = "Brand of the vehicle must be between 3 and 50 characters")
	private String brand;

	@NotNull(message = "Brand of the vehicle can't be null")
	@NotBlank(message = "Brand of the vehicle cannot be blank")
	@Size(min = 3, max = 50, message = "Brand of the vehicle must be between 3 and 50 characters")
	private String model;

	@NotNull(message = "Brand of the vehicle can't be null")
	@NotBlank(message = "Brand of the vehicle cannot be blank")
	@Size(min = 3, max = 50, message = "Brand of the vehicle must be between 3 and 50 characters")
	private String colour;
	
	@NotNull(message = "Registration of the vehicle can't be null")
	@NotBlank(message = "Registration of the vehicle cannot be blank")
	@Size(min = 5, max = 15, message = "Registration of the vehicle must be between 5 and 15 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Registration of the vehicle should contain only letters, digits, and underscores")
	private String registration;
}
