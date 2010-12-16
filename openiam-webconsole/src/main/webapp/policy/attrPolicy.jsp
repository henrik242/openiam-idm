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
								<h2 class="contentheading">Policy Management</h2>
						</td>
						</tr>
					</table>
			</td>
			<tr>
				<td>   
<form:form commandName="attrPolicyCmd">
	<form:hidden path="policyDefId"/>
		
<table width="800pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>EDIT ATTRIBUTE POLICY: </legend>

				<table class="fieldsetTable"  width="100%" >


          <tr>
					  <td><label for="username" class="attribute">Policy Id</label></td>
		        <td><form:input path="policyPKId" size="40" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td><label for="username" class="attribute">Name</label><font color="red">*</font></td>
			 			  <td><form:errors path="name" cssClass="error"  />
			  <c:if test="${attrPolicyCmd.policyPKId != null}" >
			  		<form:input path="name" size="60" maxlength="60" readonly="true"  />
			  </c:if>
			  <c:if test="${attrPolicyCmd.policyPKId == null}" >
			  		<form:input path="name" size="60" maxlength="60" readonly="false"  />
			  </c:if>
			  </td>
		  </tr>
          <tr>
			  <td><label for="username" class="attribute">Description</label></td>
              <td class="tdlightnormal" ><form:input path="description" size="60" maxlength="255" /></td>
          </tr>
          <tr>
              <td><label for="username" class="attribute">Status</label></td>
			  <td><form:select path="status">
			  		<form:option value="-1" >-Select a value</form:option>
	             	<form:option value="1">Active</form:option>
	             	<form:option value="2">In-Active</form:option> 
          		</form:select>
			  </td>
		  </tr>
          <tr>
			  <td><label for="username" class="attribute">Rule URL:</label></td>
              <td><form:input path="ruleSrcUrl" size="60" maxlength="80" /></td>
          </tr>
          <tr>
              <td><label for="username" class="attribute">Policy Rule</label></td>
			  <td>
			  <form:errors path="rule" cssClass="error"  />
			  	<form:textarea path="rule" rows="10" cols="80" />
			  </td>
		  </tr>
      
  		  


         <tr>
    	  <td colspan="2">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
		</table>
          <tr>
              <td colspan="2" align="right">
             
             <c:if test="${attrPolicyCmd.policyPKId != null}" >
              	<input type="submit" name="btn" value="Delete">
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>

	</TD>
</TR>
</TABLE>

</form:form>

	</td>
 </tr>
</table>