package com.accelex.sample.exercise.dao;

import com.accelex.sample.exercise.entity.Rental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalDao extends CrudRepository<Rental, Long> {
	
}
