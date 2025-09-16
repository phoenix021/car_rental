package com.accelex.sample.exercise.entity;

import java.time.LocalDateTime;

import com.accelex.sample.exercise.util.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rental {
	
	private LocalDateTime startDateTime;
	private LocalDateTime returnDateTime;
	private Integer status;
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rental_id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    
    public Rental(Vehicle vehicle, Customer customer) {
        this.vehicle = vehicle;
        this.customer = customer;
    }

	public Rental(LocalDateTime startDateTime, LocalDateTime returnDateTime, Status statusEnum, Vehicle vehicle,
			Customer customer) {
		super();
		this.startDateTime = startDateTime;
		this.returnDateTime = returnDateTime;
		this.status = statusEnum.getValue();
		this.vehicle = vehicle;
		this.customer = customer;
	}
	
	public Rental() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Status getStatusEnum(){
		for (Status statusEnum : Status.values()) 
		{ 
		    if (statusEnum.getValue() == status) {
		    	return statusEnum;
		    }
		}
		return Status.RETURNED_DAMAGED;
	}
	
	public void setStatusEnum(Status statusEnum) {
		status = statusEnum.getValue();
	}

	public LocalDateTime getStartDateTime() {
		// TODO Auto-generated method stub
		return startDateTime;
	}

	public LocalDateTime getReturnDateTime() {
		// TODO Auto-generated method stub
		return returnDateTime;
	}

	public void setCustomer(Customer customer) {
		// TODO Auto-generated method stub
		this.customer = customer;
		
	}

	public void setVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		this.vehicle = vehicle;
	}

	public void setStartDateTime(LocalDateTime now) {
		// TODO Auto-generated method stub
		this.startDateTime = now;
		
	}

	public void setReturnDateTime(LocalDateTime now) {
		// TODO Auto-generated method stub
		this.returnDateTime = now;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return this.customer;
	}

	public Vehicle getVehicle() {
		// TODO Auto-generated method stub
		return this.vehicle;
	}
}
