

<%@ page import="java.io.*" %>
<%@ page import = "java.text.*" %>

<%@ page import ="java.io.BufferedReader" %>
<%@ page import ="java.io.ByteArrayInputStream" %>
<%@ page import ="java.net.HttpURLConnection" %>
<%@ page import ="java.net.URL" %>
<%@ page import ="org.json.simple.JSONArray" %>
<%@ page import ="org.json.simple.JSONObject" %>
<%@ page import ="org.json.simple.parser.JSONParser" %>
<%@ page import ="java.util.Iterator" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    import="java.util.ArrayList"
    %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="WEB-INF/PharmaHeader.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Prescription Pharmacist</title>

<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-color:#aaa;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#333;background-color:#fff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#fff;background-color:#f38630;}
.tg .tg-s6z2{text-align:center}
.tg .tg-0ord{text-align:right}


body {
	margin : 0;
  padding : 0;
  background-color : #ffffff;
  color : #000000;
}
p, ul, ol, li {
  font : 10pt Arial,geneva,helvetica;
}
h1 {
  font : bold 14pt Arial, Helvetica, geneva;
}
h2 {
  font : bold 12pt Arial, Helvetica, geneva;
}
#header {
  position : fixed;
  width : 100%;
  height : 10%;
  top : 0;
  right : 0;
  bottom : auto;
  left : 0;
  border-bottom : 2px solid #cccccc;
}
#leftnavigation {
    position : fixed;
    height : 100%;
    top : 10%;
    left : 0;
    width : 150px;
    margin : 0px 0px 0px 10px;
    color : #000000;
    padding : 3px;
    overflow : auto;
}
#content {
  position : fixed;
  top : 10%;
  bottom : 100px;
  margin : 0px 0px 0px 165px;;
  width : 1500px;
  height : 100%;
  padding-left : 5px;
  padding-right : 12px;
  color : #000000;
  border-left : 2px solid #cccccc;
  overflow : auto;
}
#content h1, #content h2 {
  color : #cc0000;
}

.wordwrap
{
font-size:30px;
color:blue;
overflow-wrap: break-word;
word-wrap: break-word; 
width:1200px
}

</style>

</head>

<body>
<% Patient currentPatient = (Patient) session.getAttribute("patient");%>
<% 

	      //String httpsURL = "https://taurus.i3l.gatech.edu:8443/HealthPort/fhir/Patient/?_format=json";
		  String httpsURL = session.getAttribute("fhirURL") + "Patient/?_format=json";

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

%>

<p><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Patient Selection</SPAN>
</p>
<table class="tg" >
  <tr>
    <th >Patient Name</th>
    <th class="tg-s6z2" colspan="6">Patient Id</th>
  </tr>


<%	      
	      InputStream inStream = new ByteArrayInputStream((sb.toString().getBytes("utf-8")));
	      InputStreamReader reader = new InputStreamReader(inStream);
	      JSONParser jsonParser = new JSONParser();
	      JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	      JSONArray lang= (JSONArray) jsonObject.get("entry");
	      Iterator<?> i3 = lang.iterator();   
	       

	      while (i3.hasNext()) {
	    	  JSONObject structure = (JSONObject) i3.next();
	    	  
	    	  JSONObject structure1 = (JSONObject) structure.get("content");
	    	      	      	  
	    	  JSONArray lang1= (JSONArray) structure1.get("name");
	      
	    	      Iterator<?> i2 = lang1.iterator(); 
			      while (i2.hasNext()) {
			            JSONObject innerObj = (JSONObject) i2.next();
		            String givenname = innerObj.get("given").toString().replaceAll("\"", "").replace("[", "").replace("]", "");
		            String familyname = innerObj.get("family").toString().replaceAll("\"", "").replace("[", "").replace("]", "");
 %>
				    <tr>
				    <td class="tg-031e"> <%= givenname +"  "+familyname  %> </td>
				   	    
			<%      }	   
			      
		  JSONArray lang2= (JSONArray) structure1.get("identifier");  
		  Iterator<?> i4 = lang2.iterator(); 
		  while (i4.hasNext()) {
		           JSONObject innerObj1 = (JSONObject) i4.next();
		           String Id = innerObj1.get("value").toString();
		           %>
			   <td class="tg-031e"><a href="PharmaSummary?patient_id= <%= Id %>"> <%= Id %> </a> </td> </tr>
		 <%   } }	  %>


</table>
</body>
</html>
