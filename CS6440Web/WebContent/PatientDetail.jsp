<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Perscription - Patient View</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

	$(document).ready(function () {
		
	});

	function OnInitLoad() {
		
		var patID = GetURLParameter("id");
		
		alert("Test id = " + patID);
		var url = "https://taurus.i3l.gatech.edu:8443//HealthPort/fhir/Patient/";
		url = url + patID;
		
		url = url + "?_format=xml";
		alert("url = " + url);
		$.ajax({
			url: "PatientInfo",
			data: { id : GetURLParameter("id")},
			type: "GET",
			dataType: "xml",
			success: function( data) { parse(data); },
			failure: function() { alert("Failure: ");},
			error: function() { alert("Error: Something went wrong");}
		
		})
		
		
	}
	
	function parse(document){
		alert("document = " + document);
		$(document).find("name").each(function() {
			var nm = $(this).find("given").text() + " " + $(this).find("family").text();
			$("#lblName").text(nm);
					
		})	
	}
	
	
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
</script>

<body>
<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:20px;"></div>
		<div style="text-align:center; width:100%; float:left; padding-right:0px;">
			<h1>Patient View</h1>
		</div>
	
		<div style="float:left; width:95%; border-bottom: solid 2px; background-color : #56A5E7; border-radius: 25px;
	    background: #56A5E7; padding: 20px; height: auto; margin:10px;">
				<table style="width:100%">
					<tr>
						<td style="width:40%" style="float:left;">
						Name: <label id="lblName">Carla Sample</label>
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
		<div>
			<input type="button" id="btnSummary" onclick="OnInitLoad();" value="Summary">
		</div>
	
	<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:20px;"></div>
 	
 	
 	<%@ include file="WEB-INF/footer.jsp" %>

</body>
</html>
