<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="secDomainCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Security Domain Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  <form:hidden path="domain.domainId"  />
          <tr>
			  <td class="tddark">Name<font color="red">*</font></td>
              <td class="tdlightnormal"><form:input path="domain.name" size="40" maxlength="40" /><br>
              <form:errors path="domain.name" cssClass="error" /></td>
          </tr>
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal">			  
  	            <form:select path="domain.status">
	              <form:option value="ACTIVE" label="ACTIVE" />
	              <form:option value="IN-ACTIVE" label="IN-ACTIVE" />
          		</form:select>
			   <form:hidden path="domain.passwordPolicyId" />
	 		   <form:hidden path="domain.auditPolicyId" />
	 		   <form:hidden path="domain.defaultLoginModule"  />
			  </td>
		  </tr>


            <tr>
			  <td class="tddark">Authentication Policy</td>
              <td class="tdlightnormal" >
               <form:select path="domain.authnPolicyId" multiple="false">
              	<form:option value="" label="-Please Select-"/>
              	<form:options items="${authnPolicyList}" itemValue="policyId" itemLabel="name"/>
         	  </form:select> 
         	  </td>
          </tr>
          
                   <tr>
			  <td class="tddark">Default Authn Managed System Id</td>
              <td class="tdlightnormal" ><form:input path="domain.authSysId" size="20" maxlength="20" /></td>
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
              <c:if test="${secDomainCmd.domain.domainId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
