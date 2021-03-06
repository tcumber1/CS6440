<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    import = "gatech.cs6440.project.Medication"
    import = "gatech.cs6440.project.Allergy"
    import = "gatech.cs6440.project.Observation"
    import = "gatech.cs6440.project.Problem"
	import = "java.util.ArrayList"
    %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Perscription - Patient View</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />
</head>
<style>
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

function OnObservation() {
	OnResetAll();
	$("#divObservations").css("display", "inline-block");
}

function OnProblem() {
	OnResetAll();
	$("#divProblems").css("display", "inline-block");
}

function OnMedication() {
	OnResetAll();
	$("#divMedication").css("display", "inline-block");
}

function OnAllergies() {
	OnResetAll();
	$("#divAllergies").css("display", "inline-block");
}

function OnFeedback() {
	OnResetAll();
	$("#divFeedback").css("display", "inline-block");
}

function OnResetAll() {
	//$("#divSummary").css("display", "inline-block");
	$("#divObservations").css("display", "none");
	$("#divProblems").css("display", "none");
	$("#divMedication").css("display", "none");
	$("#divAllergies").css("display", "none");
	$("#divFeedback").css("display", "none");
}

</script>

<body>
<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:20px;"></div>
	<form action="PatientInfo">
	<% 
		
		Patient currentPatient = (Patient) session.getAttribute("patient");	
		ArrayList<Medication> currentMedications; 
		currentMedications = (ArrayList<Medication>)session.getAttribute("medicationList");	
	 	ArrayList<Problem> currentProblems;
	 	currentProblems = (ArrayList<Problem>)session.getAttribute("problemList");	
	 	ArrayList<Observation> currentObservations;
	 	currentObservations = (ArrayList<Observation>)session.getAttribute("ObservationList");
	 	ArrayList<Allergy> currentAllergies;
	 	currentAllergies = (ArrayList<Allergy>)session.getAttribute("AllergyList");
	
	 
	 %>
		<div style="text-align:center; width:100%; float:left; padding-right:0px;">
			<h1>Patient View</h1>
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
								<input type="button" id="btnProblems" onclick="OnProblem();" value="Problems">
							</div>
							<div style="padding-bottom:10px;">
								<input type="button" id="btnObservations" onclick="OnObservation();" value="Observations">
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
									<td style="width:100%; padding-left:10px;">
										<div id="divProblems" style="float: left; width: 55%; display: inline-block;">
											<table style="border: 1px solid; width:100%; padding:5px; margin-right:10px;">
											<tr>
											<td style="width:100%; padding: 5px;">
												<span>Problems</span>
											</td>
											</tr>
											<tr>
											<td style="width:100%; padding: 5px;">	
												<table style="width:100%;">
													<tr>
														<th style="width:40%; text-align:left; border-bottom: 1px solid;">Diagnosis</th>
														<th style="width:30%; text-align:left; border-bottom: 1px solid;">Onset Date</th>
														<th style="width:30%; text-align:left; border-bottom: 1px solid;">Status</th>
													</tr>
													<%
														if(currentProblems == null || currentProblems.size() == 0){
														%>
															<tr>
																<td>
																	<br />
																	No Data found.
																</td>
															</tr>		
														<%
														}
														else
														{
														for (int i=0; i< currentProblems.size(); i++ ) {
														Problem currentProblem = currentProblems.get(i);%>
													<tr>
														<td style="width:40%; text-align:left; border-top: 1px solid; padding:0px;"><%= currentProblem.getDiagnosis()  %></td>
														<td style="width:30%; text-align:left; border-top: 1px solid;"><%= currentProblem.getOnSetDate()  %></td>
														<td style="width:30%; text-align:left; border-top: 1px solid;"><%= currentProblem.getStatus()  %></td>
													</tr>
													<% }}%>
												</table>
											</td>
											</tr>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td style="width:100%; padding-left:10px;">
										<div id="divObservations" style="float: left; width: 55%; display: inline-block;">
												<table style="border: 1px solid; width:100%; padding:5px; margin-right:10px;">
												<tr>
												<td style="width:100%; padding: 5px;">
													<span>Observations</span>
												</td>
												</tr>
												<tr>
												<td style="width:100%; padding: 5px;">
												<table style="width:100%;">
													<tr>
														<th style="width:40%; text-align:left; border-bottom: 1px solid; padding:0px;">Observation</th>
														<th style="width:30%; text-align:left; border-bottom: 1px solid;">Value</th>
														<th style="width:30%; text-align:left; border-bottom: 1px solid;">Date</th>
													</tr>
													<%
													if(currentObservations == null|| currentObservations.size() == 0){
														%>
														<tr>
															<td>
																<br />
																No Data found.
															</td>
														</tr>		
													<%
													}
													else
													{
														for (int i=0; i < 10; i++ ) {//only want to display 10 observations
													
														Observation currentObservation = currentObservations.get(i);%>
													<tr>
														<td style="width:40%; text-align:left; border-top: 1px solid; padding:0px;"><%= currentObservation.getObservationName()  %>Observation</td>
														<td style="width:30%; text-align:left; border-top: 1px solid;"><%= currentObservation.getValue()   %></td>
														<td style="width:30%; text-align:left; border-top: 1px solid;"><%= currentObservation.getDate()   %></td>
													</tr>
													<% } }%>
												</table>
												</td>
												</tr>
												</table>
											</div>
									</td>
								</tr>
								<tr> 
									<td style="width:100%; padding-left:10px">
										<div id="divMedication" style="float:left; width:100%; display:block in-line;">
										<table style="border: 1px solid; width:100%; padding:5px; margin-right:10px;">
											<tr>
												<td style="width:100%; padding: 5px;">
													<span>Medications</span>
												</td>
												<td>
												</td>
											</tr>
											<tr>
												<td style="width:100%;">
													<div style="width:100%;">
															<table style="width:100%;">
																	<%
																	if(currentMedications == null || currentMedications.size() == 0){
																		%>
																		<tr>
																			<td style="width:100%;">
																				<br />
																				No Data found.
																			</td>
																		</tr>
																	<tr>	
																	<td style="width:100%;">																			
																	<%
																	}
																	else
																	{
																		for(int i=0; i<currentMedications.size(); i++) {
																	
																		Medication currentMedication = currentMedications.get(i);%>
																		
																	
																		<div style="width:45%; margin:5px; float:left;  border: 1px solid; padding-bottom:5px; padding-left:5px;">
																			<%=currentMedication.getName() %><br />
																			<%=currentMedication.getDosageForm() %><br />
																			<%=currentMedication.getDosageQuantity() %><br />
																			<%=currentMedication.getNumPills() %> units<br />
																			<%=currentMedication.getPrescriber() %><br />
																			<%=currentMedication.getDateWritten() %><br />
																			<%=currentMedication.getNDC() %><br />
																			Refills: <%=currentMedication.getRefills() %><br />
																			<a style="height:20px; color:black; border: 1px solid; padding:0px; margin: 0px;" href="GetFeedback?patientID=<%=currentPatient.getPatientID()%>&NDC=<%=currentMedication.getNDC()%>&drugName=<%=currentMedication.getName()%>&dosageForm=<%=currentMedication.getDosageForm()%>&dosage=<%=currentMedication.getDosageQuantity()%>">Feedback</a>
																		</div>
																	<%}	%> </td> <%}%>
																</tr>
															</table>
														
													</div>
												<td>
											</tr>
											
											
											
										</table>
										</div>	
									</td>
								</tr>
								<tr>
									<td style="width:100%; padding-left:10px; ">
										<div id="divAllergies" style="float:left; width:55%; display:block in-line;">
										<table style="border: 1px solid; width:100%; padding:5px; margin-right:10px;">
											<tr>
												<td style="width:100%; padding: 5px;">
													<span>Allergies</span>
												</td>
											</tr>
											<tr>
												<td style="width:100%; padding: 5px;">
													<table style="width:100%;">
														<tr>
															<th style="width:25%; text-align:left; border-bottom: 1px solid; padding:0px;">Allergy</th>
															<th style="width:40%; text-align:left; border-bottom: 1px solid;">Severity</th>
															<th style="width:35%; text-align:left; border-bottom: 1px solid;">Reaction</th>
														</tr>
														<tr>
															<%
																if(currentAllergies == null || currentAllergies.size() == 0){
																	%>
																		<tr>
																			<td colspan="3">
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
																			<td style="width:25%; text-align:left; border-top: 1px solid; padding:0px;"><%=currentAllergy.getAllergyName() %></td>
																			<td style="width:40%; text-align:left; border-top: 1px solid;"><%=currentAllergy.getSeverity() %></td>
																			<td style="width:35%; text-align:left; border-top: 1px solid;"><%=currentAllergy.getReaction() %></td>
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
	</form>
	<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:10px;"></div>
 	
 	
 	<%@ include file="WEB-INF/footer.jsp" %>

</body>
</html>
