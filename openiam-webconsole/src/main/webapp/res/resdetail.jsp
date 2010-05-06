<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 





<form:form commandName="resourceDetailCmd">
<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Resource Details For: ${resourceDetailCmd.resource.name}  </strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  
 
      
          <tr>
			  <td class="tdheader" >Resource Id</td>
              <td class="plaintext" ><form:input path="resource.resourceId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
			  <td class="tdheader" >Resource Type</td>
              <td class="plaintext" ><form:input path="resource.resourceType.resourceTypeId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
			  <td class="tdheader" >Name</td>
              <td class="plaintext" ><form:input path="resource.name" size="40" maxlength="40"  /></td>
          </tr>
          
         <tr>
			  <td class="tdheader" >Description</td>
              <td class="plaintext" ><form:input path="resource.description" size="40" maxlength="100" /></td>
          </tr>
         <tr>
			  <td class="tdheader" >Parent</td>
              <td class="plaintext" ><form:input path="resource.resourceParent" size="32" maxlength="32" /></td>
          </tr>
         <tr>
			  <td class="tdheader" >Display Order</td>
              <td class="plaintext" >
              			    <form:select path="resource.displayOrder">
			    				  <form:option value="-" label="-Please Select-"/>
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
			  <td class="tdheader" >Linked to Managed System</td>
              <td class="plaintext" >
                         <form:select path="resource.managedSysId" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${resourceDetailCmd.managedSysAry}" itemValue="managedSysId" itemLabel="name"/>
          </form:select>     
             </td>
          </tr>

  <c:if test="${resourceDetailCmd.resourceProp != null}" >	
	<c:forEach items="${resourceDetailCmd.resourceProp}" var="resourceProp" varStatus="prop">
		  
				<tr class="plaintext">
								<td class="tdheader" >
								${resourceProp.name}
									<form:hidden path="resourceProp[${prop.index}].name" />
									<form:hidden path="resourceProp[${prop.index}].resourcePropId" />
									<form:hidden path="resourceProp[${prop.index}].resourceId" />
								</td>
								<td  class="plaintext">
								<form:input path="resourceProp[${prop.index}].propValue" size="40" maxlength="200" /> </td>
				</tr>
				
	</c:forEach>
 </c:if>	
            
         <tr>
    	  <td colspan="5">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
           <tr>
              <td colspan="2" align="right"> 
              <c:if test="${resourceDetailCmd.resource.resourceId != null}" >
              	<input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Resource');">
              </c:if>
              	<input type="submit" name="btn" value="Save"> </td>
          </tr>

          
          
</table>
</form:form>

