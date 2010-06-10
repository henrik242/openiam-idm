<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="locationCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Location Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  <form:hidden path="location.locationId"  />
          <tr>
			  <td class="tddark">Name</td>
              <td class="tdlightnormal"><form:input path="location.name" size="40" maxlength="40" /><br>
              </td>
          </tr>
          <tr>
              <td class="tddark">Building Number</td>
			  <td class="tdlightnormal"><form:input path="location.bldgNum" size="20" maxlength="10"  /></td>
		  </tr>
          <tr>
			  <td class="tddark">Street Direction</td>
              <td class="tdlightnormal" ><form:input path="location.streetDirection" size="20" maxlength="20" /></td>
          </tr>
          <tr>
			  <td class="tddark">Address 1</td>
              <td class="tdlightnormal" ><form:input path="location.address1" size="45" maxlength="45" /></td>
          </tr>
            <tr>
			  <td class="tddark">Address 2</td>
              <td class="tdlightnormal" ><form:input path="location.address2" size="45" maxlength="45" /></td>
          </tr>
          
                   <tr>
			  <td class="tddark">Address 3</td>
              <td class="tdlightnormal" ><form:input path="location.address3" size="45" maxlength="45" /></td>
          </tr>
          
          <tr>
			  <td class="tddark">City</td>
              <td class="tdlightnormal" ><form:input path="location.city" size="30" maxlength="30" /></td>
          </tr>
          
          <tr>
			  <td class="tddark">State / Province</td>
              <td class="tdlightnormal" ><form:input path="location.state" size="30" maxlength="15" /></td>
          </tr>
          
          <tr>
			  <td class="tddark">Zip / PostalCode</td>
              <td class="tdlightnormal" ><form:input path="location.postalCd" size="10" maxlength="10" /></td>
          </tr>
          <tr>
			  <td class="tddark">Country</td>
              <td class="tdlightnormal" ><form:input path="location.country" size="30" maxlength="30" /></td>
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
              <c:if test="${locationCmd.location.locationId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
