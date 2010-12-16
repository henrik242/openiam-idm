<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


   		<table  width="800pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Resource Management</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>	

<form:form commandName="resourceListCmd">

	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT RESOURCE TYPE</legend>
	
	<table class="fieldsetTable"  width="600pt" >

     <tr>
		<td class="plaintext">Select Resource Type:</td>
         <td>
     	
           <form:select path="resourceTypeId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${resTypeList}" itemValue="resourceTypeId" itemLabel="description"/>
          </form:select>       
	
         </td>
  	</tr>
         <tr>
    	  <td></td>
    	  <td class="error"><form:errors path="resourceTypeId" /></td>
    	</tr>	
	</table>

          <tr class="buttonRow">
              <td align="right"> <input type="submit" value="Search"> </td>
          </tr>
	</table>

</form:form>


<c:if test="${searchResults != null}" >

<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>RESOURCE LIST - ${searchResults} Resource(s) found.</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
         <tr class="header">
			  	<th>Name</td>
          <th>Description</td>
         </tr>
	

	<c:forEach items="${resourceList}" var="resource">
		<tr>

			<td class="tableEntry"><a href="resourceDetail.cnt?resId=${resource.resourceId}&menugrp=SECURITY_RES">${resource.name}</a></td>
			<td class="tableEntry">${resource.description}</td>
		</tr>
	</c:forEach>
	</table>
</td>
</tr>
	       <tr>
              <td colspan="2" align="left"> <a href="resourceDetail.cnt?menugrp=SECURITY_RES&restype=${resTypeId}">New  Resource</a></td>
          </tr>
  
          
</table>

</c:if>