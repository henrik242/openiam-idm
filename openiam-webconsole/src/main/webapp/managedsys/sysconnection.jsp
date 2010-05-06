<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="managedSysConnectionCmd">
<form:hidden path="domainId" />
<form:hidden path="objectSearchId" />

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>System Connection Details</strong>
      </td>
   </tr>
		   <tr>
		 		<td colspan="2" align="center" class="msg">
		 		  <b>${msg}</b>
		 		</td>
		  </tr>    
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Managed Resource Id</td>
              <td class="tdlightnormal"><form:input path="managedSysId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Resource Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="name" size="40" maxlength="40"  /><br>
			  <form:errors path="name" cssClass="error" /></td>
		  </tr>
          <tr>
			  <td class="tddark">Description</td>
              <td class="tdlightnormal" ><form:input path="description" size="40" maxlength="40" /></td>
          </tr>
          <tr>
              <td class="tddark" >Resource Type<font color="red">*</font></td>
			  <td class="tdlightnormal">
			  		<form:select path="resourceType">
			  			<form:option value="-"  label="-Select a value" />
			  			<form:option value="MANAGED_SYS" label="Managed Systems" />
			  			<form:option value="SYS_FORMS"  label="System Forms"/>
			  			<form:option value="OTHER_NONPROV" label="Other Non-Provisionable Resource" /> 
		            </form:select><br>
		            <form:errors path="resourceType" cssClass="error" />
			  </td>
		  </tr>
		   
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal"><form:select path="status">
	              <form:option value="ACTIVE" />
	              <form:option value="IN-ACTIVE" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Connector<font color="red">*</font></td>
			  <td class="tdlightnormal">
			  		<form:select path="connectorId">
			  			<form:option value="-"  label="-Select a value" />
			  			<form:options items="${connectors}" itemValue="connectorId" itemLabel="name"  />
		            </form:select><br>
		            <form:errors path="connectorId" cssClass="error" />
			  </td>
		  </tr>
          <tr>
			  <td class="tddark">Host URL:Port</td>
              <td class="tdlightnormal" ><form:input path="hostUrl" size="60" maxlength="60" /> : <form:input path="port" size="10" maxlength="10" /></td>
          </tr>
          <tr>
              <td class="tddark" >Communication Protocol</td>
			  <td class="tdlightnormal"><form:select path="commProtocol">
		              <form:option value="-Select a value" />
		              <form:option value="SSL" />
		              <form:option value="CLEAR" />
	              </form:select>
			  </td>
		  </tr>  		  
          <tr>
			  <td class="tddark">Login Id</td>
              <td class="tdlightnormal" ><form:input path="userId" size="50" maxlength="100" /><br>
		            <form:errors path="userId" cssClass="error" /></td>
          </tr>
          <tr>
			  <td class="tddark">Password</td>
              <td class="tdlightnormal" ><form:password path="pswd" size="50" maxlength="100" showPassword="true" /><br>
		            <form:errors path="pswd" cssClass="error" /></td>
          </tr>
 
          <tr>
			  <td class="tddark">Object Primary Key</td>
              <td class="tdlightnormal" ><form:input path="keyField" size="50" maxlength="40" /></td>
          </tr>
          <tr>
			  <td class="tddark">Base DN</td>
              <td class="tdlightnormal" ><form:input path="baseDn" size="50" maxlength="100" /></td>
          </tr>          
          <tr>
			  <td class="tddark">Search Base DN</td>
              <td class="tdlightnormal" ><form:input path="searchBaseDn" size="50" maxlength="100" /></td>
          </tr>  
          <tr>
			  <td class="tddark">Search Filter</td>
              <td class="tdlightnormal" ><form:input path="searchFilter" size="50" maxlength="100" /></td>
          </tr>    
	

         <tr>
    	  <td colspan="2">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right">
              <c:if test="${managedSysConnectionCmd.managedSysId != null}" >
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>

</form:form>
</div>