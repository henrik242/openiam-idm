<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

       
       
<form:form commandName="orgDetailCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Organization Details</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Organization ID</td>
              <td class="tdlightnormal"><form:input path="org.orgId" size="35" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="org.organizationName" size="40" maxlength="40"  /><br>
			  <form:errors path="org.organizationName" cssClass="error" /></td>
		  </tr>
          <tr>
              <td class="tddark">Abbreviation</td>
			  <td class="tdlightnormal"><form:input path="org.abbreviation" size="40" maxlength="20"  /></td>
		  </tr>		  
          <tr>
              <td class="tddark">Symbol</td>
			  <td class="tdlightnormal"><form:input path="org.symbol" size="40" maxlength="10"  /></td>
		  </tr>		

          <tr>
			  <td class="tddark">Description</td>
              <td class="tdlightnormal" ><form:input path="org.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td class="tddark" >Metadata Type</td>
			  <td class="tdlightnormal">
			  		<form:select path="org.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${orgDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Classification</td>
			  <td class="tdlightnormal">
			  		<form:select path="org.classification">
			  			<form:option value=""  label="-Select a value" />
				  		<form:option value="ORGANIZATION"  label="ORGANIZATION" />
			  			<form:option value="DEPARTMENT"  label="DEPARTMENT" />
			  			<form:option value="DIVISION"  label="DIVISION" />
			  			<form:option value="VENDOR"  label="VENDOR" />
			  			<form:option value="PARTNER"  label="PARTNER" />
			  			<form:option value="SUBSIDIARY"  label="SUBSIDIARY" />
			  			<form:option value="CUSTOMER"  label="CUSTOMER" />
			  			<form:option value="AFFILIATE"  label="AFFILIATE" />
			  	    </form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >DomainName</td>
			  <td class="tdlightnormal"><form:input path="org.domainName" size="40" maxlength="40" />
			  </td>
		  </tr>          <tr>
              <td class="tddark" >Organization Status</td>
			  <td class="tdlightnormal">
			  		<form:select path="org.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	
		  
		  
		  
          <tr>
              <td class="tddark" >Parent Organization</td>
			  <td class="tdlightnormal"><form:input path="org.parentId" size="32"  /> 
			  <c:if test="${orgDetailCmd.org.parentId != null}" >
			  <a href="orgDetail.cnt?orgId=${orgDetailCmd.org.parentId}">View</a>
			  </c:if>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >LDAP String</td>
			  <td class="tdlightnormal">
			  		<form:input path="org.ldapStr" size="40" maxlength="40" />
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Create</td>
			  <td class="normaltext">On ${orgListCmd.org.createDate} by ${orgListCmd.group.createdBy} 
			  </td>
		  </tr>	
          <tr>
              <td class="tddark" >Last Update</td>
			  <td class="normaltext">On ${orgListCmd.org.lastUpdate} by ${orgListCmd.org.lastUpdatedBy}	  </td>
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
              <c:if test="${orgDetailCmd.org.orgId != null}" >
              <a href="orgDetail.cnt?parentOrgId=${orgDetailCmd.org.orgId}">New Child Organization</a> 
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>
</form:form>

<c:if test="${orgDetailCmd.childOrg != null}" >
<table cellpadding="2" width="60%">
	<tr class="tddark">
		<td>Name</td>
		<td>Description</td>
		<td>Status</td>
	</tr>	
	<c:forEach items="${orgDetailCmd.childOrg}" var="org">
		<tr>

			<td class="tdlightnormal"><a href="orgDetail.cnt?orgId=${org.orgId}">${org.organizationName}</a></td>
			<td class="normaltext"> ${org.description}</td>
			<td class="normaltext"> ${org.status}</td>
		</tr>
	</c:forEach>
</table>
</c:if>
