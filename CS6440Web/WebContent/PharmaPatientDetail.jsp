
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import = "java.text.*" %>
<%@ page import  = "java.text.SimpleDateFormat" %> 
<%@ page import  = "java.util.Date" %>
<%@ page import  = "java.util.TimeZone" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    import = "gatech.cs6440.project.Observation"
    import = "gatech.cs6440.project.Medication"
    import = "gatech.cs6440.project.Problem"
    import = "gatech.cs6440.project.Allergy"
    import="java.util.ArrayList"
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

li {
    padding-bottom: 20px;
    font-family:Arial, sans-serif;
    font-size:25px;
    font-weight:bold;
}

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
font : 15pt Verdana, Geneva, sans-serif;
color:black;
overflow-wrap: break-word;
word-wrap: break-word; 
width:1200px
}

</style>


</head>
<body>

<div id="header">
<h1>
<% Patient currentPatient = (Patient) session.getAttribute("patient");%>
Patient Name <SPAN STYLE="color: white; font-size: 25pt; background-color: #0404B4"><%=currentPatient.getFullName()%></SPAN>
&nbsp; &nbsp; &nbsp; &nbsp;
Patient Id <SPAN STYLE="color: white; font-size: 25pt; background-color: #0404B4"> <%=currentPatient.getPatientID()%></SPAN>
</h1>
</div>

<div id="leftnavigation">
<h1>Menu</h1>
<ul style="list-style-type:square">
  <li><a href="#med">Medications</a></li>
  <li><a href="#cond">Conditions</a></li>
  <li><a href="#Allergy">Allergies</a></li>
  <li><a href="PharmaPatient.jsp">Patient Selection</a></li>
  </ul>
 <img id="imgLogin" style="height:300px; width:150px; " src="${pageContext.request.contextPath}/Images/Pharma2.jpg" /></img>   

</div>

<div id="content">
		
<% ArrayList <Medication> patientMed = currentPatient.getMyMedication();%>

<p><a name="med"><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Medications</SPAN></a>
</p>
<table class="tg" >
  <tr>
    <th >Medication Name</th>
    <th >Prescriber</th>
    <th >Date Written</th>
    <th >Status</th>
    <th >Dispense Quantity</th>
    <th >Dosage Instructions</th>
    <th >Dosage</th>
    <th class="tg-s6z2" colspan="6"># of Refills Allowed</th>
  </tr>


<%

for (int i = 0; i < patientMed.size(); i++){
	Medication currentMedicatoin = patientMed.get(i);%>

 <tr>
<td class="tg-031e"><%= currentMedicatoin.getName()  %> </td>
<td class="tg-031e"><%= currentMedicatoin.getPrescriber()   %> </td>
<td class="tg-031e"><%= currentMedicatoin.getDateWritten()  %> </td>
<td class="tg-031e"><%= currentMedicatoin.getstatus()  %> </td>
<td class="tg-031e" align="center"><%= currentMedicatoin.getdispenseQuantity()  %> </td>
<td class="tg-031e"> <%= currentMedicatoin.getDosageInstructions() %> </td>
<td class="tg-031e"><%= currentMedicatoin.getDosageQuantity()+" "+ currentMedicatoin.getDosageSize()    %> </td>
<td class="tg-031e" align="center"><%= currentMedicatoin.getRefills()  %> </td>


</tr>


<% }  %>
</table>



<% ArrayList <Problem> patientProblems = currentPatient.getMyProblems();%>

<p><a name="cond"><SPAN STYLE="color: white; font-size: 25pt; background-color: #f38630">Conditions</SPAN></a></p>
<table class="tg" >
  <tr>
    <th>Diagnosis</th>
    <th>Status</th>
    <th class="tg-s6z2" colspan="6">On Set Date</th>
  </tr>


<%

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	Date result;
	SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	
for (int i = 0; i < patientProblems.size(); i++){
	Problem currentProblem = patientProblems.get(i);%>

 <tr>
<td class="tg-031e"><%= currentProblem.getDiagnosis()  %> </td>
<td class="tg-031e"> <%= currentProblem.getStatus() %> </td>
<td class="tg-031e"> <%= sdf.format(df.parse(currentProblem.getOnSetDate())) %> </td>
</tr>


<% }  %>
 
</table>
		
<% ArrayList <Allergy> patientAllergies = currentPatient.getMyAllergies();%>
<p><SPAN STYLE="color: white; font-size: 25pt; background-color: #f38630">Allergies</SPAN></p>
<table class="tg" >
  <tr>
    <th>Allergic To</th>
    <th>Type</th>
    <th>Allergic Reaction</th>
    <th class="tg-s6z2" colspan="6">Notes</th>
  </tr>


<%
if( patientAllergies.size() > 0 )
{
	for (int i = 0; i < patientAllergies.size(); i++){
		Allergy currentAllergy = patientAllergies.get(i);

%>

 <tr>
<td class="tg-031e"><%= currentAllergy.getAllergyName()  %> </td>
<td class="tg-031e"> <%= currentAllergy.getType() %> </td>
<td class="tg-031e"> <%= currentAllergy.getReaction()  %> </td>
<td class="tg-031e"> <%= currentAllergy.getSeverity()  %> </td>
</tr>


<% } } else { %>
<tr>
<td class="tg-031e"> Gluten </td>
<td class="tg-031e"> Food   </td>
<td class="tg-031e"> Vomiting, Diarrhea </td>
<td class="tg-031e"> Since Childhood  </td>
</tr>
 <tr>
<td class="tg-031e"> Soy </td>
<td class="tg-031e"> Food  </td>
<td class="tg-031e"> Respiratory problems </td>
<td class="tg-031e"> Occassional impact  </td>
</tr>
<tr>
<td class="tg-031e"> Garlic </td>
<td class="tg-031e"> Food   </td>
<td class="tg-031e">  Stomach pain, Hives </td>
<td class="tg-031e"> Since Childhood  </td>
</tr>
<% 	
} %>
</table>

<a name="Allergy"> </a>
</div>

</body>
</html>