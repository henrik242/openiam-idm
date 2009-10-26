<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<form:form commandName="requestDetailCmd">
<table>
	<tr>
      <td colspan="4" class="title">         
          <strong>Request Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tddark">Request Id</td>
              <td ><form:input path="request.requestId" size="32" maxlength="32" readonly="true" /></td>
              <td>Request Date</td>
			  <td><form:input path="request.requestDate" size="32" maxlength="32" readonly="true" /></td>
		  </tr>
          <tr>
			  <td class="tddark">Status</td>
              <td ><form:input path="request.status" size="32" maxlength="32" /></td>
              <td>Status Change Date</td>
			  <td><form:input path="request.statusDate" size="32" maxlength="32" /></td>
		  </tr>
          <tr>
			  <td class="tddark">Request Description</td>
              <td colspan="3"><form:input path="request.requestReason" size="50" maxlength="50" /></td>
		  </tr>
          <tr>
			  <td class="tddark">Request for User(s):</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr class="tdlight">
	              		<td>Name</td>
	              		<td>Department</td>
	              		<td>Division</td>
	              		<td>Location</td>
	              		<td>status</td>              			
              		</tr>
	              <c:forEach items="${requestDetailCmd.request.requestUsers}" var="user" >
	              	<tr class="tdlightnormal">
	              		<td>${user.firstName} ${user.middleInit} ${user.lastName}</td>
	              		<td>${user.deptCd}</td>
	              		<td>${user.division}</td>
	              		<td>${user.locationCd}</td>
	              		<td></td>
	              	</tr>
	              </c:forEach>
              	</table>
              	</td>
           
		  </tr>
          <tr>
			  <td class="tddark">Request Access for Applications:</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr class="tdlight">
	              		<td>Application Name</td>
	              		<td>Access Level</td>           			
              		</tr>
              		<tr class="tdlight">
	              		<td>Active Directory</td>
	              		<td></td>           			
              		</tr>
              		<tr class="tdlight">
	              		<td>VPN</td>
	              		<td></td>           			
              		</tr>
	              <c:forEach items="${requestDetailCmd.request.managedSyses}" var="system" >
	              	<tr class="tdlightnormal">
	              		<td>${system.name}</td>
	              		<td></td>
	              	</tr>
	              </c:forEach>
              	</table>
              </td>
		  </tr>		  		  
          <tr>
			  <td class="tddark">Approval History:</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr class="tdlight">
	              		<td>Date</td>
	              		<td>Action</td>  
	              		<td>Approver</td>    
	              		<td>Comment</td>           			
              		</tr>
	              <c:forEach items="${requestDetailCmd.request.approvalHistories}" var="history" >
	              	<tr class="tdlightnormal">
	              		<td>${history.actionDate}</td>
	              		<td>${history.action}</td>
	              		<td></td>
	              		<td></td>
	              	</tr>
	              </c:forEach>
              	</table>
              </td>
		  </tr>	
		  

         <tr>
    	  <td colspan="4">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="4" align="right"><input type="submit" name="btn" value="Claim"> <input type="submit" name="btn" value="Approve"> <input type="submit" name="btn" value="Reject"> </td>
          </tr>
</table>

</form:form>
