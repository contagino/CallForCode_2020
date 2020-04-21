package com.cfc.contagino.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CFC_CONTAGINO_LOCATION_EPIDEMIC")
public class LocationEpidemic {
	
	@EmbeddedId
	private LocationEpidemicPk id;
	
	@Column(name="cfc_instance_count")
	private int instanceCount;
	
	@Column(name="cfc_last_inc_reported")
	private Date lastReported;

	public LocationEpidemicPk getId() {
		return id;
	}

	public void setId(LocationEpidemicPk id) {
		this.id = id;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}

	public Date getLastReported() {
		return lastReported;
	}

	public void setLastReported(Date lastReported) {
		this.lastReported = lastReported;
	}
}
