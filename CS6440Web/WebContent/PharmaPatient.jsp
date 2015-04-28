<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.Map"
    import="java.util.ArrayList"
    %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="WEB-INF/PharmaHeader.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Prescription Pharmacist</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />

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

a:visited{
	color:purple;
}

a:link{
	color: black;
}

</style>

</head>

<body>
<<<<<<< HEAD
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
=======
<% ArrayList<Map<String, Object>> prescriptions = (ArrayList<Map<String, Object>>) session.getAttribute("prescriptions");%>
<div >
<p style="text-align:center;"><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Patient Selection</SPAN>
>>>>>>> refs/remotes/origin/fullProject
</p>
<table class="tg" align="center">
  <tr>
    <th >Patient Name</th>
    <th class="tg-s6z2" colspan="6">Prescription</th>
  </tr>
	<% 
		for(int i = 0; i < prescriptions.size(); i++){
			Map<String, Object> prescription = prescriptions.get(i);
			String patientID = prescription.get("patientID").toString();
			String patientName = prescription.get("patientName").toString();
			String drugName = prescription.get("drugName").toString();%>
			<tr>
				<td>
					<a href="PharmaSummary?patient_id= <%=patientID %>"><%=patientName%></a>
				</td>
				<td>
					<%=drugName %>
				</td>
			</tr>
	<%	}
	%>


</table>
</div>
</body>
</html>
