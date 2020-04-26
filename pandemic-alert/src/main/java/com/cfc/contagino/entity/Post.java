package com.cfc.contagino.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "network", "posted", "postid", "text", "lang", "type", "sentiment", "image", "url", "user",
		"urls" })
@Entity
@Table(name = "CFC_CONTAGINO_POST")
@JsonIgnoreProperties({ "processFlag" })
public class Post {

	@JsonProperty("network")
	@Column(name = "CFC_NETWORK")
	private String network;
//@Column(name="CFC_POST_DATE")
	@Transient
	@JsonProperty("posted")
	private String posted;
	@Id
	@Column(name = "CFC_POST_ID")
	@JsonProperty("postid")
	private String postid;
	@Column(name = "CFC_POST_TEXT")
	@JsonProperty("text")
	private String text;
	@Column(name = "CFC_LANGUAGE")
	@JsonProperty("lang")
	private String lang;

	@Column(name = "CFC_PROCESS_FLAG", length = 1)
	private String processFlag; // possible values N - New , C - Converted, P - Processed

	@Transient
	@JsonProperty("type")
	private String type;
	@Transient
	@JsonProperty("sentiment")
	private String sentiment;
	@Transient
	@JsonProperty("image")
	private String image;
	@Transient
	@JsonProperty("url")
	private String url;
	@Transient
	@JsonProperty("user")
	private User user;
	@Transient
	@JsonProperty("urls")
	private List<Url> urls = null;
	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("network")
	public String getNetwork() {
		return network;
	}

	@JsonProperty("network")
	public void setNetwork(String network) {
		this.network = network;
	}

	@JsonProperty("posted")
	public String getPosted() {
		return posted;
	}

	@JsonProperty("posted")
	public void setPosted(String posted) {
		this.posted = posted;
	}

	@JsonProperty("postid")
	public String getPostid() {
		return postid;
	}

	@JsonProperty("postid")
	public void setPostid(String postid) {
		this.postid = postid;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("lang")
	public String getLang() {
		return lang;
	}

	@JsonProperty("lang")
	public void setLang(String lang) {
		this.lang = lang;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("sentiment")
	public String getSentiment() {
		return sentiment;
	}

	@JsonProperty("sentiment")
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("user")
	public User getUser() {
		return user;
	}

	@JsonProperty("user")
	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty("urls")
	public List<Url> getUrls() {
		return urls;
	}

	@JsonProperty("urls")
	public void setUrls(List<Url> urls) {
		this.urls = urls;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	@PrePersist
	public void prePersist() {
		if (this.processFlag == null)
			this.processFlag = "N";
	}

}