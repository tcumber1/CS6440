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
	private static String image;
	private static ArrayList<Observation> myObservations;
	private static ArrayList<Problem> myProblems;
	private static ArrayList<Allergy> myAllergies;
	private static ArrayList<Medication> myMedications;
	
	private final static String patientURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/";
	private final static String problemURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Condition?subject:Patient=";
	private final static String observationURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Observation?subject:Patient=";
	private final static String medicationURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/MedicationPrescription?subject._id=";
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
	 * @return the image
	 */
	public static String getImage() {
		return image;
	}
	
	/**
	 * @param image the image to set
	 */
	public static void setImage(String newImage) {
		image = newImage;
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
	
	/**
	 * @return the myMedications
	 */
	public static ArrayList<Medication> getMyMedications() {
		return myMedications;
	}

	/**
	 * @param myMedications the myMedications to set
	 */
	public static void setMyMedications(ArrayList<Medication> myMedications) {
		Patient.myMedications = myMedications;
	}

	public void fetchPatient(String PatientID) throws Exception{
		patientID = PatientID;
		getPatientInfo();		
		getObservationInfo();
		getProblemInfo();
		getMedicationInfo();
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
		  	
		  	String date = content.get("appliesDateTime").toString();
		  	String updateDate = date.substring(0, date.indexOf('T'));
		  	ob.setDate(updateDate);
		  	
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
		  	String updateDate = date.substring(0, date.indexOf('T'));
		  	prob.setOnSetDate(updateDate);
		  	
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
	
	private static void getMedicationInfo() throws Exception{
		String xmlStr = makeRequest(medicationURL, patientID, jsonFormatURL);
		myMedications = new ArrayList<Medication>();
		
		InputStream inStream = new ByteArrayInputStream((xmlStr.getBytes("utf-8")));
		InputStreamReader reader = new InputStreamReader(inStream);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		JSONArray lang= (JSONArray) jsonObject.get("entry");
		Iterator<?> i2 = lang.iterator();
		
		while(i2.hasNext()){
			Medication med = new Medication();
			JSONObject innerObj = (JSONObject) i2.next();
		  	JSONObject content = (JSONObject) innerObj.get("content");
		  	
		  	String dateWritten = content.get("dateWritten").toString();
		  	String updateDateWritten = dateWritten.substring(0, dateWritten.indexOf('T'));
		  	med.setDateWritten(updateDateWritten);
		  	
		  	JSONObject prescriber = (JSONObject) content.get("prescriber");
		  	String display = prescriber.get("display").toString();
		  	med.setPrescriber(display);
		  	
		  	JSONObject medication = (JSONObject) content.get("medication");
		  	String medDisplay = medication.get("display").toString();
		  	med.setName(medDisplay);
		  	
		  	JSONObject dispense = (JSONObject) content.get("dispense");
		  	long lRefills = (long) dispense.get("numberOfRepeatsAllowed");
		  	int refills = (int) lRefills ;
		  	med.setRefills(refills);
		  	
		  	JSONObject quantity = (JSONObject) dispense.get("quantity");
		  	double dValue = (double) quantity.get("value");
		  	int iValue = (int) dValue;
		  	med.setNumPills(iValue);
		  	
		  	
		  	JSONArray dosageInstruction = (JSONArray) content.get("dosageInstruction");
		  	Iterator<?> dosageIns = dosageInstruction.iterator();
		  	
		  	while (dosageIns.hasNext()){
		  		JSONObject dosage = (JSONObject) dosageIns.next();
		  		String text = dosage.get("text").toString();
		  		med.setDosageInstructions(text);
		  		
		  		JSONObject doseQuantity = (JSONObject) dosage.get("doseQuantity");
		  		if (doseQuantity != null){
				  	Object value = doseQuantity.get("value");
				  	Object units = doseQuantity.get("units");
				  	if (units == null){
				  		units = "";
				  	}
				  	if(value == null){
				  		value = "";
				  	}
				  	med.setDoseQuantity(value.toString() + " " + units.toString());
			  	}
		  	}//end while dosageIns
		  	
		  	JSONArray jContained = (JSONArray) content.get("contained");
		  	Iterator<?> iContained = jContained.iterator();
		  	
		  	while(iContained.hasNext()){
		  		JSONObject contained = (JSONObject) iContained.next();
		  		JSONObject code = (JSONObject) contained.get("code");
		  		
		  		JSONArray jCoding = (JSONArray) code.get("coding");
		  		Iterator<?> iCoding = jCoding.iterator();
		  		
		  		while(iCoding.hasNext()){
		  			JSONObject coding = (JSONObject) iCoding.next();
		  			String packageNDC = coding.get("code").toString();
		  			String productNDC = packageNDC.substring(0, packageNDC.lastIndexOf('-'));
		  			med.setNDC(productNDC);
		  		}//end while iCoding
		  	}//end while iCotained
		  	myMedications.add(med);
		} //end while i2
	}
}


