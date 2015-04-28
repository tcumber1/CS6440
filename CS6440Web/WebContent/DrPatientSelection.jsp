<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    import="java.util.ArrayList"
    import="java.util.HashMap"
	import="java.util.Map"
    %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Prescription</title>

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

td {
	align:center;
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
<div style="float:left; width:100%; border-bottom: solid 5px; background-color : #56A5E7;">
		<div style="text-align:center; width:100%; float:left; padding-right:0px;">
			<h1>E-Prescription Web: All Patients 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href ="Login.jsp"> <font color=black face="Verdana, Geneva, sans-serif" size = "3">Logout</font> </a> </h1>
		</div>
		
<<<<<<< HEAD
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

<p><a name="patientlist"><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Patient Selection</SPAN></a>
=======
</div>
<p style="text-align:center;"><a name="patientlist"><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Patient Selection</SPAN></a>
>>>>>>> refs/remotes/origin/fullProject
<a href="DrAppointments.jsp"><SPAN STYLE="color: white; font-size: 15pt; background-color: black">Back</SPAN></a>
</p>
<table class="tg" align="center">
  <tr>
    <th >Patient Name</th>
    <th class="tg-s6z2" colspan="6">Patient Id</th>
  </tr>
  		<% ArrayList<Map<String, Object>> patients = (ArrayList<Map<String, Object>>) session.getAttribute("patients");
  		
  		for(int i = 0; i < patients.size(); i++){
  			Map<String, Object> patient = patients.get(i); %>
  		

			    <tr>
				    <td class="tg-031e"> <%=patient.get("fullName") %> </td>
  
			      
		  
			   <td class="tg-031e"><a href="DrPatientSummary?patient_id= <%= patient.get("patientID") %>"> <%= patient.get("patientID") %> </a> </td> </tr>
		<%	   }
  		%>



</table>
<%@ include file="WEB-INF/footer.jsp" %>

</body>
</html>
