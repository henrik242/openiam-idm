<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



		<table  width="800pt">
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
			<tr>
				<td>			
<form:form commandName="reconCmd">

	<table width="950pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>RECONCILIATION CONFIGURATION FOR RESOURCE: ${reconCmd.res.name}</legend>
						
						<table class="fieldsetTable"  width="100%" >
   
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal">
			  		<form:select path="config.status">
    				              <form:option value="ACTIVE" />
    				              <form:option value="IN-ACTIVE" />			              
			    	</form:select>
			  </td>
		  </tr>   	  	
          <tr>
              <td class="tddark">Mode</td>
			  <td class="tdlightnormal">
		   <form:select path="config.mode" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="FULL" label="FULL"/>
              <form:option value="INCREMENTAL" label="INCREMENTAL"/>
          </form:select>  
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">Frequency</td>
			  <td class="tdlightnormal">
		   <form:select path="config.mode" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="5MIN" label="Every 5 min"/>
              <form:option value="15MIN" label="Every 15 min"/>
              <form:option value="60MIN" label="Every 1 Hour"/>
              <form:option value="NIGHTLY" label="Run Nightly"/>  
          </form:select>  
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">Attribute Level Check</td>
			  <td class="tdlightnormal"><form:checkbox path="config.attributeLevelCheck" value="1"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Update Changed Attribute</td>
			  <td class="tdlightnormal"><form:checkbox path="config.updateChangedAttribute" value="1"  /> </td>
		  </tr> 
		</TABLE>  
		</TD>
	</TR>
</TABLE>

	<table width="950pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>RECONCILIATION EVENT MAPPING</legend>
		<table class="resourceTable" cellspacing="2" cellpadding="2" >	 
          <tr>
              <td colspan="2">
              	
    		<tr class="header">
    			<tH>Situation</td>
    			<tH>Response</td>
    			<th>Pre-Reconcile Script</td>
    			<th>Post-Reconcile Script</td>
    		</tr>
         	  <c:forEach items="${reconCmd.situationList}" var="situationList" varStatus="sit">
   			
			<tr class="tdlight">
				<td class="tableEntry">${situationList.situation}
					<form:hidden path="situationList[${sit.index}].reconConfigId" />
					<form:hidden path="situationList[${sit.index}].reconSituationId" />
					<form:hidden path="situationList[${sit.index}].situation" /> </td>
				<td class="tableEntry"> 
				<form:select path="situationList[${sit.index}].situationResp">
								  <form:option value="" label="-Please Select-"/>
    				              <form:option value="NOTHING" label="DO NOTHING"/>
    				              <form:option value="CREATE_IDM_ACCOUNT" label="CREATE IDM ACCOUNT"/>
    				              <form:option value="CREATE_RES_ACCOUNT" label="CREATE RESOURCE ACCOUNT"/>
    				              <form:option value="DEL_RES_ACCOUNT" label="DELETE RESOURCE ACCOUNT"/>
    				              <form:option value="DEL_IDM_ACCOUNT" label="DELETE IDM ACCOUNT"/>
    				              <form:option value="UPD_IDENTITY" label="UPDATE IDENTITY"/>
    				              <form:option value="UPD_RESOURCE" label="UPDATE RESOURCE"/> 				              
			    	</form:select>
				</td>
				<td class="tableEntry"><form:input path="situationList[${sit.index}].preReconScript" size="30" maxlength="60" /> </td>
				<td class="tableEntry"><form:input path="situationList[${sit.index}].postReconScript" size="30" maxlength="60" /> </td>
			
			</tr>
		</c:forEach>
     </table>     
              </td>
		  </tr> 
		  
          <tr align="right">
            <td colspan="2" >
            <input type="submit" name="btn" value="Reconcile Now"/> 
           <input type="submit" name="btn" value="Save"/>   
           <input type="submit" name="btn" value="Cancel"/>  
               </td>
          </tr>
</table>

</TD>
</TR>
</TABLE>

</form:form>

	</td>
 </tr>
</table>
