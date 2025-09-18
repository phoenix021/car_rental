package com.accelex.sample.exercise.services;

import java.util.ArrayList;
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
    		System.out.println("DataIntegrityViolationException trying to save vehicle");
    		return null;
    	} catch (DataAccessException e) {
    		System.out.println("DataAccessException trying to save vehicle");
    		return null;
    	} catch (Exception e) {
    		System.out.println("Exception trying to save vehicle");
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
	
	public Vehicle toEntity(VehicleCreationDTO vehicleDto) {
		if (vehicleDto != null)
		{
			Vehicle vehicleEntity = new Vehicle();
			vehicleEntity.setBrand(vehicleDto.getBrand());
			vehicleEntity.setColour(vehicleDto.getColour());
			vehicleEntity.setModel(vehicleDto.getModel());
			vehicleEntity.setRegistration(vehicleDto.getRegistration());
			vehicleEntity.setMakeYear(vehicleDto.getYear());
			return vehicleEntity;
		} else
			return null;
	}
	
	public List<Vehicle> getAllVehicles() {
	    List<Vehicle> result = new ArrayList<>();
	    vehicleDao.findAll().forEach(result::add);
	    return result;
	}

	public void saveAllVehicles(List<Vehicle> vehicles) {
	    for (Vehicle v : vehicles) {
	        Vehicle existing = vehicleDao.findByRegistration(v.getRegistration());
	        if (existing == null) {
	            vehicleDao.save(v);
	        } else {
	            // Optionally update fields or skip
	            existing.setBrand(v.getBrand());
	            existing.setModel(v.getModel());
	            existing.setColour(v.getColour());
	            existing.setMakeYear(v.getMakeYear());
	            vehicleDao.save(existing);
	        }
	    }
	}




}
