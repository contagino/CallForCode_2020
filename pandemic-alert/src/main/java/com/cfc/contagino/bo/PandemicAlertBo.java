package com.cfc.contagino.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cfc.contagino.entity.NluOutPut;
import com.cfc.contagino.entity.Post;
import com.cfc.contagino.exception.PandemicAlertException;

@Component
public interface PandemicAlertBo {
	
	public List<Post> saveSocialMediaPosts(List<Post> posts) throws PandemicAlertException;
	
	public List<Post> getAllPosts() throws PandemicAlertException;
	
	public List<NluOutPut> analyzeText(String postId,String text) throws PandemicAlertException;
	
	public List<Post> getAllNewPosts() throws PandemicAlertException;
	
	public List<NluOutPut> saveNluOutput(List<NluOutPut> masterList) throws PandemicAlertException;
	
	public List<NluOutPut> findNluByEntityType(String type)throws PandemicAlertException;

}
