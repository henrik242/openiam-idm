<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Role Management</h2>
						</td>
						</tr>
					</table>
			</td>
			<tr>
				<td>       
<form:form commandName="roleDetailCmd">
	<form:hidden path="mode" />
<table width="700pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>ROLE DETAILS FOR: ${roleDetailCmd.role.roleName} </legend>

				<table class="fieldsetTable"  width="100%" >

          <tr>
			  <td><label for="username" class="attribute">Security Domain</label></td>
              <td><form:input path="role.id.serviceId" size="32" maxlength="20" readonly="true" /></td>
          </tr>
          <tr>
			  			<td><label for="username" class="attribute">Role ID</label><font color="red">*</font></td>
              <td >
<c:if test="${roleDetailCmd.mode == 'NEW'}" >              
              <form:input path="role.id.roleId" size="32" maxlength="32" />
</c:if>
<c:if test="${roleDetailCmd.mode != 'NEW'}" >              
              <form:input path="role.id.roleId" size="32" maxlength="32" readonly="true" />
</c:if>
              <form:errors path="role.id.roleId" cssClass="error" /></td>
          </tr>

          <tr>
              <td><label for="username" class="attribute">Name</label><font color="red">*</font></td>
			 			  <td><form:input path="role.roleName" size="40" maxlength="40"  />
			  <form:errors path="role.roleName" cssClass="error" /><br>
			  </td>
		  </tr>
          <tr>
			  			<td><label for="username" class="attribute">Description</label></td>
              <td><form:input path="role.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td><label for="username" class="attribute">Role Type</label></td>
			  			<td>
			  			<form:select path="role.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${roleDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			 			 </td>
		  </tr>
        <tr>
              <td><label for="username" class="attribute">Role Status</label></td>
			  			<td>
			  		<form:select path="role.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	

          <tr>
              <td><label for="username" class="attribute">Role Owner</label></td>
			  			<td><form:input path="role.ownerId" size="40" maxlength="40" /></td>
		  		</tr>

          <tr>
              <td><label for="username" class="attribute">Inherit From Parent</label></td>
			  		<td>
			  		<form:select path="role.inheritFromParent">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="0"  label="NO" />
			  			<form:option value="1"  label="YES" />
		            </form:select>
			  </td>
		  </tr>		  
          <tr>
              <td><label for="username" class="attribute">Create</label></td>
			  			<td><label for="username" class="attribute">On</label> ${roleDetailCmd.role.createDate} by ${roleDetailCmd.role.createdBy} 
			  </td>
		  </tr>	
  
	</TABLE>        


          <tr class="buttonRow" align="right">
              <td c align="right">
              <c:if test="${roleDetailCmd.role.id != null}" >
              	<input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Role');">
              </c:if>
              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>
</form:form>

	</td>
 </tr>
</table>
