<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="metadataTypeListCmd">

<table width="60%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Metadata Types</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
     <tr>
		<td class="plaintext">Select Type Category:</td>
         <td>
     	
           <form:select path="categoryId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${categoryAry}" itemValue="categoryId" itemLabel="categoryName"/>
          </form:select>       
	
         </td>
  	</tr>
         <tr>
    	  <td></td>
    	  <td class="error"><form:errors path="categoryId" /></td>
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
          <strong>Metadata Type List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
         <tr>
			  <td colspan="5" class="plaintext" >${searchResults} Metadata Types(s) found.</td>
              
          </tr>  
      
         <tr>
			  <td class="tdheader" >Type ID</td>
              <td class="tdheader" >Description</td>
              <td class="tdheader" >Status </td>
			  <td class="tdheader" ></td>
          </tr>
	

	<c:forEach items="${typeAry}" var="metadataType">
		<tr class="tdlight">

			<td> ${metadataType.metadataTypeId}</td>
			<td> ${managedSys.description}</td>
			<td> ${managedSys.active}</td>
			<td><a href="metadataType.cnt?typeId=${metadataType.metadataTypeId}&menuGroup=METADATA">View Detail</a></td>
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
              <td colspan="5" align="left"> <a href="metadataType.cnt?typeId=NEW">New Managed System</a></td>
          </tr>
          
          
</table>

</c:if>