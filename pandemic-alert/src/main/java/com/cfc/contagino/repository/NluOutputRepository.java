package com.cfc.contagino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cfc.contagino.entity.NluOutPut;

public interface NluOutputRepository extends JpaRepository<NluOutPut, Long>{
	
	public List<NluOutPut> findByType(String type);
	
	public List<NluOutPut> findBypostIDIn(List<String> postIds);

	public List<NluOutPut> findByTypeAndProcessFlag(String type, String activeInd);

	public List<NluOutPut> findByProcessFlag(String activeInd);

}
