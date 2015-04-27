
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
    import = "gatech.cs6440.project.Problem"
    import = "gatech.cs6440.project.Medication"
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
		
<% ArrayList <Medication> patientMed = currentPatient.getMyMedication();%>

<p><a name="med"><SPAN STYLE="color: white; font-size: 25pt;font-weight: bold; background-color: #f38630">Medications</SPAN></a>
<a href="DrPatient.jsp#sum"><SPAN STYLE="color: white; font-size: 15pt; background-color: black">Back</SPAN></a>
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
	Medication currentMedication = patientMed.get(i);%>

 <tr>
<td class="tg-031e"><%= currentMedication.getName()  %> </td>
<td class="tg-031e"><%=currentMedication.getNDC() %></td>
<td class="tg-031e"><%=currentMedication.getDosageForm() %> </td>
<td class="tg-031e"><%=currentMedication.getDosageQuantity() %> </td>
<td class="tg-031e"><%=currentMedication.getNumPills() %> units </td>
<td class="tg-031e" align="center"><%=currentMedication.getPrescriber() %> </td>
<td class="tg-031e"> <%=currentMedication.getDateWritten() %> </td>
<td class="tg-031e" align="center">Refills: <%=currentMedication.getRefills() %></td>


</tr>


<% }  %>
 
</table>
<a href="DrPatient.jsp#sum"><SPAN STYLE="color: white; font-size: 15pt;font-weight: bold; background-color: black">Back</SPAN></a>
</body>
</html>
