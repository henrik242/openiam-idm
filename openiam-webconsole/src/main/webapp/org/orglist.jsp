<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="orgListCmd">

<table width="60%">
<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="3" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>

	<tr>
      <td colspan="3" class="title">         
          <strong>Organization List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="3" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
     <tr>
		<td class="plaintext">Filter By:</td>
		<td class="plaintext">Organization Name:</td>
        <td class="plaintext"><form:input path="nameFilter" size="40"/> </td>
  	</tr>	
     <tr>
		<td class="plaintext"></td>
		<td class="plaintext">Classification:</td>
        <td class="plaintext">
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
  	
    	<tr>
 		   <td colspan="3" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
          	  <td><a href="orgDetail.cnt">New Organization</a></td>
              <td colspan="2" align="right"> <input type="submit" value="Search"> </td>
          </tr>
</table>

</form:form>

<c:if test="${searchResult != null}" >

<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Organization List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
   </tr> 
  
    <tr>
		<td colspan="5" class="plaintext" >${resultsize} Organization(s) found.</td>       
    </tr>  
      
    <tr>
  		<td class="tdheader" >Name</td>
        <td class="tdheader" >Description</td>
        <td class="tdheader" >Classification</td>
        <td class="tdheader" >Status </td>
      </tr>
	

	<c:forEach items="${searchResult}" var="org">
		<tr class="tdlight">

			<td><a href="orgDetail.cnt?orgId=${org.orgId}">${org.organizationName}</a></td>
			<td> ${org.description}</td>
			<td> ${org.classification}</td>
			<td> ${org.status}</td>
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
    	 <td colspan="5" align="left"></td>
    </tr>
           
</table>

</c:if>