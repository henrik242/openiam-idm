<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


		<table  width="600pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Managed Connections</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
					<tr>
				<td>		
<form:form commandName="managedSysListCmd">

				<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>SEARCH FOR CONNECTIONS</legend>
				
					<table class="fieldsetTable"  width="100%" >

     <tr>
		<td>Filter Security Domain:</td>
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
              <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>
</td>
</tr>
</table>
</form:form>

<c:if test="${searchResults != null}" >
<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CONNECTION LIST- ${searchResults} Connections(s) found.</legend>
						
						<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
       
		         <tr class="header">
					  		<th>Name</td>
		            <th>Description</td>
		            <th>Status </td>
					  		<th>hostURL</td>
		          </tr>
							<c:forEach items="${managedSysAry}" var="managedSys">
								<tr>
						
									<td class="tableEntry"><a href="managedSysConnection.cnt?sysId=${managedSys.managedSysId}&connectorId=${managedSys.connectorId}&menuGroup=MANAGESYS">${managedSys.name}</a></td>
									<td class="tableEntry"> ${managedSys.description}</td>
									<td class="tableEntry"> ${managedSys.status}</td>
									<td class="tableEntry"> ${managedSys.hostUrl} </td> 
								</tr>
							</c:forEach>
					</table>
			</td>
			</tr>
          <tr>
              <td align="left"> <a href="managedSysConnection.cnt?domainId=${domainId}">New Managed Resource</a></td>
          </tr>
          
          
</table>

</c:if>

