<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<form:form commandName="requestDetailCmd">
<table>
	<tr>
      <td colspan="4" class="title">         
          <strong>Request Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td>Request Id</td>
              <td ><form:input path="requestId" size="32" maxlength="32" readonly="true" /></td>
              <td>Request Date</td>
			  <td><form:input path="requestDate" size="32" maxlength="32" readonly="true" /></td>
		  </tr>
          <tr>
			  <td>Status</td>
              <td ><form:input path="status" size="32" maxlength="32" /></td>
              <td>Status Change Date</td>
			  <td><form:input path="statusDate" size="32" maxlength="32" /></td>
		  </tr>
          <tr>
			  <td>Request Description</td>
              <td colspan="3"><form:input path="requestReason" size="50" maxlength="50" /></td>
		  </tr>
          <tr>
			  <td>Request for User</td>
              <td colspan="3"><form:input path="firstName" size="32" maxlength="32" /> <form:input path="lastName" size="32" maxlength="32" /></td>
		  </tr>
		  		  


         <tr>
    	  <td colspan="4">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="4" align="right"><input type="submit" name="btn" value="Approve"> <input type="submit" name="btn" value="Reject"> </td>
          </tr>
</table>

</form:form>
