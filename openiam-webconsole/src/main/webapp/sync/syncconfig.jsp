<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="syncCmd">

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Synchronization Configuration </strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="syncConfig.name" size="40" maxlength="40"  />
			  </td>
		  </tr>
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.status" size="40" maxlength="40"  />
			  </td>
		  </tr>   	  	
          <tr>
              <td class="tddark">Synchronization Source</td>
			  <td class="tdlightnormal">
		   <form:select path="syncConfig.synchSrc" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="CSV" label="CSV FILE"/>
              <form:option value="RES" label="MANAGED RESOURCE"/>
          </form:select>  
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">File Path</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.fileName" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Managed Resource</td>
			  <td class="tdlightnormal">
		   <form:select path="syncConfig.managedSysId" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <!-- form:options items="${orgList}" itemValue="orgId" itemLabel="organizationName"/ -->
          </form:select>           
			  </td>
		  </tr> 
          <tr>
              <td class="tddark">Load Matching Objects Only</td>
			  <td class="tdlightnormal">
		   <form:checkbox path="syncConfig.loadMatchOnly" value="1" />         
			  </td>
		  </tr> 
          <tr>
              <td class="tddark">Update Changed Attributes</td>
			  <td class="tdlightnormal">
		   <form:checkbox path="syncConfig.updateAttribute" value="1" />         
			  </td>
		  </tr> 
          <tr>
              <td class="tddark">Transformation Rule</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.transformationRule" size="60" maxlength="60"  /> </td>
		  </tr> 		  		  		  		  
          <tr>
              <td class="tddark">Delete Rule</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.deleteRule" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Process Rule</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.processRule" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Reject Rule</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.rejectRule" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Match Object On</td>
			  <td class="tdlightnormal">
			  	<table>
			  		<tr class="normaltext">
			  			<td><b>IDM Repository Field</b></td>
			  			<td><b>Synch SRC Field<b></td>
			  		</tr>
			  		<tr class="normaltext">
			  			<td>
			 <form:select path="syncConfig.matchFieldName" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="USERID" label="IDM UserId"/>
              <form:option value="PRINCIPAL" label="PRINCIPAL NAME"/>
              <form:option value="EMAIL" label="PRIMARY EMAIL ADDRESS"/>
              <form:option value="EMPLOYEE ID" label="EMPLOYEE ID"/>
              
          </form:select>  
			  			</td>
			  			<td><form:input path="syncConfig.matchSrcFieldName" size="20" maxlength="40"  /> </td>
			  		</tr>
			  		<tr class="normaltext">
			  			<td>Managed Sys
			 <form:select path="syncConfig.matchManagedSysId" multiple="false">
              <form:option value="-" label="-Please Select-"/>     
          </form:select>  
          <br><i>Only needed if matching on a principal name</i>
			  			</td>
			  			<td></td>
			  		</tr>
			  	</table>
			  </td>
		  </tr>
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
            <td colspan="2" align="right">
           <input type="submit" name="btn" value="Submit"/>   
    	  <input type="submit" name="btn" value="Reset" />
               </td>
          </tr>
</table>

</form:form>

