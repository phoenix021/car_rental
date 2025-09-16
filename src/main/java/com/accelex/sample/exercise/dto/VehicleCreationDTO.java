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

	public VehicleCreationDTO(String makeYear, String brand, String model, String colour, String registration) {
		// TODO Auto-generated constructor stub
		this.year = makeYear;
		this.brand = brand;
		this.model = model;
		this.colour = colour;
		this.registration = registration;
	}

	public VehicleCreationDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getRegistration() {
		// TODO Auto-generated method stub
		return registration;
	}

	public String getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getBrand() {
		// TODO Auto-generated method stub
		return brand;
	}

	public String getYear() {
		// TODO Auto-generated method stub
		return year;
	}

	public String getColour() {
		// TODO Auto-generated method stub
		return colour;
	}

	public void setBrand(String brand) {
		// TODO Auto-generated method stub
		this.brand = brand;
	}

	public void setColour(String colour) {
		// TODO Auto-generated method stub
		this.colour = colour;
	}

	public void setModel(String model) {
		// TODO Auto-generated method stub
		this.model = model;
	}

	public void setRegistration(String registration) {
		// TODO Auto-generated method stub
		this.registration = registration;
	}

	public void setYear(String makeYear) {
		// TODO Auto-generated method stub
		this.year = makeYear;
	}
}
