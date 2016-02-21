package com.nhs.trust.json.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResourceProperties {

	private String uid;

	@JsonProperty("dc:description")
	private String description;
	
	@JsonProperty("vrc:subjects")
	private List<String> subjects;
	
	@JsonProperty("vrc:longitude")
	private String logitude;
	
	@JsonProperty("vrc:order")
	private String order;

	@JsonProperty("properties")
	private FooterRightCol footerRightCol;
	
	@JsonProperty("vrc:footermidcol")
	private FooterMidCol footerMidCol;
	
	@JsonProperty("vrc:footerleftcol")
	private FooterLeftCol footerLeftCol;

}