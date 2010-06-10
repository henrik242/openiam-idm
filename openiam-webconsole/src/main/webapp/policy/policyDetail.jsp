<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

   
<form:form commandName="policyDetailCmd">
<form:hidden path="policy.policyDefId"/>
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Policy Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="3" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
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
   <tr>
 		<td  class="tdheader">Rule Name</td>
 		<td  class="tdheader">Value</td>
 		<td  class="tdheader">Operation</td>
  </tr> 

  <c:if test="${policyDetailCmd.policyAttr != null}" >	
	<c:forEach items="${policyDetailCmd.policyAttr}" var="policyAttr" varStatus="attr">
		  
				<tr class="plaintext">
								<td >
								${policyAttr.name}
									<form:hidden path="policyAttr[${attr.index}].name" />
									<form:hidden path="policyAttr[${attr.index}].policyId" />
									<form:hidden path="policyAttr[${attr.index}].defParamId" />
									<form:hidden path="policyAttr[${attr.index}].policyAttrId" />
								</td>
								<td  class="plaintext">
								<form:input path="policyAttr[${attr.index}].value1" size="20" maxlength="200" />-<form:input path="policyAttr[${attr.index}].value2" size="20" maxlength="200" />
								 </td>
								<td  class="plaintext"><form:input path="policyAttr[${attr.index}].operation" size="20" maxlength="20" /> </td>

				</tr>
				
	</c:forEach>
 </c:if>


      
  		  


         <tr>
    	  <td colspan="3">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="3" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
          	  <td>
          	  <%  
          	  	if ( ((String)request.getAttribute("policyDefId")).equalsIgnoreCase("100")) { %>
          	   	<a href="assocPasswordPolicy.cnt?policyId=${policyDetailCmd.policy.policyId}">Associate Policy</a>
          	  <% } %>
          	  </td>
              <td colspan="2" align="right">
             
             <c:if test="${policyDetailCmd.policy.policyId != null}" >
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
