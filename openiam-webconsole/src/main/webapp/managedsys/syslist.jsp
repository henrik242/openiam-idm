<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="managedSysListCmd">

<table width="60%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Managed Connections</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
     <tr>
		<td class="plaintext">Filter Security Domain:</td>
         <td>
     	
           <form:select path="domainId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${secDomainAry}" itemValue="domainId" itemLabel="name"/>
          </form:select>       
	
         </td>
  	</tr>
         <tr>
    	  <td></td>
    	  <td><font color="red"><form:errors path="domainId" /></font> </td>
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
          <strong>Managed Connection List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
         <tr>
			  <td colspan="5" class="plaintext" >${searchResults} Managed Resource(s) found.</td>
              
          </tr>  
      
         <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Description</td>
              <td class="tdheader" >Status </td>
			  <td class="tdheader" >hostURL</td>
			  <td class="tdheader" ></td>
          </tr>
	

	<c:forEach items="${managedSysAry}" var="managedSys">
		<tr class="tdlight">

			<td> ${managedSys.name}</td>
			<td> ${managedSys.description}</td>
			<td> ${managedSys.status}</td>
			<td> ${managedSys.hostUrl} </td> 
			<td><a href="managedSysConnection.cnt?sysId=${managedSys.managedSysId}&menuGroup=MANAGESYS">View Detail</a></td>
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
              <td colspan="5" align="left"> <a href="managedSysConnection.cnt?domainId=${domainId}">New Managed Resource</a></td>
          </tr>
          
          
</table>

</c:if>