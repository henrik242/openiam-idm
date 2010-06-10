<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

   
<form:form commandName="assocPolicyCmd">
	<form:hidden path="assoc.policyObjectId"/>
<table width="75%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Associate Password Policy</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

          <tr>
			  <td class="tddark" width="20%">Association Level</td>
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
			  <td class="tddark" width="20%">Association Value</td>
              <td class="tdlightnormal"><form:input path="assoc.associationValue"  /></td>
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
             

              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
