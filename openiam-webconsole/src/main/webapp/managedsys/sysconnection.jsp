<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="managedSysConnectionCmd">
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
			  <td class="tddark">Managed System Id</td>
              <td class="tdlightnormal"><form:input path="managedSysId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">System Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="name" size="40" maxlength="40"  /></td>
		  </tr>
          <tr>
			  <td class="tddark">Description</td>
              <td class="tdlightnormal" ><form:input path="description" size="40" maxlength="40" /></td>
          </tr>
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal"><form:select path="status">
	              <form:option value="1.0" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Connector</td>
			  <td class="tdlightnormal"><form:select path="connectorId">
		              <form:option value="-Select a value" />
		              <form:option value="SSL" />
		              <form:option value="CLEAR" />
	              </form:select>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark">Domain</td>
              <td class="tdlightnormal" ><form:input path="domainId" size="60" maxlength="60" /></td>
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
			  <td class="tddark">Login Id:</td>
              <td class="tdlightnormal" ><form:input path="userId" size="40" maxlength="40" /></td>
          </tr>
          <tr>
			  <td class="tddark">Password</td>
              <td class="tdlightnormal" ><form:password path="pswd" size="40" maxlength="40" /></td>
          </tr>
          <tr>
			  <td class="tddark">Start - End Date (MMDDYYYY)</td>
              <td class="tdlightnormal" ><form:input path="startDate" size="15" maxlength="15" /> - <form:input path="endDate" size="15" maxlength="15" /></td>
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
</div>