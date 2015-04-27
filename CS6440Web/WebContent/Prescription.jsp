<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "gatech.cs6440.project.Patient"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make a Prescription</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body style="width:80%; padding-right:10%; padding-left:10%;">
	<div style="text-align:center; width:100%; float:left; padding-right:0px;">
		<h1>Create a Prescription</h1>
	</div>
	<%
		Patient currentPatient = (Patient) session.getAttribute("patient");
	%>
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
	
	<div id="SearchArea" style="width:45%; float:left; padding:2.5%;">
		<input type="text" name="searchText" id="searchText" />
		<button onclick="fetchDrug()">Search</button>
		<table>
			<tbody id="searchResultsTable">
	    	</tbody>
		</table>
	</div>
	<script>
		function fetchDrug() {
			//alert(document.getElementById("searchText").value);
	    	$.ajax({
				url: "DrugSearch",
				data: { searchTerm : document.getElementById("searchText").value},
				type: "GET",
				dataType: "json",
				success: function(response) {parse(response);},
				failure: function() { alert("Failure: ");},
				error: function(data) {parse("There was an error");}
			});
		}
		
		function parse (data){
			//alert("error: " + data.error);
			if(data.error == ""){
				$("#searchResultsTable").html('<tr><td id=NDC>ProductNDC</td><td id="name">Name</td><td id="dosageForm">Dosage Form</td><td id="dosage">Dosage</td></tr>');
				$.each(data.drugs, function(idx, drug){
				     $("#searchResultsTable").append('<tr onclick="displayResult(this)"><td value="test">' + drug.productNDC + '</td><td id="name">' + drug.name + '</td><td id="dosageForm">' + drug.dosageForm + '</td><td id="dosage">' + drug.dosage + '</td></tr>');
				});
			}
			else{
				$("#searchResultsTable").html("<tr><td>" + data.error + "</td></tr>");
			}
		}
		
		function displayResult(row){
			//alert(row.cells[0].textContent);
			document.getElementById("drugNDC").value = row.cells[0].textContent;
            document.getElementById("drugName").value = row.cells[1].textContent;
            document.getElementById("drugMethod").value = row.cells[2].textContent;
            document.getElementById("drugDosage").value = row.cells[3].textContent;
		}
	</script>
	
	<div id="DrugInfo" style="width:45%; float:right; padding:2.5%;">
		<table>
			<tbody>
				<tr><td>Drug NDC:</td><td><input type="text" name="drugNDC" id="drugNDC" disabled="disabled"></td></tr>
				<tr><td>Drug name:</td><td><input type="text" name="drugName" id="drugName" disabled="disabled"></td></tr>
				<tr><td>Method:</td><td><input type="text" name="drugMethod" id="drugMethod" disabled="disabled"></td></tr>
				<tr><td>Size:</td><td><input type="text" name="drugDosage" id="drugDosage" disabled="disabled"></td></tr>
				<tr><td>Amount:</td><td><input type="text" name="drugAmount" id="drugAmount"></td></tr>
				<tr><td>Refills:</td><td><input type="text" name="drugRefills" id="drugRefills"></td></tr>
				<tr><td><input type="hidden" id="hPatientID" value="<%=currentPatient.getPatientID() %>"></td>
					<td><button onclick="createPrescription()">Create</button></td></tr>
			</tbody>
		</table>
	</div>
	<script>
		function createPrescription(){
			//alert("here");
			var ndc = document.getElementById("drugNDC").value
			var name = document.getElementById("drugName").value
			var amount = document.getElementById("drugAmount").value
			var refills = document.getElementById("drugRefills").value
			var patientID = document.getElementById("hPatientID").value
			if(isNaN(amount) || isNaN(refills)){
				//display error message for not number
				alert("The amount or number of refills you entered are not numbers. \n Please enter a number and try again.");
			}
			else if(amount == null || amount == "" || refills == null || refills == ""){
				//dispay error message for nothing enter in 'amount'
				alert("No amount or number of refills were enter. \n Please enter a number and try again.");
			}
			else if(name == null || refills == ""){
				//display error message for a drub not being selected
				alert("Please select a drug from the left before submitting.");
			}
			else{
				$.ajax({
					url: "CreatePrescription",
					data: {drugNDC: ndc, drugAmount: amount, numRefills: refills, patientID: patientID},
					type: "GET",
					dataType: "json",
					success: function(response) {alert(response.message);},
					failure: function() { alert("Failure: ");},
					error: function(data) {alert("There was an error");}
				});
			}
		}
	</script>
	
	<%@ include file="WEB-INF/footer.jsp" %>

</body>

</html>