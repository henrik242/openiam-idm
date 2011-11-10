<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<script type="text/javascript">
<!--


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


//-->
</script>

		<table  width="800pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">UPDATE USER</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<tr>
				<td>

<form:form commandName="editUserCmd">

	<form:hidden path="user.userId" />

	<form:hidden path="user.dateChallengeRespChanged" />
	<form:hidden path="user.datePasswordChanged" />
	
	<form:hidden path="addressId" />
	
	<form:hidden path="workPhoneId" />
	<form:hidden path="cellPhoneId" />
	<form:hidden path="faxPhoneId" />
	<form:hidden path="homePhoneIdr" />
	<form:hidden path="altCellNbrId" />
	<form:hidden path="altPhoneNbrId" />
	<form:hidden path="personalNbrId" />


<table width="800pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>EDIT USER INFORMATION</legend>

				<table class="fieldsetTable"  width="100%" >

				   <tr>
				       <td><label for="username" class="attribute">User GUID</label></td>
				       <td class="userformInput" for="username" class="labelValue">${editUserCmd.user.userId}</td>

                       <td><label for="username" class="attribute">User Status</label></td>
				       <td class="userformInput" for="username" class="labelValue">${editUserCmd.user.status}</td>

                       <td><label for="username" class="attribute">Acct State</label></td>
				       <td class="userformInput" for="username" class="labelValue">${editUserCmd.user.secondaryStatus}</td>

				    </tr>				   

				   <tr>
				       <td><label for="username" class="attribute">First Name</label><font color="red">*</font></td>
				       <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.firstName" size="35" maxlength="40" onchange="firstName.value = firstName.value.toProperCase();" />  
							<br><form:errors path="user.firstName" cssClass="error" />     
						</td>
					   <td><label for="username" class="attribute">Middle</label></td>
					   <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.middleInit" size="10" maxlength="10"  onchange="middleName.value = middleName.value.toProperCase();" />
					  </td>
				       <td><label for="username" class="attribute">Last Name</label><font color="red">*</font></td>
				       <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.lastName" size="35" maxlength="40" onchange="lastName.value = lastName.value.toProperCase();" /> 
							<form:errors path="user.lastName" cssClass="error" />   
						</td>
						
				    </tr>
				   <tr>
					  <td  ><label for="username" class="attribute">A.K.A(Nickname)</label></td>
					  <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.nickname" size="20"  maxlength="20"  onchange="nickname.value = nickname.value.toProperCase();" />
				       </td>
					   <td><label for="username" class="attribute">Maiden-Name</label></td>
					   <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.maidenName" size="20" maxlength="20"  onchange="maiden.value = maiden.value.toProperCase();"   />
					  </td>
					  <td  ><label for="username" class="attribute">Suffix</label></td>
					  <td class="userformInput" for="username" class="labelValue">
							<form:input path="user.suffix" size="20"  maxlength="20"  onchange="suffix.value = suffix.value.toProperCase();" />
				       </td>
				    </tr>
				        <tr >
						 <td ><label for="username" class="attribute">Organization</label></td>
						 <td class="userformInput" for="username" class="labelValue">
						   <form:select path="user.companyId" multiple="false">
				              <form:option value="" label="-Please Select-"/>
				              <form:options items="${orgList}" itemValue="orgId" itemLabel="organizationName"/>
				          </form:select>  
				          <BR><form:errors path="user.companyId" cssClass="error" />   
				          
						 </td>
				
				        <td ><label for="username" class="attribute">Department</label></td>
				        <td class="userformInput" for="username" class="labelValue">
				           <form:select path="user.deptCd" multiple="false">
				              <form:option value="" label="-Please Select-"/>            
				              <form:options items="${deptList}" itemValue="orgId" itemLabel="organizationName"/>
				          </form:select>     
				          <form:errors path="user.deptCd" cssClass="error" />  
				
				        </td>
						<td ><label for="username" class="attribute">Division</label></td>
				        <td class="userformInput" for="username" class="labelValue">
				           <form:select path="user.division" multiple="false">
				              <form:option value="" label="-Please Select-"/>
				              <form:options items="${divList}" itemValue="orgId" itemLabel="organizationName"/>
				          </form:select>                    
				        </td>
				     </tr>  
				    <tr >
				         <td ><label for="username" class="attribute">Functional Title</label></td>
				         <td class="userformInput" for="username" class="labelValue"> 
				         	<form:input path="user.title" size="35" /> 
				         	<BR><form:errors path="user.title" cssClass="error" />  
				         </td>
						<td algn="right"><label for="username" class="attribute">Job-Code</label></td>
				        <td class="userformInput" for="username" class="labelValue">
				               <form:select path="user.jobCode">
				               		 <form:option value="" label="-Please Select-"/>
					              	  <c:forEach items="${jobCodeList}" var="jobCode">
						                <form:option value="${jobCode.id.statusCd}" label="${jobCode.description}" />
						              </c:forEach>
				          		</form:select>         		
				        </td>
							<td ><label for="username" class="attribute">Classification</label></td>
				        	<td class="userformInput" for="username" class="labelValue"><form:input path="user.classification" size="35" /> </td>
				     </tr>
				 
				     <tr >
				         <td ><label for="username" class="attribute">Employee Id</label></td>
				         <td class="userformInput" for="username" class="labelValue"> 
				         	<form:input path="user.employeeId" size="35" /> 
				         </td>
						<td algn="right"><label for="username" class="attribute">User Type</label></td>
				        <td class="userformInput" for="username" class="labelValue"><form:input path="user.userTypeInd" size="30" />       		
				        </td>
							<td ><label for="username" class="attribute">Empl. Type</label></td>
				      <td class="userformInput" for="username" class="labelValue">
					            <form:select path="user.employeeType">
					            	  <form:option value="-" label="-Please Select-"/>
					                  <c:forEach items="${userTypeList}" var="userType">
						                <form:option value="${userType.id.statusCd}" label="${userType.description}" />
						              </c:forEach>
				          		</form:select>
				          		<BR><form:errors path="user.employeeType" cssClass="error" />  
						</td>
				     </tr>
				
				    <tr >
						 <td><label for="username" class="attribute">Supervisor</label> </td>
				     <td colspan="3" class="userformInput" for="username" class="labelValue">
				         	<form:hidden path="supervisorId" />
				            <form:input path="supervisorName" size="35" readonly="true" /> <a href="javascript:showSupervisorDialog('supervisorId', 'supervisorName' );">Select Supervisor</a>
				
						</td>
						<td></td>
				
				    </tr>   
				    <tr >
						 <td ><label for="username" class="attribute">Alt. Contact</label></td>
				     <td colspan="3" class="userformInput" for="username" class="labelValue">
				         	<form:hidden path="user.alternateContactId" />
				            <form:input path="alternateContactName" size="35" readonly="true" /> <a href="javascript:showSupervisorDialog('user.alternateContactId', 'alternateContactName' );">Select Alternate</a>
				
						</td>
						<td  align="right" colspan="2"></td>
				    </tr>
				    <tr>
				   		<td><label for="username" class="attribute">Date of Birth<label></td>
				        <td class="userformInput" for="username" class="labelValue" ><form:input path="user.birthdate" size="20" />(MM/dd/yyyy)<br>
				        	<font color="red"><form:errors path="user.birthdate" /></font>
				        </td>
				    
				
						<td    ><label for="username" class="attribute">Gender</label></td>
				        <td  colspan="3" class="userformInput" for="username" class="labelValue" >
					        <form:select path="user.sex">
								  <form:option value="" label="-Please Select-"  />
					        <form:option value="M" label="Male" />
								  <form:option value="F" label="Female" />
								  <form:option value="D" label="Declined to State" />
				          		</form:select>
				          		<br><form:errors path="user.sex" cssClass="error" />  
						</td>
				    </tr>
				       <tr >
						 <td    ><label for="username" class="attribute">Start Date</label></td>
						<td  class="userformInput" for="username" class="labelValue"><form:input path="user.startDate" size="20" />(MM/dd/yyyy)<br>
				        	<font color="red"><form:errors path="user.startDate" /></font>
				        </td>
						
						
						<td    ><label for="username" class="attribute">Last Date</label></td>
						<td colspan="3" class="userformInput" for="username" class="labelValue" ><form:input path="user.lastDate" size="20" />(MM/dd/yyyy)<br>
				        	<font color="red"><form:errors path="user.lastDate" /></font>
				        </td>
				    </tr>  

				    <tr   >
						 <td ><label for="username" class="attribute">Show in Search</label></td>
				         <td class="userformInput" for="username" class="labelValue">
				                 <form:select path="user.showInSearch">
								  <form:option value="0" label="0"  />
					              <form:option value="1" label="1" />
								  <form:option value="2" label="2" />
								  <form:option value="3" label="3"  />
					              <form:option value="4" label="4" />
								  <form:option value="5" label="5" />
				          		</form:select>
						</td>
						 <td  ><label for="username" class="attribute">Delegated-Admin</label></td>
				         <td class="userformInput" for="username" class="labelValue" >
                               <form:select path="user.delAdmin">
								  <form:option value="0" label="NO"  />
					              <form:option value="1" label="YES" />
				          		</form:select>
						</td>
				
						</td>
						<td align="right" colspan="2"></td>
				
				    </tr>
				  </table>
			   </td>
			</tr>
			<tr>
				<td>
				  		 
			<fieldset class="userformSearch" >
			<legend>EDIT USER CONTACT INFORMATION</legend>

				<table class="fieldsetTable"  width="100%" >
				 
				  <tr >
				       <td   ><label for="username" class="attribute">Building Name</label></td>
				       <td  class="userformInput" for="username" class="labelValue" > 
				       	   <form:select path="user.locationCd" multiple="false" >
				              <form:option value="" label="-Please Select-"/>
				              <c:forEach items="${locationAry}" var="location">
				                <form:option value="${location.locationId}" label="${location.name}" />
				              </c:forEach>
				          </form:select>  
				
				       </td>
				       <td   ><label for="username" class="attribute">Desk Phone</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue"><form:input path="workAreaCode" size="3"  maxlength="3"   /> 
				       <form:input path="workPhone" size="10" maxlength="10"  /> 
				      	<font color="red"><form:errors path="workAreaCode" />
				       		<form:errors path="workPhone" />
				       	</font>
				       </td>
				   </tr>
				  <tr >
				       <td   ><label for="username" class="attribute">Bldg Num - Address</label></td>
				       <td  class="userformInput" for="username" class="labelValue" ><form:input path="user.bldgNum" size="5"  /> <form:input path="user.address1" size="20"  /></td>
				       <td   ><label for="username" class="attribute">Cell/BlackBerry Phone</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue">
				       		<form:input path="cellAreaCode" size="3" maxlength="3"  /> 
				       		<form:input path="cellPhone" size="10" maxlength="10"  />
				       	</td>
				   </tr>
				  <tr >
				       <td   ></td>
				       <td  class="userformInput" for="username" class="labelValue"><form:input path="user.address2" size="30"  /></td>
				       <td   ><label for="username" class="attribute">Fax</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue">
							<form:input path="faxAreaCode" size="3" maxlength="3" /> 
							<form:input path="faxPhone" size="10" maxlength="10" /> 
				       	</font>   
				       </td>
				   </tr>
				 	<tr >
				       <td   ><label for="username" class="attribute">City</label></td>
				       <td   class="userformInput" for="username" class="labelValue"><form:input path="user.city" size="30"  /></td>
				       <td   ></td>
				       <td   colspan="3">   
				       </td>
				   </tr>
				     
				 <tr >
				       <td><label for="username" class="attribute">State</label></td>
				       <td  class="userformInput" for="username" class="labelValue"><form:input path="user.state" size="30"  /></td>
				       <td><label for="username" class="attribute">Personal Phone</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue"><form:input path="altCellAreaCode" size="3" maxlength="3" /> 
				       					  <form:input path="altCellNbr" size="10" maxlength="10"  /> 
 
				        </td>
				   </tr> 
				
				  <tr >
				       <td   ><label for="username" class="attribute">Zip Code</label></td>
				       <td  class="userformInput" for="username" class="labelValue" ><form:input path="user.postalCd" size="30" maxlength="10"  /></td>
				      <td   ><label for="username" class="attribute">Home Phone</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue"><form:input path="homePhoneAreaCode" size="3" maxlength="3"  /> 
				       					  <form:input path="homePhoneNbr" size="10" maxlength="10"  /> 
				      	<font color="red"> <form:errors path="homePhoneAreaCode" />
				       		<form:errors path="homePhoneNbr" />
				       	</font>
				       </td>
				   </tr>
				
				  <tr >
				       <td   ></td>
				       <td  ></td>
				       <td   ><label for="username" class="attribute">Corporate Email-1</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue"><form:input path="email1" size="35" maxlength="40"  />
				       <form:hidden path="email1Id" />
				       <form:hidden path="email2Id" />
				       <form:hidden path="email3Id" />
				</td>  
				
				   </tr>
				  <tr >
				       <td   ></td>
				       <td  ></td>
				       <td   ><label for="username" class="attribute">Personal Email</label></td>
				       <td   colspan="3" class="userformInput" for="username" class="labelValue"><form:input path="email2" size="35" maxlength="320"  /></td>
				   </tr>
				    </td>
				  </tr>
				   <tr>
				       <td><label for="username" class="attribute">Last Password Change:</label></td>
				       <td class="userformInput" for="username" class="labelValue">${editUserCmd.user.datePasswordChanged}</td>
					   <td></td>
					   <td class="userformInput" for="username" class="labelValue"></td>
				       <td><label for="username" class="attribute"></label></td>
				       <td class="userformInput" for="username" class="labelValue"></td>						
				    </tr>					
				   <tr>
				       <td><label for="username" class="attribute">Last Challenge Response Change:</label></td>
				       <td class="userformInput" for="username" class="labelValue">${editUserCmd.user.dateChallengeRespChanged}</td>
					   <td></td>
					   <td class="userformInput" for="username" class="labelValue"></td>
				       <td><label for="username" class="attribute"></label></td>
				       <td class="userformInput" for="username" class="labelValue"></td>						
				    </tr>	
				    				
				</table>				 
				 		</td>
	</tr>
		<tr>
				<td class="buttonRow" align="right">
				   <c:if test="${user.status != 'ACTIVE'}" >
				 <input type="submit" name="saveBtn" value="ACTIVE"/>
					</c:if>	    
				    <c:if test="${user.status != 'DELETED'}" >
				 <input type="submit" name="saveBtn" value="DELETE"/> | 
					</c:if>    
				   <input type="submit" name="saveBtn" value="DISABLE"/> <input type="submit" name="saveBtn" value="ENABLE"/>
					   
				 
				    	  | <input type="submit" name="saveBtn" value="Save"/>   <input type="submit" name="_cancel" value="Cancel" />

			</td>
	</tr>
 	</table> 
	</form:form>
 </td>
</tr>
</table>
