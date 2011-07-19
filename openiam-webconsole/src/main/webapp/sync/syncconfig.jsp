<%@ page language="java" contentType="text/html;"   %>
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
								<h2 class="contentheading">Synchronization</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>    
<form:form commandName="syncCmd">
			<table width="800pt"  class="bodyTable" height="100%" >
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CONFIGURE SYNCHRONIZATION </legend>
						
						<table class="fieldsetTable"  width="100%" >
		
          <tr>
              <td colspan="2"><font color="red">${syncCmd.errorText}</font></td>
		  </tr>
		  							

          <tr>
              <td>Name<font color="red">*</font></td>
			  <td><form:hidden path="syncConfig.synchConfigId"  />
			  <form:input path="syncConfig.name" size="40" maxlength="40"  />
               <form:errors path="syncConfig.name" cssClass="error" />
			  </td>
		  </tr>
          <tr>
              <td>Status</td>
			  <td>
			  	<form:select path="syncConfig.status" multiple="false">
              		<form:option value="" label="-Please Select-"/>
              		<form:option value="ACTIVE" />
              		<form:option value="INACTIVE" />
          		</form:select> 
			  </td>
		  </tr>   	  	
          <tr>
              <td class="tddark">Synchronization Source <font color="red">*</font></td>
			  <td class="tdlightnormal">
		   <form:select path="syncConfig.synchAdapter" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:option value="CSV" label="CSV FILE"/>
              <form:option value="RDBMS" label="RDBMS"/>
              <form:option value="LDAP" label="LDAP"/>
              <form:option value="CUSTOM" label="CUSTOM ADAPTER"/>
             <!--
              <form:option value="AD" label="ACTIVE DIRECTORY"/>
              <form:option value="CUSTOM" label="CUSTOM ADAPTER"/>
          	-->
          </form:select>
          <form:errors path="syncConfig.synchAdapter" cssClass="error" />
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">Custom Adapter Script</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.customAdatperScript" size="60" maxlength="60"  /> </td>
		  </tr> 
		  
          <tr>
              <td class="tddark">File Path</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.fileName" size="60" maxlength="60"  /> </td>
		  </tr> 

          <tr>
              <td class="tddark">Host Name</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.srcHost" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Src Login</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.srcLoginId" size="60" maxlength="60"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Src Password</td>
			  <td class="tdlightnormal"><form:password path="syncConfig.srcPassword" size="60" maxlength="60"  /> </td>
		  </tr> 

          <tr>
              <td class="tddark">JDBC Driver</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.driver" size="60" maxlength="100"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Connection URL</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.connectionUrl" size="60" maxlength="100"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">SQL Query / Directory Filter</td>
			  <td class="tdlightnormal"><form:textarea  path="syncConfig.query" rows="5" cols="60"  /> </td>
		  </tr>

            <tr>
                <td class="tddark">Base DN</td>
                <td class="tdlightnormal">
                    <form:input path="syncConfig.baseDn" size="60" maxlength="60"  /> </td>
            </tr>

          <tr>
              <td class="tddark">Last Update Field</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.queryTimeField" size="60" maxlength="60"  /> </td>
		  </tr> 

          <tr>
              <td class="tddark">Synch Type</td>
			  <td class="tdlightnormal">
			  		   <form:select path="syncConfig.synchType" multiple="false">
              				<form:option value="FULL" label="COMPLETE"/>
              				<form:option value="INCREMENTAL" label="INCREMENTAL"/>
          				</form:select> 
			 </td>
		  </tr> 

          <tr>
              <td class="tddark">Synch Frequency</td>
			  <td class="tdlightnormal">
			  		   <form:select path="syncConfig.synchFrequency" multiple="false">
              				<form:option value="" label="-Please Select-"/>
              				<form:option value="MINUTE" label="1 min"/>
              				<form:option value="5MIN" label="5 min"/>
             				<form:option value="15MIN" label="15 min"/>
             				<form:option value="60MIN" label="60 min"/>
             				<form:option value="NIGHTLY" label="NIGHTLY"/>
          				</form:select> 
			
			 </td>
		  </tr> 
		  
          <tr>
              <td class="tddark">Last Record Processed</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.lastExecTime" size="60" maxlength="60"  readonly="true" /></td>
		  </tr> 
          <tr>
              <td class="tddark">Directory Last Record Processed</td>
			  <td class="tdlightnormal"><form:input path="syncConfig.lastRecProcessed" size="60" maxlength="60"  readonly="true" /></td>

          <tr>
              <td class="tddark">Validate Rule <font color="red">*</font></td>
			  <td class="normaltext"><form:input path="syncConfig.validationRule" size="60" maxlength="60"  />
			   <br><i>Validates the row of data for accepting it into the transformation process</i>
              <form:errors path="syncConfig.validationRule" cssClass="error" /></td>
		  </tr>
          <tr>
              <td class="tddark">Transformation Rule <font color="red">*</font></td>
			  <td class="normaltext"><form:input path="syncConfig.transformationRule" size="60" maxlength="60"  />
			  <br><i>Transforms the data into a form that can be used by the identity manager.</i>
              <form:errors path="syncConfig.transformationRule" cssClass="error" /></td>
		  </tr> 		  		  		  		  
          <tr>
              <td class="tddark">Process Rule</td>
			  <td class="normaltext"><form:input path="syncConfig.processRule" size="60" maxlength="60"  />
			  <br><i>Criteria to trigger a process flow.</i> </td>
		  </tr> 

		   
          <tr>
              <td class="tddark">Match Object On <font color="red">*</font></td>
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
				              <form:option value="EMPLOYEE_ID" label="EMPLOYEE ID"/>
				               <form:option value="ATTRIBUTE" label="CUSTOM ATTRIBUTE"/>
				          </form:select>  
			  			</td>
			  			<td><form:input path="syncConfig.matchSrcFieldName" size="40" maxlength="40"  /> </td>
			  		</tr>
          <tr>
              <td class="tddark">Attribute Name</td>
			  <td class="normaltext"><form:input path="syncConfig.customMatchAttr" size="40" maxlength="40"  />
			  	</td>
		  </tr>
			  	</table>
			  </td>
		  </tr>
         </TABLE>

          <tr class="buttonRow">

            <td align="right">
            <c:if test="${syncCmd.syncConfig.synchConfigId != null}" >
            <input type="submit" name="btn" value="Delete" onclick="return confirm('Are you sure you want to delete this Configuration?');"/>
            <input type="submit" name="btn" value="Sync Now" onclick="return confirm('Are your sure that you want to start synchronization?');"/>
            </c:if> 
           <input type="submit" name="btn" value="Submit"/>   
    	  <input type="submit" name="btn" value="Reset" />
               </td>
          </tr>

        </TD>
       </TR>
</table>

</form:form>
</TD>
</TR>
</TABLE>
