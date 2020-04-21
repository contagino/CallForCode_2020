package com.cfc.contagino.entity;

import java.util.List;

public class CityMapOutput {
	String city;
	String LATITUDE;
	String LONGITUDE;
	int occurance;
	private List<CitySymptomsOutput>symptoms;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public List<CitySymptomsOutput> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<CitySymptomsOutput> symptoms) {
		this.symptoms = symptoms;
	}
	public int getOccurance() {
		return occurance;
	}
	public void setOccurance(int occurance) {
		this.occurance = occurance;
	}
	
}
