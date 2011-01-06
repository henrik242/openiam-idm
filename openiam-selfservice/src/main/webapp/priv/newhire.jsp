<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<script type="text/javascript">
<!--

function defaultFields() {
	var theForm = document.getElementById ('newHireCmd');
	theForm.userPrincipalName.value = theForm.user.firstName.value + '.' + theForm.user.lastName.value  ;
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

function showUserDialog(idfield, namefield) {
	var ua = window.navigator.userAgent;
    var msie = ua.indexOf ( "MSIE " );

    if ( msie > 0 ) {	
		dialogReturnValue = window.showModalDialog("user/dialogshell.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
		document.getElementById (idfield).value = dialogReturnValue.id;
		document.getElementById (nameField).value = dialogReturnValue.name;
    }else {
    	dialogReturnValue = window.showModalDialog("user/seluser.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
    	document.getElementById (idfield).value = dialogReturnValue.id;
    	document.getElementById (namefield).value = dialogReturnValue.name;
    }
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

<form:form commandName="newHireCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="4" class="title">         
          <strong>New User Information</strong>
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
       <td class="tdlight" colspan="3">
			<font color="red"></font>
       </td>
    </tr>

   <tr>
       <td class="tddarknormal" align="right">First Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="user.firstName" size="40" maxlength="40" onchange="defaultFields(); firstName.value = firstName.value.toProperCase();" />  
			<br><form:errors path="user.firstName" cssClass="error" />     
		</td>
	   <td class="tddarknormal" align="right">Middle</td>
	   <td class="tdlightnormal">
			<form:input path="user.middleInit" size="10" maxlength="10"  onchange="middleName.value = middleName.value.toProperCase();" />
	  </td>
       <td class="tddarknormal" align="right">Last Name<font color="red">*</font></td>
       <td class="tdlightnormal">
			<form:input path="user.lastName" size="40" maxlength="40" onchange="defaultFields(); lastName.value = lastName.value.toProperCase();" /> 
			<form:errors path="user.lastName" cssClass="error" />   
		</td>
		
    </tr>
   <tr>
	  <td class="tddarknormal" align="right">Nickname</td>
	  <td>
			<form:input path="user.nickname" size="20"  maxlength="20"  onchange="nickname.value = nickname.value.toProperCase();" />
       </td>
	   <td class="tddarknormal" align="right">Maiden Name</td>
	   <td class="tdlightnormal">
			<form:input path="user.maidenName" size="20" maxlength="20"  onchange="maiden.value = maiden.value.toProperCase();"   />
	  </td>
	  <td class="tddarknormal" align="right">Suffix</td>
	  <td class="tdlightnormal">
			<form:input path="user.suffix" size="20"  maxlength="20"  onchange="suffix.value = suffix.value.toProperCase();" />
       </td>
    </tr>
    <tr>
		 <td class="tddarknormal" align="right">Organization<font color="red">*</font></td>
		 <td class="tdlightnormal" valign="middle">
		   <form:select path="user.companyId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${orgList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>  
          <BR><form:errors path="user.companyId" cssClass="error" />   
          
		 </td>

        <td class="tddarknormal" align="right">Department<font color="red">*</font></td>
        <td class="tdlightnormal">
           <form:select path="user.deptCd" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>     
          <form:errors path="user.deptCd" cssClass="error" />  

        </td>
		<td class="tddarknormal" align="right">Division</td>
        <td class="tdlight">
           <form:select path="user.division" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${divList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>                    
        </td>
     </tr>  
    <tr >
         <td class="tddarknormal"  align="right">Functional Title</td>
         <td class="tdlight">
         	<form:input path="user.title" size="40" /> 
         	  
         </td>
		<td class="tddarknormal"  align="right">Job Classification</td>
        <td  class="tdlight">
               <form:select path="user.jobCode">
               		 <form:option value="" label="-Please Select-"/>
	              	  <c:forEach items="${jobCodeList}" var="jobCode">
		                <form:option value="${jobCode.id.codeGroup}" label="${jobCode.description}" />
		              </c:forEach>
          		</form:select>         		
        </td>
			<td class="tddarknormal"  align="right">Employment Type<font color="red">*</font></td>
        	<td  class="tdlightnormal">
	            <form:select path="user.employeeType">
	            	  <form:option value="-" label="-Please Select-"/>
	                  <c:forEach items="${userTypeList}" var="userType">
		                <form:option value="${userType.id.codeGroup}" label="${userType.description}" />
		              </c:forEach>
          		</form:select>
          		<BR><form:errors path="user.employeeType" cssClass="error" />  
		</td>
		
     </tr>
    <tr >
		 <td class="tddarknormal"  align="right">Supervisor<font color="red">*</font>
		 </td>
         <td class="tdlightnormal" colspan="5">
           	<form:hidden path="supervisorId" />
            <form:input path="supervisorName" size="50" readonly="true" /> <a href="javascript:showUserDialog('supervisorId', 'supervisorName' );">Select Supervisor</a>
            <br><form:errors path="supervisorId" cssClass="error" />  
  
		</td>
	</tr>

    <tr >
   		<td class="tddarknormal" align="right">Birthdate</td>
        <td class="tdlight"><form:input path="user.birthdate" size="20" /><br>
        	<font color="red"><form:errors path="user.birthdate" /></font>
        </td>
    

		<td class="tddarknormal"  align="right">Gender<font color="red">*</font></td>
        <td  colspan="3"  class="tdlightnormal">
	            <form:select path="user.sex">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="M" label="Male" />
				  <form:option value="F" label="Female" />
				  <form:option value="D" label="Declined to State" />
          		</form:select>
          		<br><form:errors path="user.sex" cssClass="error" />  
		</td>
    </tr>
     <tr >
		 <td class="tddarknormal"  align="right">Start Date</td>
		<td class="tdlight"><form:input path="user.startDate" size="20" /><br>
        	<font color="red"><form:errors path="user.startDate" /></font>
        </td>
		
		
		<td class="tddarknormal"  align="right">Last Date</td>
		<td class="tdlight" colspan="3"><form:input path="user.lastDate" size="20" /><br>
        	<font color="red"><form:errors path="user.lastDate" /></font>
        </td>
    </tr>

     <tr >
		 <td class="tddarknormal"  align="right">Group</td>
         <td class="tdlight">
          <form:select path="group" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${groupList}" itemValue="grpId" itemLabel="grpName"/>
          </form:select> 
         
		</td>
		
		
		<td class="tddarknormal"  align="right">Role<font color="red">*</font></td>
        <td class="tdlightnormal" colspan="3">
          <form:select path="role" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${roleList}" itemValue="id.roleId" itemLabel="roleName"/>
          </form:select> 
          <br><form:errors path="role" cssClass="error" />  
         
		</td>
    </tr>
 
   <tr>
   	<td colspan="6" class="tddark" align="center">Contact Information</td>
   </tr>

 <tr>
       <td class="tddarknormal" align="right">Building Name</td>
       <td class="tdlight"> 
       	   <form:select path="user.locationCd" multiple="false" onchange="selectChange('locationBldg');">
              <form:option value="-" label="-Please Select-"/>
              <c:forEach items="${locationAry}" var="location">
                <form:option value="${location.locationId}" label="${location.name}-${location.bldgNum}-${location.address1}-${location.city}-${location.state}-${location.postalCd} " />
              </c:forEach>
          </form:select>  

       </td>
       <td class="tddarknormal" align="right">Desk Phone</td>
       <td class="tdlight" colspan="3"><form:input path="workAreaCode" size="3"  maxlength="3" onblur="return validateInt(workAreaCode)"  /> 
       <form:input path="workPhone" size="10" maxlength="10" onblur="return  validateInt(workPhone)" /> 
      	<font color="red"><form:errors path="workAreaCode" />
       		<form:errors path="workPhone" />
       	</font>
       </td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Bldg Num - Address</td>
       <td class="tdlight"><form:input path="user.bldgNum" size="5"  /> <form:input path="user.address1" size="20"  /></td>
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
       <td class="tdlight"><form:input path="user.address2" size="30"  /></td>
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
       <td class="tdlight"><form:input path="user.city" size="30"  /></td>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight" colspan="3">
       	</font>   
       </td>
   </tr>
     
 <tr>
       <td class="tddarknormal" align="right">State</td>
       <td class="tdlight"><form:input path="user.state" size="30"  /></td>
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
       <td class="tdlight"><form:input path="user.postalCd" size="30" maxlength="10"  /></td>
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
       <td class="tddarknormal" align="right">Corporate Email-1</td>
       <td class="tdlight" colspan="3"><form:input path="email1" size="40" maxlength="40"  /></td>  

   </tr>
  <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"></td>
       <td class="tddarknormal" align="right">Personal Email</td>
       <td class="tdlight" colspan="3"><form:input path="email2" size="40" maxlength="40"  /></td>
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
  	<td class="normaltext" colspan="4">
  
  	</td>
    <td colspan="2" align="right">   
    	  <input type="submit" name="_finish" value="Submit"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>
  

</table>

</form:form>

