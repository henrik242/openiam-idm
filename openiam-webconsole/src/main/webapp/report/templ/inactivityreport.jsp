<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.openiam.webadmin.admin.JSPUtil" %>
<%@ page import="org.openiam.idm.srvc.audit.dto.IdmAuditLog" %>

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
          <strong>Inactivity Report</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="7" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

     <tr>
		<td class="tdheader">ACTION</td>
		<td class="tdheader">STATUS</td>
		<td class="tdheader">REASON</td>
		<td class="tdheader">ACTION TIME</td>
		<td class="tdheader">REQUESTOR ID</td>
		<td class="tdheader">TARGET PRINCIPAL</td>
		<td class="tdheader">REQUEST_ID</td>
  	</tr>
<% 
Collection auditData = (Collection)session.getAttribute("rptResult");
if (auditData != null && !auditData.isEmpty()) {
	Iterator it = auditData.iterator();
	while (it.hasNext()) {
		IdmAuditLog log = (IdmAuditLog)it.next();
%>  
  	
  	  	
    	<tr class="plaintext">
			<td><%=JSPUtil.display(log.getActionId())%></td>
			<td><%=JSPUtil.display(log.getActionStatus())%></td>
			<td><%=JSPUtil.display(log.getReason())%></td>
			<td><%=JSPUtil.display(log.getActionDatetime())%></td>
			<td><%=JSPUtil.display(log.getUserId())%></td>
			<td><%=JSPUtil.display(log.getPrincipal())%></td>
			<td><%=JSPUtil.display(log.getRequestId())%></td>
    	</tr>
<% 	}
} %>
</table>



