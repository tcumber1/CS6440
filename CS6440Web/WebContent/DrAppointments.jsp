<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.io.*" %>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.DrPatientSummary"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="WEB-INF/DrHeader.jsp" %>

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
		Class.forName("com.mysql.jdbc.Driver");
		//java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eprescriptions","root","may@2007");
		java.sql.Connection con = DriverManager.getConnection((String)session.getAttribute("databaseURL"),(String)session.getAttribute("dbuser"),(String)session.getAttribute("dbpassword"));

		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM doctor");
		ResultSetMetaData metaData = rs.getMetaData();

 		String patient_id,picture="";
 		int i =0;
        while(rs.next())
        {
        	patient_id=rs.getString(3);	
            %>
			<tr>		
			<td align=center> <a href="DrPatientSummary?patient_id= <%= patient_id %>"> <%= rs.getString(5)%>      </a>     </td>	
			<td align=center><a href="DrPatientSummary?patient_id= <%= patient_id %> "> <%= rs.getString(4)%>    </a>      </td>	
			<%
				if (i == 0) picture = "Picture1.jpg";
				else if (i == 1) 
					picture = "Picture3.jpg";	
				else if (i == 2) 
					picture = "Picture4.jpg";
			%>
					<td><img id="imgLogin1" style="height:55px; width:55px;" src= "${pageContext.request.contextPath}/Images/<%= picture %>"  /></img></td>
			</tr>
<% 		
        i = i + 1; } 
 %>
 <tr><td align=center><a href="DrPatientSelection.jsp"> New </a></td>
 <td align=center> <a href="DrPatientSelection.jsp"> Select Patient </a></td> 
 <td><img id="imgLogin2" style="height:55px; width:55px;"  src="${pageContext.request.contextPath}/Images/NewPatient.jpg" /></img></td>
 </tr> 
</table>
</div>


</body>

</html>
