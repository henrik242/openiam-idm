<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<script type="text/javascript">
<!--

function defaultFields() {
	var theForm = document.getElementById ('newHireCmd');
	theForm.userPrincipalName.value = theForm.nickname.value + '.' + theForm.lastName.value  ;
}

String.prototype.toProperCase = function() 
{
    return this.charAt(0).toUpperCase() + this.substring(1,this.length).toLowerCase();
}


function validateInt(fld) {
   if (isNaN(fld.value)) {
		alert(fld.name + " is not a number");
		return false;
	}
	return true;
}



//-->
</script>

<form:form commandName="changeAccessCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>Create Request - Application Access</strong>
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>

			  <td class="tddark">Request Membership to Group:</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr>
              			<td>
              		<form:select path="group" multiple="false">
              			<form:option value="" label="-Please Select-"/>
              			<form:options items="${groupList}" itemValue="grpId" itemLabel="grpName"/>
          			</form:select>
          				</td> 
              		</tr>
              	</table>
              </td>
		  </tr>
          <tr>
			  <td class="tddark">Request Membership to Role:</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr>
              			<td>
              		        <form:select path="role" multiple="false">
              				<form:option value="-" label="-Please Select-"/>
              				<form:options items="${roleList}" itemValue="id.roleId" itemLabel="roleName"/>
          					</form:select> 
              			</td>
              		</tr>

              	</table>
              </td>
		  </tr>

          <tr>
			  <td class="tddark">Change Department:</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr>
              			<td>
              		        <form:select path="role" multiple="false">
              				<form:option value="-" label="-Please Select-"/>
              				<form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
          					</form:select> 
              			</td>
              		</tr>

              	</table>
              </td>
		  </tr>

          <tr>
			  <td class="tddark">Device</td>
              <td colspan="3">
              	<table cellpadding="2">
              		<tr>
              			<td>
              		        <form:select path="role" multiple="false">
              				<form:option value="-" label="-Please Select-"/>
              				<form:option value="GEMALTO-CARD" label="Gemalto .NET Card"/>
          					</form:select> 
              			</td>
              		</tr>

              	</table>
              </td>
		  </tr>

       <tr>
 		<td colspan="6" class="help">
 		  Please select applications to include in this request. Note: This overrides the applications
 		  linked to any selected Roles.
 		</td>
  </tr> 
    
   <tr>
       <td class="tddarknormal" align="right">APPLICATION</td>
       <td class="tddarknormal" colspan="3">ENTITLEMENTS </td>
    </tr>
<c:forEach items="${resourceList}" var="res" varStatus="resource">
	<tr>
 		<td> <form:checkbox path="resourceList[${resource.index}].resourceId" value="false" /> ${res.name} </td>
		<td> 
		    <form:select path="role" multiple="false">
            	<form:option value="-" label="-Please Select-"/>
              	<form:options items="${roleList}" itemValue="id.roleId" itemLabel="roleName"/>
          	</form:select> 
		</td>
		<TD COLSPAN="2">
		<INPUT TYPE="CHECKBOX">C <INPUT TYPE="CHECKBOX">R <INPUT TYPE="CHECKBOX">U <INPUT TYPE="CHECKBOX">D <INPUT TYPE="CHECKBOX">EXEC	</TD>
		
	</tr>
</c:forEach>

    <tr>
    	  <td colspan="4">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="4" align="right"> 
    	  <input type="submit" name="_target1" value="Previous"/> 
    	  <input type="submit" name="_Finish" value="Finish"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>
  
</table>

</form:form>

