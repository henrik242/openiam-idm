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
								<h2 class="contentheading">New User</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>   
<form:form commandName="newUserCmd">
		<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>    
							<fieldset class="userformSearch" >
							<legend>SELECT USER OBJECT CLASS</legend>
				
					<table class="fieldsetTable"  width="100%" >


   
   
	<tr>
 		<td >Object Class:<font color=red>*</font></td>
		<td> <form:select path="user.metadataTypeId" multiple="false">
              <form:option value="" label="-Please Select-"/>
              <form:options items="${metadataTypeAry}" itemValue="metadataTypeId" itemLabel="description"/>
              </form:select>
			 <BR><form:errors path="user.metadataTypeId" cssClass="error" />  
		</td>
		
	</tr>
</table>
 

  <tr class="buttonRow">
    <td align="right"> 
          <input type="submit" name="_target1" value="Next"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>

</form:form>

</TD>
</TR>
</TABLE>
