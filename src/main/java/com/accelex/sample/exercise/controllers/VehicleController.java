package com.accelex.sample.exercise.controllers;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Vehicle;
import com.accelex.sample.exercise.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/vehicle")
@Slf4j
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping(value = "/add")
	public ResponseEntity<VehicleCreationDTO> addVehicle(@Valid @RequestBody VehicleCreationDTO newVehicleDTO) {
		// TODO: use mapping
		Vehicle vehicleFromDb = vehicleService.getVehicle(newVehicleDTO.getRegistration());
		// if there is an existing vehicle with that same registration plate, we should not try to modify it (this is not an api for modification)
		if (vehicleFromDb != null) {
			return new ResponseEntity<VehicleCreationDTO>(vehicleService.toDto(vehicleFromDb), HttpStatus.BAD_REQUEST);
		}

		Vehicle newVehicle = new Vehicle();
		newVehicle.setBrand(newVehicleDTO.getBrand());
		newVehicle.setColour(newVehicleDTO.getColour());
		newVehicle.setMakeYear(newVehicleDTO.getYear());
		newVehicle.setRegistration(newVehicleDTO.getRegistration());
		newVehicle.setModel(newVehicleDTO.getModel());

		Vehicle savedVehicle = vehicleService.saveVehicle(newVehicle);
		if (savedVehicle == null) {
			return new ResponseEntity<VehicleCreationDTO>(vehicleService.toDto(savedVehicle), HttpStatus.BAD_REQUEST);
		}
		// TODO:transform back to dto
		System.out.println("****************");
		System.out.println(savedVehicle);
		System.out.println("****************");
		log.info(savedVehicle.toString());
		return new ResponseEntity<VehicleCreationDTO>(newVehicleDTO, HttpStatus.CREATED);
	}

}
