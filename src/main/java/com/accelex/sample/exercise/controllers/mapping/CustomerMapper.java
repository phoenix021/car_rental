package com.accelex.sample.exercise.controllers.mapping;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.CustomerDTO;
import com.accelex.sample.exercise.dto.RentalDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Rental;
import com.accelex.sample.exercise.services.RentalService;

import org.springframework.stereotype.Component;

@Component // ?
public class CustomerMapper implements Mapper<CustomerDTO, Customer> {

	@Override
	public CustomerDTO toDto(Customer entity) {
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		String driverLicenceNumber = entity.getDriverLicenceNumber();
		String birthDate = entity.getBirthDate().toString();

		List<RentalDTO> rentals = entity.getRentals().stream()
				.map(rental -> new RentalDTO(rental.getStartDateTime(), rental.getReturnDateTime(), rental.getStatusEnum(), null, null))
				.collect(toList());

		return new CustomerDTO(firstName, lastName, driverLicenceNumber, birthDate, rentals);
	}

	@Override
	public Customer toEntity(CustomerDTO dto) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDate birthDate = null;
		try {
			birthDate = LocalDate.parse(dto.getBirthDate(), pattern);
			// log.error("Converted birth date to: " + dt.toString());
		} catch (DateTimeParseException e) {
			// log.error(e.getMessage());
		}

		return new Customer(dto.getFirstName(), dto.getLastName(), dto.getDriverLicenceNumber(), birthDate);
	}

}
