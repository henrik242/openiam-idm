<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<script type="text/javascript">
<!--
function defaultFields() {
	var theForm = document.getElementById ('newHireCmd');
	theForm.userPrincipalName.value = theForm.firstName.value + '.' + theForm.lastName.value + '@ceoit.ocgov.com' ;
	theForm.email1.value = theForm.nickname.value + '.' + theForm.lastName.value + '@ceoit.ocgov.com' ;
	theForm.email2.value = theForm.nickname.value + '.' + theForm.lastName.value + '@ocgov.com' ;
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

<form:form commandName="newRequestCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="3" class="title">         
          <strong>Create Request</strong>
      </td>
      <td colspan="1" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 


   <tr>
       <td class="tddarknormal" align="right">Employee Id / User Principal Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="firstName" size="40" maxlength="40"  onchange="defaultFields(); firstName.value = firstName.value.toProperCase();" /> 
		</td>
    </tr>
   <tr>
       <td class="tddarknormal" align="right">Request Type<font color="red">*</font></td>
       <td class="tdlightnormal">
			<select>
				<option>-Select-</option>
				<option>Request Access</option>
				<option>Revoke Access</option>
				<option>Terminate User</option>
				<option>Suspend User</option>
			</select> 
		</td>
    </tr>
   <tr>
       <td class="tddarknormal" align="right">New Role<font color="red">*</font></td>
       <td class="tdlightnormal">
			<select>
				<option>-Select-</option>
				<option>Manager</option>
				<option>LSA</option>
				<option>User</option>
			</select> 
		</td>
    </tr>
   <tr>
       <td class="tddarknormal" align="right">Reason<font color="red">*</font></td>
       <td class="tdlightnormal">
       	<textarea cols="60" rows="5">
       	</textarea>

		</td>
    </tr>
   <tr>
   	<td colspan="2" class="tddark" align="center">Request Application Access</td>
   	<td></td>
   </tr>
  <tr>
       <td class="tddark" align="right">App Name</td>
       <td class="tddark" align="right" >App Enabled</td>

   </tr>
   <tr>
       <td class="tdlightNormal" align="right">Active Directory</td>
       <td class="tdlightNormal" align="right"><input type="checkbox" name="app_enabled" ></td>

   </tr>
   <tr>
       <td class="tdlightNormal" align="right">LDAP</td>
       <td class="tdlightNormal" align="right"><input type="checkbox" name="app_enabled" ></td>

   </tr>
   <tr>
       <td class="tdlightNormal" align="right">Notes</td>
       <td class="tdlightNormal" align="right"><input type="checkbox" name="app_enabled" ></td>

   </tr>
   
   <tr>
       <td class="tddarkNormal" align="right">SecurID</td>
       <td class="tddarkNormal" align="right"><input type="checkbox" name="app_enabled2" ></td>

   </tr>
   
   <tr>
       <td class="tddarkNormal" align="right">AI Card</td>
        <td class="tddarkNormal" align="right"><input type="checkbox" name="app_enabled2" ></td>

   </tr>
 

    <tr>
    	  <td colspan="6">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="6" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>



          <tr>
              <td colspan="6" align="right"> <input type="submit" value="Submit"> </td>
          </tr>
</table>

</form:form>

