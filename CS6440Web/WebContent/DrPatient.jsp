
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
  <li><a href="#sum">Summary</a></li>
  <li><a href="DrObservation.jsp#observ">Observations</a></li>
  <li><a href="#cond">Conditions</a></li>
  <li><a href="DrMedication.jsp#med">Medications</a></li>
  <li><a href="#Allergy">Allergies</a></li>
  <li><a href=" ">ePrescription</a></li>
  <li><a href="DrAppointments.jsp">Home</a></li>
</ul>
</div>

<div id="content">
		<p><a name="sum"><SPAN STYLE="color: white; font-size: 25pt; background-color: #f38630">Summary </SPAN></a></p>


<% 
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eprescriptions","root","may@2007");
		Statement st= con.createStatement();
		String patientid1 = currentPatient.getPatientID().trim();
		ResultSet rs=st.executeQuery("SELECT * FROM doctor where Patient_id='"+patientid1+"'");
		ResultSetMetaData metaData = rs.getMetaData(); 
		int checkData = 0;
		while(rs.next())
        { 
		checkData = checkData + 1; %>
		
		<table class="wordwrap"><tr><td><%=rs.getString(6) %></td></tr></table> 
		<table class="wordwrap"><tr><td><%=	rs.getString(7) %></td></tr></table>
		<p><SPAN STYLE="color: white; font-size: 20pt; background-color:  #DF0101" ><%=rs.getString(8) %> </SPAN></p>

<%
        }
		if (checkData == 0) { 
			System.out.println("\n checkData in while "+checkData);
		%>
			<table class="wordwrap"><tr><td><font face="Verdana, Geneva, sans-serif" size = "4">
			The patient has history of previous stroke and hyper tension.  While walking, patient accidentally fell to knees and  hit head on the ground, near left eye.  Patient does not profess any loss of consciousness, recalling the entire event.  The patient does have a history of previous falls, one of which resulted in a hip fracture. </font></td></tr>
			<tr><td>  </td></tr>
			<tr><td><font face="Verdana, Geneva, sans-serif" size = "4"> Underwent physical therapy and recovered completely from that.  Initial examination showed bruising around the left eye. Patient was admitted for evaluation of the fall and to rule out syncope and possible stroke with positive histories.
			</font></td></tr></table> 
			<table class="wordwrap"><tr><td> <font face="Verdana, Geneva, sans-serif" size = "4"> Status post fall with trauma:  The patient was unable to walk normally secondary to traumatic injury of knee, causing significant pain and swelling.</font></td></tr></table>
			<p><SPAN STYLE="color: white; font-size: 20pt; background-color:  #DF0101" >Patient had a chest x-ray, which showed cardiomegaly with atherosclerotic heart disease </SPAN></p>
			
<% 		
		} 
		con.close();

%>

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
		

<p><SPAN STYLE="color: white; font-size: 25pt; background-color: #f38630">Allergies</SPAN></p>
<table class="tg" >
  <tr>
    <th>Allergic To</th>
    <th>Type</th>
    <th>Allergic Reaction</th>
    <th class="tg-s6z2" colspan="6">Notes</th>
  </tr>


<%

		//Class.forName("com.mysql.jdbc.Driver");
System.out.println("inside allergy");
		java.sql.Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/eprescriptions","root","may@2007");
		Statement st1= con1.createStatement();
		String patientid2 = currentPatient.getPatientID().trim();
		System.out.println("inside allergy"+patientid2);		
		
		ResultSet rs1=st1.executeQuery("select pinfo.patientid, pinfo.pid, algy.allergynotes, algy.reaction, adata.allergy, adata.type from patientinfo pinfo join allergy algy on pinfo.pid  = algy.pid join allergydata adata on adata.allergyid = algy.allergyid where pinfo.patientid ='"+patientid2+"'");
		ResultSetMetaData metaData1 = rs1.getMetaData(); 
		int checkResults=0;
		while(rs1.next())
        { checkResults = checkResults+1;

%>

 <tr>
<td class="tg-031e"><%= rs1.getString(5)  %> </td>
<td class="tg-031e"> <%= rs1.getString(6) %> </td>
<td class="tg-031e"> <%= rs1.getString(4)  %> </td>
<td class="tg-031e"> <%= rs1.getString(3)  %> </td>
</tr>


<% } if (checkResults == 0) { %>
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
} con1.close();  %>
</table>

<a name="Allergy"> </a>
</div>

</body>
</html>