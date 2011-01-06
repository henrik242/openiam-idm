<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="700pt">
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
 		<td class="msg" colspan="5" align="center"  >
 		  <b><%=msg %></b>
 		</td>
  </tr> 
<% } %>

			<tr>
				<td>

<form:form commandName="attrMapCmd">
          <form:hidden path="resId" />
          <form:hidden path="managedSysId" />
          	
	<table>
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>RESOURCE POLICY MAP: ${attrMapCmd.resourceName }</legend>
	
		<table class="resourceTable" cellspacing="2" cellpadding="2" >	
	
    
        <tr class="header">
			  <th></td>
			  <th>Object Type</td>
			  <th>Attribute Name</td>
			  <th>Policy</td>
			  <th>Status</td>
       </tr>
      	  <c:forEach items="${attrMapCmd.attrMapList}" var="resAttr" varStatus="attr">
   			
			<tr>
				<td class="tableEntry"><form:checkbox path="attrMapList[${attr.index}].selected" /> </td>
			  <td class="tableEntry"><form:hidden path="attrMapList[${attr.index}].attributeMapId" /> 
			    	<form:hidden path="attrMapList[${attr.index}].resourceId" /> 
			    <form:select path="attrMapList[${attr.index}].mapForObjectType">
			    				  <form:option value="-" label="-Please Select-"/>
    				              <form:option value="USER" label="USER"/>
    				              <form:option value="PRINCIPAL" label="PRINCIPAL"/>
    				              <form:option value="EMAIL" label="EMAIL"/>
    				              <form:option value="GROUP" label="GROUP"/>
			    	</form:select>
			     </td>
				<td class="tableEntry"><form:input path="attrMapList[${attr.index}].attributeName" maxlength="50" />  </td>
				<td class="tableEntry"> 
					<form:select path="attrMapList[${attr.index}].attributePolicy.policyId" >
            					<form:option value="" label="-Please Select-"/>
            					<form:options items="${attrPolicyAry}" itemValue="policyId" itemLabel="name"/>
       					</form:select>   
				 </td>
				 <td class="tableEntry"> <form:select path="attrMapList[${attr.index}].status">
    				              <form:option value="-" label="-Please Select-"/>
    				              <form:option value="ACTIVE" label="ACTIVE"/>
    				              <form:option value="IN-ACTIVE" label="IN-ACTIVE"/>
       					  </form:select>
       			</td>
			</tr>
		</c:forEach>
	</table>

           <tr>
              <td  align="right"> <input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this mapping?');" />  <input type="submit" name="btn" value="Save" /> <input type="submit" name="btn" value="Cancel" />  </td>
          </tr>
</table>
</TD>
</TR>
</TABLE>

</form:form>

	</td>
 </tr>
</table>

