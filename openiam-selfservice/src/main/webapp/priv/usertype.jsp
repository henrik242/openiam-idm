<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="newHireCmd">
		<table width="600pt"  class="bodyTable" height="100%" >
				<tr>
					<td>
							<fieldset class="userformSearch" >
							<legend>SELECT TYPE OF USER</legend>

					<table class="fieldsetTable"  width="100%" >




	<tr>
 		<td ></td>
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
