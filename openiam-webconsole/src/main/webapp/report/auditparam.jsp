<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="650pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Audit Report</h2>
						</td>
						</tr>
					</table>
			</td>
			<tr>
				<td>
             
<form:form commandName="auditReportCmd" target="_blank">

<table width="650pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>REPORT PARAMETERS </legend>

				<table class="fieldsetTable"  width="100%" >
				
          <tr>
			  <td class="tddark">Principal Name:</td>
              <td class="tdlightnormal"><form:input path="principal" size="50" maxlength="50" /></td>
          </tr>
          <tr>
			  <td class="tddark">Action:</td>
              <td class="tdlightnormal">
              	<form:select path="action">
   	              <form:option value="" label="-Please Select-"/>
   	              <form:option value="AUTHENTICATION" label="AUTHENTICATION" />
   	              <form:option value="CREATE" label="CREATE USER" />
   	              <form:option value="MODIFY" label="MODIFY USER" />
   	              <form:option value="DELETE" label="DELETE USER" />
   	               <form:option value="DISABLE" label="DISABLE USER" />
   	               <form:option value="ENABLE" label="ENABLE USER" />
   	                <form:option value="ADD ROLE" label="ADD ROLE" />
   	                <form:option value="ADD IDENTITY" label="ADD IDENTITY" />
				  <form:option value="SET PASSWORD" label="SET PASSWORD" />
				  <form:option value="RESET PASSWORD" label="SET PASSWORD" />
				  <form:option value="NEW HIRE" label="NEW HIRE" />
				   <form:option value="REQUEST_REJECTED" label="REQUEST REJECTED" />
				   <form:option value="REQUEST_APPROVED" label="REQUEST APPROVED" />
				   <form:option value="NEW_PENDING_REQUEST" label="PENDING REQUEST" /> 
          		</form:select>
              
              
              </td>
          </tr>
          <tr>
			  <td class="tddark">Start Date:(MM/dd/yyyy)</td>
              <td class="msg"><form:input path="startDate" size="20" maxlength="20" /><br>
              	<form:errors path="startDate" />             
              
              </td>
          </tr>   
          <tr>
			  <td class="tddark">End Date:(MM/dd/yyyy)</td>
              <td class="msg"><form:input path="endDate" size="20" maxlength="20" /><br>
              	<form:errors path="endDate" />             
              
              </td>
          </tr>  

          <tr>
              <td colspan="2" align="right">

              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>
</form:form>
	</td>
 </tr>
</table>