package com.accelex.sample.exercise.services;

import java.util.List;

import com.accelex.sample.exercise.dao.CustomerDao;
import com.accelex.sample.exercise.dto.CustomerCreationDTO;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
    private CustomerDao customerDao;

    public Customer saveCustomer(Customer customer) {
    	try {
            return customerDao.save(customer);
    	} catch (DataIntegrityViolationException e) {
    		log.error("DataIntegrityViolationException trying to save customer");
    		return null;
    	} catch (DataAccessException e) {
    		log.error("DataAccessException trying to save customer");
    		return null;
    	} catch (Exception e) {
    		log.error("Exception trying to save customer" + e.toString());
    	}
		return null;

    }

	public List<Customer> getAll() {
		return (List<Customer>) customerDao.findAll();
	}

	public Customer getCustomer(String driversLicense) {
		return customerDao.findByDriverLicenceNumber(driversLicense);
	}
	
	public CustomerCreationDTO toDto(Customer customer) {
		if (customer != null) {
			CustomerCreationDTO customerDto = new CustomerCreationDTO();
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setBirthDate(customer.getBirthDate().toString());
			customerDto.setDriverLicenceNumber(customer.getDriverLicenceNumber());
			return customerDto;
		} else
			return null;
	}

}