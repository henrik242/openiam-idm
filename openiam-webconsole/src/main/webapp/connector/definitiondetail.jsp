<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Connector Details</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
		<tr>
				<td>		
<form:form commandName="connectorDetailCmd">
		<table width="700pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>EDIT CONNECTOR CONFIGURATION</legend>
				
					<table class="fieldsetTable"  width="100%" >
	
	
          <tr>
			  <td >Connector Id</td>
              <td><form:input path="provConnectorId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td>Connector Name<font color="red">*</font></td>
			  <td><form:input path="name" size="40" maxlength="40"  /></td>
		  </tr>
          <tr>
			  <td>Type</td>
        <td><form:input path="metadataTypeId" size="40" maxlength="40" /></td>
          </tr>
          <tr>
              <td>Communication Protocol</td>
			  			<td><form:select path="clientCommProtocol">
		              <form:option value="-Select a value" />
		              <form:option value="SSL" />
		              <form:option value="CLEAR" />
	              </form:select>
			  </td>
		  </tr>
          <tr>
			  <td >Service URL</td>
        <td><form:input path="serviceUrl" size="60" maxlength="100" /></td>
          </tr>
          <tr>
			  <td>Service Namespace</td>
        <td><form:input path="serviceNameSpace" size="60" maxlength="100" /></td>
          </tr>
          <tr>
			  	<td>Service Port</td>
        	<td><form:input path="servicePort" size="60" maxlength="60" /></td>
          </tr>
          
</table>
</td>
</tr>
          <tr class="buttonRow" align="right">
              <td><input type="submit" name="btn" value="Delete">  <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>

</td>
</tr>
</table>
