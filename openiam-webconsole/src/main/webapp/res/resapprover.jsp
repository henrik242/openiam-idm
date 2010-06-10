<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<script type="text/javascript">
<!--


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


<form:form commandName="approvalCmd">
<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Resource Approval Flow For:${approvalCmd.resourceName } </strong>
          <form:hidden path="resId" />
          <form:hidden path="managedSysId" />
      </td>
   </tr>

   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
<% 	String msg = (String)request.getAttribute("msg");
	if (msg != null) { %> 
   <tr>
 		<td class="msg" colspan="5" align="center"  >
 		  <b><%=msg %></b>
 		</td>
  </tr> 
<% } %>
    
          <tr>
			  <td class="tdheader" ></td>
			  <td class="tdheader" >Approval Stage</td>
			  <td class="tdheader" >Object Type</td>
			  <td class="tdheader" >Object ID</td>
			  <td class="tdheader" >Approver Name</td>
          </tr>
      	  <c:forEach items="${approvalCmd.approverAssoc}" var="approverAssoc" varStatus="assoc">
   			
			<tr class="tdlight">
				<td><form:checkbox path="approverAssoc[${assoc.index}].selected" /> </td>
			    <td><form:hidden path="approverAssoc[${assoc.index}].approverAssocId" /> 
			    <form:select path="approverAssoc[${assoc.index}].approverLevel">
    				              <form:option value="1" label="1"/>
    				              <form:option value="2" label="2"/>
    				              <form:option value="3" label="3"/>
    				              <form:option value="4" label="4"/>
    				              <form:option value="5" label="5"/>
			    	</form:select>
			     </td>
				<td>
				    <form:select path="approverAssoc[${assoc.index}].associationType">
			 				      <form:option value="" label="-Please Select-"/>
    				              <form:option value="RESOURCE" label="Resource"/>
    				              <form:option value="ROLE" label="Role"/>
    				              <form:option value="SUPERVISOR" label="Supervisor"/>
			    	</form:select>
				  </td>
				<td>
				    <form:input path="approverAssoc[${assoc.index}].associationObjId" />
			 				      
				  </td>


				<td> <form:hidden path="approverAssoc[${assoc.index}].approverUserId" />
				<form:input path="approverAssoc[${assoc.index}].approverName" readonly="true"  />
				<a href="javascript:showSupervisorDialog('approverAssoc${assoc.index}.approverUserId', 'approverAssoc${assoc.index}.approverName' );">Select Approver</a>
 
				 </td>
			</tr>
		</c:forEach>


           <tr>
              <td colspan="5" align="right"> <input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this stage in the process?');" />  <input type="submit" name="btn" value="Save" /> <input type="reset" />  </td>
          </tr>

          
          
</table>
</form:form>

