package com.cfc.contagino.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfc.contagino.dao.PandemicAlertDao;
import com.cfc.contagino.entity.NluOutPut;
import com.cfc.contagino.entity.Post;
import com.cfc.contagino.exception.PandemicAlertException;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.natural_language_understanding.v1.model.Features;

@Component
public class PandemicAlertBoImpl implements PandemicAlertBo{
	
	@Autowired
	private PandemicAlertDao pandemicAlertDao;

	@Override
	public List<Post> saveSocialMediaPosts(List<Post> posts) throws PandemicAlertException {
		return pandemicAlertDao.saveSocialMediaPost(posts);
	}

	@Override
	public List<Post> getAllPosts() throws PandemicAlertException {
		return pandemicAlertDao.getAllPosts();
	}

	@Override
	public List<NluOutPut> analyzeText(String postId, String text) throws PandemicAlertException {
		List<NluOutPut>nluEntites=new ArrayList<NluOutPut>();
		IamAuthenticator authenticator = new IamAuthenticator("LVuxollv9MZQD56_Tx8Jvtttt8xV9iiN05tLTAsbOWNl");
		NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2019-07-12", authenticator);
		naturalLanguageUnderstanding.setServiceUrl("https://api.eu-gb.natural-language-understanding.watson.cloud.ibm.com/instances/b3736d00-1f58-487a-905b-701f723c3faa");

		EntitiesOptions entities= new EntitiesOptions.Builder()
		  .sentiment(true)
		  .build();

		Features features = new Features.Builder()
		  .entities(entities)
		  .build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
		  .text(text)
		  .features(features)
		  .build();

		AnalysisResults response = naturalLanguageUnderstanding
		  .analyze(parameters)
		  .execute()
		  .getResult();
		List<EntitiesResult> result=response.getEntities();
		
		NluOutPut nlu=new NluOutPut();
		for(EntitiesResult res:result){
			nlu=new NluOutPut();
			nlu.setPostId(postId);
			nlu.setType(res.getType());
			nlu.setRelevance(res.getRelevance());
			nlu.setConfidence(res.getRelevance());
			nlu.setText(res.getText());
			nluEntites.add(nlu);
		}
		return nluEntites;
	}

	@Override
	public List<Post> getAllNewPosts() throws PandemicAlertException {
		return pandemicAlertDao.getAllNewPosts();
	}

	@Override
	public List<NluOutPut> saveNluOutput(List<NluOutPut> masterList) throws PandemicAlertException {
		return pandemicAlertDao.saveNluOutput(masterList);
	}

	@Override
	public List<NluOutPut> findNluByEntityType(String type) throws PandemicAlertException {
		return pandemicAlertDao.findNluByEntityType(type);
	}
	
}
