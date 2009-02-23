<%@ page import="java.util.*,java.text.*,diamelle.app.base.*,javax.servlet.http.*" %>
<%
 	Date dt = new Date(System.currentTimeMillis());
	java.text.DateFormat df = java.text.DateFormat.getDateInstance();

%>
<head>
<title>Diamelle Identity Management - Service Profile Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<html:html locale="true">
<body>
<table width="100%" cellspacing="1" cellpadding="2">
  <tr>
  	<td colspan="2"><h3>Service Profile Report</h3></td>
  	<td colspan="2"><b>Created: <%=df.format(dt)%></b></td>
  </tr>
   
  <tr class="tdheader">
		<td>Service ID</td>
		<td>Service Name</td>
		<td>Group ID</td>
		<td>Group Name</td>
		</tr>
<%
	javax.sql.DataSource dataSource = null;
	java.sql.Connection con = null;
	java.sql.PreparedStatement stmt = null;
	java.sql.ResultSet rs = null;

	String sql = (String)request.getAttribute("sql");

	try {
		javax.naming.Context ctx = new javax.naming.InitialContext();
		dataSource = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/diamellePool");
		con = dataSource.getConnection();
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		int rowCtr = 0;
		while (rs.next()) {
			rowCtr++;	
%>
	
  <tr bgcolor="#F5F7F9"> 
    <td><%=JSPUtil.display( rs.getString(1))%></td>		
    <td><%=JSPUtil.display(rs.getString(2))%></td>		
    <td><%=JSPUtil.display(rs.getString(3))%></td>		
    <td><%=JSPUtil.display(rs.getString(4))%></td>		
  </tr>

<%	} 
	if (rowCtr == 0) {
%>
	<tr>
		<td colspan="4">No records found.</td>
	</tr>
	
<%	
	}
	}catch(Exception se) { 
		se.printStackTrace();
	}finally {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
%>

</table>
</body>
</html:html>