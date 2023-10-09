package com.accelex.sample.exercise.controllers;

import java.util.List;

import com.accelex.sample.exercise.dto.RentalCreationDTO;
import com.accelex.sample.exercise.dto.RentalDTO;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import com.accelex.sample.exercise.services.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "api/rental")
@Slf4j
public class RentalController {

	@Autowired
	RentalService rentalService;

	@GetMapping(value = "/listCurrentlyRentedVehicles")
	public ResponseEntity<List<VehicleCreationDTO>> listRentedVehicles() {
		List<VehicleCreationDTO> rentedVehicles = rentalService.getRentedVehicles();
		return new ResponseEntity<List<VehicleCreationDTO>>(rentedVehicles, HttpStatus.OK);
	}
	
	@PostMapping(value = "/rent")
	public ResponseEntity<RentalDTO> rentVehicleToCustomer(@Valid @RequestBody RentalCreationDTO rentalCreationDto) {
		RentalDTO rentalDto = rentalService.rentVehicleToCustomer(rentalCreationDto.getRegistration(), rentalCreationDto.getDriverLicenceNumber());
		if (rentalDto == null){
			return new ResponseEntity<RentalDTO>(rentalDto, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RentalDTO>(rentalDto, HttpStatus.CREATED);
	}
	
	/*
	@PostMapping(value = "/rent")
	public ResponseEntity<RentalDTO> rentVehicleToCustomer(
			@RequestParam(name = "registrationPlate", required = true, defaultValue = "") String registrationPlate,
			@RequestParam(name = "driversLicence", required = true, defaultValue = "") String driversLicense) {
		RentalDTO rentalDto = rentalService.rentVehicleToCustomer(registrationPlate, driversLicense);
		return new ResponseEntity<RentalDTO>(rentalDto, HttpStatus.OK);
	}
	*/

	@PostMapping(value = "/return")
	public ResponseEntity<RentalDTO> returnVehicle(
			@RequestParam(name = "registrationPlate", required = true, defaultValue = "") String registrationPlate,
			@RequestParam(name = "driversLicence", required = true, defaultValue = "") String driversLicense) {
		RentalDTO rentalDto = rentalService.returnVehicle(registrationPlate, driversLicense);
		if (rentalDto == null)
			return new ResponseEntity<RentalDTO>(rentalDto, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<RentalDTO>(rentalDto, HttpStatus.OK);
	}
	

}
