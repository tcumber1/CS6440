<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>
<link rel="stylesheet" type="text/css" href="CS6440Web.css" media="screen" />
</head>
<body >
	<div style="background-color : #56A5E7; width:100%; height:100%">
	<jsp:include page="header.jsp" />
	<h1>${param.title}</h1>
	
	<jsp:include page="${param.content}.jsp" />
	
	
	
	<jsp:include page="footer.jsp"/>
	</div>
	
</body>
</html>