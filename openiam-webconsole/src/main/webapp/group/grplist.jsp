<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="groupListCmd">

<table width="60%">

<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="2" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>

	<tr>
      <td colspan="2" class="title">         
          <strong>Group List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
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

</form:form>

<c:if test="${searchResult != null}" >

<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Group List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
   </tr> 
  
    <tr>
		<td colspan="5" class="plaintext" >${resultsize} Group(s) found.</td>       
    </tr>  
      
    <tr>
  		<td class="tdheader" >Name</td>
        <td class="tdheader" >Description</td>
        <td class="tdheader" >Status </td>
      </tr>
	

	<c:forEach items="${searchResult}" var="group">
		<tr class="tdlight">

			<td><a href="groupDetail.cnt?groupId=${group.grpId}">${group.grpName}</a></td>
			<td> ${group.description}</td>
			<td> ${group.status}</td>
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

           
</table>

</c:if>

