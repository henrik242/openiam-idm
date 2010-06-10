<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.openiam.webadmin.admin.JSPUtil" %>
<%@ page import="org.openiam.idm.srvc.user.dto.User" %>

<head>
<title>OpenIAM CE - Administration Console - Audit Report</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<!-- OpenIAM Legacy style sheets -->
<link href="<%=request.getContextPath()%>/diamelleapp.css" rel="stylesheet" type="text/css">
</head>

<img src="<%=request.getContextPath()%>/images/openiamlog.jpg">



<table width="100%">
	<tr>
      <td colspan="7" class="title">         
          <strong>User Report</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="7" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

     <tr>
		<td class="tdheader">NAME</td>
		<td class="tdheader">TITLE</td>
		<td class="tdheader">DEPT </td>
		<td class="tdheader">LOCATION</td>
		<td class="tdheader">EMAIL</td>
		<td class="tdheader">PHONE</td>
  	</tr>
<% 
Collection userData = (Collection)session.getAttribute("rptResult");
if (userData != null && !userData.isEmpty()) {
	Iterator it = userData.iterator();
	while (it.hasNext()) {
		User user = (User)it.next();
%>  
  	
  	  	
    	<tr class="plaintext">
			<td><%=JSPUtil.display(user.getFirstName())%> <%=JSPUtil.display(user.getMiddleInit())%> <%=JSPUtil.display(user.getLastName())%></td>
			<td><%=JSPUtil.display(user.getTitle())%></td>
			<td><%=JSPUtil.display(user.getDeptCd())%></td>
			<td><%=JSPUtil.display(user.getLocationCd())%></td>
			<td><%=JSPUtil.display(user.getEmail())%></td>
			<td><%=JSPUtil.display(user.getPhoneNbr())%></td>
    	</tr>
<% 	}
} %>
</table>



