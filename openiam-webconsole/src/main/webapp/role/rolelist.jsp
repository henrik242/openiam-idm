<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="roleListCmd">

<table width="60%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Roles</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  <% if (request.getAttribute("message") != null) { %>
   <tr>
 		<td colspan="2" align="center" class="msg" >
 		  <%=request.getAttribute("message") %>
 		</td>
  </tr> 
  <% } %>
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
          <strong>Role List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
         <tr>
			  <td colspan="5" class="plaintext" >${searchResults} Role(s) found.</td>
              
          </tr>  
      
         <tr>
			  <td class="tdheader" >Name</td>
              <td class="tdheader" >Description</td>
              <td class="tdheader" >Status </td>
          </tr>
	

	<c:forEach items="${roleList}" var="role">
		<tr class="tdlight">

			<td><a href="roleDetail.cnt?domainid=${role.id.serviceId}&roleid=${role.id.roleId}"> ${role.roleName}</a></td>
			<td> ${role.description}</td>
			<td> ${role.status}</td>
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
              <td colspan="5" align="left"> <a href="roleDetail.cnt?domainid=${domainId}">New Role</a></td>
          </tr>
          
          
</table>

</c:if>