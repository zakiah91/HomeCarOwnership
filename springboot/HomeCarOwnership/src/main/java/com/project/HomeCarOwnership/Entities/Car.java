package com.project.HomeCarOwnership.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {

	@Id
	private String platenumber;
	
	private String colour;
	
	private String type;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="housenumber")
	private House house;
	
	public Car() {
		
	}
	
	public Car(String platenumber, String colour, String type, House house) {
		this.platenumber = platenumber;
		this.colour = colour;
		this.type = type;
		this.house = house;
	}
	
	public void setPlateNumber(String platenumber){
		this.platenumber = platenumber;
	}
	
	public String getPlateNumber() {
		return platenumber;
	}
	
	
	public void setColour(String colour) {
		 this.colour = colour;
	}
	
	
	public String getColour() {
		return colour; 
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getType() {
		return type;
	}
	
	public House getHouse() {
		return this.house;
	}
	
	public void setHouse(House house) {
		this.house = house;
	}
	
	
}
