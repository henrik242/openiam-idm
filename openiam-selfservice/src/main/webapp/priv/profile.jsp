<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<%@ page import="org.openiam.idm.srvc.auth.dto.Login" %>

<script type="text/javascript">
<!--
function defaultFields() {
	var theForm = document.getElementById ('profileCmd');
	if (theForm.nickname.value == null || theForm.nickname.value.length ==0) {
		theForm.nickname.value = theForm.firstName.value;
	}

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

function MoveItem(ctrlSource, ctrlTarget) {
    var Source = document.getElementById(ctrlSource);
    var Target = document.getElementById(ctrlTarget);

    if ((Source != null) && (Target != null)) {
        while ( Source.options.selectedIndex >= 0 ) {
            var newOption = new Option(); // Create a new instance of ListItem
            newOption.text = Source.options[Source.options.selectedIndex].text;
            newOption.value = Source.options[Source.options.selectedIndex].value;
           
            Target.options[Target.length] = newOption; //Append the item in Target
            Source.remove(Source.options.selectedIndex);  //Remove the item from Source
        }
    }
}

function selectAllOptions(id)
{
var ref = document.getElementById(id);

for(i=0; i<ref.options.length; i++)
ref.options[i].selected = true;
}


function selectChange(ctrl) {
		var src = document.getElementById(ctrl);
		var theForm = document.getElementById ('profileCmd');
	
		
    
	   selIdx = src.options.selectedIndex;
	   newSel = src.options[selIdx].text;

	   if (newSel == '-Please Select-') {
		   theForm.bldgNbr.value = ""; 
		   theForm.address1.value = ""; 
		   theForm.city.value = ""; 
		   theForm.state.value = ""; 
		   theForm.postalCode.value = "";
	   }else {
	   
		   var spltStr = newSel.split('-');
	
		   theForm.bldgNbr.value = spltStr[1]; 
		   theForm.address1.value = spltStr[2]; 
		   theForm.city.value = spltStr[3]; 
		   theForm.state.value = spltStr[4]; 
		   theForm.postalCode.value = spltStr[5];
	   } 
	}

//-->
</script>

	
<form:form commandName="profileCmd">

			<form:hidden  path="userId" />
			<form:hidden path="secdomain" />
			<form:hidden path="managedSysId" />
   			<form:hidden path="email1Id"   />
   			<form:hidden path="email2Id"   />
   			<form:hidden path="email3Id"   />
   			
   			<form:hidden path="workPhoneId" />
			<form:hidden path="cellPhoneId" />
	<form:hidden path="faxPhoneId" />
	<form:hidden path="homePhoneIdr" />
	<form:hidden path="altCellNbrId" />
	<form:hidden path="personalNbrId" />
   			
<table width="540" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="4" class="title">         
          <strong>Edit Profile</strong>
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
			
       </td>
    </tr>

   <tr>
       <td class="tddarknormal" align="right">First Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="firstName" size="40" maxlength="40" onchange="defaultFields(); firstName.value = firstName.value.toProperCase();" />    
		</td>
	   <td class="tddarknormal" align="right">Middle</td>
	   <td class="tdlightnormal">
			<form:input path="middleName" size="10" maxlength="10"  onchange="middleName.value = middleName.value.toProperCase();" />
	  </td>
       <td class="tddarknormal" align="right">Last Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="lastName" size="40" maxlength="40" onchange="defaultFields(); lastName.value = lastName.value.toProperCase();" /> 
		</td>
		
    </tr>
   <tr>
	  <td class="tddarknormal" align="right">A.K.A<font color="red">*</font></td>
	  <td>
			<form:input path="nickname" size="20"  maxlength="20"  onchange="nickname.value = nickname.value.toProperCase();" />
       </td>
	   <td class="tddarknormal" align="right">Prior Surname</td>
	   <td class="tdlightnormal">
			<form:input path="maiden" size="20" maxlength="20"  onchange="maiden.value = maiden.value.toProperCase();"   />
	  </td>
	  <td class="tddarknormal" align="right">Suffix</td>
	  <td>
			<form:input path="suffix" size="20"  maxlength="20"  onchange="suffix.value = suffix.value.toProperCase();" />
       </td>
    </tr>
    <tr>
		 <td class="tddarknormal" align="right">Company<font color="red">*</font></td>
		 <td class="tdlight" valign="middle">
		   <form:select path="companyName" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${profileCmd.orgList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>  
          
		 </td>

        <td class="tddarknormal" align="right">Department<font color="red">*</font></td>
        <td class="tdlight">
           <form:select path="department" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${profileCmd.departmentList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>     

        </td>
		<td class="tddarknormal" align="right">Division<font color="red">*</font></td>
        <td class="tdlight">
           <form:select path="division" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${profileCmd.divisionList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>                    
        </td>
     </tr>  
    <tr >
         <td class="tddarknormal"  align="right">Functional Title<font color="red">*</font></td>
         <td class="tdlight">
         	<form:input path="title" size="40" /> 
         </td>
		<td class="tddarknormal"  align="right">Job Classification</td>
        <td  class="tdlight">
               <form:select path="jobCode">
               		 <form:option value="-" label="-Please Select-"/>
	              	  <c:forEach items="${profileCmd.jobCodeList}" var="jobCode">
		                <form:option value="${jobCode.id.codeGroup}" label="${jobCode.description}" />
		              </c:forEach>
          		</form:select>
          		
        </td>
			<td class="tddarknormal"  align="right">Employment Type</td>
        	<td  colspan="3"  class="tdlight">
	            <form:select path="employeeType">
	            	  <form:option value="-" label="-Please Select-"/>
	                  <c:forEach items="${profileCmd.userTypeList}" var="userType">
		                <form:option value="${userType.id.codeGroup}" label="${userType.description}" />
		              </c:forEach>
          		</form:select>
		</td>
		
     </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Supervisor
		 </td>
         <td class="tdlight">
	            <form:select path="managedBy">
	              	  <form:option value="-" label="-Please Select-"  />
		              <c:forEach items="${profileCmd.managerList}" var="manager">
		                <form:option value="${manager.userId}" label="${manager.firstName} ${manager.lastName} " />
		              </c:forEach>
          		</form:select>
		</td>
		<td class="tddarknormal"  align="right">Status</td>
         <td class="tdlight" colspan="3" >${profileCmd.status}</td>
		

    </tr>

    <tr >
   		<td class="tddarknormal" align="right">DOB<br>(MM/dd/yyyy)</td>
        <td class="tdlight"><form:input path="birthDate" size="20" /><br>
        	<font color="red"><form:errors path="birthDate" /></font>
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
 
    <tr>
		 <td class="tddarknormal"  align="right">Member of Groups:</td>
         <td class="tdlight" valign="center">
         <c:if test="${profileCmd.selectedGroups != null}" >
         	<table>
			<c:forEach items="${profileCmd.selectedGroups}" var="group">
	       		<tr>
	       			<td>${group.grpName}</td>
	       		</tr>
       		</c:forEach>
       		</table>
       	</c:if>

		</td>
		<td class="tddarknormal"  align="right">Member of Roles:</td>
        <td colspan="3"  class="tdlight">
        <c:if test="${profileCmd.roleAry != null}" >
         	<table>
			<c:forEach items="${profileCmd.roleAry}" var="role">
	       		<tr>
	       			<td>${role.roleName}</td>
	       		</tr>
       		</c:forEach>
       		</table>
       	</c:if>
		</td>
    </tr>

   <tr>
   	<td colspan="6" class="tddark" align="center">Contact Information</td>
   </tr>

  <tr>
       <td class="tddarknormal" align="right">Building Name</td>
       <td class="tdlight"> 
       	   <form:select path="locationBldg" multiple="false" onchange="selectChange('locationBldg');">
              <form:option value="-" label="-Please Select-"/>
              <c:forEach items="${profileCmd.locationAry}" var="location">
                <form:option value="${location.locationId}" label="${location.name}-${location.bldgNum}-${location.address1}-${location.city}-${location.state}-${location.postalCd} " />
              </c:forEach>
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
       <td class="tddarknormal" align="right">Bldg Num - Address</td>
       <td class="tdlight"><form:input path="bldgNbr" size="5"  /> <form:input path="address1" size="20"  /></td>
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
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"><form:input path="address2" size="30"  /></td>
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
       <td class="tddarknormal" align="right">City</td>
       <td class="tdlight"><form:input path="city" size="30"  /></td>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
       	</font>   
       </td>
   </tr>
     
 <tr>
       <td class="tddarknormal" align="right">State</td>
       <td class="tdlight"><form:input path="state" size="30"  /></td>
       <td class="tddarknormal" align="right">Emergency Contact:</td>
       <td class="tdlight" colspan="3"><form:input path="altCellAreaCode" size="3" maxlength="3" onchange="return  validateInt(altCellAreaCode);" /> 
       					  <form:input path="altCellNbr" size="10" maxlength="10"  onchange="return  validateInt(altCellNbr);" /> 
      	<font color="red"> <form:errors path="altCellAreaCode" />
       		<form:errors path="altCellNbr" />
       	</font>   
        </td>
   </tr> 

  <tr>
       <td class="tddarknormal" align="right">Zip Code</td>
       <td class="tdlight"><form:input path="postalCode" size="30" maxlength="10"  /></td>
      <td class="tddarknormal" align="right">Home Phone</td>
       <td class="tdlight" colspan="3"><form:input path="homePhoneAreaCode" size="3" maxlength="3" onchange="return  validateInt(homePhoneAreaCode);" /> 
       					  <form:input path="homePhoneNbr" size="10" maxlength="10" onchange="return  validateInt(homePhoneNbr);" /> 
      	<font color="red"> <form:errors path="homePhoneAreaCode" />
       		<form:errors path="homePhoneNbr" />
       	</font>
       </td>
   </tr>

  <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"></td>
       <td class="tddarknormal" align="right">Corporate Email-1 <font color="red">*</font></td>
       <td class="tdlight" colspan="3"><form:input path="email1" size="40" maxlength="40"  /></td>  

   </tr>
  <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"></td>
       <td class="tddarknormal" align="right">Personal Email</td>
       <td class="tdlight" colspan="3"><form:input path="email2" size="40" maxlength="40"  /></td>
   </tr>



          <tr>
              <td colspan="6" align="right"> <input type="submit" value="Submit"> <input type="reset" value="Restore">  </td>
          </tr>
</table>

</form:form>

