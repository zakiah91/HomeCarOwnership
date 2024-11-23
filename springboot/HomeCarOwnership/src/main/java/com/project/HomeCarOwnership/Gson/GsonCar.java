package com.project.HomeCarOwnership.Gson;

public class GsonCar {
	private String platenumber;
	
	private String colour;
	
	private String type;
	
	public GsonCar(String platenumber, String colour, String type) {
		this.platenumber = platenumber;
		this.colour = colour;
		this.type = type;
	}

	public String getPlatenumber() {
		return platenumber;
	}

	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
