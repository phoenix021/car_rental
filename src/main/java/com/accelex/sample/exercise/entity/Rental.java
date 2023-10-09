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

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;
	
    @ManyToOne(cascade = CascadeType.ALL)
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
	
	

}
