package com.cfc.contagino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfc.contagino.entity.Post;
import com.cfc.contagino.exception.PandemicAlertException;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	public List<Post> findByLang(String ln) throws PandemicAlertException;

	public List<Post> findByProcessFlag(String activeInd) throws PandemicAlertException;
}
