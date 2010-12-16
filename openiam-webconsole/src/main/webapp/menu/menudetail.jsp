<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="groupDetailCmd">

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Detail Details</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Menu ID</td>
              <td class="tdlightnormal"><form:input path="group.grpId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="group.grpName" size="40" maxlength="40"  /><br>
			  <form:errors path="group.grpName" cssClass="error" /></td>
		  </tr>
          <tr>
			  <td class="tddark">Description</td>
              <td class="tdlightnormal" ><form:input path="group.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td class="tddark" >Group Type</td>
			  <td class="tdlightnormal">
			  		<form:select path="group.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${groupDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Group Class</td>
			  <td class="tdlightnormal"><form:input path="group.groupClass" size="40" maxlength="40" />
			  </td>
		  </tr>          <tr>
              <td class="tddark" >Group Status</td>
			  <td class="tdlightnormal">
			  		<form:select path="group.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	
          <tr>
              <td class="tddark" >Parent Group</td>
			  <td class="tdlightnormal"><form:input path="group.parentGrpId" size="32" readonly="true"  /> 
			  <c:if test="${groupDetailCmd.group.parentGrpId != null}" >
			  <a href="groupDetail.cnt?groupId=${groupDetailCmd.group.parentGrpId}">View</a>
			  </c:if>
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Organization</td>
			  <td class="tdlightnormal">
			  		<form:select path="group.companyId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${groupDetailCmd.orgList}" itemValue="orgId" itemLabel="organizationName"  />
		            </form:select><br>
		           
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Group Owner</td>
			  <td class="tdlightnormal">
			  		<form:input path="group.ownerId" size="40" maxlength="40" />
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Inherit From Parent</td>
			  <td class="tdlightnormal">
			  		<form:select path="group.inheritFromParent">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="0"  label="NO" />
			  			<form:option value="1"  label="YES" />
		            </form:select>
			  </td>
		  </tr>		  
          <tr>
              <td class="tddark" >Create</td>
			  <td class="normaltext">On ${groupDetailCmd.group.createDate} by ${groupDetailCmd.group.createdBy} 
			  </td>
		  </tr>	
          <tr>
              <td class="tddark" >Last Update</td>
			  <td class="normaltext">On ${groupDetailCmd.group.lastUpdate} by ${groupDetailCmd.group.lastUpdatedBy}
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
              <c:if test="${groupDetailCmd.group.grpId != null}" >
              <a href="groupDetail.cnt?parentGroupId=${groupDetailCmd.group.grpId}">New Child Group</a> 
              	<input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Group');">
              </c:if>
              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>
</form:form>
<c:if test="${groupDetailCmd.childGroup != null}" >
<table cellpadding="2" width="60%">
	<tr class="tddark">
		<td>Name</td>
		<td>Description</td>
		<td>Status</td>
	</tr>	
	<c:forEach items="${groupDetailCmd.childGroup}" var="group">
		<tr>

			<td class="tdlightnormal"><a href="groupDetail.cnt?groupId=${group.grpId}">${group.grpName}</a></td>
			<td class="normaltext"> ${group.description}</td>
			<td class="normaltext"> ${group.status}</td>
		</tr>
	</c:forEach>
</table>
</c:if>
</div>