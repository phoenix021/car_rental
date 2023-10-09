package com.accelex.sample.exercise.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accelex.sample.exercise.dao.RentalDao;
import com.accelex.sample.exercise.dto.RentalDTO;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Rental;
import com.accelex.sample.exercise.entity.Vehicle;
import com.accelex.sample.exercise.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RentalService {
	@Autowired
	VehicleService vehicleService;

	@Autowired
	CustomerService customerService;

	@Autowired
	private RentalDao rentalDao;

	public Rental saveRental(Rental rental) {
		return rentalDao.save(rental);
	}

	public RentalDTO rentVehicleToCustomer(String registrationPlate, String driversLicense) {
		// TODO: first check if the vehicle is available (Status.returnerd_damaged or
		// pending)
		Vehicle vehicle = vehicleService.getVehicle(registrationPlate);
		if (vehicle == null) {
			log.error("No vehicle found with that registration plate number. Aborting...");
			return null;
		}
		Customer customer = customerService.getCustomer(driversLicense);
		if (customer == null) {
			log.error("No customer found with that driver licence number. Aborting...");
			return null;
		}
		Rental rentalFromDb = findRentedVehicleByRegistration(registrationPlate);
		if (rentalFromDb != null) {
			if (rentalFromDb.getReturnDateTime() == null) {
				log.error("Vehicle already rented. Aborting...");
				return null;
			}
		} else {
			log.error("Vehicle not rented. Proceeding...");
		}

		Rental newRental = new Rental();
		newRental.setCustomer(customer);
		newRental.setVehicle(vehicle);
		newRental.setStartDateTime(LocalDateTime.now());
		newRental.setStatusEnum(Status.OUT);

		Rental savedRental = rentalDao.save(newRental);
		log.info("Saved rental: " + savedRental.toString());

		/*
		// Update Customer's Rentals
		log.error("Rental to be saved: " + savedRental.toString());
		Customer customerToUpdate = customerService.saveCustomer(savedRental.getCustomer());
		customerToUpdate.getRentals().add(savedRental);
		Customer savedCustomer = customerService.saveCustomer(customerToUpdate);
		log.info("Saved customer: " + savedCustomer.toString());
		
		//vehicle = vehicleService.saveVehicle(savedRental.getVehicle());
		vehicle = savedRental.getVehicle();
		vehicle.getRentals().add(savedRental);
		vehicleService.saveVehicle(vehicle);
		log.info("Saved Vehicle: " + vehicle.toString());
		*/

		return toDto(savedRental);
	}

	// TODO: add option to return vehicle in a certain state
	public RentalDTO returnVehicle(String registrationPlate, String driversLicense) {
		List<Rental> rentals = rentalDao.findByReturnDateTime(null);
		if (rentals.isEmpty()) {
			return null;
		} else {
			log.info(rentals.toString());
			Rental returnRental = new Rental();
			for (Rental rental : rentals) {
				if (rental.getReturnDateTime() == null || rental.getStatusEnum().equals(Status.OUT)) {
					returnRental = rental;
					break;
				}
			}
			returnRental.setReturnDateTime(LocalDateTime.now());
			returnRental.setStatusEnum(Status.RETURNED_OK);

			Rental savedRental = returnRental = rentalDao.save(returnRental);
			customerService.saveCustomer(savedRental.getCustomer());
			vehicleService.saveVehicle(savedRental.getVehicle());

			return toDto(savedRental);
		}
	}

	public Rental findByStatus(Status status) {
		return rentalDao.findByStatus(status.getValue());
	}

	public List<VehicleCreationDTO> getRentedVehicles() {
		List<Rental> rentalsWithoutReturnDate = rentalDao.findByReturnDateTime(null);
		log.info("Rental entries with no return date: " + rentalsWithoutReturnDate);
		List<VehicleCreationDTO> rentedVehicles = rentalsWithoutReturnDate.stream()
				.map(rental -> vehicleService.toDto(rental.getVehicle())).collect(Collectors.toList());
		return rentedVehicles;
	}
	
	public Rental findRentedVehicleByRegistration(String registration) {
		try {
			return rentalDao.findRentedVehicleByRegistration(registration);
		} catch (Exception e) {
			log.error("Error while geting rented vehicle by registration");
			log.error(e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public RentalDTO toDto(Rental rental) {
		if (rental != null) {
			RentalDTO rentalDto = new RentalDTO();
			rentalDto.setReturnDateTime(rental.getReturnDateTime());
			rentalDto.setStartDateTime(rental.getStartDateTime());
			rentalDto.setStatus(rental.getStatusEnum());
			rentalDto.setVehicle(vehicleService.toDto(rental.getVehicle()));
			rentalDto.setCustomer(customerService.toDto(rental.getCustomer()));
			return rentalDto;
		} else
			return null;
	}
}
