package com.nhs.trust.spring.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nhs.trust.json.domain.DocumentFolder;
import com.nhs.trust.service.NuxeoVrcService;
import com.nhs.trust.service.NuxeoVrcServiceImpl;

@Ignore
/** Not used - delete this file as its just used for initial quick test **/
public class SpringTest {

	@Test
	public void callRest() throws IOException{
		RestTemplate restTemplate = new RestTemplate();

		NuxeoVrcService nuxeoVrcService = new NuxeoVrcServiceImpl();
		//String url = "http://localhost:8088/SpringHSTProject/hello";
		//Rest Call for Nuxeo
		//String url = "http://127.0.0.1:18080//nuxeo/api/v1/path/default-domain/sections/nhstrust/resources/images/@children";
		//String url = "http://127.0.0.1:18080/nuxeo/api/v1/doc#!/id/14d33e6f-5101-4f40-aee1-9f63de93d086";
		//String url = "http://127.0.0.1:18080/nuxeo/api/v1/id/{a7bc9e7c-6206-4cba-bbe5-1528f0e5d4fb}";
		//String url = "http://127.0.0.1:18080/nuxeo/site/api/v1/id/9fbe030c-69a1-47c7-a8af-c187858a40a4";
		//String url = "http://127.0.0.1:18080//nuxeo/api/v1/path/default-domain/sections/nhstrust/resources/images/@children";
		//String url = "http://127.0.0.1:48080/nuxeo/api/v1/id/14d33e6f-5101-4f40-aee1-9f63de93d086";
		
		//String url = "https://dev.edrm.tewv.clients.wtg.co.uk/nuxeo/document-request/vrc/true/true";
		//String url ="https://dev.edrm.tewv.clients.wtg.co.uk/nuxeo/api/v1/path/default-domain/sections/vrc/@children?pageSize=10000";
		String url="http://10.100.8.177:18080/nuxeo/api/v1/path/default-domain/sections/VRC/@children?pageSize=10000";
		//String url = "http://127.0.0.1:18080//nuxeo/api/v1/id/69db0c1a-5259-4387-b633-056d9eaf331f";
		//String url ="http://127.0.0.1:18080/nuxeo/site/api/v1/id/0897ae96-a492-4fcc-ab51-77a985d616a9";
		
		//DocumentFolder documentFolder = nuxeoVrcService.findVrcDocuments("VRC");
		
		
		String user = "Administrator";
		String password ="Qazxsw21!";
		
//		 ResponseEntity<String> response = restTemplate.exchange
//		 (url, HttpMethod.GET, new HttpEntity<String>(createHeaders(user, password)), String.class);
//		 String body = (String) response.getBody();
//		ObjectMapper mapper = new ObjectMapper();
//		DocumentFolder folder = mapper.readValue(body, DocumentFolder.class);
				// ResponseEntity<String> response = restTemplate.exchange
				 //(url, HttpMethod.GET, new HttpEntity<String>(createHeaders(user, password)), String.class);
		 url="/document-request/VRC";
		 ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(createHeaders(user, password)), String.class);
		
		ResponseEntity<DocumentFolder> documentFolder = restTemplate.getForEntity(url, DocumentFolder.class) ;
				 //DocumentFolder documentFolder =  response.getBody();
		//System.out.println(response);
		
		//DocumentFolder documentFolder=new ObjectMapper().readValue(response.getBody(), DocumentFolder.class);
		
		System.out.println(documentFolder.getBody().getEntries().size());

//		 JsonNode tree = mapper.readTree(body);
//		    Iterator<JsonNode> iter = tree.path("data").elements();
//		    while (iter.hasNext()){
//		        JsonNode node = iter.next();
//		       // FileContents fileContents = mapper.readValue(node.path("file:content"), FileContents.class);
//		        //System.out.println("fileContents.getFileData():" +fileContents.getFileData());
//		    }

	}

	HttpHeaders createHeaders( final String username, final String password ){
		   return new HttpHeaders(){
			private static final long serialVersionUID = 1L;
			{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		         setContentType(MediaType.APPLICATION_JSON);
		         set("X-NXenrichers.document", "thumbnail");
		         set("X-NXDocumentProperties", "*");
		      }
		   };
		}
	
	
	/** - Working CODE **/
	public void callRest1(){
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8088/SpringNuxeoInterface/hello";
		//String url = "http://localhost:18080/nuxeo/api/v1/path//default-domain/sections/arfpnm/SecondFile";
		ResponseEntity<String>  response =
				restTemplate.getForEntity(url, String.class);
		System.out.println("responseMessage from sprinmg rest call - title: ");
	}

	/***/

	@Test
	public void creatingHospitalsAndLocationTest(){
		String[] hospitalArray = {"NoHospitalLocan1", "Victoria/Chelmsford Hospital", "Victoria/St Henry Hospital", "County Durham and Darlington/Auckland park hosptital", "Victoria/Warwick Hospital", "County Durham and Darlington/Bowes Lyon Unit", "County Durham and Darlington/New park hosptital", "Unknown Location/Unknown Hospital", "County Berkshire/CEDAR Surgery hosptital", "Essex/South End NHS Hospital", "County Berkshire/Thames Valley hosptital", "Essex/Basildon Hospital", "NoHospitalLocan2"};
		List<String> hospitals =hospitalArray != null ?  Arrays.asList(hospitalArray) : null;		
		populateLocationAndHospitals(hospitals);
	}
	
	
	
	private Map<String, List<String>> populateLocationAndHospitals(List<String> hospitalsWithLocation){
		if(hospitalsWithLocation == null || hospitalsWithLocation.isEmpty()){
			return null;
		}
		Map<String, List<String>> hospitalsWithLocationMap = new HashMap<String, List<String>> ();
		List<String> hospitals = new ArrayList<String>();
		String location = null;
		String hospital = null;
		for(String hospitalWithLocation : hospitalsWithLocation){
			String[] locationAndHospital = hospitalWithLocation.split("/");
			if(locationAndHospital != null && locationAndHospital.length > 1){
				location = locationAndHospital[0];
				hospital = locationAndHospital[1];
				if(hospitalsWithLocationMap.containsKey(location)){
					hospitals = hospitalsWithLocationMap.get(location);
					hospitals.add(hospital);
					hospitalsWithLocationMap.put(location, hospitals);
				}
				else{
					hospitals = new ArrayList<String>();
					hospitals.add(hospital);
					hospitalsWithLocationMap.put(location, hospitals);
				}
			}
			else{
				location = locationAndHospital[0];
				hospitalsWithLocationMap.put(location, new ArrayList<String>());
			}
		}
		for(Entry<String, List<String>> entry :  hospitalsWithLocationMap.entrySet()){
			System.out.println("Key is : "+entry.getKey()+ " And the Value is : "+entry.getValue());
		}
		return hospitalsWithLocationMap;
	}

}
