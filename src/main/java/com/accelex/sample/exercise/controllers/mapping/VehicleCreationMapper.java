package com.accelex.sample.exercise.controllers.mapping;

import java.time.LocalDate;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Vehicle;

public class VehicleCreationMapper implements Mapper<VehicleCreationDTO, Vehicle>{

	@Override
	public VehicleCreationDTO toDto(Vehicle entity) {
		String makeYear = entity.getMakeYear();
		String brand = entity.getBrand();
		String model = entity.getModel();
		String colour = entity.getColour();
		String registration = entity.getRegistration();
        /*List<Rental> rentals = entity
                .getRentals()
                .stream()
                .map(Rental::getTime)
                .collect(toList());
                */
        return new VehicleCreationDTO(makeYear, brand, model, colour, registration);
	}

	@Override
	public Vehicle toEntity(VehicleCreationDTO dto) {
		return new Vehicle(dto.getYear(), dto.getBrand(), dto.getModel(), dto.getColour(), dto.getRegistration());
	}

}
