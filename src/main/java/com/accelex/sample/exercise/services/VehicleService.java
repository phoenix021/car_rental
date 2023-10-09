package com.accelex.sample.exercise.services;

import java.util.List;

import com.accelex.sample.exercise.dao.VehicleDao;
import com.accelex.sample.exercise.dto.VehicleCreationDTO;
import com.accelex.sample.exercise.dto.VehicleDTO;
import com.accelex.sample.exercise.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleService {
	@Autowired
    private VehicleDao vehicleDao;

    public Vehicle saveVehicle(Vehicle vehicle) {
    	try {
    		Vehicle savedVehicle = vehicleDao.save(vehicle);
            return savedVehicle;
    	} catch (DataIntegrityViolationException e) {
    		log.error("DataIntegrityViolationException trying to save vehicle");
    		return null;
    	} catch (DataAccessException e) {
    		log.error("DataAccessException trying to save vehicle");
    		return null;
    	} catch (Exception e) {
    		log.error("Exception trying to save vehicle");
    	}
		return null;
    }
   /* 
    public List<Vehicle> searchVehicles(String brand, String model, Integer year) {
        // Implement your search logic using the provided parameters
        // This is just a simple example; you can customize it based on your requirements
        return vehicleDao.findByBrandAndModelAndMakeYear(brand, model, year);
    }
    */

	public Vehicle getVehicle(String registration) {
		// TODO Auto-generated method stub
		return vehicleDao.findByRegistration(registration);
	}
	
	public VehicleCreationDTO toDto(Vehicle vehicle) {
		if (vehicle != null)
		{
			VehicleCreationDTO vehicleDto = new VehicleCreationDTO();
			vehicleDto.setBrand(vehicle.getBrand());
			vehicleDto.setColour(vehicle.getColour());
			vehicleDto.setModel(vehicle.getModel());
			vehicleDto.setRegistration(vehicle.getRegistration());
			vehicleDto.setYear(vehicle.getMakeYear());
			return vehicleDto;
		} else
			return null;
	}

}
