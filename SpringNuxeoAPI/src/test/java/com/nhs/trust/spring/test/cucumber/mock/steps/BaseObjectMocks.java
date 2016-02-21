package com.nhs.trust.spring.test.cucumber.mock.steps;
/**
 * @author arif.mohammed
 */
import java.util.ArrayList;
import java.util.List;

import com.nhs.trust.json.domain.Document;
import com.nhs.trust.json.domain.DocumentFolder;

public class BaseObjectMocks {
    
	public DocumentFolder mockLazyLoadDocumentFolder(){
		DocumentFolder documentFolder = new DocumentFolder();
		documentFolder.setTotalSize(2);
		Document document1 = new Document();
		Document document2 = new Document();
		document1.setTitle("NuxeoDocument1");
		document1.setType("wordDoc");
		document2.setTitle("NuxeoDocument2");
		document2.setType("PDFFile");
		Document document3 = new Document();
		document3.setTitle("NuxeoDocument3");
		document3.setType("Folder");
		Document document4 = new Document();
		document4.setTitle("NuxeoDocument4");
		document4.setType("File");
		
		List<Document> entries = new ArrayList<Document>();
		entries.add(document1);
		entries.add(document2);
		entries.add(document3);
		entries.add(document4);
		documentFolder.setEntries(entries);
		return documentFolder;
	}
	
	public DocumentFolder mockEagerLoadDocumentFolder(){
		DocumentFolder documentFolder = new DocumentFolder();
		documentFolder.setTotalSize(2);
		Document document1 = new Document();
		Document document2 = new Document();
		document1.setTitle("NuxeoDocument1");
		document1.setType("wordDoc");
		document2.setTitle("NuxeoDocument2");
		document2.setType("PDFFile");
		Document document3 = new Document();
		document3.setTitle("NuxeoDocument3");
		document3.setType("Folder");
		
		List<Document> entries = new ArrayList<Document>();
		entries.add(document1);
		entries.add(document2);
		entries.add(document3);
		documentFolder.setEntries(entries);
		
		List<DocumentFolder> subFolderFolderList = new ArrayList<DocumentFolder>();
		
		//The subfolder value will be retrieved only if the subfolder flag is true in the request params
		
		DocumentFolder subfolderFolder = new DocumentFolder();
		subfolderFolder.setTotalSize(1);
		document3.setTitle("NuxeoDocument3");
		document3.setType("Folder");
		String[] facets = {"folderish"};
		document3.setFacets(facets);
		subFolderFolderList.add(subfolderFolder);
		List<Document> subEntries = new ArrayList<Document>();
		subEntries.add(document3);
		subfolderFolder.setEntries(subEntries);
		documentFolder.setSubfolder(subFolderFolderList);
		return documentFolder;
	}
	
}
