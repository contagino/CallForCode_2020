package com.cfc.contagino.entity;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ID",
"ID_STATE",
"CITY",
"COUNTY",
"LATITUDE",
"LONGITUDE"
})
public class CityMapLocation {

@JsonProperty("ID")
private String ID;
@JsonProperty("ID_STATE")
private String ID_STATE;
@JsonProperty("CITY")
private String CITY;
@JsonProperty("COUNTY")
private String COUNTY;
@JsonProperty("LATITUDE")
private String LATITUDE;
@JsonProperty("LONGITUDE")
private String LONGITUDE;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("ID")
public String getID() {
return ID;
}

@JsonProperty("ID")
public void setID(String iD) {
this.ID = iD;
}

@JsonProperty("ID_STATE")
public String getIDSTATE() {
return ID_STATE;
}

@JsonProperty("ID_STATE")
public void setIDSTATE(String iDSTATE) {
this.ID_STATE = iDSTATE;
}

@JsonProperty("CITY")
public String getCITY() {
return CITY;
}

@JsonProperty("CITY")
public void setCITY(String cITY) {
this.CITY = cITY;
}

@JsonProperty("COUNTY")
public String getCOUNTY() {
return COUNTY;
}

@JsonProperty("COUNTY")
public void setCOUNTY(String cOUNTY) {
this.COUNTY = cOUNTY;
}

@JsonProperty("LATITUDE")
public String getLATITUDE() {
return LATITUDE;
}

@JsonProperty("LATITUDE")
public void setLATITUDE(String lATITUDE) {
this.LATITUDE = lATITUDE;
}

@JsonProperty("LONGITUDE")
public String getLONGITUDE() {
return LONGITUDE;
}

@JsonProperty("LONGITUDE")
public void setLONGITUDE(String lONGITUDE) {
this.LONGITUDE = lONGITUDE;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
