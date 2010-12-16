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

function showSupervisorDialog(idfield, namefield) {
	var ua = window.navigator.userAgent;
    var msie = ua.indexOf ( "MSIE " );

    if ( msie > 0 ) {	
		dialogReturnValue = window.showModalDialog("user/dialogshell.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
		document.getElementById (idfield).value = dialogReturnValue.id;
		document.getElementById (nameField).value = dialogReturnValue.name;
    }else {
    	dialogReturnValue = window.showModalDialog("user/selsupervisor.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
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

		<table  width="1000pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="100%">
								<h2 class="contentheading">New User</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
		
<form:form commandName="newUserCmd">
		<table width="800pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>ENTER NEW USER INFORMATION</legend>
				
					<table class="fieldsetTable"  width="800pt" >
	



   <tr>
       <td    >First Name<font color="red">*</font></td>
       <td  >
			<form:input path="user.firstName" size="30" maxlength="40" onchange="defaultFields(); firstName.value = firstName.value.toProperCase();" />  
			<br><form:errors path="user.firstName" cssClass="error" />     
		</td>
	   <td>Middle</td>
	   <td>
			<form:input path="user.middleInit" size="10" maxlength="10"  onchange="middleName.value = middleName.value.toProperCase();" />
	  </td>
       <td>Last Name<font color="red">*</font></td>
       <td>
			<form:input path="user.lastName" size="30" maxlength="40" onchange="defaultFields(); lastName.value = lastName.value.toProperCase();" /> 
			<form:errors path="user.lastName" cssClass="error" />   
		</td>
		
    </tr>
   <tr>
	  <td    >A.K.A(Nickname)</td>
	  <td>
			<form:input path="user.nickname" size="20"  maxlength="20"  onchange="nickname.value = nickname.value.toProperCase();" />
       </td>
	   <td    >Maiden Name</td>
	   <td  >
			<form:input path="user.maidenName" size="20" maxlength="20"  onchange="maiden.value = maiden.value.toProperCase();"   />
	  </td>
	  <td    >Suffix</td>
	  <td  >
			<form:input path="user.suffix" size="20"  maxlength="20"  onchange="suffix.value = suffix.value.toProperCase();" />
       </td>
    </tr>
    <tr>
		 <td    >Company</td>
		 <td   valign="middle">
		   <form:select path="user.companyId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:options items="${orgList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>  
          <BR><form:errors path="user.companyId" cssClass="error" />   
          
		 </td>

        <td    >Department</td>
        <td  >
           <form:select path="user.deptCd" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>     
          <br><form:errors path="user.deptCd" cssClass="error" />  

        </td>
		<td    >Division</td>
        <td  >
           <form:select path="user.division" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${divList}" itemValue="orgId" itemLabel="organizationName"/>
          </form:select>                    
        </td>
     </tr>  
    <tr >
         <td>Functional-Title</td>
         <td>
         	<form:input path="user.title" size="30" /> 
         	<BR><form:errors path="user.title" cssClass="error" />  
         </td>
		<td>Job-Classification</td>
        <td>
               <form:select path="user.jobCode">
               		 <form:option value="" label="-Please Select-"/>
	              	  <c:forEach items="${jobCodeList}" var="jobCode">
		                <form:option value="${jobCode.id.statusCd}" label="${jobCode.description}" />
		              </c:forEach>
          		</form:select>         		
        </td>
			<td>Employment-Type</td>
        	<td   >
	            <form:select path="user.employeeType">
	            	  <form:option value="" label="-Please Select-"/>
	                  <c:forEach items="${userTypeList}" var="userType">
		                <form:option value="${userType.id.statusCd}" label="${userType.description}" />
		              </c:forEach>
          		</form:select>
          		<br><form:errors path="user.employeeType" cssClass="error" />   
		</td>
		
     </tr>
    <tr >
         <td     >Cost Center</td>
         <td  >
         	<form:input path="user.costCenter" size="30" /> 
         </td>
		<td>Classification</td>
        <td><form:input path="user.classification" size="30" /></td>
		<td>Employee ID</td>
        <td><form:input path="user.employeeId" size="30" /></td>
		
     </tr>
    <tr >
		 <td>Supervisor
		 </td>
         <td   colspan="3" >
         	<form:hidden path="supervisorId" />
            <form:input path="supervisorName" size="30" readonly="true" /> <a href="javascript:showSupervisorDialog('supervisorId', 'supervisorName' );">Select Supervisor</a>

		</td>
		<td      colspan="2"></td>

    </tr>
    <tr >
		 <td     >Alternate Contact</td>
         <td   colspan="3">
         	<form:hidden path="user.alternateContactId" />
            <form:input path="alternateContactName" size="30" readonly="true" /> <a href="javascript:showSupervisorDialog('user.alternateContactId', 'alternateContactName' );">Select Alternate</a>

		</td>
		<td      colspan="2"></td>
    </tr>
    <tr >
   		<td>DOB </td>
        <td  ><form:input path="user.birthdate" size="20" /><br>
        	<font color="red"><form:errors path="user.birthdate" /></font>
        </td>
    

		<td>Gender</td>
        <td  colspan="3"   >
	            <form:select path="user.sex">
				  <form:option value="" label="-Please Select-"  />
	              <form:option value="M" label="Male" />
				  <form:option value="F" label="Female" />
				  <form:option value="D" label="Declined to State" />
          		</form:select>
          		<br><form:errors path="user.sex" cssClass="error" />  
		</td>
    </tr>
     <tr>
		 <td>Start Date</td>
		<td><form:input path="user.startDate" size="20" /><br>
        	<font color="red"><form:errors path="user.startDate" /></font>
        </td>
		
		
		<td>Last Date</td>
		<td colspan="3"><form:input path="user.lastDate" size="20" /><br>
        	<font color="red"><form:errors path="user.lastDate" /></font>
        </td>
    </tr>
    <tr>
		 <td>Mail Code</td>
		<td><form:input path="user.mailCode" size="20" /></td>
		
		
		<td>Show in search:</td>
		<td colspan="3">
				<form:select path="user.showInSearch">
 				   <form:option value="1" label="Yes"  />				  
	              <form:option value="0" label="No" />
          		</form:select>
          		<br>
        	<font color="red"><form:errors path="user.lastDate" /></font>
        </td>
    </tr>

     <tr >
		 <td>Group</td>
         <td>
          <form:select path="group" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${groupList}" itemValue="grpId" itemLabel="grpName"/>
          </form:select> 
         
		</td>
		
		
		<td>Role</td>
        <td   colspan="3">
          <form:select path="role" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <c:forEach items="${roleList}" var="role">
                <form:option value="${role.id.serviceId}*${role.id.roleId}" label="${role.id.serviceId}-${role.roleName}" />
              </c:forEach>
          </form:select> 
          <br><form:errors path="role" cssClass="error" />  
         
		</td>
    </tr>
   </table>
  </td>
</tr>
</table>

 		<table width="1000pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>ENTER EXTENDED USER ATTRIBUTES</legend>
				
		<table class="resourceTable" cellspacing="2" cellpadding="2" width="1000pt" >	
    			<tr class="header">
    				<th></td>
    				<th>NAME</td>
    				<th>VALUE</td>
    			</tr>
    			<c:forEach items="${newUserCmd.attributeList}" var="userAttr" varStatus="attr">
		  
				<tr>
					<td class="tableEntry"><form:checkbox path="attributeList[${attr.index}].selected" /></td>
					<td class="tableEntry">${newUserCmd.attributeList[attr.index].name}
						<form:hidden path="attributeList[${attr.index}].id" />
						<form:hidden path="attributeList[${attr.index}].userId" />
						<form:hidden path="attributeList[${attr.index}].name" />						
						<form:hidden path="attributeList[${attr.index}].attrGroup"  />
					 	<form:hidden path="attributeList[${attr.index}].metadataElementId"  />
					</td>
					<td class="tableEntry"><form:input path="attributeList[${attr.index}].value" size="35" maxlength="50" />
					</td>


				</tr>
				
			</c:forEach>
			</table>
  </td>
</tr>
</table>

 
 		<table width="1000pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>ENTER USER CONTACT INFORMATION</legend>
				
					<table class="fieldsetTable"  width="1150pt" >

 <tr>
       <td>Building Name</td>
       <td> 
       	   <form:select path="user.locationCd" multiple="false" onchange="selectChange('locationBldg');">
              <form:option value="-" label="-Please Select-"/>
              <c:forEach items="${locationAry}" var="location">
                <form:option value="${location.locationId}" label="${location.name}-${location.bldgNum}-${location.address1}-${location.city}-${location.state}-${location.postalCd} " />
              </c:forEach>
          </form:select>  

       </td>
       <td>Desk Phone</td>
       <td   colspan="3"><form:input path="user.areaCd" size="3"  maxlength="3" onblur="return validateInt(workAreaCode)"  /> 
       <form:input path="user.phoneNbr" size="10" maxlength="10" onblur="return  validateInt(workPhone)" /> - <form:input path="user.phoneExt" size="10" maxlength="10" onblur="return  validateInt(workPhone)" /> 
      	<font color="red"><form:errors path="workAreaCode" />
       		<form:errors path="workPhone" />
       	</font>
       </td>
   </tr>
  <tr>
       <td>Bldg Num - Address</td>
       <td><form:input path="user.bldgNum" size="5"  /> <form:input path="user.address1" size="20"  /></td>
       <td    >Cell/BlackBerry Phone</td>
       <td   colspan="3">
       		<form:input path="cellAreaCode" size="3" maxlength="3" onchange="return  validateInt(cellAreaCode);" /> 
       		<form:input path="cellPhone" size="10" maxlength="10"  onchange="return  validateInt(cellPhone);"/>
      	<font color="red"><form:errors path="cellPhone" />
       		<form:errors path="workPhone" />
       	</font>
       	</td>
   </tr>
  <tr>
       <td    ></td>
       <td  ><form:input path="user.address2" size="30"  /></td>
       <td    >Fax</td>
       <td   colspan="3">
			<form:input path="faxAreaCode" size="3" maxlength="3" onchange="return  validateInt(faxAreaCode);" /> 
			<form:input path="faxPhone" size="10" maxlength="10" onchange="return  validateInt(faxPhone);"/> 
      	<font color="red"> <form:errors path="faxAreaCode" />
       		<form:errors path="faxPhone" />
       	</font>   
       </td>
   </tr>
 	<tr>
       <td    >City</td>
       <td  ><form:input path="user.city" size="30"  /></td>
       <td    ></td>
       <td   colspan="3">
       </td>
   </tr>
     
 <tr>
       <td    >State</td>
       <td  ><form:input path="user.state" size="30"  /></td>
       <td    >Emergency Contact:</td>
       <td   colspan="3"><form:input path="altCellAreaCode" size="3" maxlength="3" onchange="return  validateInt(altCellAreaCode);" /> 
       					  <form:input path="altCellNbr" size="10" maxlength="10"  onchange="return  validateInt(altCellNbr);" /> 
      	<font color="red"> <form:errors path="altCellAreaCode" />
       		<form:errors path="altCellNbr" />
       	</font>   
        </td>
   </tr> 

  <tr>
       <td    >Zip Code</td>
       <td  ><form:input path="user.postalCd" size="30" maxlength="10"  /></td>
      <td    >Home Phone</td>
       <td   colspan="3"><form:input path="homePhoneAreaCode" size="3" maxlength="3" onchange="return  validateInt(homePhoneAreaCode);" /> 
       					  <form:input path="homePhoneNbr" size="10" maxlength="10" onchange="return  validateInt(homePhoneNbr);" /> 
      	<font color="red"> <form:errors path="homePhoneAreaCode" />
       		<form:errors path="homePhoneNbr" />
       	</font>
       </td>
   </tr>

  <tr>
       <td    ></td>
       <td  ></td>
       <td    >Corporate Email-1 </td>
       <td   colspan="3"><form:input path="user.email" size="30" maxlength="40"  /></td>  

   </tr>
  <tr>
       <td    ></td>
       <td  ></td>
       <td    >Alternate Email</td>
       <td   colspan="3"><form:input path="email2" size="30" maxlength="40"  /></td>
   </tr>


</table>

  <tr class="buttonRow">

    <td align="right"> 
	 	  <input type="submit" name="_target0" value="Previous" /> 
    	  <!--  input type="submit" name="_target2" value="Next"/ -->   
    	  <input type="submit" name="_finish" value="Submit"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>

</form:form>
</TD>
</TR>
</TABLE>
