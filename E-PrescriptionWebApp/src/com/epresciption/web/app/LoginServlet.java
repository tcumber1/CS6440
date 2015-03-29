package com.epresciption.web.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.xpath.*;

import java.net.*;

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
		System.out.println("in Get");
		System.out.println("Username: " + request.getParameter("un"));
		session = request.getSession(true);
		if (request.getParameter("un").equals("Patient")){
			System.out.println("enter if");
			try{
				String PatientID = (String)request.getParameter("pw");
				System.out.println("The PatientID is " + PatientID);
				String FHIRResponse = makePatientMediationRequest(PatientID);
				session.setAttribute("pid", PatientID);
				getMedicationInfo(FHIRResponse);
				response.sendRedirect("Patient.jsp");
				
			}
			catch(Exception e){
				System.out.println(e);
			}
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
		String httpsURL =  "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/MedicationPrescription?subject._id=" + pid;
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
	    
	    return sb.toString();
	}
	
	/*
	 * Takes the XML response from the FHIR API and parses it for the necessary information.
	 * Sets the variables using session.SetAttribute(varName, var)
	 */
	private void getMedicationInfo (String xmlStr) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		//Robert: I honestly don't know exactly how this is working, but I just copied and pasted the code from Ravi
		
		
		InputStream inStream = new ByteArrayInputStream((xmlStr).getBytes("utf-8"));
	    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	        
	    Node objects = doc.getDocumentElement();
	        
	    for (Node object = objects.getFirstChild(); object != null; object = object.getNextSibling()) {
	        if (object instanceof Element) {
	            Element e = (Element)object;
	            if (e.getTagName().equalsIgnoreCase("entry")) {
	           	   String id1 = e.getFirstChild().getNodeName();
	           	   //Example of setting session
	           	   session.setAttribute("id1", id1);
                   System.out.println("The ID is  \n" + id1);
                } 
	                
	        }
	    }    
	        
	    NodeList nodeLst = doc.getElementsByTagName("entry");
	    System.out.println("\n Length is " + nodeLst.getLength());
			
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath(); 
    	
        for (int temp = 0; temp < nodeLst.getLength(); temp++) {
	    		 
    		Node nNode = nodeLst.item(temp);
	     
    		System.out.println("\nCurrent Element :" + nNode.getNodeName());
	     
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	     
    			Element eElement = (Element) nNode;
	    			
    			System.out.println("title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
    			System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
    			System.out.println("published : " + eElement.getElementsByTagName("published").item(0).getTextContent());


    	    }
   		}
	        
        XPathExpression expr = xpath.compile("/feed/entry/content/MedicationPrescription/contained/Medication/code/coding/system/@value");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println(nl.getLength());
        for (int i = 0; i < nl.getLength(); i++){
            Node currentItem = nl.item(i);
            //System.out.println(currentItem.getNodeName());
            String key = currentItem.getNodeValue();
            System.out.println(key);
        }
		
	}

}
