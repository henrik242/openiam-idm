<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

    
<form:form commandName="questionCmd">
<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Challenge Response Question</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  		<form:hidden path="idQuest.identityQuestionId"  />
          <tr>
			  <td class="tddark">Question<font color="red">*</font></td>
              <td class="tdlightnormal"><form:input path="idQuest.questionText" size="60" maxlength="150" /><br>
              <form:errors path="idQuest.questionText" cssClass="error" /></td>
          </tr>
          <tr>
              <td class="tddark">Status</td>
			  <td class="tdlightnormal">			  
  	            <form:select path="idQuest.active">
	              <form:option value="1" label="ACTIVE" />
	              <form:option value="0" label="IN-ACTIVE" />
          		</form:select>
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
              <c:if test="${questionCmd.idQuest.identityQuestionId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
