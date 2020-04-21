package com.cfc.contagino.entity;

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
"requestid",
"http_code",
"network",
"query_type",
"limit",
"page"
})
public class Meta {

@JsonProperty("requestid")
private String requestid;
@JsonProperty("http_code")
private Integer httpCode;
@JsonProperty("network")
private String network;
@JsonProperty("query_type")
private String queryType;
@JsonProperty("limit")
private Integer limit;
@JsonProperty("page")
private Integer page;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("requestid")
public String getRequestid() {
return requestid;
}

@JsonProperty("requestid")
public void setRequestid(String requestid) {
this.requestid = requestid;
}

@JsonProperty("http_code")
public Integer getHttpCode() {
return httpCode;
}

@JsonProperty("http_code")
public void setHttpCode(Integer httpCode) {
this.httpCode = httpCode;
}

@JsonProperty("network")
public String getNetwork() {
return network;
}

@JsonProperty("network")
public void setNetwork(String network) {
this.network = network;
}

@JsonProperty("query_type")
public String getQueryType() {
return queryType;
}

@JsonProperty("query_type")
public void setQueryType(String queryType) {
this.queryType = queryType;
}

@JsonProperty("limit")
public Integer getLimit() {
return limit;
}

@JsonProperty("limit")
public void setLimit(Integer limit) {
this.limit = limit;
}

@JsonProperty("page")
public Integer getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Integer page) {
this.page = page;
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
