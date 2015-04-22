<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<input type="text" name="search" />
		<input type="submit" id="'btnSearch" value="Search" />
		<table id="searchResultsTable">
			<tbody>
	        	<tr><td>Item one</td></tr>
	        	<tr><td>Item two</td></tr>
	        	<tr><td>Item three</td></tr>
	    	</tbody>
		</table>
	</div>
	<script>
		function addRowHandlers() {
		    var table = document.getElementById("searchResultsTable");
		    var rows = table.getElementsByTagName("tr");
		    for (i = 0; i < rows.length; i++) {
		        var currentRow = table.rows[i];
		        var createClickHandler = 
		            function(row) 
		            {
		                return function() { 
		                   var cell = row.getElementsByTagName("td")[0];
		                   var id = cell.innerHTML;
		                   alert("id:" + id);
		                   //TODO: Add code to populate textboxes
		                 };
		            };
		
		        currentRow.onclick = createClickHandler(currentRow);
		    }
		}
		window.onload = addRowHandlers();
	</script>
	<div id="DrugInfo" style="width:45%; float:right; padding:2.5%;">
		<table>
			<tbody>
				<tr><td>Drug name:</td><td><input type="text" name="drugName" id="drugName" disabled="disabled"></td></tr>
				<tr><td>Method:</td><td><input type="text" name="drugMethod" id="drugMethod" disabled="disabled"></td></tr>
				<tr><td>Size:</td><td><input type="text" name="drugSize" id="drugSize" disabled="disabled"></td></tr>
				<tr><td>Amount:</td><td><input type="text" name="drugAmount" id="drugAmount"></td></tr>
				<tr><td></td><td><input type="submit" id="btnCreatePrescription" value="Create" /></td></tr>
			</tbody>
		</table>
	</div>
	

</body>
</html>