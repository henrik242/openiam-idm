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
								<h2 class="contentheading">Scheduled Tasks</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>	    
<form:form commandName="batchTaskCmd">
	<table width="600pt">
			<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CONFIGURE SCHEDULED TASK</legend>
	<table class="fieldsetTable"  width="600pt" >  

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
	              <form:option value="MINUTE" 	label="1 MINUTE" />
	              <form:option value="5MIN" 	label="5 MINUTES" />
               	  <form:option value="15MIN" 	label="15 MINUTES" />
	              <form:option value="60MIN" 	label="60 MINUTES" />
	              <form:option value="NIGHTLY" 	label="NIGHTLY" />
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
			 			 <td>Script URL</td>
              <td><form:input path="batch.taskUrl" size="60" maxlength="60" /></td>
          </tr>
          <tr>
			 			 <td>Execution Order</td>
              <td><form:input path="batch.executionOrder" size="10" maxlength="10" />
          	  </td>
          </tr> 
          
          <tr>
			  <td>Status</td>
              <td class="">
              	 <form:select path="batch.status">
	              <form:option value="" label="IDLE" />
	              <form:option value="1" label="IN-PROGRESS" />
	              <form:option value="2" label="COMPLETE" />
          		</form:select>
          	  </td>
          </tr>                    
          <tr>
			  <td>PARAM 1</td>
              <td><form:input path="batch.param1" size="60" maxlength="60" />
              </td>
          </tr>        
          <tr>
			  <td>PARAM 2</td>
              <td><form:input path="batch.param2" size="60" maxlength="60" />
              </td>
          </tr>            
          <tr>
			  <td>PARAM 3</td>
              <td><form:input path="batch.param3" size="60" maxlength="60" />
              </td>
          </tr>  
          <tr>
			  <td>PARAM 4</td>
              <td><form:input path="batch.param4" size="60" maxlength="60" />
              </td>
          </tr>  

    	<tr>
    </TABLE>
          <tr class="buttonRow">
              <td colspan="2" align="right">
              <c:if test="${batchTaskCmd.batch.taskId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
</td>
</tr>
</table>