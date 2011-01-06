<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

       
		<table  width="600pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Organization Manager</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>       
<form:form commandName="orgDetailCmd">
		<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>ORGANIZATION DETAILS</legend>
				
					<table class="fieldsetTable"  width="100%" >
	
       <tr>
			  <td>Organization ID</td>
              <td class="tdlightnormal"><form:input path="org.orgId" size="35" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td><form:input path="org.organizationName" size="40" maxlength="40"  /><br>
			  <form:errors path="org.organizationName" cssClass="error" /></td>
		  </tr>
       <tr>
        <td>Abbreviation</td>
			  <td><form:input path="org.abbreviation" size="40" maxlength="20"  /></td>
		  </tr>		  
          <tr>
              <td>Symbol</td>
			  <td><form:input path="org.symbol" size="40" maxlength="10"  /></td>
		  </tr>		

          <tr>
			  <td>Description</td>
              <td><form:input path="org.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td>Metadata Type</td>
			  <td>
			  		<form:select path="org.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${orgDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			  </td>
		  </tr>
          <tr>
              <td>Classification</td>
			  <td>
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
              <td>Alias</td>
			  <td><form:input path="org.alias" size="40" maxlength="40" />
			  </td>
		  </tr> 
          <tr>
              <td>Internal Organization ID</td>
			  <td><form:input path="org.internalOrgId" size="40" maxlength="40" />
			  </td>
		  </tr> 		  
          <tr>
              <td>DomainName</td>
			  <td><form:input path="org.domainName" size="40" maxlength="40" />
			  </td>
		  </tr>          
		  <tr>
              <td>Organization Status</td>
			  <td>
			  		<form:select path="org.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
			  			<form:option value="IN-ACTIVE"  label="IN-ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	
		  
		  
		  
          <tr>
              <td>Parent Organization</td>
			  <td><form:input path="org.parentId" size="32"  /> 
			  <c:if test="${orgDetailCmd.org.parentId != null}" >
			  <a href="orgDetail.cnt?orgId=${orgDetailCmd.org.parentId}">View</a>
			  </c:if>
			  </td>
		  </tr>
          <tr>
              <td>LDAP String</td>
			  <td>
			  		<form:input path="org.ldapStr" size="40" maxlength="40" />
			  </td>
		  </tr>
 
    <c:if test="${orgDetailCmd.orgAttributeSet != null}" >	
	<c:forEach items="${orgDetailCmd.orgAttributeSet}" var="orgAttributeSet" varStatus="attr">
				<tr>
								<td>${orgAttributeSet.name}
									<form:hidden path="orgAttributeSet[${attr.index}].name" />
									<form:hidden path="orgAttributeSet[${attr.index}].attrId" />
									<form:hidden path="orgAttributeSet[${attr.index}].organizationId" />
								</td>
								<td>
								<form:input path="orgAttributeSet[${attr.index}].value" size="40" maxlength="200" /> </td>
				</tr>
				
	</c:forEach>
 </c:if>	
  
          <tr>
              <td>Create</td>
			  <td>On ${orgListCmd.org.createDate} by ${orgListCmd.group.createdBy} 
			  </td>
		  </tr>	
          <tr>
              <td>Last Update</td>
			  <td>On ${orgListCmd.org.lastUpdate} by ${orgListCmd.org.lastUpdatedBy}	  </td>
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
              <input type="submit" name="btn" value="Save"> <input type="submit" name="btn" value="Cancel"></td>
          </tr>
	</table>
</form:form>
</TD>
</TR>
</TABLE>


							
<c:if test="${orgDetailCmd.childOrg != null}" >

<table width="600pt" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CHILD ORGANIZATIONS </legend>
						<table class="resourceTable" cellspacing="2" cellpadding="2" width="600pt">	
							
	<tr class="header">
		<th>Name</th>
		<th>Description</th>
		<th>Status</th>
	</tr>	
	<c:forEach items="${orgDetailCmd.childOrg}" var="org">
		<tr>

			<td class="tableEntry"><a href="orgDetail.cnt?orgId=${org.orgId}">${org.organizationName}</a></td>
			<td class="tableEntry"> ${org.description}</td>
			<td class="tableEntry"> ${org.status}</td>
		</tr>
	</c:forEach>
</table>
</td>
</tr>
</table>
</c:if>

 </td>
</tr>
</table>