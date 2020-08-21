import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.jackrabbit.commons.json.JsonParser;
import org.apache.lucene.util.IOUtils;
import org.apache.sling.commons.json.JSONObject;
import org.json.simple.JSONArray;




public class damserviceImpl{

    public static void getJson() {
		
    	JSONArray jArray = new JSONArray();
    	
		List<Employee> liEmployees = new ArrayList<Employee>();
		
		Employee employee = null ;
		
		String response = null;
		
		// TODO Auto-generated method stub
		JSONObject json = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet getRequest = new HttpGet("https://api.mockaroo.com/api/ccaecc90?count=1000&key=33378a00");
	

		getRequest.addHeader("accept", "application/json");
		
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(getRequest);
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpEntity != null) {
				response = EntityUtils.toString(httpEntity);
				
			}
	////	System.out.println(response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	
	    	ObjectMapper objMapper = new ObjectMapper();
    		
    		JsonNode jNode=objMapper.readTree(response);
    		
    		String payLoad = jNode.toString();
    		
    		Employee[] empArray =objMapper.readValue(payLoad,Employee[].class);
	    	
	    	System.out.println(empArray[0]);
	    	
	    	org.json.simple.JSONObject jObj = new org.json.simple.JSONObject();
	    	
	    	
	    	for(int i = 0; i<empArray.length ; i++) {
	    		jObj.put("name", empArray[i].getName());
	    		jObj.put("title", empArray[i].getTitle());
	    		jObj.put("designation", empArray[i].getDesignation());
	    		jObj.put("country", empArray[i].getCountry());
	    		jObj.put("image", empArray[i].getImage());
	    		jObj.put("email", empArray[i].getEmail());
	    	
	    		jArray.add(jObj);
	    	}
	    }catch(Exception ioe) {
	    	ioe.printStackTrace();
	    }  
	    try(BufferedWriter fwFile = new BufferedWriter(new FileWriter("employee.json"))){
	    	
	    	fwFile.write(jArray.toJSONString());
	    	
	    }catch(IOException ioe) {
	    	ioe.printStackTrace();
	    }
	      
	  /*  try(BufferedReader brRead = new BufferedReader(new FileReader("employee.json"))){
	    	
	    	String input = brRead.readLine();
	    	
	    	while(input != null) {
	    		System.out.println(input);
	    	}
	    	
	    }catch(IOException e ) {
	    	
	    }*/
    }

 

	public static void main(String[] args) {
		getJson();
	}

}

