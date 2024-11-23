package com.project.HomeCarOwnership.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class House {
	
	@Id
	private String housenumber;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ownerid")
	private Owner owner;
	
	
	public House() {
		
	}
	
	public House(String housenumber, Owner owner) {
		this.housenumber = housenumber;
		this.owner = owner;
	}
	
	public void setHouseNumber(String housenumber) {
		this.housenumber = housenumber;
	}
	
	public String getHouseNumber() {
		return housenumber;
	}
	
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Owner getOwner() {
		return owner;
	}

	

}
