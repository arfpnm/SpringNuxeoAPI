package com.nhs.trust.spring.test.cucumber.mock.steps;
/**
 * @author arif.mohammed
 */
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.trust.controller.NuxeoController;
import com.nhs.trust.json.domain.Document;
import com.nhs.trust.json.domain.DocumentFolder;
import com.nhs.trust.service.NuxeoService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration("/cucumber.xml")
@PropertySource("classpath*:test-resource.properties")
@WebAppConfiguration 
public class NuxeoDocumentMockSteps extends BaseObjectMocks{

	public String url;
	public List<Document> documents;
	public DocumentFolder documentFolder;
	@Mock
	public NuxeoService nuxeoService; 
	@InjectMocks
	public NuxeoController nuxeoController; 

	public MockMvc mockMvc; 
    @Autowired 
    public WebApplicationContext context; 
    @Value("${folder-under-section-url}")
    String urlFromProps;
    
    @Before 
    public void setUp() { 
    	 MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(nuxeoController).build();
    } 
	
	@Given("^a url to check primary level folders and files \"(.*?)\"$")
	public void a_url_to_check_primary_level_folders_and_files(String url) throws Throwable {
		this.url = url;
	}

	@When("^I send a folder name \"(.*?)\" to get all the documents from Nuxeo with isChildrenRequired as \"(.*?)\" and checkMetaDataForChildrenRequired as \"(.*?)\"$")
	public void i_send_a_folder_name_to_get_all_the_documents_from_Nuxeo_with_isChildrenRequired_as_and_checkMetaDataForChildrenRequired_as(String folderName, String isChildrenRequired, String checkMetaDataForChildrenRequired) throws Throwable {
		DocumentFolder documentFolderResponse = mockLazyLoadDocumentFolder();
		when(nuxeoService.findDocuments(folderName)).thenReturn(documentFolderResponse);

		MvcResult mvcResult = mockMvc.perform(get("/document-request/nhstrust/"+folderName+"/"+isChildrenRequired+"/"+ checkMetaDataForChildrenRequired))
		         .andExpect(status().isOk()).andReturn();
 		
		ObjectMapper mapper = new ObjectMapper();
		documentFolder = mapper.readValue(mvcResult.getResponse().getContentAsString(), DocumentFolder.class);
		documents = documentFolder.getEntries();
		verify(nuxeoService, times(1)).findDocuments(folderName);
		verifyNoMoreInteractions(nuxeoService);
	}
	
	@Then("^the response should contain the document with title \"(.*?)\"$")
	public void the_response_should_contain_the_document_with_title(String documentTitle) throws Throwable {
		 assertEquals(documents.get(0).getTitle(), documentTitle);
		 assertEquals(4, documents.size());
	}
	
	@Then("^the \"(.*?)\" must be empty$")
	public void the_must_be_empty(String subfolder) throws Throwable {
		subfolder = documentFolder.getSubfolder() == null ? null : (documentFolder.getSubfolder().size() > 0 ? documentFolder.getSubfolder().toString() : null);
		assertEquals(null, subfolder);
	}
	

}
