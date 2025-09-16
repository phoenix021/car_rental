package com.accelex.sample.exercise.dto;

import java.time.LocalDateTime;

import com.accelex.sample.exercise.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO: check if necessary, maybe remove
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
	public RentalDTO(LocalDateTime startDateTime, LocalDateTime returnDateTime, Status statusEnum, VehicleCreationDTO vehicle,
			CustomerCreationDTO customer) {
		// TODO Auto-generated constructor stub
		this.startDateTime = startDateTime;
		this.returnDateTime = returnDateTime;
		this.status = statusEnum;
		this.vehicle = vehicle;
		this.customer = customer;
	}
	public RentalDTO() {
		// TODO Auto-generated constructor stub
		super();
	}
	private LocalDateTime startDateTime;
	private LocalDateTime returnDateTime;
	private Status status;
	private VehicleCreationDTO vehicle;
	private CustomerCreationDTO customer;
	
	public void setReturnDateTime(LocalDateTime returnDateTime) {
		// TODO Auto-generated method stub
		this.returnDateTime = returnDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		// TODO Auto-generated method stub
		this.startDateTime = startDateTime;
	}
	public void setStatus(Status statusEnum) {
		// TODO Auto-generated method stub
		this.status = statusEnum;
	}
	public void setVehicle(VehicleCreationDTO dto) {
		// TODO Auto-generated method stub
		this.vehicle = dto;
	}
	public void setCustomer(CustomerCreationDTO dto) {
		// TODO Auto-generated method stub
		this.customer = dto;
	}
	
	public LocalDateTime getReturnDateTime() {
		// TODO Auto-generated method stub
		return returnDateTime;
	}
	public LocalDateTime getStartDateTime() {
		// TODO Auto-generated method stub
		return startDateTime;
	}
	public Status getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	public VehicleCreationDTO getVehicle() {
		// TODO Auto-generated method stub
		return vehicle;
	}
	public CustomerCreationDTO getCustomer(CustomerCreationDTO dto) {
		// TODO Auto-generated method stub
		return customer;
	}
	
	@Override
	public String toString() {
	    return "Rental{" +
	           ", startDateTime=" + startDateTime +
	           ", returnDateTime=" + returnDateTime +
	           ", status=" + status +
	           ", vehicle=" + (vehicle != null ? vehicle.getRegistration() : "null") +
	           ", customer=" + (customer != null ? customer.getDriverLicenceNumber() : "null") +
	           '}';
	}

}
