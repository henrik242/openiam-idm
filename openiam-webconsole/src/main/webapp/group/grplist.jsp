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
								<h2 class="contentheading">Group Manager</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="2" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>
			<tr>
				<td>		
<form:form commandName="groupListCmd">

				<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>FILTER GROUP LIST</legend>
				
					<table class="fieldsetTable"  width="100%" >
						
     <tr>
		<td class="plaintext">Filter By:</td>
        <td class="plaintext">Group Name: <form:input path="nameFilter" size="40"/> </td>
  	</tr>


    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
          	  <td><a href="groupDetail.cnt">New Group</a></td>
              <td align="right"> <input type="submit" value="Search"/> </td>
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
						<legend>GROUP LIST - ${resultsize} Group(s) found.</legend>
	
     	<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	

    
    <tr class="header">
  			<th>Name</td>
        <th>Description</td>
        <th>Status </td>
    </tr>
	

	<c:forEach items="${searchResult}" var="group">
		<tr class="tdlight">

			<td class="tableEntry"><a href="groupDetail.cnt?groupId=${group.grpId}">${group.grpName}</a></td>
			<td class="tableEntry">${group.description}</td>
			<td class="tableEntry">${group.status}</td>
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