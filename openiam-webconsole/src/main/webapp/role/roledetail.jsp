<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="roleDetailCmd">
<form:hidden path="mode" />
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Role Details</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Security Domain</td>
              <td class="tdlightnormal"><form:input path="role.id.serviceId" size="32" maxlength="20" readonly="true" /></td>
          </tr>
          <tr>
			  <td class="tddark">Role ID<font color="red">*</font></td>
              <td class="tdlightnormal">
<c:if test="${roleDetailCmd.mode == 'NEW'}" >              
              <form:input path="role.id.roleId" size="32" maxlength="32" />
</c:if>
<c:if test="${roleDetailCmd.mode != 'NEW'}" >              
              <form:input path="role.id.roleId" size="32" maxlength="32" readonly="true" />
</c:if>
              <form:errors path="role.id.roleId" cssClass="error" /></td>
          </tr>

          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="role.roleName" size="40" maxlength="40"  />
			  <form:errors path="role.roleName" cssClass="error" /><br>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark">Description</td>
              <td class="tdlightnormal" ><form:input path="role.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td class="tddark" >Role Type</td>
			  <td class="tdlightnormal">
			  		<form:select path="role.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${roleDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			  </td>
		  </tr>
        <tr>
              <td class="tddark" >Role Status</td>
			  <td class="tdlightnormal">
			  		<form:select path="role.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	

          <tr>
              <td class="tddark" >Role Owner</td>
			  <td class="tdlightnormal">
			  		<form:input path="role.ownerId" size="40" maxlength="40" />
			  </td>
		  </tr>
          <tr>
              <td class="tddark" >Inherit From Parent</td>
			  <td class="tdlightnormal">
			  		<form:select path="role.inheritFromParent">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="0"  label="NO" />
			  			<form:option value="1"  label="YES" />
		            </form:select>
			  </td>
		  </tr>		  
          <tr>
              <td class="tddark" >Create</td>
			  <td class="normaltext">On ${roleDetailCmd.role.createDate} by ${roleDetailCmd.role.createdBy} 
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
              <c:if test="${roleDetailCmd.role.id != null}" >
              	<input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Role');">
              </c:if>
              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>
</form:form>

</div>