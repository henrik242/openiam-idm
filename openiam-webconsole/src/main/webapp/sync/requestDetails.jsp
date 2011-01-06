<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="850pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Synchronization Request Transaction Log Viewer</h2>
						</td>
						</tr>
					</table>
			</td>
	
	</table>

<c:if test="${auditLog != null}" >

<table width="850pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Synchronization Request Details </legend>
	
     		<table class="resourceTable" cellspacing="2" cellpadding="2" width="850pt">	
  

      
         <tr>
			  <th>Date/Time</td>
              <th>User ID</td>
              <th>Action</td>
              <th>Result</td>
              <th>Match Field</td>
              <th>Request ID</td>
          </tr>
	

	<c:forEach items="${auditLog}" var="log">
		<tr>

			<td class="tableEntry">${log.actionDatetime}</a></td>
			<td class="tableEntry">${log.objectId}</td>
			<td class="tableEntry">${log.actionId}</td>
			<td class="tableEntry">${log.actionStatus}</td>
			<td class="tableEntry">${log.customAttrname1}:${log.customAttrvalue1}</td>
			<td class="tableEntry">${log.requestId}</td>
		</tr>
	</c:forEach>
	
	</table>

          
          
</table>

</c:if>
