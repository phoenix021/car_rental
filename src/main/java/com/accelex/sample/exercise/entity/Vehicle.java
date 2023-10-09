package com.accelex.sample.exercise.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Entity
@Data
@NoArgsConstructor
public class Vehicle { //implements java.io.Serializable?
	
	@Column(name = "MAKEYEAR", nullable = false, length = 4)
	private String makeYear;
	private String brand;
	private String model;
	private String colour;
	private String registration;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vehicle_id;
	
    @OneToMany(mappedBy = "vehicle")
    private Set<Rental> rentals = new HashSet<>();

	public Vehicle(String makeYear, String brand, String model, String colour, String registration) {
		super();
		this.makeYear = makeYear;
		this.brand = brand;
		this.model = model;
		this.colour = colour;
		this.registration = registration;
	}



}
