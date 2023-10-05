package com.accelex.sample.exercise.services;

import com.accelex.sample.exercise.dao.VehicleDao;
import com.accelex.sample.exercise.entity.Customer;
import com.accelex.sample.exercise.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	@Autowired
    private VehicleDao vehicleDao;

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleDao.save(vehicle);
    }

	public Vehicle addVehicle(Vehicle newVehicle) {
		return null;
	}
}
