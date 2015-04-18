package gatech.cs6440.project;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;





//these are for the database connection
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.net.*;
import java.sql.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class DataAccess {

	private static ArrayList<Observation> myObservations;
	private static ArrayList<Problem> myProblems;
	private static ArrayList<Allergy> myAllergies;
	
	private final static String patientURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/";
	private final static String problemURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Condition?subject:Patient=";
	private final static String observationURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Observation?subject:Patient=";
	private final static String jsonFormatURL = "&_format=json";
	private final static String xmlFormatURL = "?_format=xml";
	
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/eprescriptions";
	
	//these lines need to match your local mysql settings
	private String USER = "userme";
	private String PASS = "passwordme$2";
	
	public DataAccess()
	{
		
	}
	public DataAccess(String username, String pwd){
		USER = username;
		PASS = pwd;
	}
	
	/*
	 * Takes the PatientID and searches for it using the FHIR API
	 * Returns the xml returned by the request
	 */
	private static String makeRequest(String url, String pid, String formatURL) throws Exception{
		String httpsURL = url + pid + formatURL;
		URL myURL = new URL(httpsURL);
		HttpURLConnection con = (HttpURLConnection) myURL.openConnection();
		InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
          sb.append(inputLine);
        }
	    in.close();
	    isr.close();
	    ins.close();
	    String rtnString = sb.toString();
	    sb.delete(0, sb.length());
	    
	    return rtnString;
	}
	
	/*
	 * Takes the XML response from the FHIR API and parses it for the necessary information.
	 */
	public Patient getPatient(String patientID) throws Exception{
		String xmlStr = makeRequest(patientURL, patientID, xmlFormatURL);
		
		InputStream inStream = new ByteArrayInputStream((xmlStr).getBytes("utf-8"));
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	    XPathFactory xpf = XPathFactory.newInstance();
	    XPath xpath = xpf.newXPath(); 
	    
	  
	    XPathExpression family = xpath.compile( "/Patient/name/family/@value" );
	    XPathExpression given= xpath.compile( "/Patient/name/given/@value" );
	    NodeList familyNodeList = (NodeList) family.evaluate(doc, XPathConstants.NODESET);
	    NodeList givenNodeList = (NodeList) given.evaluate(doc, XPathConstants.NODESET);
	    
	    Patient pat = new Patient();
	    //what is this function doing? 
	    	pat.setFirstName(givenNodeList.item(0).getFirstChild().getNodeValue() );
	    	pat.setLastName(familyNodeList.item(0).getFirstChild().getNodeValue() );
	    	pat.setIsPatient(true); 
	    	
	    	return pat;
	}
	
	public static Patient getPatientInfo (String patientID) throws Exception{
		String xmlStr = makeRequest(patientURL, patientID, xmlFormatURL);
		
		InputStream inStream = new ByteArrayInputStream((xmlStr).getBytes("utf-8"));
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	    XPathFactory xpf = XPathFactory.newInstance();
	    XPath xpath = xpf.newXPath(); 
	    
	  
	    XPathExpression family = xpath.compile( "/Patient/name/family/@value" );
	    XPathExpression given= xpath.compile( "/Patient/name/given/@value" );
	    NodeList familyNodeList = (NodeList) family.evaluate(doc, XPathConstants.NODESET);
	    NodeList givenNodeList = (NodeList) given.evaluate(doc, XPathConstants.NODESET);
	    
	    Patient pat = new Patient();
	    //what is this function doing? 
	    	pat.setFirstName(givenNodeList.item(0).getFirstChild().getNodeValue() );
	    	pat.setFirstName(familyNodeList.item(0).getFirstChild().getNodeValue() );
	    	pat.setIsPatient(true); 
	    	
	    	return pat;
	}
	
	// Currently not used
	public String getPatientList() throws Exception{
		String sURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient?_count=10&_format=json";
		URL myURL = new URL(sURL);
		HttpURLConnection con = (HttpURLConnection) myURL.openConnection();
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			sb.append(inputLine);
		}
		return sb.toString();
	
	}
	
	public static ArrayList<Observation> getObservationInfo(String patientID) throws Exception{
		
		String xmlStr = makeRequest(observationURL, patientID, jsonFormatURL);
		myObservations = new ArrayList<Observation> ();
		
		InputStream inStream = new ByteArrayInputStream((xmlStr.getBytes("utf-8")));
		InputStreamReader reader = new InputStreamReader(inStream);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		JSONArray lang= (JSONArray) jsonObject.get("entry");
		Iterator<?> i2 = lang.iterator();
				 
		while (i2.hasNext()) {
			Observation ob = new Observation();
		    JSONObject innerObj = (JSONObject) i2.next();
		  	JSONObject content = (JSONObject) innerObj.get("content");
		  	JSONObject name = (JSONObject) content.get("name");
		  	JSONArray coding = (JSONArray) name.get("coding");
		  	Iterator<?> iCoding = coding.iterator();
		  	
		  	while (iCoding.hasNext()){
		  		JSONObject jCoding = (JSONObject) iCoding.next();
		  		if (jCoding.get("system").toString().equals("http://loinc.org")){
		  			String display = jCoding.get("display").toString();
				  	ob.setObservationName(display);
		  		}
		  	}
		  	
		  	String date = content.get("appliesDateTime").toString(); //TODO: Format to look pretty
		  	ob.setDate(date);
		  	
		  	JSONObject valueQuantity = (JSONObject) content.get("valueQuantity");
		  	if (valueQuantity != null){
			  	Object value = valueQuantity.get("value");
			  	Object units = valueQuantity.get("units");
			  	if (units == null){
			  		units = "";
			  	}
			  	if(value == null){
			  		value = "";
			  	}
			  	ob.setValue(value + " " + units.toString());
		  	}
		  	myObservations.add(ob);
		} //end while i2
		
		return myObservations;
	}
	
	public ArrayList<Problem> getProblemInfo(String patientID) throws Exception{
		String xmlStr = makeRequest(problemURL, patientID, jsonFormatURL);
		myProblems = new ArrayList<Problem>();
		
		InputStream inStream = new ByteArrayInputStream((xmlStr.getBytes("utf-8")));
		InputStreamReader reader = new InputStreamReader(inStream);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		JSONArray lang= (JSONArray) jsonObject.get("entry");
		Iterator<?> i2 = lang.iterator();
		
		while(i2.hasNext()){
			Problem prob = new Problem();
			JSONObject innerObj = (JSONObject) i2.next();
		  	JSONObject content = (JSONObject) innerObj.get("content");
		  	
		  	String status = content.get("status").toString();
		  	prob.setStatus(status);
		  	String date = content.get("onsetDate").toString();
		  	prob.setOnSetDate(date);
		  	
		  	JSONObject code = (JSONObject) content.get("code");
		  	JSONArray coding = (JSONArray) code.get("coding");
		  	Iterator<?> iCoding = coding.iterator();
		  	
		  	while (iCoding.hasNext()){
		  		JSONObject jCoding = (JSONObject) iCoding.next();
		  		if (jCoding.get("system").toString().equals("http://hl7.org/fhir/sid/icd-9")){
		  			String display = jCoding.get("display").toString();
				  	prob.setDiagnosis(display);
		  		}
		  	}
		  	myProblems.add(prob);
		} //end while i2
		
		return myProblems;
	}
	
}
