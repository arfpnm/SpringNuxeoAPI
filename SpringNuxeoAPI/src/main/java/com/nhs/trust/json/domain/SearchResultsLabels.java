package com.nhs.trust.json.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResultsLabels {


	/**
	 * 
	 "vrc:searchresultslabels":{
		"userstories":"User stories",
		"documents":"Documents",
		"heading":null,
		"htmlpages":"Pages",
		"websitesonlinelinks":
		"Websites and Online Links",
		"supportgroups":"Support and recovery groups"
	},
	"vrc:imagepath":null,
	"vrc:sitename":"ARCH Online recovery college",
	"vrc:logopath":"logopath",
	"vrc:url":null,
	 
	 
	 */
	
	
	private String userstories;
	private String documents;
	private String heading;
	private String htmlpages;
	private String websitesnlineLinks;
	private String supportGroups;
	public String getUserstories() {
		return userstories;
	}
	public void setUserstories(String userstories) {
		this.userstories = userstories;
	}
	public String getDocuments() {
		return documents;
	}
	public void setDocuments(String documents) {
		this.documents = documents;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getHtmlpages() {
		return htmlpages;
	}
	public void setHtmlpages(String htmlpages) {
		this.htmlpages = htmlpages;
	}
	public String getWebsitesnlineLinks() {
		return websitesnlineLinks;
	}
	public void setWebsitesnlineLinks(String websitesnlineLinks) {
		this.websitesnlineLinks = websitesnlineLinks;
	}
	public String getSupportGroups() {
		return supportGroups;
	}
	public void setSupportGroups(String supportGroups) {
		this.supportGroups = supportGroups;
	}

	

}
