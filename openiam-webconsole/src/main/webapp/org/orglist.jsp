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
								<h2 class="contentheading">Organization Manager</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="3" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>
			<tr>
				<td>

<form:form commandName="orgListCmd">
				<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>ORGANIZATION LIST</legend>
				
					<table class="fieldsetTable"  width="100%" >
									
     <tr>
		<td>Filter By:</td>
		<td>Organization Name:</td>
    <td><form:input path="nameFilter" size="40"/> </td>
  	</tr>	
     <tr>
		<td></td>
		<td>Classification:</td>
        <td>
        <form:select path="orgType">
			  			<form:option value=""  label="-Select a value" />
				  		<form:option value="ORGANIZATION"  label="ORGANIZATION" />
			  			<form:option value="DEPARTMENT"  label="DEPARTMENT" />
			  			<form:option value="DIVISION"  label="DIVISION" />
			  			<form:option value="VENDOR"  label="VENDOR" />
			  			<form:option value="PARTNER"  label="PARTNER" />
			  			<form:option value="SUBSIDIARY"  label="SUBSIDIARY" />
			  			<form:option value="CUSTOMER"  label="CUSTOMER" />
			  			<form:option value="AFFILIATE"  label="AFFILIATE" />
			  	    </form:select>
		 </td>
  	</tr>	
  	
          <tr class="button">
          	  <td colspan="2"><a href="orgDetail.cnt">New Organization</a></td>
              <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>
</td>
</tr>
</table>

</form:form>




<c:if test="${searchResult != null}" >

<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>ORGANIZATION LIST - ${resultsize} Organization(s) found.</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	

     
    <tr class="header">
  		<th>Name</td>
        <th>Description</td>
        <th>Classification</td>
        <th>Status </td>
      </tr>
	

	<c:forEach items="${searchResult}" var="org">
		<tr>

			<td class="tableEntry"><a href="orgDetail.cnt?orgId=${org.orgId}">${org.organizationName}</a></td>
			<td class="tableEntry">${org.description}</td>
			<td class="tableEntry">${org.classification}</td>
			<td class="tableEntry">${org.status}</td>
		</tr>
	</c:forEach>
          
</table>
</td>
</tr>
</table>

</c:if>

 </td>
</tr>
</table>