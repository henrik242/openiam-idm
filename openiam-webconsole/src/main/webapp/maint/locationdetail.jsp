<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
   		<table  width="800pt" >
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Locations</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>	
<form:form commandName="locationCmd">
	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>EDIT LOCATIONS</legend>
	
					<table class="fieldsetTable"  width="600pt" >   
  					<form:hidden path="location.locationId"  />
          <tr>
			  			<td>Name</td>
              <td><form:input path="location.name" size="40" maxlength="40" /><br>
              </td>
          </tr>
          <tr>
              <td>Building Number</td>
			 			 <td><form:input path="location.bldgNum" size="20" maxlength="10"  /></td>
		  </tr>
          <tr>
			  			<td>Street Direction</td>
              <td ><form:input path="location.streetDirection" size="20" maxlength="20" /></td>
          </tr>
          <tr>
			  			<td>Address 1</td>
              <td ><form:input path="location.address1" size="45" maxlength="45" /></td>
          </tr>
            <tr>
			  			<td>Address 2</td>
              <td ><form:input path="location.address2" size="45" maxlength="45" /></td>
          </tr>
          
                   <tr>
			  			<td>Address 3</td>
              <td ><form:input path="location.address3" size="45" maxlength="45" /></td>
          </tr>
          
          <tr>
			 				 <td>City</td>
              <td ><form:input path="location.city" size="30" maxlength="30" /></td>
          </tr>
          
          <tr>
			  			<td>State / Province</td>
              <td ><form:input path="location.state" size="30" maxlength="15" /></td>
          </tr>
          
          <tr>
			  			<td>Zip / PostalCode</td>
              <td ><form:input path="location.postalCd" size="10" maxlength="10" /></td>
          </tr>
          <tr>
			  			<td>Country</td>
              <td ><form:input path="location.country" size="30" maxlength="30" /></td>
          </tr>

    </TABLE>
          <tr>
              <td align="right">
              <c:if test="${locationCmd.location.locationId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>

</td>
</tr>
</table>