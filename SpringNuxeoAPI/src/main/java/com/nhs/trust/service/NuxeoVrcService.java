package com.nhs.trust.service;

import java.io.IOException;

import com.nhs.trust.json.domain.Document;
import com.nhs.trust.json.domain.DocumentFolder;

	public interface NuxeoVrcService {
		
	public DocumentFolder findVrcDocuments(String folderName) 
			throws IOException;
	public DocumentFolder findVrcResources(String folderName) 
			throws IOException;
	Document findResourceById(String id, boolean isResourceDocument)
			throws IOException;
	String findWebCommonAttibutesDocumentById(String id) throws IOException;

}
