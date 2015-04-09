package com.eprescription.web.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.xpath.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		String username = request.getParameter("un");
		String password = request.getParameter("pw");
		if (username.equalsIgnoreCase("Patient")){
			System.out.println("enter if");
			try{
				String PatientID = password;
				String FHIRResponse = makePatientMediationRequest(PatientID);
				session.setAttribute("pid", PatientID);
				if (getMedicationInfo(FHIRResponse)){
					response.sendRedirect("Patient.jsp");
				}
				
				
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		else if (username.equalsIgnoreCase("Doctor") && password.equalsIgnoreCase("Doctor")){
			// TODO go to doctor page
		}
		
		else if (username.equalsIgnoreCase("Pharmacist") && password.equalsIgnoreCase("Pharmacist")){
			// TODO go to pharmacist page
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Takes the PatientID and searches for it using the FHIR API
	 * Returns the xml returned by the request
	 */
	private String makePatientMediationRequest(String pid) throws Exception{
		String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/" + pid + "?_format=xml";
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
        System.out.println("\n"+sb.toString());
	    in.close();
	    isr.close();
	    ins.close();
	    String rtnString = sb.toString();
	    sb.delete(0, sb.length());
	    
	    return rtnString;
	}
	
	/*
	 * Takes the XML response from the FHIR API and parses it for the necessary information.
	 * Sets the variables using session.SetAttribute(varName, var)
	 */
	private boolean getMedicationInfo (String xmlStr) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
	
		
		InputStream inStream = new ByteArrayInputStream((xmlStr).getBytes("utf-8"));
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	    XPathFactory xpf = XPathFactory.newInstance();
	    XPath xpath = xpf.newXPath(); 
	    
	  
	    XPathExpression family = xpath.compile( "/Patient/name/family/@value" );
	    XPathExpression given= xpath.compile( "/Patient/name/given/@value" );
	    NodeList familyNodeList = (NodeList) family.evaluate(doc, XPathConstants.NODESET);
	    NodeList givenNodeList = (NodeList) given.evaluate(doc, XPathConstants.NODESET);
	    
	    Patient tmpPatient = new Patient();
	     
	    for (int i = 0; i < familyNodeList.getLength(); i++)
	    {
	    	String patientFirstName = givenNodeList.item(i).getFirstChild().getNodeValue();
	    	String patientLastName = familyNodeList.item(i).getFirstChild().getNodeValue();
	    	tmpPatient.setFirstName(patientFirstName);
	    	tmpPatient.setLastName(patientLastName);
	    	session.setAttribute("patient", tmpPatient);
	    	return true;
	    }
	    return false;
		
	}

}
