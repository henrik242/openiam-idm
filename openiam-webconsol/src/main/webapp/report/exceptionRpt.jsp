<%@ page import="java.util.*,java.text.*,diamelle.app.base.*,javax.servlet.http.*" %>
<%
 	Date dt = new Date(System.currentTimeMillis());
	java.text.DateFormat df = java.text.DateFormat.getDateInstance();;

%>
<head>
<title>Diamelle Identity Management - Exception Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<html:html locale="true">
<body>
<table width="100%" cellspacing="1" cellpadding="2">
  <tr>
  	<td colspan="3"><h3>Exception Report</h3></td>
  	<td><b>Created: <%=df.format(dt)%></b></td>
  </tr>
  <tr class="tdheader">
		<td width="15%">Service</td>
		<td width="15%">Login</td>
		<td width="15%">Name</td>
		<td width="15%">Status</td>
		<td width="15%">Last Login</td>
	</tr>
<%
	javax.sql.DataSource dataSource = null;
	java.sql.Connection con = null;
	java.sql.PreparedStatement stmt = null;
	java.sql.ResultSet rs = null;

	String sql = (String)request.getAttribute("sql");

%>
	

	<tr>
		<td colspan="5">No exceptions found.</td>
	</tr>
	


</table>
</body>
</html:html>