package com.accelex.sample.exercise.entity;

import java.time.LocalDateTime;

import com.accelex.sample.exercise.util.Status;

import jakarta.persistence.Entity;
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
	private Status status;
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rental_id;

	@ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    public Rental(Vehicle vehicle, Customer customer) {
        this.vehicle = vehicle;
        this.customer = customer;
    }

	public Rental(LocalDateTime startDateTime, LocalDateTime returnDateTime, Status status, Vehicle vehicle,
			Customer customer) {
		super();
		this.startDateTime = startDateTime;
		this.returnDateTime = returnDateTime;
		this.status = status;
		this.vehicle = vehicle;
		this.customer = customer;
	}

}
