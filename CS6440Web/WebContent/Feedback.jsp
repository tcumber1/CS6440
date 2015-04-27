<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.Map"
    import = "java.util.*"
    import = "java.lang.*"
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Feedback</title>

<style type="text/css">
textarea{
	overflow:"auto";
	resize:"none";
}

.starRating:not(old){
  display        : inline-block;
  width          : 7.5em;
  height         : 1.5em;
  overflow       : hidden;
  vertical-align : bottom;
}

.starRating:not(old) > input{
  margin-right : -100%;
  opacity      : 0;
}

.starRating:not(old) > label{
  display         : block;
  float           : right;
  position        : relative;
  background-image: url('Images/star-off.svg');
  background-size : contain;
}

.starRating:not(old) > label:before{
  content         : '';
  display         : block;
  width           : 1.5em;
  height          : 1.5em;
  background-image: url('Images/star-on.svg');
  background-size : contain;
  opacity         : 0;
  transition      : opacity 0.2s linear;
}

.starRating:not(old) > label:hover:before,
.starRating:not(old) > label:hover ~ label:before,
.starRating:not(:hover) > :checked ~ label:before{
  opacity : 1;
}

</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
function saveFeedback(){
	var sRating = document.querySelector('input[name = "rating"]:checked').value;
	var rating = sRating.substring(0,1); //I'm not sure why but a ')' is being added to all of the values for the radio buttons
	var drugID = document.getElementById("drugID").value;
	var patientID = document.getElementById("patientID").value;
	var comments = document.getElementById("comments").value
	$.ajax({
		url: "ProvideFeedback",
		data: {patientID: patientID, drugID: drugID, rating: rating, comments: comments},
		type: "GET",
		dataType: "json",
		success: function(response) {alert(response.message); document.getElementById("saveFeedback").textContent = "Update Feedback";},
		failure: function() { alert("Failure");},
		error: function(data) {alert("There was an error");}
	});
}

</script>

</head>
<body>
<%
Map<String, Object> drugInfo = (Map<String, Object>)session.getAttribute("drugInfo");
Map<String, Object> userFeedback = (Map<String, Object>)session.getAttribute("userFeedback");
ArrayList<Map<String, Object>> othersFeedback = (ArrayList<Map<String, Object>>) session.getAttribute("othersFeedback");
%>
<div id="page" style="padding:10px 2.5%">
<div style="text-align:center; width:100%; float:left; padding-right:0px;">
	<h1>Feedback for <%=drugInfo.get("drugName").toString().toUpperCase() %></h1>
</div>

<br />
<h2>Your Feedback:</h2>
<input type="hidden" id="patientID" value="<%= session.getAttribute("patientID") %>">
<input type="hidden" id ="drugID" value="<%= drugInfo.get("productID") %>">
<% if(userFeedback.get("comment") == null){ %>
	<textarea rows="4" cols="150" placeholder="Enter your feedback." id="comments"></textarea>
	<div style="padding: 10px 0px">
	<span class="starRating">
  		<input id="rating5" type="radio" name="rating" value="5">
  		<label for="rating5">5</label>
  		<input id="rating4" type="radio" name="rating" value="4">
  		<label for="rating4">4</label>
  		<input id="rating3" type="radio" name="rating" value="3">
  		<label for="rating3">3</label>
  		<input id="rating2" type="radio" name="rating" value="2">
  		<label for="rating2">2</label>
  		<input id="rating1" type="radio" name="rating" value="1">
  		<label for="rating1">1</label>
	</span>
	</div>
	<div>
		<button onclick="saveFeedback()" id="saveFeedback">Save Feedback</button>
	</div>
<%
}
else{
%>
	<textarea rows="4" cols="150" id="comments"><%=userFeedback.get("comment") %></textarea>
	<div style="padding-top: 5px; padding-bottom: 10px">
	<span class="starRating">
			<%for(int i = 5; i > 0; i--){
				if(Integer.parseInt(userFeedback.get("rating").toString()) == i){ %>
					<input id="rating<%=i %>" type="radio" name="rating" value="<%=i%>)" checked="checked">
				<%}
				else{ %>
					<input id="rating<%=i %>" type="radio" name="rating" value="<%=i%>)">
				<%}%>
				<label for="rating<%=i %>"><%=i %></label>
			<%}%>
	</span>
	</div>
	<div>
		<button onclick="saveFeedback()" id="saveFeedback">Update Feedback</button>
	</div>
<%	
}
%>
<div style="padding-bottom: 30px">
<h2 style="padding-top: 10px">Others' Feedback:</h2>
<% Iterator<Map<String, Object>> i = othersFeedback.iterator();
if (!i.hasNext()){ %>
	<div style="padding-top: 10px; padding-bottom:20px">No other feedback available.</div>
<% }
else { 
	while( i.hasNext()){
		Map<String, Object> item = i.next(); %>
		<%=item.get("comment") %> <br>
		<img alt="Stars" src="Images/Stars<%=item.get("rating") %>.svg" width="117.63" style="padding-top:10px; padding-bottom:20px">
		
		<% if (i.hasNext()){ %>
			<hr>
		<% }%>
	<%}
}
%>
</div>

<%@ include file="WEB-INF/footer.jsp" %>
</div>
</body>
</html>