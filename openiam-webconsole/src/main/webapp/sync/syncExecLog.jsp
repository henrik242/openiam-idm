<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="700pt">
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
	
			<tr>
				<td>


<form:form commandName="syncExecLogCmd">
	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT SYNCHRONIZATION LOG</legend>


				<table class="fieldsetTable"  width="100%" >
							<tr>
								<td align="right">Configuration<font color="red">*</font></td>
								<td>
								    <form:select path="configId" multiple="false">
              								<form:option value="-" label="-Please Select-"/>
              								<form:options items="${configList}" itemValue="synchConfigId" itemLabel="name"/>
          							</form:select> 
								</td>
							</tr>

							<tr>
								<td align="right">Date (MM/dd/yyyy)</td>
								<td>
									<form:input path="startDate" size="15" /> - <form:input path="endDate" size="15"  /><br>
									<font color="red"><form:errors path="startDate" />
									<form:errors path="endDate" /></font>
								</td>
							</tr>
							<tr>
								<td align="right"></td>
						    <td align="right"><input type="Submit" value="Submit"></td>
							</tr>							
					</TABLE>
</TABLE>
</form:form>
			</td>
		</tr>

	</table>



<table width="700pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>Synchronization Log Results </legend>
	
     		<table class="resourceTable" cellspacing="2" cellpadding="2" width="700pt">	
  

      
         <tr>
			  <th>Date/Time</td>
              <th>Synch Config ID</td>
              <th>Session ID </td>
          </tr>
	
 <c:if test="${auditLog != null}" >
	<c:forEach items="${auditLog}" var="log">
		<tr>

			<td class="tableEntry"><a href="syncSessionLog.cnt?sessionId=${log.sessionId}" target="_blank">${log.actionDatetime}</a></td>
			<td class="tableEntry">${log.objectId}</td>
			<td class="tableEntry">${log.sessionId}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${auditLog == null}" >
		<tr>

			<td class="tableEntry" colspan="3">No transaction logs found.</td>

		</tr>
</c:if>


	</table>

          
          
</table>


