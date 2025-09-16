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

	public Vehicle() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void setRegistration(String registration) {
		// TODO Auto-generated method stub
		this.registration = registration;
	}

	public void setModel(String model) {
		// TODO Auto-generated method stub
		this.model = model;
		
	}

	public void setBrand(String brand) {
		// TODO Auto-generated method stub
		this.brand = brand;
	}

	public void setColour(String colour) {
		// TODO Auto-generated method stub
		this.colour = colour;
	}

	public void setMakeYear(String year) {
		// TODO Auto-generated method stub
		this.makeYear = year;
	}

	public String getMakeYear() {
		// TODO Auto-generated method stub
		return makeYear;
	}

	public String getBrand() {
		// TODO Auto-generated method stub
		return brand;
	}

	public String getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getColour() {
		// TODO Auto-generated method stub
		return colour;
	}

	public String getRegistration() {
		// TODO Auto-generated method stub
		return registration;
	}

}
