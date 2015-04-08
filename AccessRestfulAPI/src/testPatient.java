import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class testPatient {

    public static void main(String[] args) throws Exception
    {
    int len = args.length;
    System.out.println("\n"+len);
      for (int i1 = 0; i1 < len; i1++) {	
    	
      //String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/3.568001602-01?_format=xml";   	
      String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/"+args[i1]+"?_format=xml";
      System.out.println("\n ID "+args[i1]);
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
     // System.out.println("\n"+sb.toString());
	  
  
	
    InputStream inStream = new ByteArrayInputStream((sb.toString()).getBytes("utf-8"));
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream);
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath(); 
    
  
    XPathExpression expr = xpath.compile( "/Patient/name/family/@value" );
    XPathExpression expr1= xpath.compile( "/Patient/name/given/@value" );
    NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nl1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
     
    for (int i = 0; i < nl.getLength(); i++)
    {
       System.out.println(nl.item(i).getFirstChild().getNodeValue());
       System.out.println(nl1.item(i).getFirstChild().getNodeValue());

    }
    
    sb.delete(0, sb.length());
    in.close();
    inStream.close();
    ins.close();
    isr.close();
    } 
	
    }
}
