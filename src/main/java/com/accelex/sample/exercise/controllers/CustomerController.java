package com.accelex.sample.exercise.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.accelex.sample.exercise.controllers.mapping.CustomerCreationMapper;
import com.accelex.sample.exercise.controllers.mapping.CustomerMapper;
import com.accelex.sample.exercise.controllers.mapping.Mapper;
import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.dto.CustomerDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/getAll")
	public ResponseEntity<List<CustomerDTO>> getAll() {
		Mapper<CustomerDTO, Customer> mapper = new CustomerMapper();

		List<CustomerDTO> customers = customerService.getAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<CustomerCreationDTO> addCustomer(@Valid @RequestBody CustomerCreationDTO customerCreationDto) {
		Customer customerFromDb = customerService.getCustomer(customerCreationDto.getDriverLicenceNumber());
		// if there is an existing customer with that same drivers licence, we should not try to modify him (this is not an api for modification)
		if (customerFromDb != null) {
			return new ResponseEntity<CustomerCreationDTO>(customerService.toDto(customerFromDb), HttpStatus.BAD_REQUEST);
		}
		//TODO: use mapper for conversion
		Customer customerToAddToDb = new Customer();
		customerToAddToDb.setBirthDate(LocalDate.parse(customerCreationDto.getBirthDate()));
		customerToAddToDb.setDriverLicenceNumber(customerCreationDto.getDriverLicenceNumber());
		customerToAddToDb.setFirstName(customerCreationDto.getFirstName());
		customerToAddToDb.setLastName(customerCreationDto.getLastName());

		Customer savedCustomer = customerService.saveCustomer(customerToAddToDb);
		if (savedCustomer == null) {
			return new ResponseEntity<CustomerCreationDTO>(customerService.toDto(savedCustomer), HttpStatus.BAD_REQUEST);
		}
		log.info(savedCustomer.toString());
		return new ResponseEntity<CustomerCreationDTO>(customerService.toDto(savedCustomer), HttpStatus.CREATED);
	}

}
