<%@ page import="org.openiam.idm.srvc.audit.dto.IdmAuditLog, org.openiam.webadmin.admin.JSPUtil" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
<title>OpenIAM Identity Manager 2.1.0 - Administration Console</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/screen.css">

</head>

<% List<IdmAuditLog> auditLogList = (List<IdmAuditLog>)session.getAttribute("eventList"); %>

		<table  width="1800pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Synchronization Transaction Log Viewer</h2>
						</td>
						</tr>
					</table>
			</td>

	</table>

<% if (auditLogList != null ) {  %>


<table width="1800pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Synchronization Session Details </legend>

     		<table class="resourceTable" cellspacing="1" cellpadding="1" width="1800pt">



         <tr>
			  <th>Date/Time</td>
			  <th>Object Type</td>
              <th>User ID</td>
              <th>Principal</th>
              <th>System</th>
              <th>Action</td>
              <th>Result</td>
              <th>Reason</td>
              <th>Explanation</td>
              <th>Request ID</td>
          </tr>

<ul>
	<% for (IdmAuditLog log : auditLogList) { %>


		<tr>

			<td class="tableEntry">
				<% if ( log.getObjectTypeId().equalsIgnoreCase("USER") && !log.getTargetSystemId().equals('0') ) { %>
					<li>
                <% } %>
				<fmt:formatDate type="both" dateStyle="default" timeStyle="default" value="${log.actionDatetime}" /></td>
			<td class="tableEntry"><%=log.getObjectTypeId()%></td>
			<td class="tableEntry">
                <% if ( !log.getObjectTypeId().equalsIgnoreCase("SYNCH_USER")  ) { %>
                    <%=log.getObjectId() %>
                <% } %>
			</td>
			<td class="tableEntry"><%=JSPUtil.display(log.getPrincipal())%></td>
			<td class="tableEntry"><%=JSPUtil.display(log.getTargetSystemId())%></td>
			<td class="tableEntry"><%=JSPUtil.display(log.getActionId())%></td>
			<td class="tableEntry"><%=JSPUtil.display(log.getActionStatus())%></td>
			<td class="tableEntry"><%=JSPUtil.display(log.getReason())%></td>
            <td class="tableEntry"><%=JSPUtil.display(log.getReasonDetail())%></td>
			<td class="tableEntry"><%=JSPUtil.display(log.getRequestId())%></td>
		</tr>
  <% } %>


	</table>
</ul>


</table>

<% } %>