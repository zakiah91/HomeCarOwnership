package com.project.HomeCarOwnership.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Owner {
	
	@Id
	private String ownerid;
	
	private int age;
	
	private String name;
	
	public Owner() {
		
	}
	
	public Owner(String ownerid, int age, String name) {
		this.ownerid = ownerid;
		this.age = age;
		this.name = name;
	}
	
	public String getOwnerId() {
		return ownerid;
	}
	
	public void setOwnerId(String ownerid) {
		this.ownerid = ownerid;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
