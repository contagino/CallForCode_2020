package com.cfc.contagino.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.cfc.contagino.entity.CityMapLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@EnableJpaAuditing
public class PandemicAlertConfiguration {
	
	@Value("${pandemic.social-search.service-url}")
	private String socialSearchServiceUrl;
	
	@Value("${pandemic.social-search.api-key1}")
	private String socialSearchApiKey;
	
	@Value("${pandemic.social-search.search-keyword}")
	private String socialSearchKeywords;
	
	//IBM NLU constants
	@Value("${pandemic.ibm-nlu.service-url}")
	private String ibmNluServiceUrl;
	
	@Value("${pandemic.ibm-nlu.api-key}")
	private String ibmNluApiKey;
	
	
	@Value("${diseases.key}")
	private String diseases;
	
	@Value("${diseases.tag}")
	private String tag;
	
	private Map<String, CityMapLocation> cityMap;
	
	private List<String>cityList;

	public String getSocialSearchServiceUrl() {
		return socialSearchServiceUrl;
	}

	public void setSocialSearchServiceUrl(String socialSearchServiceUrl) {
		this.socialSearchServiceUrl = socialSearchServiceUrl;
	}

	public String getSocialSearchApiKey() {
		return socialSearchApiKey;
	}

	public void setSocialSearchApiKey(String socialSearchApiKey) {
		this.socialSearchApiKey = socialSearchApiKey;
	}

	public String getSocialSearchKeywords() {
		return socialSearchKeywords;
	}

	public void setSocialSearchKeywords(String socialSearchKeywords) {
		this.socialSearchKeywords = socialSearchKeywords;
	}

	public String getIbmNluServiceUrl() {
		return ibmNluServiceUrl;
	}

	public void setIbmNluServiceUrl(String ibmNluServiceUrl) {
		this.ibmNluServiceUrl = ibmNluServiceUrl;
	}

	public String getIbmNluApiKey() {
		return ibmNluApiKey;
	}

	public void setIbmNluApiKey(String ibmNluApiKey) {
		this.ibmNluApiKey = ibmNluApiKey;
	}

	public Map<String, CityMapLocation> getCityMap() {
		return cityMap;
	}
	

	public Map<String,String> getDiseases() {
		Map<String,String> diseaseKeywordsMap = new HashMap<>();
//		COVID19-corona virus OR covid19|FEVER-fever
		String[] diseasesKeys = this.diseases.split("\\|");
		for(String key : diseasesKeys){
			String[] values = key.split("\\~");
			diseaseKeywordsMap.put(values[0], values[1]);
		}
		return diseaseKeywordsMap;
	}
	
	public String getAllDiseasesValues() {
		Collection<String> values = this.getDiseases().values();
		StringBuilder resultValues = new StringBuilder();
		for(String value : values){
			if(resultValues.length() == 0) {
				resultValues.append(value);
			}
			else {
				resultValues.append(" OR ").append(value);
			}
			
		}
		return resultValues.toString();
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setCityMap(Map<String, CityMapLocation> cityMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<CityMapLocation> cityLocation = mapper.readValue(new File("C:\\Users\\Administrator\\Desktop\\Call or Code\\pandemic-alert\\src\\main\\resources\\us_cities.json"), new TypeReference<List<CityMapLocation>>(){});
		Map<String, CityMapLocation> tmpMap=new HashMap<String, CityMapLocation>();
		List<String>cityLst=new ArrayList<String>();
		for(CityMapLocation city:cityLocation){
			tmpMap.put(city.getCITY().toUpperCase(), city);
			cityLst.add(city.getCITY().toUpperCase());
		}
		this.cityMap = tmpMap;
		setCityList(cityLst);
	}

	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
}
