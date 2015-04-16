package com.eprescription.web.app;

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
/////////////////////////////////////////



import org.json.simple.*;
import org.json.simple.parser.*;

public class Patient {
	
	private static String patientID;
	private static String firstName;
	private static String lastName;
	private static String phoneNumber;
	private static String street;
	private static String city;
	private static int zipCode;
	private static String state;
	private static String sex;
	private static String dateOfBirth;
	private static boolean isPatient;
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
	static final String USER = "userme";
	static final String PASS = "passwordme$2";
	

	public Patient(){
		super();
		System.out.println("Hello everyone");
		
		
		Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected server successfully...");
		      
		      
		      
		      
		      
		      
		      System.out.println("Inserting records into the table...");
		      stmt = conn.createStatement();
		      
		      //String sql = "INSERT into patientinfo " +
		                   //"VALUES (samplePatient.getPatientID(),samplePatient.getFirstName(),samplePatient.getMiddleName(),samplePatient.getLastName(),samplePatient.getDateOfBirth(),samplePatient.getSex(),samplePatient.getStreet(),samplePatient.getCity(),samplePatient.getState(),samplePatient.getZip(),samplePatient.getHomePhoneNumber(),samplePatient.getWorkPhoneNumber(),samplePatient.getCellPhoneNumber)";
		      //String sql = "INSERT into PATIENTINFO(patientid,firstname,middle,lastname,dob,sex,street,city,state,zip,phone_home,phone_work,phone_cell) " +
	                   //"VALUES (0.3, 'Marla','Super','Illrson','1999-04-12','female','hi','bye','yo','900',678678,678678,678678)";
	      
		      
		      
		    //String sql = "INSERT into patientinfo(patientid,firstname,middle,lastname,dob,sex,street,city,state,zip,phone_home,phone_work,phone_cell) " +
           //"VALUES (.3,samplePatient.getFirstName(),samplePatient.getMiddleName(),samplePatient.getLastName(),samplePatient.getDateOfBirth(),samplePatient.getSex(),samplePatient.getStreet(),samplePatient.getCity(),samplePatient.getState(),samplePatient.getZip(),samplePatient.getHomePhoneNumber(),samplePatient.getWorkPhoneNumber(),samplePatient.getCellPhoneNumber)";
		      
		      
		      
		      //stmt.executeUpdate(sql);
		      //SELECT `patientid`, `firstname`, `middle`, `lastname`, `dob`, `sex`, `street`, `city`, `state`, `zip`, `phone_home`, `phone_work`, `phone_cell` FROM `eprescriptions`.`patientinfo`;

		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      //PreparedStatement updateemp = conn.prepareStatement("insert into patientinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    	      //updateemp.setDouble(1,23.5);
		    	      //updateemp.setString(2,"Marla");
		    	      //updateemp.setString(3, "Super");
		    	      //updateemp.setString(4, "Illperson");
		    	      //updateemp.setString(5, "20150909");
		    	      //updateemp.setString(6, "female");
		    	      //updateemp.setString(7, samplePatient.getStreet());
		    	      //updateemp.setString(8, samplePatient.getCity());
		    	      //updateemp.setString(9, samplePatient.getState());
		    	      //updateemp.setInt(10, samplePatient.getZipCode());
		    	      //updateemp.setString(11, samplePatient.getHomePhoneNumber());
		    	      //updateemp.setString(12, samplePatient.getWorkPhoneNumber());
		    	      //updateemp.setString(13, samplePatient.getCellPhoneNumber());
		    	      
		    	      //updateemp.executeUpdate();
		    	      
		    	      //`sex`, `street`, `city`, `state`, `zip`, `phone_home`, `phone_work`, `phone_cell`
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @return the patientID
	 */
	public String getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(String newPatientID) {
		patientID = newPatientID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	
	/**
	 * @return the full name of the patient
	 */
	public String getFullName() {
		return firstName + "Yo middle name " + lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String newPhoneNumber) {
		phoneNumber = newPhoneNumber;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String newStreet) {
		street = newStreet;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String newCity) {
		city = newCity;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int newZipCode) {
		zipCode = newZipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String newState) {
		state = newState;
	}
	
	/**
	 * @return the full address
	 */
	public String getAddres() {
		return street + ", " + city + ", " + state + " " + zipCode;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String newSex) {
		sex = newSex;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String newDateOfBirth) {
		dateOfBirth = newDateOfBirth;
	}
	
	/**
	 * @return the isPatient
	 */
	public boolean isPatient() {
		return isPatient;
	}

	/**
	 * @param isPatient the isPatient to set
	 */
	public void setIsPatient(boolean newIsPatient) {
		isPatient = newIsPatient;
	}
	
	/**
	 * @return the myObservations
	 */
	public ArrayList<Observation> getMyObservations() {
		return myObservations;
	}

	/**
	 * @param myObservations the myObservations to set
	 */
	public void setMyObservations(ArrayList<Observation> newMyObservations) {
		myObservations = newMyObservations;
	}

	/**
	 * @return the myProblems
	 */
	public ArrayList<Problem> getMyProblems() {
		return myProblems;
	}

	/**
	 * @param myProblems the myProblems to set
	 */
	public void setMyProblems(ArrayList<Problem> newMyProblems) {
		myProblems = newMyProblems;
	}

	/**
	 * @return the myAllergies
	 */
	public ArrayList<Allergy> getMyAllergies() {
		return myAllergies;
	}

	/**
	 * @param myAllergies the myAllergies to set
	 */
	public void setMyAllergies(ArrayList<Allergy> newMyAllergies) {
		myAllergies = newMyAllergies;
	}
	
	public void fetchPatient(String PatientID) throws Exception{
		patientID = PatientID;
		getPatientInfo();		
		getObservationInfo();
		getProblemInfo();
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
	private static void getPatientInfo () throws Exception{
		String xmlStr = makeRequest(patientURL, patientID, xmlFormatURL);
		
		InputStream inStream = new ByteArrayInputStream((xmlStr).getBytes("utf-8"));
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	    XPathFactory xpf = XPathFactory.newInstance();
	    XPath xpath = xpf.newXPath(); 
	    
	  
	    XPathExpression family = xpath.compile( "/Patient/name/family/@value" );
	    XPathExpression given= xpath.compile( "/Patient/name/given/@value" );
	    NodeList familyNodeList = (NodeList) family.evaluate(doc, XPathConstants.NODESET);
	    NodeList givenNodeList = (NodeList) given.evaluate(doc, XPathConstants.NODESET);
	    
	    //what is this function doing? 
	    for (int i = 0; i < familyNodeList.getLength();)
	    {
	    	String patientFirstName = givenNodeList.item(i).getFirstChild().getNodeValue();
	    	String patientLastName = familyNodeList.item(i).getFirstChild().getNodeValue();
	    	firstName = patientFirstName;
	    	lastName = patientLastName;
	    	isPatient = true;
	    	return;
	    }
	    isPatient = false;
	}
	
	
	private static void getObservationInfo() throws Exception{
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
	}
	
	private static void getProblemInfo() throws Exception{
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
	}
}


