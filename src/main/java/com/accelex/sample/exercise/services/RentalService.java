package com.accelex.sample.exercise.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.CustomerRentalsDTO;
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
			System.out.println("No vehicle found with that registration plate number. Aborting...");
			return new RentalDTO();
		}
		Customer customer = customerService.getCustomer(driversLicense);
		if (customer == null) {
			System.out.println("No customer found with that driver licence number. Aborting...");
			return new RentalDTO();
		}
		Rental rentalFromDb = findRentedVehicleByRegistration(registrationPlate);
		if (rentalFromDb != null) {
			if (rentalFromDb.getReturnDateTime() == null) {
				System.out.println("Vehicle already rented. Aborting...");
				return null;
			}
		} else {
			System.out.println("Vehicle not rented. Proceeding...");
		}

		Rental newRental = new Rental();
		newRental.setCustomer(customer);
		newRental.setVehicle(vehicle);
		newRental.setStartDateTime(LocalDateTime.now());
		newRental.setStatusEnum(Status.OUT);

		Rental savedRental = rentalDao.save(newRental);
		System.out.println("Saved rental: " + savedRental.toString());

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
			System.out.println(rentals.toString());
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
		System.out.println("Rental entries with no return date: " + rentalsWithoutReturnDate);
		List<VehicleCreationDTO> rentedVehicles = rentalsWithoutReturnDate.stream()
				.map(rental -> vehicleService.toDto(rental.getVehicle())).collect(Collectors.toList());
		return rentedVehicles;
	}
	
	public Rental findRentedVehicleByRegistration(String registration) {
		try {
			return rentalDao.findRentedVehicleByRegistration(registration);
		} catch (Exception e) {
			System.out.println("Error while geting rented vehicle by registration");
			System.out.println(e.toString());
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
			return new RentalDTO();
	}


	public List<CustomerRentalsDTO> getRentalsByDriverLicence(
			String driverLicenceNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<RentalDTO> getCustomerRentalHistory(String driverLicenceNumber) {
	    List<Rental> rentals = rentalDao.findAllByCustomerDriverLicenceNumber(driverLicenceNumber);

	    return rentals.stream().map(r -> {
	    	RentalDTO rentalDto = new RentalDTO();
	        Vehicle v = r.getVehicle();
	        VehicleCreationDTO  vehicleDto = vehicleService.toDto(v);
	        CustomerCreationDTO customerDto = customerService.toDto(r.getCustomer());
	        rentalDto.setVehicle(vehicleDto);
	        rentalDto.setStartDateTime(r.getStartDateTime());
	        rentalDto.setReturnDateTime(r.getReturnDateTime());
	        rentalDto.setStatus(r.getStatusEnum());
	        return rentalDto;
	    }).toList();
	}
}
