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
								<h2 class="contentheading">User Management</h2>
						</td>
						</tr>
					</table>
			</td>
		</tr>
			<tr>
				<td>
<form:form commandName="filtercmd">

<form:hidden path="perId" />

<table width="700pt"  class="bodyTable" height="100%" >
<tr>
	<td>    
			<fieldset class="userformSearch" >
			<legend>DELEGATED ADMINISTRATION</legend>

				<table class="fieldsetTable"  width="100%" >
					
    

    <tr>
       <td><label for="username" class="attribute">Organization</LABEL></td>
       <td colspan="3">
			<select size="5" multiple="multiple">
				<option>BLUE CROSS BLUE SHIELD IL</option>
				<option>HAMLINE UNIVERSITY</option>
			</select>
       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Division</LABEL></td>
       <td colspan="3">
			<select size="5" multiple="multiple">
			</select>
       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Department<LABEL></td>
       <td class="tdlight" colspan="3">
			<select size="5" multiple="multiple">
			</select>
       </td>
    </tr>

    <tr>
       <td><label for="username" class="attribute">Resources</LABEL></td>
       <td class="tdlight" colspan="3">
			<select size="5" multiple="multiple">
			</select>
       </td>
    </tr>   
    				</table>				 
				 		</td>
	</tr>

  <tr class="buttonRow">
    <td align="right"> 
          <input type="submit" value="Save"/> <input type="submit" value="Cancel"/>  
    </td>
  </tr>
  
</table>

</form:form>
 </td>
</tr>
</table>

