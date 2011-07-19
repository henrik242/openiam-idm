<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<form:form commandName="requestSearchCriteria">
<table width="100%" cellpadding="1">
	<tr>
      <td colspan="4" class="title">         
          <strong>Manage Requests</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td>Filter requests by:</td>
              <td align="right"><b>Status:</b></td>
              <td>
              	<form:select path="status">
	              <form:option value="PENDING_APPROVAL" />
	              <form:option value="APPROVED" />
	              <form:option value="REJECTED" />
	              <form:option value="CLAIMED" />
          		</form:select>
          	  </td>
          </tr>
          <tr>
			  <td></td>
              <td align="right"><b>Date (MM/DD/YYYY):</b></td>
              <td>
              <form:input path="startDate" size="10" /> - <form:input path="endDate" size="10" /> 
          	  </td>

          </tr>
          <tr>
			  <td></td>
              <td align="right"><b>Requests By Direct Reports:</b></td>
              <td>
              	 <form:select path="requestorId">
	            	  <form:option value="-" label="-Please Select-"/>
	                  <c:forEach items="${directReports}" var="direct">
		                <form:option value="${direct.employee.userId}" label="${direct.employee.firstName} ${direct.employee.lastName}" />
		              </c:forEach>
          		 </form:select>
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
              <td colspan="4" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>

</form:form>
<c:if test="${resultList != null}" >
	<table width="100%">
		<tr class="tdlight">
	      <td>Create Date</td>
	      <td>Requestor</td>
	      <td>Status</td>
	      <td></td>
	   </tr>
	   
	<c:forEach items="${resultList}" var="provisionRequest">
		<tr>
			<td> ${provisionRequest.requestDate}</td>
			<td> </td>
			<td>  ${provisionRequest.status}</td>
			<td><a href="requestDetail.selfserve?requestId=${provisionRequest.requestId}">View</a></td>
		</tr>
	</c:forEach>
	
	</table>
</c:if>
