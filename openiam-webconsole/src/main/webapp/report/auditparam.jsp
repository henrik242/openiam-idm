<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



             
<form:form commandName="auditReportCmd" target="_blank">

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Audit Report Parameters</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Principal Name:</td>
              <td class="tdlightnormal"><form:input path="principal" size="50" maxlength="50" /></td>
          </tr>
          <tr>
			  <td class="tddark">Action:</td>
              <td class="tdlightnormal">
              	<form:select path="action">
   	              <form:option value="" label="-Please Select-"/>
   	              <form:option value="AUTHENTICATION" label="AUTHENTICATION" />
				  <form:option value="SET PASSWORD" label="SET PASSWORD" />
				  <form:option value="NEW HIRE" label="NEW HIRE" />
          		</form:select>
              
              
              </td>
          </tr>
          <tr>
			  <td class="tddark">Start Date:(MM/dd/yyyy)</td>
              <td class="msg"><form:input path="startDate" size="20" maxlength="20" /><br>
              	<form:errors path="startDate" />             
              
              </td>
          </tr>   
          <tr>
			  <td class="tddark">End Date:(MM/dd/yyyy)</td>
              <td class="msg"><form:input path="endDate" size="20" maxlength="20" /><br>
              	<form:errors path="endDate" />             
              
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
              <td colspan="2" align="right">

              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>
</form:form>
