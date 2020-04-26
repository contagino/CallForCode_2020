package com.cfc.contagino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationEpidemicPk implements Serializable{
	@Column(name="cfc_city_name")
	private String cityName;
	@Column(name="cfc_epidemic")
	private String epidemic;
	
	public LocationEpidemicPk(String cityName, String epidemic) {
		super();
		this.cityName = cityName;
		this.epidemic = epidemic;
	}

	public LocationEpidemicPk() {
		super();
	}


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getEpidemic() {
		return epidemic;
	}

	public void setEpidemic(String epidemic) {
		this.epidemic = epidemic;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((epidemic == null) ? 0 : epidemic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationEpidemicPk other = (LocationEpidemicPk) obj;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (epidemic == null) {
			if (other.epidemic != null)
				return false;
		} else if (!epidemic.equals(other.epidemic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationEpidemicPk [cityName=" + cityName + ", epidemic=" + epidemic + "]";
	}
	
	
}
