package com.accelex.sample.exercise.services;

import java.util.List;

import com.accelex.sample.exercise.dao.CustomerDao;
import com.accelex.sample.exercise.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
    private CustomerDao customerDao;

    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

	public List<Customer> getAll() {
		return (List<Customer>) customerDao.findAll();
	}

	public Customer addCustomer(Customer newCustomer) {
		return null;
	}
}