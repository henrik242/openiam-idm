<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<form:form commandName="requestSearchCriteria">
<table>
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
	              <form:option value="Open" />
          		</form:select>
          	  </td>
			  <td>
			  	<!-- 
			  		<b>Date(MM/DD/YYYY):<b> <form:input path="startDate" /> - <form:input path="endDate" /></td>
			  	-->
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
		<tr>
	      <td><strong>Date</strong></td>
	      <td><strong>Status</strong></td>
	      <td><strong>Description</strong></td>
	      <td><strong>Request For</strong></td>
	      <td></td>
	   </tr>
	   
	<c:forEach items="${resultList}" var="provisionRequest">
		<tr>
			<td> ${provisionRequest.requestDate}</td>
			<td> ${provisionRequest.status}</td>
			<td> ${provisionRequest.requestReason}</td>
			<td>  </td> 
			<td><a href="requestDetail.selfserve?requestId=${provisionRequest.requestId}">View Detail</a></td>
		</tr>
	</c:forEach>
	
	</table>
</c:if>
