<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 




<form:form commandName="resourceListCmd">

<table width="60%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Resources</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
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

    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>

</form:form>


<c:if test="${searchResults != null}" >

<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Resource List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
         <tr>
			  <td colspan="5" class="plaintext" >${searchResults} Resource(s) found.</td>
              
          </tr>  
      
         <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Description</td>

          </tr>
	

	<c:forEach items="${resourceList}" var="resource">
		<tr class="tdlight">

			<td><a href="resourceDetail.cnt?resId=${resource.resourceId}&menugrp=SECURITY_RES">${resource.name}</a></td>
			<td> ${resource.description}</td>
		</tr>
	</c:forEach>

         <tr>
    	  <td colspan="5">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="5" align="left"> <a href="resourceDetail.cnt?menugrp=SECURITY_RES&restype=${resTypeId}">New  Resource</a></td>
          </tr>
          
          
</table>

</c:if>