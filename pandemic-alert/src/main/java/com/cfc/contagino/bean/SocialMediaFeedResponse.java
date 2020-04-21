package com.cfc.contagino.bean;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.cfc.contagino.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialMediaFeedResponse {
	
	@JsonProperty(value="post", required=true)
	private List<Post> post=new ArrayList<Post>();

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "SocialMediaFeedResponse [post=" + post + "]";
	}

	public SocialMediaFeedResponse() {
		super();
	}
	
}
