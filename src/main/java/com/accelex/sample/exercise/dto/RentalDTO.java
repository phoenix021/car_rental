package com.accelex.sample.exercise.dto;

import java.time.LocalDateTime;

import com.accelex.sample.exercise.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: check if necessary, maybe remove
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
	private LocalDateTime startDateTime;
	private LocalDateTime returnDateTime;
	private Status status;
	private VehicleCreationDTO vehicle;
	private CustomerCreationDTO customer;

}
