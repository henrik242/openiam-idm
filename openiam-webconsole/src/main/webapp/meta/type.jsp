<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        
<form:form commandName="metadataTypeCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Metadata Type Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
          <tr>
			  <td class="tddark">Type Id</td>
              <td class="tdlightnormal"><form:input path="metadataTypeId" size="32" maxlength="32" readonly="true" /></td>
          </tr>
          <tr>
              <td class="tddark">Type Name<font color="red">*</font></td>
			  <td class="tdlightnormal"><form:input path="description" size="40" maxlength="40"  /></td>
		  </tr>
          <tr>
			  <td class="tddark">Active</td>
              <td class="tdlightnormal" ><form:checkbox path="active" value="1" /></td>
          </tr>
          <tr>
              <td class="tddark">Synchronize System</td>
			  <td class="tdlightnormal"><form:checkbox path="syncManagedSys" value="1" />
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
          <tr>
              <td colspan="2" align="right"><input type="submit" name="btn" value="Delete">  <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
</div>