import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;   

import org.w3c.dom.*;
import javax.xml.xpath.*;
import java.net.*;


public class medicationtest {
      
	      public static void main(String[] args) throws Exception
	      {
	        String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/MedicationPrescription?medication.name=protamine";
	        URL myurl = new URL(httpsURL);
	        HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
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
	        
	        InputStream inStream = new ByteArrayInputStream((sb.toString()).getBytes("utf-8"));
	        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
	        
	        Node objects = doc.getDocumentElement();
	        
	        for (Node object = objects.getFirstChild(); object != null; object = object.getNextSibling()) {
	            if (object instanceof Element) {
	                Element e = (Element)object;
	               if (e.getTagName().equalsIgnoreCase("entry")) {
	            	   String id1 = e.getFirstChild().getNodeName(); 
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
	        for (int i = 0; i < nl.getLength(); i++)
	        {
	            Node currentItem = nl.item(i);
	            //System.out.println(currentItem.getNodeName());
	            String key = currentItem.getNodeValue();
	            System.out.println(key);
	        }
	        
	        
	    	}
	        }  
             
	    
   
