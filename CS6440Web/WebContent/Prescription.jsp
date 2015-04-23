<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    
    import = "gatech.cs6440.project.Patient"
    import = "gatech.cs6440.project.Drug"
	import = "java.util.ArrayList"
    import = "org.json.simple.JSONArray"
	import = "org.json.simple.JSONObject"
	import = "org.json.simple.parser.JSONParser"
	import = "org.json.simple.parser.ParseException"
	
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make a Prescription</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />

</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	
	$(document).ready(function () {
		
	});
	
		function fetchDrug() {
			//alert(document.getElementById("searchText").value);
			//alert("Here " + document.getElementById("searchText").value);
	    	$.ajax({
				url: "DrugSearch",
				data: { searchTerm : document.getElementById("searchText").value},
				type: "GET",
				dataType: "text",
				success: function(data) {parse(data);},
				failure: function() { alert("Failure: ");},
				error: function() {alert("Error: ");}
			});
		}
		
		function parse (data){
			$("#searchResultsTable").html(data);
		}
		
	</script>



<body style="width:80%; padding-right:10%; padding-left:10%;">
	<div style="text-align:center; width:100%; float:left; padding-right:0px;">
		<h1>Patient View</h1>
	</div>
	
	<div class="divPatientHeader" >
		<table style="width:100%; padding:10px;">
			<tr>
				<td style="width:40%" style="float:left;">
				Name: <label id="lblName">Test Patient</label>
				</td>
				<td style="width:40%">
				Sex: <label id="lblSex">Female</label>
				</td>
				<td style="width:20%" rowspan="3">
					<img id="imgPatient"  style=" width:75px; background: transparent; background-color: transparent; " src="Images/Picture1.jpg" ></img>
				</td>
			</tr>
			<tr>
				<td style="width:40%" style="float:left;">
				Phone # <label>404-000-0000</label>
				</td>
				<td style="width:40%">
				DOB: <label id="lblSex">1/1/1980</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="float:left;">
				Address: <label id="lblAddress">1001 Gtech Drive, Atlanta 50001, GA, USA</label>
				</td>
			</tr>
		</table>		
	</div>
	
	<div id="SearchArea" style="width:45%; float:left; padding:2.5%;">
		<input type="text" name="searchText" id="searchText" />
		<input type="button" id="btnSearch" value="Search" onclick="fetchDrug();" />
		<div  id="searchResultsTable">
			<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:10px;"></div>
		</div>
	
	</div>

	
	<div id="DrugInfo" style="width:45%; float:right; padding:2.5%;">
		<table>
			<tbody>
				<tr><td>Drug NDC:</td><td><input type="text" name="drugNDC" id="drugNDC" disabled="disabled"></td></tr>
				<tr><td>Drug name:</td><td><input type="text" name="drugName" id="drugName" disabled="disabled"></td></tr>
				<tr><td>Method:</td><td><input type="text" name="drugMethod" id="drugMethod" disabled="disabled"></td></tr>
				<tr><td>Size:</td><td><input type="text" name="drugDosage" id="drugSize" disabled="disabled"></td></tr>
				<tr><td>Amount:</td><td><input type="text" name="drugAmount" id="drugAmount"></td></tr>
				<tr><td></td><td><button>Create</button></td></tr>
			</tbody>
		</table>
	</div>
	

</body>
</html>