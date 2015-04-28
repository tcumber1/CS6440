<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    import = "gatech.cs6440.project.Medication"
    import = "gatech.cs6440.project.Allergy"
    import = "gatech.cs6440.project.Observation"
    import = "gatech.cs6440.project.Problem"
	import = "java.util.ArrayList"
	import = "java.util.Map"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Prescription</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />
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
}

table, th, td {
    border-collapse: collapse;
}
span{
	color:white;
	font-size: 25pt;
	background-color: #f38630;
}
th{
	color:white;
	background-color: #f38630;
}

</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script >
$(document).ready(function () {
	OnSummary();
});


function GetURLParameter(sParam){
	var sPageURL = window.location.search.substring(1);
	    var sURLVariables = sPageURL.split('&');
	    for (var i = 0; i < sURLVariables.length; i++)
	    {
	        var sParameterName = sURLVariables[i].split('=');
	        if (sParameterName[0] == sParam)
	        {
	            return sParameterName[1];
	        }
	    }
}

function OnHome() {
	window.location.href = "Login.jsp";
}

function OnSummary() {
	OnResetAll();
	//$("#divSummary").css("display", "inline-block");
	$("#divProblems").css("display", "inline-block");
	$("#divObservations").css("display", "inline-block");
	$("#divMedication").css("display", "inline-block");
	$("#divAllergies").css("display", "inline-block");
	$("#divFeedback").css("display", "inline-block");
}

function OnMedication() {
	OnResetAll();
	$("#divMedication").css("display", "inline-block");
}

function OnAllergies() {
	OnResetAll();
	$("#divAllergies").css("display", "inline-block");
}

function OnResetAll() {
	//$("#divSummary").css("display", "inline-block");
	$("#divObservations").css("display", "none");
	$("#divProblems").css("display", "none");
	$("#divMedication").css("display", "none");
	$("#divAllergies").css("display", "none");
	$("#divFeedback").css("display", "none");
}

function doPrescription(prescriptionID){
	$.ajax({
		url: "FillPrescription",
		data: {medicineid: prescriptionID},
		type: "GET",
		dataType: "json",
		success: function(response) {
			alert(response.message);
			document.getElementById("medicationID" + response.prescriptionID).textContent = "active";
			},
		failure: function() { alert("Failure");},
		error: function(jqXHR, textStatus, errorThrown) {alert(jqXHR+" - "+textStatus+" - "+errorThrown);}
	});
}

function prescriptionFIlled(response){
	alert(response.message);
	document.getElementById("medicationID" + response.prescriptionID).textContent = "active";
}

</script>

</head>
<body>
<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:20px;"></div>
	
	<% 
		
		Patient currentPatient = (Patient) session.getAttribute("patient");	
		ArrayList<Medication> currentMedications; 
		currentMedications = currentPatient.getMyMedication();	
	 	ArrayList<Allergy> currentAllergies;
	 	currentAllergies = currentPatient.getMyAllergies();
	
	 
	 %>
		<div style="text-align:center; width:100%; float:left; padding-right:0px;">
			<h1>Doctor View</h1>
		</div>
		<div class="divPatientHeader" >
				<table style="width:100%; padding:10px;">
					<tr>
						<td style="width:40%" style="float:left;">
						Name: <label id="lblName"><%=currentPatient.getFullName()%></label>
						</td>
						<td style="width:40%">
						Sex: <label id="lblSex"><%=currentPatient.getSex()%></label>
						</td>
						<td style="width:20%" rowspan="3">
							<img id="imgPatient"  style=" width:75px; background: transparent; background-color: transparent; " src="Images/Picture1.jpg" ></img>
						</td>
					</tr>
					<tr>
						<td style="width:40%" style="float:left;">
						Phone # <label><%=currentPatient.getPhoneNumber()%></label>
						</td>
						<td style="width:40%">
						DOB: <label id="lblDob"><%=currentPatient.getDateOfBirth()%></label>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="float:left;">
						Address: <label id="lblAddress"><%=currentPatient.getStreet()%></label>
						</td>
					</tr>
				</table>		
		</div>
	<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:1px;"></div>
		<div class="divPatient">
			<table style="width:95%; margin-left:20px; margin-right:20px;">
				<tr>
					<td style="width:155px; text-align: top; vertical-align: top;">
						<div style="text-align: top;">
							<div style="padding-top:10px; padding-bottom:10px;">
								<input type="button" id="btnSummary" onclick="OnSummary();" value="Summary">
							</div>
							<div style="padding-bottom:10px;">
								<input type="button" id="btnMedications" onclick="OnMedication();" value="Medications">
							</div>
							<div style="padding-bottom:10px;">
								<input type="button" id="btnAllergies" onclick="OnAllergies();" value="Allergies">
							</div>
							<div style="padding-bottom:10px;">
								<input type="button" id="btnHome" onclick="OnHome();" value="Log out">
							</div>
						</div>
					</td>
					<td style="width:100%">
						<div id="divSummary" style="width:100%; float:left;">
							<table style="width:100%">
								<tr> 
									<td style="width:100%; padding-left:10px">
										<div id="divMedication" style="float:left; width:100%; display:block in-line;">
										<table style="width:100%; padding:5px; margin-right:10px;">
											<tr>
												<td>
													<span>Medications</span>
												</td>
												<td>
												</td>
											</tr>
											<tr>
												<td>
												</td>
											</tr>
											<tr>
												<td style="width:100%;">
													<table class="tg" >
													  <tr>
													    <th >Medication Name</th>
													    <th >Dosage Form</th>
													    <th>Drug NDC</th>
													    <th >Dosage Quantity</th>
													    <th >Dosage</th>
													    <th ># of Refills Allowed</th>
													    <th >Prescriber</th>
													    <th >Date Written</th>
													    <th >Status</th>
													  </tr>
													
													
													<%
													
													for (int i = 0; i < currentMedications.size(); i++){
														Medication currentMedication = currentMedications.get(i);%>
													
													 <tr>
													<td class="tg-031e"><%= currentMedication.getName()  %> </td>
													<td class="tg-031e"> <%= currentMedication.getDosageForm() %> </td>
													<td class="tg-031e"> <%= currentMedication.getNDC() %> </td>
													<td class="tg-031e" align="center"><%= currentMedication.getDosageQuantity()  %> </td>
													<td class="tg-031e"><%= currentMedication.getNumPills() %> </td>
													<td class="tg-031e" align="center"><%= currentMedication.getRefills()  %> </td>
													<td class="tg-031e"><%= currentMedication.getPrescriber()   %> </td>
													<td class="tg-031e"><%= currentMedication.getDateWritten()  %> </td>
													<% if(currentMedication.getstatus().equals("pickup")){%>
														<td class="tg-031e" id="medicationID<%=currentMedication.getMedicationID() %>">
															<button style="cursor: pointer; color:white; background-color:#56A5E7; width:auto; padding:0px 10px 0px 10px;"onclick="doPrescription(<%=currentMedication.getMedicationID() %>)">fill</button>
														</td>
													<%} else{%>
														<td class="tg-031e"><%= currentMedication.getstatus()  %> </td>
													<%} %>
													
													
													
													</tr>
													
													
													<% }  %>
													</table>
												<td>
											</tr>
											
											
											
										</table>
										</div>	
									</td>
								</tr>
								<tr>
									<td style="width:100%; padding-left:10px; ">
										<div id="divAllergies" style="float:left; width:55%; display:block in-line;">
										<table style="width:100%; padding:5px; margin-right:10px;">
											<tr>
												<td>
													<span>Allergies</span>
												</td>
											</tr>
											<tr>
												<td>
													<table class="tg">
														<tr>
															<th>Allergy</th>
															<th>Severity</th>
															<th>Reaction</th>
														</tr>
														<tr>
															<%
																if(currentAllergies == null || currentAllergies.size() == 0){
																	%>
																		<tr>
																			<td colspan="3" class="tg-031e">
																				No Data found.
																			</td>
																		</tr>
																		
																																				
																	<%
																	}
																	else
																	{
																		for(int i=0; i<4; i++) {
																			Allergy currentAllergy = currentAllergies.get(i);%>
																		<tr>
																			<td class="tg-031e"><%=currentAllergy.getAllergyName() %></td>
																			<td class="tg-031e"><%=currentAllergy.getSeverity() %></td>
																			<td class="tg-031e"><%=currentAllergy.getReaction() %></td>
																		</tr>
																		<%
																		}
																		
																	} %>
														
													</table>
												</td>
											</tr>
										</table>
										</div>
									</td>								
								</tr>
							</table>
						</div>
					<td>
				</tr>
			</table>
			
		
		</div>

	<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:10px;"></div>
 	
 	
 	<%@ include file="WEB-INF/footer.jsp" %>

</body>
</html>