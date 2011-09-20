<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<table  width="850pt">
	<tr>
		<td>
			<table width="100%">
				<tr>
					<td class="pageTitle" width="70%">
						<h2 class="contentheading">Metadata</h2>
				</td>
				</tr>
			</table>
	</td>
	<tr>
		<td>

<div>
<form:form commandName="metadataTypeCmd">
<table width="850pt class="bodyTable" height="100%"">
	<tr>
		<td>
				<fieldset class="userformSearch" >
				<legend>METADATA TYPE DETAILS </legend>

					<table class="fieldsetTable"  width="100%" >

	          <tr>
				  <td><label for="username" class="attribute">Type Id</label></td>
	              <td  class="userformInput" for="username" class="labelValue" ><form:input path="metadataType.metadataTypeId" size="32" maxlength="32" readonly="true" /></td>
	          </tr>
	          <tr>
				  <td><label for="username" class="attribute">Type Name</label></td>
	              <td  class="userformInput" for="username" class="labelValue" ><form:input path="metadataType.description" size="32" maxlength="32" readonly="true" /></td>
	          </tr>
	          <tr>
			              <td class="buttonRow" align="right">

			           	  <input type="submit" name="btn" value="Delete"> <input type="submit" name="btn" value="Submit"/> </td>
			  </tr>


</table>

</form:form>
</div>