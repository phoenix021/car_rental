package com.accelex.sample.exercise.dao;

import java.util.List;

import com.accelex.sample.exercise.entity.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {
	/*
	@Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.rentals")
	List<Customer> findAll();
	*/
	
    void deleteByDriverLicenceNumber(String driverLicenceNumber);
	
	Customer findByDriverLicenceNumber(String driversLicense);
	
	
}