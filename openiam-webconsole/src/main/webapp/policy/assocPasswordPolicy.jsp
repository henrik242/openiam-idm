<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

   		<table  width="600pt" >
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
   
<form:form commandName="assocPolicyCmd">
	<form:hidden path="assoc.policyObjectId"/>
		
			<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>ASSOCIATE POLICY</legend>
	
	<table class="fieldsetTable"  width="600pt" >
		

          <tr>
			  <td >Association Level</td>
              <td class="tdlightnormal">
              	<form:select path="assoc.associationLevel">
			  		<form:option value="" >-Select a value</form:option>
	             	<form:option value="GLOBAL">GLOBAL</form:option>
	             	<form:option value="DOMAIN">DOMAIN</form:option> 
	             	<form:option value="TYPE">TYPE</form:option> 
	             	<form:option value="CLASSIFICATION">CLASSIFICATION</form:option> 
          		</form:select>
          	  </td>
          </tr>
          <tr>
			  <td>Association Value</td>
              <td class="tdlightnormal"><form:input path="assoc.associationValue"  /></td>
          </tr>          
    	</table>   
  		  
    	</tr>
          <tr>
              <td class="buttonRow" align="right">
             

              <input type="submit" name="btn" value="Submit"> <input type="submit" name="btn" value="Cancel"> </td>
          </tr>
</table>

</form:form>

</td>
 </tr>
</table>
