package com.project.HomeCarOwnership.Gson;

import java.util.ArrayList;
import java.util.List;

import com.project.HomeCarOwnership.Entities.Car;
import com.project.HomeCarOwnership.Entities.House;
import com.project.HomeCarOwnership.Entities.Owner;

public class Profile {
	
	private String ownerid;
	private String name;
	private int age;
	private String housenumber;
	private List<GsonCar> gsonCar;
	
	
	
	public Profile() {
	}


	public void setOwnerDetails(String ownerid, String name, int age) {
		this.ownerid = ownerid;
		this.name = name;
		this.age = age;
	}
	
	
	public void setCarDetails(List<Car> car) {
		
		List<GsonCar> gsonCarList = new ArrayList<GsonCar>();
		
		GsonCar gsonCar_;
		for(int i=0; i<car.size();i++) {
			gsonCar_ = new GsonCar(car.get(i).getPlateNumber(),car.get(i).getColour(), car.get(i).getType());
			gsonCarList.add(gsonCar_);
		}
		this.gsonCar = gsonCarList;
		
	}

	public String getOwnerid() {
		return ownerid;
	}



	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getHousenumber() {
		return housenumber;
	}



	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}



	public List<GsonCar> getCar() {
		return gsonCar;
	}



	public void setCar(List<GsonCar> car) {
		this.gsonCar = car;
	}
	

	


}
