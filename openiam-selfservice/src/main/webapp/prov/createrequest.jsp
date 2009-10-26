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

<form:form commandName="changeAccessCmd">
<table width="620" border="0" cellspacing="2" cellpadding="1" align="center"> 
	<tr>
      <td colspan="2" class="title">         
          <strong>Create Request </strong>
      </td>
   </tr>
  
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
 
   <tr>
       <td class="tddark" align="center" colspan="2">Requestor Information</td>
    </tr>
   <tr>
       <td class="tdlight" align="right" width="30%">Name<br>
       <i>First Name - Middle - Last Name</i></td>
       <td class="normal" >
       <form:hidden path="RequestorUserId" />
       <form:input path="firstName" size="20" readonly="true" /> <form:input path="middleName" size="5" readonly="true" /> <form:input path="lastName" size="25" readonly="true" /></td>
    </tr>
   <tr>
       <td class="tdlight" align="right" width="30%">Title</td>
       <td class="normal"><form:input path="title" size="25" readonly="true" /></td>
    </tr>
   <tr>
       <td class="tdlight" align="right" width="30%">Department</td>
       <td class="normal"><form:input path="department" size="25" readonly="true" /></td>
    </tr>
   <tr>
       <td class="tdlight" align="right" width="30%">Phone</td>
       <td class="normal">(<form:input path="phoneAreaCd" size="10" readonly="true" />)<form:input path="phoneNbr" size="25" readonly="true" /></td>
    </tr>
    <tr>
       <td class="tdlight" align="right" width="30%">Email</td>
       <td class="normal"><form:input path="email" size="50" readonly="true" /></td>
    </tr>
   <tr>
       <td class="tddark" align="center" colspan="2">Request Information</td>
    </tr>    
    <tr>
       <td class="tdlight" align="right" width="30%">Type</td>
       <td class="normal">
       
              	<form:select path="requestType">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="CHANGE" label="Change Access" />
				  <form:option value="TERMINATE" label="Terminate" />
          		</form:select>
       </td>
    </tr>
    <tr>
       <td class="tdlight" align="right" width="30%">Start Date</td>
       <td class="normal"><form:input path="startDate" size="15" /></td>
    </tr>
    <tr>
       <td class="tdlight" align="right" width="30%">End Date</td>
       <td class="normal"><form:input path="endDate" size="15" /></td>
    </tr>
    <tr>
       <td class="tdlight" align="right" width="30%">Change Access By</td>
       <td class="normal">
                <form:select path="changeAccessBy">
				  <form:option value="-" label="-Please Select-"  />
	              <form:option value="JOBROLE" label="Job Role" />
				  <form:option value="MANUAL" label="Manual" />
          		</form:select>
       </td>
    </tr>
    <tr>
       <td class="tdlight" align="right" width="30%">Select Job Role
       	<i>Select role if changing access by role</i></td>
       <td class="normal">
       
       </td>
    </tr>
   <tr>
       <td class="tdlight" align="right"></td>
       <td class="tdlight">
			<font color="red"></font>
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
    	  <input type="submit" name="_target1" value="Next"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>
  

</table>

</form:form>

