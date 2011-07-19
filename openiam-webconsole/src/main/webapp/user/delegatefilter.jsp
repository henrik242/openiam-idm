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
								<h2 class="contentheading">User Management</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<tr>
				<td>
<form:form commandName="filtercmd">

<form:hidden path="perId" />

<table width="700pt"  class="bodyTable" height="100%" >
<tr>
	<td>
			<fieldset class="userformSearch" >
			<legend>DELEGATION FILTER</legend>

	<table class="fieldsetTable"  width="700pt" >
    <tr>
       <td><label for="username" class="attribute">Organization</LABEL></td>
       <td>
               <form:select path="orgFilter" multiple="true">
               <form:option value="" label="-Please Select-"/>
               <form:options items="${orgList}" itemValue="orgId" itemLabel="organizationName"/>
              </form:select>
       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Division</LABEL></td>
       <td>
			  <form:select path="divisionFilter" multiple="true">
			  <form:option value="" label="-Please Select-"/>
               <form:options items="${divList}" itemValue="orgId" itemLabel="organizationName"/>
              </form:select>

       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Department<LABEL></td>
       <td>
              <form:select path="deptFilter" multiple="true">
              <form:option value="" label="-Please Select-"/>
               <form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
              </form:select>
       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Group</LABEL></td>
       <td>
			  <form:select path="groupList" multiple="true">
			  <form:option value="" label="-Please Select-"/>
                <form:options items="${groupList}" itemValue="grpId" itemLabel="grpName"/>
              </form:select>

       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Role</LABEL></td>
       <td>
			  <form:select path="roleList" multiple="true">
			  	<form:option value="" label="-Please Select-"/>
              	<c:forEach items="${roleList}" var="role">
                	<form:option value="${role.id.serviceId}*${role.id.roleId}" label="${role.id.serviceId}-${role.roleName}" />
              	</c:forEach>
              </form:select>

       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Applications</LABEL></td>
       <td>
			  <form:select path="appList" multiple="true">
               <form:option value="" label="-Please Select-"/>
               <form:options items="${resourceList}" itemValue="resourceId" itemLabel="name"/>
              </form:select>

       </td>
    </tr>
    				</table>
				 		</td>
	</tr>

  <tr class="buttonRow">
    <td align="right">
          <input type="submit" name="saveBtn" value="Save"/>    <input type="submit" name="_cancel" value="Cancel" />
    </td>
  </tr>

</table>

</form:form>
 </td>
</tr>
</table>


