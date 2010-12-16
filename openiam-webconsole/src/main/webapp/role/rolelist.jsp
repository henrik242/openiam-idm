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
								<h2 class="contentheading">Role Management</h2>
						</td>
						</tr>
					</table>
				</td>
			</TR>
			<% if (request.getAttribute("message") != null) { %>
		   <tr>
		 		<td colspan="2" align="center" class="msg" >
		 		  <%=request.getAttribute("message") %>
		 		</td>
		  </tr> 
  		<% } %>
					<tr>
				<td>	

<form:form commandName="roleListCmd">

	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>SELECT ROLES</legend>
	
	<table class="fieldsetTable"  width="600pt" >
		
 

     <tr>
		<td class="plaintext">Filter By Security Domain:</td>
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
	</Table>

      <tr class="buttonRow">
          <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
      </tr>
</table>

</form:form>

<c:if test="${searchResults != null}" >

<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>ROLE LIST - ${searchResults} Role(s) found.</legend>
	
     		<table class="resourceTable" cellspacing="2" cellpadding="2" width="600pt">	
  

      
         <tr>
			  <th>Name</td>
              <th>Description</td>
              <th>Status </td>
          </tr>
	

	<c:forEach items="${roleList}" var="role">
		<tr>

			<td class="tableEntry"><a href="roleDetail.cnt?domainid=${role.id.serviceId}&roleid=${role.id.roleId}"> ${role.roleName}</a></td>
			<td class="tableEntry">${role.description}</td>
			<td class="tableEntry">${role.status}</td>
		</tr>
	</c:forEach>
	
	</table>

          <tr>
              <td align="left"> <a href="roleDetail.cnt?domainid=${domainId}">New Role</a></td>
          </tr>
          
          
</table>

</c:if>