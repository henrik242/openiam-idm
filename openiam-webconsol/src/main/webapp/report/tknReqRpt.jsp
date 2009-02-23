<%@ page import="java.util.*,java.text.*,diamelle.app.base.*,javax.servlet.http.*" %>
<%
 	Date dt = new Date(System.currentTimeMillis());
	java.text.DateFormat df = java.text.DateFormat.getDateInstance();

%>
<head>
<title>Diamelle Identity Management - User Profile Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<html:html locale="true">
<body>
<table width="100%" cellspacing="1" cellpadding="2">
  <tr>
  	<td colspan="10"><h3>User Profile Report</h3></td>
  	<td colspan="3"><b>Created: <%=df.format(dt)%></b></td>
  </tr>
   
  <tr class="tdheader">
		<td>Login</td>
		<td>Name</td>
		<td>Service</td>
		<td>Status</td>
		<td>Create Date</td>
		<td>Group Name</td>
		<td>TKN Ser. Nbr.</td>
		<td>Current Login Host</td>
		<td>Address</td>	
		<td>City</td>	
		<td>State</td>	
		<td>Postal Code</td>
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
		
    <td><%=JSPUtil.display(rs.getString(2))%> <%=JSPUtil.display(rs.getString(3))%></td>
		
    <td><%=JSPUtil.display(rs.getString(4))%></td>
		
    <td><%=JSPUtil.display(rs.getString(5))%></td>
		
    <td><%=JSPUtil.display(rs.getString(6))%></td>
		
    <td><%=JSPUtil.display(rs.getString(7))%></td>
		
    <td><%=JSPUtil.display(rs.getString(8))%></td>
		
    <td><%=JSPUtil.display(rs.getString(9))%></td>
    <td><%=JSPUtil.display(rs.getString(10))%></td>
    <td><%=JSPUtil.display(rs.getString(11))%></td>
    <td><%=JSPUtil.display(rs.getString(12))%></td>
    <td><%=JSPUtil.display(rs.getString(13))%></td>
   </tr>

<%	} 
	if (rowCtr == 0) {
%>
	<tr>
		<td colspan="13">No records found.</td>
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