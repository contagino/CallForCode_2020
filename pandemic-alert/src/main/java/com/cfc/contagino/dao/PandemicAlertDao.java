package com.cfc.contagino.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cfc.contagino.entity.NluOutPut;
import com.cfc.contagino.entity.Post;
import com.cfc.contagino.exception.PandemicAlertException;

@Component
public interface PandemicAlertDao {
	public List<Post> saveSocialMediaPost(List<Post> posts) throws PandemicAlertException;
	
	public List<Post> getAllPosts() throws PandemicAlertException;
	
	public List<Post> getPostsByLanguage(String ln) throws PandemicAlertException;
	
	public List<NluOutPut> saveNluOutput(List<NluOutPut> masterList) throws PandemicAlertException;
	
	public List<NluOutPut> findNluByEntityType(String type) throws PandemicAlertException;
}
