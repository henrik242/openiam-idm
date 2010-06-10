<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="batchTaskCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Batch Task Configuration Details</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  <form:hidden path="batch.taskId"  />
          <tr>
			  <td class="tddark">Batch Task Name<font color="red">*</font></td>
              <td class="tdlightnormal"><form:input path="batch.taskName" size="40" maxlength="40" />
              </td>
          </tr>
          <tr>
              <td class="tddark">Frequency<font color="red">*</font></td>
			  <td class="tdlightnormal">			  
  	            <form:select path="batch.frequencyUnitOfMeasure">
	              <form:option value="MINUTE" label="MINUTE" />
	              <form:option value="5MIN" label="5 MINUTES" />
               	  <form:option value="15MIN" label="15 MINUTES" />
	              <form:option value="60MIN" label="60 MINUTES" />
	              <form:option value="NIGHTLY" label="NIGHTLY" />
          		</form:select>
			  </td>
		  </tr>
          <tr>
			  <td class="tddark">Enabled<font color="red">*</font></td>
              <td class="tdlightnormal">
              	 <form:select path="batch.enabled">
	              <form:option value="1" label="Yes" />
	              <form:option value="0" label="No" />
          		</form:select>
              </td>
          </tr>

          <tr>
			  <td class="tddark">Script URL</td>
              <td class="tdlightnormal"><form:input path="batch.taskUrl" size="60" maxlength="60" />
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
              <c:if test="${batchTaskCmd.batch.taskId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
