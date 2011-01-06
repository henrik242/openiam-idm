<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="orgPolicyCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Organization Policy Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>  

           <tr>
			  <td class="tddark">Name<font color="red">*</font></td>
              <td class="tdlightnormal">
               <form:hidden path="orgPolicy.orgPolicyId"  />
               <form:input path="orgPolicy.name" size="40" maxlength="40" /></td>
          </tr>
            <tr>
              <td class="tddark">Audience Type</td>
			  <td class="tdlightnormal">
			  	 <form:select path="orgPolicy.targetAudienceType">
				  <form:option value="" label="-Please Select-"  />
	              <form:option value="ALL" label="ALL" />
				  <form:option value="GROUP" label="GROUP" />
				  <form:option value="DEPT" label="DEPARTMENT" />
				   <form:option value="DIV" label="DIVISION" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
              <td class="tddark">Audience</td>
			  <td class="tdlightnormal"><form:input path="orgPolicy.targetAudience" size="40" maxlength="100"  /></td>
		  </tr>
           <tr>
			  <td class="tddark">Start Date</td>
              <td class="tdlightnormal" ><form:input path="orgPolicy.startDate" size="20" /></td>
          </tr>
          <tr>
			  <td class="tddark">End Date</td>
              <td class="tdlightnormal" ><form:input path="orgPolicy.endDate" size="20" /></td>
          </tr>
          <tr>
			  <td class="tddark">Policy Text</td>
              <td class="tdlightnormal" >
              	<form:textarea path="orgPolicy.policyText" rows="10" cols="60" /></td>
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
              <c:if test="${orgPolicyCmd.orgPolicy.orgPolicyId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
