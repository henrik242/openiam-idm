<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

   
<form:form commandName="attrPolicyCmd">
	<form:hidden path="policyDefId"/>
<table width="75%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Attribute Policy Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

          <tr>
			  <td class="tddark" width="20%">Policy Id</td>
              <td class="tdlightnormal"><form:input path="policyPKId" size="40" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark" width="20%">Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:errors path="name" cssClass="error"  />
			  <c:if test="${attrPolicyCmd.policyPKId != null}" >
			  		<form:input path="name" size="60" maxlength="60" readonly="true"  />
			  </c:if>
			  <c:if test="${attrPolicyCmd.policyPKId == null}" >
			  		<form:input path="name" size="60" maxlength="60" readonly="false"  />
			  </c:if>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark" width="20%">Description</td>
              <td class="tdlightnormal" ><form:input path="description" size="60" maxlength="255" /></td>
          </tr>
          <tr>
              <td class="tddark" width="25%">Status</td>
			  <td class="tdlightnormal"><form:select path="status">
			  		<form:option value="-1" >-Select a value</form:option>
	             	<form:option value="1">Active</form:option>
	             	<form:option value="2">In-Active</form:option> 
          		</form:select>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark" width="20%">Rule Script URL:</td>
              <td class="tdlightnormal" ><form:input path="ruleSrcUrl" size="60" maxlength="80" /></td>
          </tr>
          <tr>
              <td class="tddark" width="25%">Policy Rule</td>
			  <td class="tdlightnormal">
			  <form:errors path="rule" cssClass="error"  />
			  	<form:textarea path="rule" rows="10" cols="80" />
			  </td>
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
             
             <c:if test="${attrPolicyCmd.policyPKId != null}" >
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
