<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

   		<table  width="825pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Policy Management</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>	
					
<form:form commandName="policyDetailCmd">
<form:hidden path="policy.policyDefId"/>


	<table width="800pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>AUTHENTICATION POLICY OVERVIEW</legend>
	
	<table class="fieldsetTable"  width="800pt" >
          <tr>
			  <td class="tddark" width="20%">Policy Id</td>
              <td class="tdlightnormal" colspan="2"><form:input path="policy.policyId" size="40" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark" width="20%">Name<font color="red">*</font></td>
			  <td class="tdlightnormal" colspan="2"><form:errors path="policy.name" cssClass="error"  />
			  		<form:input path="policy.name" size="60" maxlength="60"  />
			  </td>
		  </tr>
          <tr>
			  <td class="tddark" width="20%">Description</td>
              <td class="tdlightnormal" colspan="2" ><form:input path="policy.description" size="60" maxlength="255" /></td>
          </tr>
          <tr>
              <td class="tddark" width="20%">Policy Type</td>
			  <td class="tdlightnormal" colspan="2">
			  		<form:input path="policyDefName" size="40" readonly="true" />
			  </td>
		  </tr>
          <tr>
              <td class="tddark" width="25%">Status</td>
			  <td class="tdlightnormal" colspan="2"><form:select path="policy.status">
			  		<form:option value="-1" >-Select a value</form:option>
	             	<form:option value="1">Active</form:option>
	             	<form:option value="2">In-Active</form:option> 
          		</form:select>
			  </td>
		  </tr>
	</TABLE>
</TD>
</TR>
	
	<table width="800pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CONFIGURE AUTHENTICATION POLICY OPTIONS</legend>
	
		<table class="resourceTable" cellspacing="2" cellpadding="2" width="800pt">	
   <tr class="header">
 		<th>Rule Name</td>
 		<th>Value</td>
  </tr> 

  <c:if test="${policyDetailCmd.policyAttr != null}" >	
	<c:forEach items="${policyDetailCmd.policyAttr}" var="policyAttr" varStatus="attr">
		  
				<tr>
								<td  class="tableEntry">
								${policyAttr.name}
									<form:hidden path="policyAttr[${attr.index}].name" />
									<form:hidden path="policyAttr[${attr.index}].policyId" />
									<form:hidden path="policyAttr[${attr.index}].defParamId" />
									<form:hidden path="policyAttr[${attr.index}].policyAttrId" />
									<form:hidden path="policyAttr[${attr.index}].operation"  />
								</td>
								<td  class="tableEntry">
								
								<c:choose>
  									<c:when test="${policyAttr.name == 'TOKEN_TYPE'}">
    									<form:select path="policyAttr[${attr.index}].value1" multiple="false">
				              					<form:option value="" label="-Please Select-"/>
				              					<form:option value="OPENIAM_TOKEN" label="OPENIAM_TOKEN"/>
																<form:option value="SAML1_TOKEN" label="SAML1_TOKEN"/>
																<form:option value="SAML2_TOKEN" label="SAML2_TOKEN"/>
						                </form:select>
 									 </c:when>

                                    <c:when test="${policyAttr.name == 'PROTOCOL'}">
    									<form:select path="policyAttr[${attr.index}].value1" multiple="false">
				              					<form:option value="" label="-Please Select-"/>
				              					<form:option value="CLEAN" />
																<form:option value="SSL" />
																<form:option value="TLS" />
						                </form:select>
 									</c:when>

                                    <c:when test="${policyAttr.name == 'DEFAULT_LOGIN_MOD'}">
    									<form:select path="policyAttr[${attr.index}].value1" multiple="false">
				              					<form:option value="" label="-Please Select-"/>
				              					<form:option value="org.openiam.idm.srvc.auth.spi.DefaultLoginModule" label="Default Login Module"/>
																<form:option value="org.openiam.idm.srvc.auth.spi.LDAPLoginModule" label="LDAP Login Module"/>
																<form:option value="org.openiam.idm.srvc.auth.spi.ActiveDirectoryLoginModule" label="Active Directory Login"/>																
																<form:option value="CUSTOM" label="Custom Login Module"/>
						                </form:select>
						                <form:input path="policyAttr[${attr.index}].value2" size="40" maxlength="200" />
 									 </c:when>

                                     <c:when test="${policyAttr.name == 'AUTH_REPOSITORY'}">
    						            <form:select path="policyAttr[${attr.index}].value1" multiple="false">
				              					<form:option value="" label="-Please Select-"/>
                                                <form:options items="${policyDetailCmd.resourceList}" itemValue="resourceId" itemLabel="name"/>
						                </form:select>
 									 </c:when>


  									<c:otherwise>
    									<form:input path="policyAttr[${attr.index}].value1" size="30" maxlength="200" />
  									</c:otherwise>
								</c:choose>
								 </td>

				</tr>
				
	</c:forEach>
 </c:if>

			</table>
	</td>	
	</tr>   
</table>
  </td>
</tr>   
  		  
          <tr>
              <td class="buttonRow" align="right">

          	  <%  
          	  	if ( ((String)request.getAttribute("policyDefId")).equalsIgnoreCase("100")) { %>
          	   	<a href="assocPasswordPolicy.cnt?policyId=${policyDetailCmd.policy.policyId}">Associate Policy</a>
          	  <% } %>
             
             <c:if test="${policyDetailCmd.policy.policyId != null}" >
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Save"> <input type="submit" name="_cancel" value="Cancel" />
              </td>
          </tr>
</table>

</form:form>
</td>
 </tr>
</table>
