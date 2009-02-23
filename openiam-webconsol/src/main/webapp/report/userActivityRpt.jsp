<%@ page import="java.util.*,java.text.*,org.openiam.webadmin.busdel.base.*,javax.servlet.http.*" %>
<%
 	Date dt = new Date(System.currentTimeMillis());
	java.text.DateFormat df = java.text.DateFormat.getDateInstance();;

%>
<head>
<title>Diamelle Identity Management - User Activity Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<body>
<table width="100%" cellspacing="1" cellpadding="2">
  <tr>
  	<td colspan="6"><h3>User Activity Report</h3></td>
  	<td colspan="2"><b>Created: <%=df.format(dt)%></b></td>
  </tr>
  <tr class="tdheader">
		<td width="13%">Time</td>
		<td width="10%">Service</td>
		<td width="10%">Login</td>
		<td width="10%">Name</td>
		<td width="10%">Session</td>
		<td width="10%">Session Duration</td>
		<td width="10%">Action</td>
		<td width="25%">Description</td>
		<td width="10%">URL</td>
		<td width="8%">IP / Host</td>
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
	
  <tr class="tdlightnormal"> 
    <td width="13%"><%=JSPUtil.display( rs.getString(1)).substring(0,19)%></td>
		
    <td width="10%"><%=JSPUtil.display(rs.getString(2))%></td>
		
    <td width="10%"><%=JSPUtil.display(rs.getString(3))%></td>
		
    <td width="10%"><%=JSPUtil.display(rs.getString(4))%> <%=JSPUtil.display(rs.getString(5))%></td>
		
    <td width="10%"></td>
    <td width="10%"></td>

    <td width="10%"><%=JSPUtil.display(rs.getString(6))%></td>
		
    <td width="25%"><%=JSPUtil.display(rs.getString(7))%></td>
		
    <td width="10%"><%=JSPUtil.display(rs.getString(8))%></td>
		
    <td width="8%"><%=JSPUtil.display(rs.getString(9))%></td>
	</tr>

<%	} 
	if (rowCtr == 0) {
%>
	<tr>
		<td colspan="8">No records found.</td>
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
