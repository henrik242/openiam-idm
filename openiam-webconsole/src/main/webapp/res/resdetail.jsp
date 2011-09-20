<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<script type="text/javascript">
<!--


function showResourceDialog(idfield, namefield) {
	var ua = window.navigator.userAgent;
    var msie = ua.indexOf ( "MSIE " );

    if ( msie > 0 ) {	
		dialogReturnValue = window.showModalDialog("res/dialogshell.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
		document.getElementById (idfield).value = dialogReturnValue.id;
		document.getElementById (nameField).value = dialogReturnValue.name;
    }else {
    	dialogReturnValue = window.showModalDialog("res/selresource.jsp",null,"dialogWidth:670px;dialogHeight:600px;");
    	document.getElementById (idfield).value = dialogReturnValue.id;
    	document.getElementById (namefield).value = dialogReturnValue.name;
    }
}


//-->
</script>

		<table  width="850pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Resource Management</h2>
						</td>
						</tr>
					</table>
			</td>

<% 	String msg = (String)request.getAttribute("msg");
	if (msg != null) { %>
<tr>
    <td class="msg"  align="center">
        <b><%=msg %>
        </b>
    </td>
</tr>
<% } %>

			<tr>
				<td>
<form:form commandName="resourceDetailCmd">
<table width="850pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>RESOURCE DETAILS FOR: ${resourceDetailCmd.resource.name} </legend>

				<table class="fieldsetTable"  width="100%" >
      
          <tr>
			  <td><label for="username" class="attribute">Resource Id</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><form:input path="resource.resourceId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
			  <td><label for="username" class="attribute">Resource Type</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><form:input path="resource.resourceType.resourceTypeId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
			  <td><label for="username" class="attribute">Name</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><form:input path="resource.name" size="40" maxlength="40"  /></td>
          </tr>
          
         <tr>
			  <td><label for="username" class="attribute">Description</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><form:input path="resource.description" size="40" maxlength="100" /></td>
          </tr>
         <tr>
			  <td><label for="username" class="attribute">Parent</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><form:input path="resource.resourceParent" size="32" maxlength="32" />
              <a href="javascript:showSupervisorDialog('supervisorId', 'supervisorName' );">Select Parent Resource</a>
              </td>
          </tr>
         <tr>
			  <td><label for="username" class="attribute">Display Order</label><font color="red">*</font></td>
              <td  class="userformInput" for="username" class="labelValue" >
              			    <form:select path="resource.displayOrder">
    				              <form:option value="1" label="1"/>
    				              <form:option value="2" label="2"/>
    				              <form:option value="3" label="3"/>
    				              <form:option value="4" label="4"/>
    				              <form:option value="5" label="5"/>
    				              <form:option value="6" label="6"/>
    				              <form:option value="7" label="7"/>
    				              <form:option value="8" label="8"/>
    				              <form:option value="9" label="9"/>
    				              <form:option value="10" label="10"/>
    				              <form:option value="11" label="11"/>
    				              <form:option value="12" label="12"/>
    				              <form:option value="13" label="13"/>
    				              <form:option value="14" label="14"/>
    				              <form:option value="15" label="15"/>  				              
			    	</form:select>
              
              </td>
          </tr>
         <tr>
			  <td><label for="username" class="attribute">Linked to Managed System</label></td>
              <td  class="userformInput" for="username" class="labelValue" >
                         <form:select path="resource.managedSysId" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${resourceDetailCmd.managedSysAry}" itemValue="managedSysId" itemLabel="name"/>
          </form:select>     
             </td>
          </tr>



  <c:if test="${resourceDetailCmd.resourceProp != null}" >	
	<c:forEach items="${resourceDetailCmd.resourceProp}" var="resourceProp" varStatus="prop">
		  
				<tr>
								<td><label for="username" class="attribute">
								${resourceProp.name}</label>
									<form:hidden path="resourceProp[${prop.index}].name" />
									<form:hidden path="resourceProp[${prop.index}].resourcePropId" />
									<form:hidden path="resourceProp[${prop.index}].resourceId" />
								</td>
								<td>
								<form:input path="resourceProp[${prop.index}].propValue" size="40" maxlength="200" /> </td>
				</tr>
				
	</c:forEach>
 </c:if>	


  <c:if test="${resourceDetailCmd.resource.resourceType.resourceTypeId == 'SSO_APP'}" >
  		<tr>
			  <td><label for="username" class="attribute">Proxy Path</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><input type="text" size="60" value="salesforce">

             </td>
       </tr>
   		<tr>
			  <td><label for="username" class="attribute">Target System URL</label></td>
              <td  class="userformInput" for="username" class="labelValue" ><input type="text" size="60" value="http://wwww.salesforce.com/login">

             </td>
       </tr>

 </c:if>


           <tr>
              <td class="buttonRow" align="right"> 
              <c:if test="${resourceDetailCmd.resource.resourceId != null}" >
              	<input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Resource');">
              </c:if>
              	<input type="submit" name="btn" value="Save"> <input type="submit" name="_cancel" value="Cancel" /> </td>
          </tr>  		          
</table>
</form:form>

	</td>
 </tr>
</table>
