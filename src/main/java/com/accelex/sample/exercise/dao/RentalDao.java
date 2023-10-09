package com.accelex.sample.exercise.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.accelex.sample.exercise.entity.Rental;
import com.accelex.sample.exercise.entity.Vehicle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalDao extends CrudRepository<Rental, Long> {
	Rental findByStatus(int status);

	@Query("SELECT r FROM Rental r JOIN r.customer c JOIN r.vehicle v WHERE c.driverLicenceNumber = :driverLicenceNumber AND v.registration = :registration")
	List<Rental> findByRegistrationAndDriverLicenceNumber(@Param("registration") String registration, @Param("driverLicenceNumber") String driverLicenceNumber);


	List<Rental> findByReturnDateTime(LocalDateTime dateTime);
	
	@Query("SELECT r FROM Rental r WHERE r.vehicle.registration = :registration AND r.returnDateTime IS NULL")
	Rental findRentedVehicleByRegistration(@Param("registration") String registration);
}
