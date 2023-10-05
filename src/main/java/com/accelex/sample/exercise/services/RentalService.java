package com.accelex.sample.exercise.services;

import com.accelex.sample.exercise.dao.RentalDao;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
	@Autowired
    private RentalDao rentalDao;

    public Rental saveRental(Rental rental) {
        return rentalDao.save(rental);
    }
}
