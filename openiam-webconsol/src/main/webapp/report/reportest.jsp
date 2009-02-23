<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 			<h1>Using Multi-Format View</h1>
				<a href="auditReport.report?action=auditReport&format=pdf
 				<a href="/report/simpleReportMulti.pdf">PDF</a>
 				<a href="/report/simpleReportMulti.html">HTML</a>

				<form name="testForm1" method="POST" action="simpleReportPost.pdf">
    					<input name="reportTitle" value="foo" type="test"/>
    					<input type="Submit" value="Try with a POST"/>
    			</form>
    			
			<h2>With .action Extension</h2>

  				<form name="testForm2" method="POST" action="simpleReportPost.action">
   					<input name="reportTitle" value="foo" type="test"/>
   					<input type="Submit" value="Try with a POST"/>
   				</form>


</body>
</html>