package com.nhs.trust.json.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FooterRightCol {

	@JsonProperty("columntitle")
	private String columnTitle;
	@JsonProperty("footerimage")
	private Object footerImage;
	
	public String getColumnTitle() {
		return columnTitle;
	}
	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}
	public Object getFooterImage() {
		return footerImage;
	}
	public void setFooterImage(Object footerImage) {
		this.footerImage = footerImage;
	}
	
}
