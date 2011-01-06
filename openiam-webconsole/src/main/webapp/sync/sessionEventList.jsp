<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 




		<table  width="1200pt">
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

<c:if test="${auditLog != null}" >

<table width="1200pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Synchronization Session Details </legend>
	
     		<table class="resourceTable" cellspacing="1" cellpadding="1" width="900pt">	
  

      
         <tr>
			  <th>Date/Time</td>
			   <th>Object Type</td>
              <th>User ID</td>
              <th>Principal</th>
              <th>System</th>
              <th>Action</td>
              <th>Result</td>
              <th>Reason</td>
              <th>Request ID</td>
          </tr>
	
<ul>
	<c:forEach items="${auditLog}" var="log">
		<tr>

			<td class="tableEntry">
				<c:if test="${log.objectTypeId == 'USER' && log.targetSystemId != '0' }" >
					<li>
				</c:if>
				<fmt:formatDate type="both" dateStyle="default" timeStyle="default" value="${log.actionDatetime}" /></td>
			<td class="tableEntry">${log.objectTypeId}</td>
			<td class="tableEntry">
				<c:if test="${log.objectTypeId != 'SYNCH_USER' }" >
				${log.objectId}
				</c:if>
			</td>
			<td class="tableEntry">${log.principal}</td>
			<td class="tableEntry">${log.targetSystemId}</td>
			<td class="tableEntry">${log.actionId}</td>
			<td class="tableEntry">${log.actionStatus}</td>
			<td class="tableEntry">${log.reason}</td>
			<td class="tableEntry">${log.requestId}</td>
		</tr>
	</c:forEach>
	
	</table>
</ul>
          
          
</table>

</c:if>
