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

<form:form commandName="newHireCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="4" class="title">         
          <strong>New Hire Information</strong>
      </td>
      <td colspan="2" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="6" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
   <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="5">
			<font color="red"><form:errors path="userPrincipalName" /></font>
       </td>
    </tr>

   <tr>
       <td class="tddarknormal" align="right">First Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="firstName" size="40" maxlength="40"  onchange="defaultFields(); firstName.value = firstName.value.toProperCase();" /> 
		</td>
	   <td class="tddarknormal" align="right">Middle<font color="red">*</font></td>
	   <td class="tdlightnormal">
			<form:input path="middleName" size="1" maxlength="1"  onchange="defaultFields(); middleName.value = middleName.value.toProperCase();" />
	  </td>
	  <td class="tddarknormal" align="right">Nickname</td>
	  <td>
			<form:input path="nickname" size="20"  maxlength="20" onchange="defaultFields(); nickname.value = nickname.value.toProperCase();" />
       </td>
    </tr>
   <tr>
       <td class="tddarknormal" align="right">Last Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="lastName" size="40" maxlength="40"  onchange="defaultFields(); lastName.value = lastName.value.toProperCase();" /> 
		</td>
	   <td class="tddarknormal" align="right">Maiden</td>
	   <td class="tdlightnormal">
			<form:input path="maiden" size="20" maxlength="20"  onchange="maiden.value = maiden.value.toProperCase();"   />
	  </td>
	  <td class="tddarknormal" align="right">Suffix</td>
	  <td>
			<form:input path="suffix" size="20"  maxlength="20"   />
       </td>
    </tr>
    <tr>
		 <td class="tddarknormal" align="right">Company Name:<font color="red">*</font></td>
		 <td class="tdlight" valign="middle">
              	<form:select path="companyName">
	              <form:option value="County of Orange" />
          		</form:select>
		 </td>

        <td class="tddarknormal" align="right">Agency<font color="red">*</font></td>
        <td class="tdlight">
	            <form:select path="department">
	              <form:option value="CEO" />
          		</form:select>
        </td>
		<td class="tddarknormal" align="right">Division<font color="red">*</font></td>
        <td class="tdlight">
	            <form:select path="division">
	              <form:option value="CEOIT" />
          		</form:select>
        </td>
     </tr>  
    <tr >
         <td class="tddarknormal"  align="right">FCN Title<font color="red">*</font></td>
         <td class="tdlight">
         	<form:input path="title" size="40" /> 
         </td>
		<td class="tddarknormal"  align="right">Job Classification</td>
        <td   colspan="3"  class="tdlight">
	            <form:select path="jobCode">
	              <form:option value="EXEC" label="EXEC" />
					<form:option value="ADMIN" label="Admin" />
					<form:option value="STAFF SPECIALIST" label="Staff Specialist" />
					<form:option value="OFFICE SUPERVISOR" label="Office Supervisor" />
					<form:option value="OFFICE ADMIN" label="Office Admin" />
					<form:option value="OFFICE ASSISTANT" label="Office Assistant" />
					<form:option value="PAYROLL SPECIALIST" label="Payroll Specialist" />
					<form:option value="OFFICER" label="Officer" />
					<form:option value="NOT LISTED" label="Not Listed" />
          		</form:select>

		</td>
     </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Managed By</td>
         <td class="tdlight">
	            <form:select path="managedBy">
	              <form:option value="3100" label="Bradbury, Neville" />
          		</form:select>
		</td>
		<td class="tddarknormal"  align="right">Type</td>
        <td  colspan="3"  class="tdlight">
	            <form:select path="jobCode">
	              <form:option value="EMPLOYEE" label="Employee" />
				  <form:option value="CONTRACTOR" label="Contractor" />
				  <form:option value="SUPPLIER" label="Supplier" />
				  <form:option value="AFFILIATE" label="Affiliate" />
          		</form:select>
		</td>
    </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Status</td>
         <td class="tdlight">
	            <form:select path="status">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="PENDING" label="In-Active"  />
	              <form:option value="APPROVED" label="Active" />				  
				  <form:option value="NA" label="NA" />
          		</form:select>
		</td>
		<td class="tddarknormal"  align="right">Full/Part</td>
        <td  colspan="3"  class="tdlight">
	            <form:select path="fullPart">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="FULL" label="Full time" />
				  <form:option value="PART" label="Part time" />
          		</form:select>
		</td>
    </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Password Theme</td>
         <td class="tdlight">
	            <form:select path="passwordTheme">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="Music" label="Music"  />		  
				  <form:option value="NA" label="NA" />
          		</form:select>
		</td>
		<td class="tddarknormal"  align="right">Gender</td>
        <td  colspan="3"  class="tdlight">
	            <form:select path="gender">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="M" label="Male" />
				  <form:option value="F" label="Female" />
				  <form:option value="D" label="Declined to State" />
          		</form:select>
		</td>
    </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Languages Spoken</td>
         <td  class="tdlight">
	            <form:select path="languagesSpoken" multiple="true" size="3" >
	              <form:option value="EN" label="English"  />
	              <form:option value="ES" label="Spanish" />				  
				  <form:option value="FR" label="French" />
				  <form:option value="DE" label="German" />
          		</form:select>
		</td>
		<td class="tddarknormal"  align="right">Volunteer Interpreter</td>
        <td   colspan="3"  class="tdlight">
	            <form:select path="volunteerInterpreter">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="YES" label="Yes" />
				  <form:option value="NO" label="No" />
          		</form:select>
		</td>
    </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Group</td>
         <td class="tdlight">
	            <form:select path="group" multiple="true" >
	              <form:option value="SUBMITTER" label="SUBMITTER"  />
	              <form:option value="REVIEWER" label="REVIEWER" />	
	              <form:option value="APPROVER" label="APPROVER" />			  
          		</form:select>
		</td>
		
		
		<td class="tddarknormal"  align="right">Role</td>
        <td colspan="3"  class="tdlight">
	            <form:select path="role">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="LSA" label="LSA" />
				  <form:option value="MANAGER" label="MANAGER" />
				  <form:option value="USER" label="USER" />
					<form:option value="SECURITY" label="SECURITY" />
					<form:option value="HR" label="HR" />
	          		</form:select>
		</td>
    </tr>
 
   <tr>
   	<td colspan="6" class="tddark" align="center">Contact Information</td>
   </tr>

  <tr>
       <td class="tddarknormal" align="right">Building Name</td>
       <td class="tdlight">
	            <form:select path="locationBldg">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="HOA" label="HOA" />
          		</form:select>       		

      </td>
       <td class="tddarknormal" align="right">Desk Phone <font size="1" color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="workAreaCode" size="3"  maxlength="3" onblur="return validateInt(workAreaCode)"  /> 
       <form:input path="workPhone" size="10" maxlength="10" onblur="return  validateInt(workPhone)" /> 
      	<font color="red"><form:errors path="workAreaCode" />
       		<form:errors path="workPhone" />
       	</font>
       </td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Room</td>
       <td class="tdlight">
	            <form:select path="locationRoom">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="210A" label="210a" />
          		</form:select>   

      </td>
       <td class="tddarknormal" align="right">Cell/BlackBerry Phone</td>
       <td class="tdlight" colspan="3">
       		<form:input path="cellAreaCode" size="3" maxlength="3" onchange="return  validateInt(cellAreaCode);" /> 
       		<form:input path="cellPhone" size="10" maxlength="10"  onchange="return  validateInt(cellPhone);"/>
      	<font color="red"><form:errors path="cellPhone" />
       		<form:errors path="workPhone" />
       	</font>
       	</td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Address</td>
       <td class="tdlight"><form:input path="address1" size="30"  />
       <td class="tddarknormal" align="right">Fax</td>
       <td class="tdlight" colspan="3">
			<form:input path="faxAreaCode" size="3" maxlength="3" onchange="return  validateInt(faxAreaCode);" /> 
			<form:input path="faxPhone" size="10" maxlength="10" onchange="return  validateInt(faxPhone);"/> 
      	<font color="red"> <form:errors path="faxAreaCode" />
       		<form:errors path="faxPhone" />
       	</font>   
       </td>
   </tr>
 	<tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"><form:input path="address2" size="30"  /></td>
       <td class="tddarknormal" align="right">Personal Phone</td>
       <td class="tdlight" colspan="3"><form:input path="personalAreaCode" size="3" maxlength="3" onchange="return  validateInt(personalAreaCode);" /> 
       					  <form:input path="personalNbr" size="10" maxlength="10"  onchange="return  validateInt(personalNbr);" /> 
      	<font color="red"> <form:errors path="personalAreaCode" />
       		<form:errors path="personalNbr" />
       	</font>   
       </td>
   </tr>
     
 <tr>
       <td class="tddarknormal" align="right">City</td>
       <td class="tdlight"><form:input path="city" size="30"  /></td>
       <td class="tddarknormal" align="right">Alt. Cell Phone</td>
       <td class="tdlight" colspan="3"><form:input path="altCellAreaCode" size="3" maxlength="3" onchange="return  validateInt(altCellAreaCode);" /> 
       					  <form:input path="altCellNbr" size="10" maxlength="10"  onchange="return  validateInt(altCellNbr);" /> 
      	<font color="red"> <form:errors path="altCellAreaCode" />
       		<form:errors path="altCellNbr" />
       	</font>   
        </td>
   </tr> 

  <tr>
       <td class="tddarknormal" align="right">State</td>
       <td class="tdlight"><form:input path="state" size="30"  /></td>
      <td class="tddarknormal" align="right">Home Phone</td>
       <td class="tdlight" colspan="3"><form:input path="homePhoneAreaCode" size="3" maxlength="3" onchange="return  validateInt(homePhoneAreaCode);" /> 
       					  <form:input path="homePhoneNbr" size="10" maxlength="10" onchange="return  validateInt(homePhoneNbr);" /> 
      	<font color="red"> <form:errors path="homePhoneAreaCode" />
       		<form:errors path="homePhoneNbr" />
       	</font>
       </td>
   </tr>
   <tr>
       <td class="tddarknormal" align="right">Zip Code</td>
       <td class="tdlight"><form:input path="postalCode" size="30" maxlength="10"  /></td>
        <td class="tddarknormal" align="right">UPN <font color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="userPrincipalName" size="30" readonly="true"  /></td>       
   </tr>

  <tr>
       <td class="tddarknormal" align="right">Country</td>
       <td class="tdlight"><form:input path="country" size="30"  /></td>
       <td class="tddarknormal" align="right">Email - 1 <font color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="email1" size="30" maxlength="30" readonly="true"  /></td>   </tr>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Latitude</td>
       <td class="tdlight"></td>
       <td class="tddarknormal" align="right">Email - 2 <font color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="email2" size="30" maxlength="30"  readonly="true"  /></td>
  <tr>
       <td class="tddarknormal" align="right">Longitude</td>
       <td class="tdlight"></td>
       <td class="tddarknormal" align="right">Alt. Email<font color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="email3" size="30" maxlength="30"  /></td>
   </tr>


   
   

	  <td colspan="6">&nbsp;</td>
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

