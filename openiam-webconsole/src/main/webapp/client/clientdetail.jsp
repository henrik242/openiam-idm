<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

       
		<table  width="600pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">CLIENT Manager</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>       
<form:form commandName="clientDetailCmd">
		<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>CLIENT DETAILS</legend>
				
					<table class="fieldsetTable"  width="100%" >
	
       <tr>
			  <td>CLIENT ID</td>
              <td class="tdlightnormal"><form:input path="org.orgId" size="35" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Name<font color="red">*</font></td>
			  <td><form:input path="org.organizationName" size="40" maxlength="40"  /><br>
			  <form:errors path="org.organizationName" cssClass="error" /></td>
		  </tr>
       <tr>
        <td>Abbreviation</td>
			  <td><form:input path="org.abbreviation" size="40" maxlength="20"  /></td>
		  </tr>		  
          <tr>
              <td>Symbol</td>
			  <td><form:input path="org.symbol" size="40" maxlength="10"  /></td>
		  </tr>		

          <tr>
			  <td>Description</td>
              <td><form:input path="org.description" size="40" maxlength="80" /></td>
          </tr>
          <tr>
              <td>Metadata Type</td>
			  <td>
			  		<form:select path="org.metadataTypeId">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${orgDetailCmd.typeList}" itemValue="metadataTypeId" itemLabel="description"  />
		            </form:select>
			  </td>
		  </tr>
          <tr>
              <td>Classification</td>
			  <td>
			  		<form:select path="org.classification">
			  			<form:option value=""  label="-Select a value" />
				  		<form:option value="ORGANIZATION"  label="ORGANIZATION" />

			  	    </form:select>
			  </td>
		  </tr>
          <tr>
              <td>Alias</td>
			  <td><form:input path="org.alias" size="40" maxlength="40" />
			  </td>
		  </tr> 

          <tr>
              <td>DomainName</td>
			  <td><form:input path="org.domainName" size="40" maxlength="40" />
			  </td>
		  </tr>          
		  <tr>
              <td>Organization Status</td>
			  <td>
			  		<form:select path="org.status">
			  			<form:option value=""  label="-Select a value" />
			  			<form:option value="ACTIVE"  label="ACTIVE" />
                        <form:option value="DISABLE"  label="DISABLE" />
			  			<form:option value="IN-ACTIVE"  label="IN-ACTIVE" />
			  			<form:option value="DELETED"  label="DELETED" />
			  			<form:option value="PENDING_APPROVAL"  label="PENDING APPROVAL" />
		            </form:select>
			  </td>
		  </tr>	
		  
		  
	      <tr>
              <td>Client Contact</td>
			  <td><form:input path="firstName" size="20" maxlength="40" />   <form:input path="lastName" size="20" maxlength="40" />
			  </td>
		  </tr>


  
          <tr>
              <td>Create</td>
			  <td>On ${orgListCmd.org.createDate} by ${orgListCmd.group.createdBy} 
			  </td>
		  </tr>	
          <tr>
              <td>Last Update</td>
			  <td>On ${orgListCmd.org.lastUpdate} by ${orgListCmd.org.lastUpdatedBy}	  </td>
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

              <input type="submit" name="btn" value="Save"> <input type="submit" name="btn" value="Cancel"></td>
          </tr>
	</table>
</form:form>
</TD>
</TR>
</TABLE>


							


 </td>
</tr>
</table>