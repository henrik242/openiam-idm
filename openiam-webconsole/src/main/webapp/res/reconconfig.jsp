<%@ page language="java" contentType="text/html;"   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="reconCmd">

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Reconcilation Configuration For Resource: ${reconCmd.res.name} </strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 

          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal"><form:input path="config.status" size="40" maxlength="40"  />
			  </td>
		  </tr>   	  	
          <tr>
              <td class="tddark">Mode</td>
			  <td class="tdlightnormal">
		   <form:select path="config.mode" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="FULL" label="FULL"/>
              <form:option value="INCREMENTAL" label="INCREMENTAL"/>
          </form:select>  
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">Frequency</td>
			  <td class="tdlightnormal">
		   <form:select path="config.mode" multiple="false">
              <form:option value="-" label="-Please Select-"/>
              <form:option value="5MIN" label="Every 5 min"/>
              <form:option value="15MIN" label="Every 15 min"/>
              <form:option value="60MIN" label="Every 1 Hour"/>
              <form:option value="NIGHTLY" label="Run Nightly"/>  
          </form:select>  
          
			  </td>
		  </tr>  
          <tr>
              <td class="tddark">Attribute Level Check</td>
			  <td class="tdlightnormal"><form:checkbox path="config.attributeLevelCheck" value="1"  /> </td>
		  </tr> 
          <tr>
              <td class="tddark">Updated Changed Attribute</td>
			  <td class="tdlightnormal"><form:checkbox path="config.updateChangedAttribute" value="1"  /> </td>
		  </tr> 
		  
     	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
            <td colspan="2" align="right">
           <input type="submit" name="btn" value="Submit"/>   
    	  <input type="submit" name="btn" value="Reset" />
               </td>
          </tr>
</table>

</form:form>

