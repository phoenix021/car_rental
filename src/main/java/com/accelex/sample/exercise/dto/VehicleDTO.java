package com.accelex.sample.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
	private String makeYear;
	private String brand;
	private String model;
	private String colour;
	private String registration;
}
