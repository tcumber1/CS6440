package gatech.cs6440.project;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



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
	

	public Patient(){
		super();
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
		return firstName + " " + lastName;
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


