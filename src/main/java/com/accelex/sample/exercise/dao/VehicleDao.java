package com.accelex.sample.exercise.dao;

import com.accelex.sample.exercise.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends CrudRepository<Vehicle, Long> {

	Vehicle findByRegistration(String registration);

}
