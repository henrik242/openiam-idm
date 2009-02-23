<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="connectorDetailCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Connector Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tddark">Connector Id</td>
              <td class="tdlightnormal"><form:input path="provConnectorId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Connector Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="name" size="40" maxlength="40"  /></td>
		  </tr>
          <tr>
			  <td class="tddark">Type</td>
              <td class="tdlightnormal" ><form:input path="metadataTypeId" size="40" maxlength="40" /></td>
          </tr>
          <tr>
              <td class="tddark">Compliance Level</td>
			  <td class="tdlightnormal"><form:select path="stdComplianceLevel">
	              <form:option value="1.0" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Communication Protocol</td>
			  <td class="tdlightnormal"><form:select path="clientCommProtocol">
		              <form:option value="-Select a value" />
		              <form:option value="SSL" />
		              <form:option value="CLEAR" />
	              </form:select>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark">Service URL</td>
              <td class="tdlightnormal" ><form:input path="serviceUrl" size="60" maxlength="60" /></td>
          </tr>
          <tr>
			  <td class="tddark">Class Name</td>
              <td class="tdlightnormal" ><form:input path="className" size="60" maxlength="60" /></td>
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
              <td colspan="2" align="right"><input type="submit" name="btn" value="Delete">  <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
