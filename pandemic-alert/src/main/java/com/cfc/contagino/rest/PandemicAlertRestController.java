package com.cfc.contagino.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cfc.contagino.bean.SocialMediaFeedResponse;
import com.cfc.contagino.bo.PandemicAlertBo;
import com.cfc.contagino.config.PandemicAlertConfiguration;
import com.cfc.contagino.entity.CityMapLocation;
import com.cfc.contagino.entity.CityMapOutput;
import com.cfc.contagino.entity.CitySymptomsOutput;
import com.cfc.contagino.entity.LocationEpidemic;
import com.cfc.contagino.entity.LocationEpidemicPk;
import com.cfc.contagino.entity.NluOutPut;
import com.cfc.contagino.entity.Post;
import com.cfc.contagino.entity.SocialMediaFeed;
import com.cfc.contagino.exception.PandemicAlertException;
import com.cfc.contagino.repository.LocationEpidemicRepository;
import com.cfc.contagino.repository.NluOutputRepository;
import com.cfc.contagino.uti.PatternMatcher;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/contagino/pandemic")
@ComponentScan(basePackages={"com.cfc.contagino"})
public class PandemicAlertRestController {
	
	@Autowired
	PandemicAlertBo pandemicAlertBo;
	
	@Autowired
	PandemicAlertConfiguration pandemicAlertConfiguration;
	
	@Autowired
	NluOutputRepository nluOutputRepository;
	
	@Autowired
	LocationEpidemicRepository locationEpidemicRepository;
	
	RestTemplate restTemplate=new RestTemplate();
	
	private HashMap<String, String>uriParams=new HashMap<String,String>();
	
	@GetMapping("/test")
	public String testService() throws JsonMappingException, JsonProcessingException{
		saveAllPost();
		return "Service is UP and Running....";
	}
	
	@GetMapping("/getSocialFeed")
	public SocialMediaFeedResponse getSocialMediaFeed(){
		String serviceUrl=pandemicAlertConfiguration.getSocialSearchServiceUrl();
		String apiKey=pandemicAlertConfiguration.getSocialSearchApiKey();
		String keywords=pandemicAlertConfiguration.getSocialSearchKeywords();
		uriParams.put("q", keywords);
		uriParams.put("key", apiKey);
		serviceUrl=serviceUrl+"?q="+keywords+"&key="+apiKey;
		System.out.println(serviceUrl);
		SocialMediaFeed socialMediaFeed=restTemplate.getForObject(serviceUrl, SocialMediaFeed.class);
		SocialMediaFeedResponse socialMediaFeedResponse=new SocialMediaFeedResponse();
		socialMediaFeedResponse.setPost(socialMediaFeed.getPosts());
		return socialMediaFeedResponse;
	}
	
	@GetMapping("/savePosts")
	public List<Post> saveAllPost(){
		List<Post>posts=new ArrayList<Post>();
		SocialMediaFeedResponse socialMediaFeedResponse=getSocialMediaFeed();
		posts=socialMediaFeedResponse.getPost();
		try {
			posts=pandemicAlertBo.saveSocialMediaPosts(posts);
		} catch (PandemicAlertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	@GetMapping("/getAllPosts")
	public List<Post>getAllPosts(){
		List<Post>posts=new ArrayList<Post>();
		try {
			posts=pandemicAlertBo.getAllPosts();
		} catch (PandemicAlertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	@GetMapping("/processPosts")
	public List<NluOutPut> processPost(){
		List<NluOutPut> masterList=new ArrayList<NluOutPut>();
		List<NluOutPut> deltaList=new ArrayList<NluOutPut>();
		try{
			List<Post>posts=pandemicAlertBo.getPostsByLanguage("en");
			for(Post post:posts){
				try{
					deltaList=pandemicAlertBo.analyzeText(post.getPostid(), post.getText());
					masterList.addAll(deltaList);
					deltaList=null;
				}catch(Exception e){}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return masterList;
	}
	
	@GetMapping("/saveNluOutput")
	public List<NluOutPut>saveNluOutput(){
		List<NluOutPut> allNluOutput=new ArrayList<NluOutPut>();
		try {
				List<NluOutPut>masterList=processPost();
				allNluOutput=pandemicAlertBo.saveNluOutput(masterList);
		} catch (PandemicAlertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allNluOutput;
	}
	
	@GetMapping("/saveLocationEpidemic")
	public Object saveLocationEpidemic(){
		Map<LocationEpidemicPk,Integer> locationEpidemicPkWiseCounts = new HashMap<>();
		try{
			/*
			 * First fetch all the distinct postIds having entity type  "Location" only.
			 */
			List<NluOutPut> nluLocationOutputList = pandemicAlertBo.findNluByEntityType("Location");
			List<String> postIds = new ArrayList<>();
			
			Map<String,String> postIdWiseLocation = new HashMap<>();
			nluLocationOutputList.forEach(output -> {
				postIds.add(output.getPostId());
				postIdWiseLocation.put(output.getPostId(), output.getText().trim().toUpperCase());
			});
			
			/*
			 * Now get the all NluLocationOutput objects for above postIds.
			 */
			System.out.println("pandemicAlertConfiguration.getSocialSearchKeywords():"+pandemicAlertConfiguration.getSocialSearchKeywords());
			System.out.println("getAllDiseasesValues:"+pandemicAlertConfiguration.getAllDiseasesValues());
			Set<String> uniquePostIds = new HashSet<>();
			Set<String> uniqueLocations = new HashSet<>();
			List<NluOutPut> nluOutPutDatas = nluOutputRepository.findAll();
			nluOutPutDatas.stream().filter(data -> (postIds.contains(data.getPostId()) && !data.getType().equalsIgnoreCase("Location")))
								   .filter(data -> (PatternMatcher.matcher(pandemicAlertConfiguration.getAllDiseasesValues(),data.getText())))
								   .forEach(data -> {
									   for(String key : pandemicAlertConfiguration.getDiseases().keySet()) {
										   if(PatternMatcher.matcher(pandemicAlertConfiguration.getAllDiseasesValues(),data.getText())  && PatternMatcher.matcher(pandemicAlertConfiguration.getTag(),data.getType())) { 
											   if(!uniquePostIds.add(key+":"+data.getPostId())) {
												   continue;
											   }
											   String location = postIdWiseLocation.get(data.getPostId());
											   uniqueLocations.add(location);
											   LocationEpidemicPk pk = new LocationEpidemicPk(location,key);
											   locationEpidemicPkWiseCounts.put(pk,  locationEpidemicPkWiseCounts.get(pk) == null ? 1 : locationEpidemicPkWiseCounts.get(pk) + 1);
											   break;
										   }
									    }
								   });
			System.out.println("New pandemic count location wise::"+locationEpidemicPkWiseCounts);
			
			
			/*
			 * Logic to fetch existing records from database and merge common data with them and fresh insert new records
			 */
			
			List<LocationEpidemic> existingLocationEpidemicRecrds = locationEpidemicRepository.findAllById(locationEpidemicPkWiseCounts.keySet());
			Map<LocationEpidemicPk,LocationEpidemic> existingRecords = existingLocationEpidemicRecrds.stream().collect(Collectors.toMap(data -> data.getId(), data -> data));
			List<LocationEpidemic> entities = new ArrayList<>();
			locationEpidemicPkWiseCounts.keySet().forEach(pk -> {
				LocationEpidemic object = existingRecords.get(pk);
				if(object != null) {
					object.setInstanceCount(object.getInstanceCount() + locationEpidemicPkWiseCounts.get(pk));
					object.setLastReported(new Date());
					entities.add(object);
					System.out.println("Old record updated for location:"+object.getId().getCityName()+" & pandemic:"+object.getId().getEpidemic());
				}
				else{
					LocationEpidemic newObject = new LocationEpidemic();
					newObject.setId(pk);
					newObject.setInstanceCount(locationEpidemicPkWiseCounts.get(pk));
					newObject.setLastReported(new Date());
					entities.add(newObject);
					System.out.println("New record updated for location:"+newObject.getId().getCityName()+" & pandemic:"+newObject.getId().getEpidemic());
				}
			});
			
			/*
			 * Finally save all data in the database.
			 */
			System.out.println("Entities:"+entities);
			locationEpidemicRepository.saveAll(entities);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return locationEpidemicRepository.findAll();
	}
	
	@GetMapping("/getCityMap")
	public List<CityMapOutput> getCityMapOutput() throws JsonParseException, JsonMappingException, IOException{
		List<LocationEpidemic> locationList=locationEpidemicRepository.findAll();
		List<CityMapOutput>cityMapOutput=new ArrayList<CityMapOutput>();
		CityMapOutput tmpCity=new CityMapOutput();
		String tmpCityName="";
		List<CitySymptomsOutput>list=new ArrayList<CitySymptomsOutput>();
		LocationEpidemicPk pk=null;
		CitySymptomsOutput symp=new CitySymptomsOutput();
		pandemicAlertConfiguration.setCityMap(null);
		List<String>cityList=pandemicAlertConfiguration.getCityList();
		Map<String,CityMapLocation>cityLocation=pandemicAlertConfiguration.getCityMap();
		for(LocationEpidemic ep:locationList){
			pk=ep.getId();
			tmpCity=new CityMapOutput();
			tmpCityName=pk.getCityName();
			list=new ArrayList<CitySymptomsOutput>();
			if(cityList.contains(tmpCityName)){
				tmpCity.setCity(tmpCityName);
				tmpCity.setLATITUDE(cityLocation.get(tmpCityName).getLATITUDE());
				tmpCity.setLONGITUDE(cityLocation.get(tmpCityName).getLONGITUDE());
				tmpCity.setOccurance(ep.getInstanceCount());
				symp.setSymptoms(pk.getEpidemic());
				list.add(symp);
				tmpCity.setSymptoms(list);
				cityMapOutput.add(tmpCity);
			}
		}
		
		return cityMapOutput;
	}
}
