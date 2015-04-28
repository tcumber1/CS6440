<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.io.*" %>

 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.DrPatientSummary"
    import = "java.util.ArrayList"
    import = "java.util.Map"
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

#content {
    width:300px;
    height:300px;
    position:fixed;
    margin-left:-150px; /* half of width */
    margin-top:-150px;  /* half of height */
    top:50%;
    left:70%;
}

#content h1, #content h2 {
  color : #cc0000;
}
#box {
    width:300px;
    height:300px;
    position:fixed;
    margin-left:-150px; /* half of width */
    margin-top:-150px;  /* half of height */
    top:50%;
    left:50%;
}


</style>



</head>
<body>
<%@ include file="WEB-INF/DrHeader.jsp" %>
<div id="content">
 <img id="imgLogin" style="height:350px; width:300px; " src="${pageContext.request.contextPath}/Images/Dr.jpg" /></img>
 </div>
 
<div id="box">
<table class="tg" >
  <tr>
    <th >Appointment Details</th>
    <th >Patient Name </th>
    <th class="tg-s6z2" colspan="6">Patient Photo </th>
  </tr>
    <%

		ArrayList<Map<String, Object>> patientList = (ArrayList<Map<String, Object>>) session.getAttribute("patientList");
    	for(int i = 0; i < patientList.size(); i++){
    		Map<String, Object> patient = patientList.get(i);
    		String patientID = patient.get("patientID").toString();
    		String patientName = patient.get("patientName").toString();
    		String appointment = patient.get("appointment").toString();

            %>
			<tr>		
			<td align=center> <a href="DrPatientSummary?patient_id= <%= patientID %>"> <%= appointment%>      </a>     </td>	
			<td align=center><a href="DrPatientSummary?patient_id= <%= patientID %> "> <%= patientName%>    </a>      </td>	
			<td><img id="imgLogin1" style="height:55px; width:55px;" src= "Images/Picture<%=i%>.jpg"  /></img></td>
			</tr>
	<%} %>
 <tr><td align=center></td>
 <td align=center> <a href="AllPatientServlet">All Patients</a></td> 
 <td><img id="imgLogin2" style="height:55px; width:55px;"  src="${pageContext.request.contextPath}/Images/NewPatient.jpg" /></img></td>
 </tr> 
</table>
</div>


</body>

</html>
