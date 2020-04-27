package com.cfc.contagino.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfc.contagino.constant.PandemicAlertConstant;
import com.cfc.contagino.entity.NluOutPut;
import com.cfc.contagino.entity.Post;
import com.cfc.contagino.exception.PandemicAlertException;
import com.cfc.contagino.repository.NluOutputRepository;
import com.cfc.contagino.repository.PostRepository;

@Component
public class PandemicAlertDaoImpl implements PandemicAlertDao{
	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private NluOutputRepository nluOutputRepository;

	@Override
	public List<Post> saveSocialMediaPost(List<Post> posts) throws PandemicAlertException {
		return postRepository.saveAll(posts);
	}

	@Override
	public List<Post> getAllPosts() throws PandemicAlertException {
		return postRepository.findAll();
	}

	@Override
	public List<Post> getAllNewPosts() throws PandemicAlertException {
		return postRepository.findByProcessFlag(PandemicAlertConstant.ACTIVE_IND);
	}

	@Override
	public List<NluOutPut> saveNluOutput(List<NluOutPut> masterList) throws PandemicAlertException {
		return nluOutputRepository.saveAll(masterList);
	}

	@Override
	public List<NluOutPut> findNluByEntityType(String type) throws PandemicAlertException {
		return nluOutputRepository.findByTypeAndProcessFlag(type,PandemicAlertConstant.ACTIVE_IND);
	}

}
