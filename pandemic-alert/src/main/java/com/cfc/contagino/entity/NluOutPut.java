package com.cfc.contagino.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="CFC_CONTAGINO_NLU_OUTPUT")
//@JsonIgnoreProperties(ignoreUnknown=true)
public class NluOutPut {
	@Id
	@Column(name="CFC_OUTPUT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("nluId")
	private long nluId;
	
	@Column(name="CFC_ENTITY_TYPE")
	@JsonProperty("type")
	private String type;
	
	@Column(name="CFC_ENTITY_TEXT")
	@JsonProperty("text")
	private String text;
	
	@Column(name="CFC_RELEVANCE")
	@JsonProperty("relevance")
	private double relevance;
	
	@Column(name="CFC_CONFIDENCE")
	@JsonProperty("confidence")
	private double confidence;
	
	@Column(name="CFC_POST_ID")
	@JsonProperty("postID")
	private String postID;
	
	@JsonProperty("nluId")
	public long getNluId() {
		return nluId;
	}
	@JsonProperty("nluId")
	public void setNluId(long nluId) {
		this.nluId = nluId;
	}
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("text")
	public String getText() {
		return text;
	}
	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}
	
	@JsonProperty("relevance")
	public double getRelevance() {
		return relevance;
	}
	@JsonProperty("relevance")
	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}
	
	@JsonProperty("confidence")
	public double getConfidence() {
		return confidence;
	}
	@JsonProperty("confidence")
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	@JsonProperty("postID")
	public String getPostId() {
		return postID;
	}
	@JsonProperty("postId")
	public void setPostId(String postId) {
		this.postID = postId;
	}
}
