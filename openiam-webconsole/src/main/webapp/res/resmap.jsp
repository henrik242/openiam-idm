<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 





<form:form commandName="attrMapCmd">
<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Resource Policy Map For:${attrMapCmd.resourceName } </strong>
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
			  <td class="tdheader" >Object Type</td>
			  <td class="tdheader" >Attribute Name</td>
			  <td class="tdheader" >Policy</td>
			  <td class="tdheader" >Status</td>
          </tr>
      	  <c:forEach items="${attrMapCmd.attrMapList}" var="resAttr" varStatus="attr">
   			
			<tr class="tdlight">
				<td><form:checkbox path="attrMapList[${attr.index}].selected" /> </td>
			    <td><form:hidden path="attrMapList[${attr.index}].attributeMapId" /> 
			    	<form:hidden path="attrMapList[${attr.index}].resourceId" /> 
			    <form:select path="attrMapList[${attr.index}].mapForObjectType">
			    				  <form:option value="-" label="-Please Select-"/>
    				              <form:option value="USER" label="USER"/>
    				              <form:option value="PRINCIPAL" label="PRINCIPAL"/>
    				              <form:option value="EMAIL" label="EMAIL"/>
    				              <form:option value="GROUP" label="GROUP"/>
			    	</form:select>
			     </td>
				<td><form:input path="attrMapList[${attr.index}].attributeName" maxlength="50" />  </td>
				<td> 
					<form:select path="attrMapList[${attr.index}].attributePolicy.policyId" >
            					<form:option value="" label="-Please Select-"/>
            					<form:options items="${attrPolicyAry}" itemValue="policyId" itemLabel="name"/>
       					</form:select>   
				 </td>
				 <td> <form:select path="attrMapList[${attr.index}].status">
    				              <form:option value="-" label="-Please Select-"/>
    				              <form:option value="ACTIVE" label="ACTIVE"/>
    				              <form:option value="IN-ACTIVE" label="IN-ACTIVE"/>
       					  </form:select>
       			</td>
			</tr>
		</c:forEach>


           <tr>
              <td colspan="5" align="right"> <input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this mapping?');" />  <input type="submit" name="btn" value="Save" /> <input type="reset" />  </td>
          </tr>

          
          
</table>
</form:form>

