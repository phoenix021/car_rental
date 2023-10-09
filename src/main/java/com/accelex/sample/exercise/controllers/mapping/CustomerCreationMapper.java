package com.accelex.sample.exercise.controllers.mapping;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.entity.Customer;

import org.springframework.stereotype.Component;

@Component // ?
public class CustomerCreationMapper implements Mapper<CustomerCreationDTO, Customer> {

	@Override
	public CustomerCreationDTO toDto(Customer entity) {
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		String driverLicenceNumber = entity.getDriverLicenceNumber();
		String birthDate = entity.getBirthDate().toString();
		/*
		 * List<Rental> rentals = entity .getRentals() .stream() .map(Rental::getTime)
		 * .collect(toList());
		 */
		return new CustomerCreationDTO(firstName, lastName, driverLicenceNumber, birthDate);
	}

	@Override
	public Customer toEntity(CustomerCreationDTO dto) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDate birthDate = null;
		try {
			birthDate = LocalDate.parse(dto.getBirthDate(), pattern);
		    //log.error("Converted birth date to: " + dt.toString()); 
		} catch (DateTimeParseException e) {
			//log.error(e.getMessage());
		}
		 
		 return new Customer(dto.getFirstName(), dto.getLastName(), dto.getDriverLicenceNumber(), birthDate);
	}

}
