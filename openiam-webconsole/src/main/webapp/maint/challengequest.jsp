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
								<h2 class="contentheading">Challenge Response Question</h2>
						</td>
						</tr>
					</table>
			</td>
				<tr>
				<td>
    
<form:form commandName="questionCmd">
<table width="600pt">
				<tr>
				<td align="center" height="100%">
			     <fieldset class="userform" >
						<legend>CHALLENGE QUESTION</legend>
	<table class="fieldsetTable"  width="600pt" >  
	  
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
  		<form:hidden path="idQuest.identityQuestionId"  />
          <tr>
			 				 <td>Question<font color="red">*</font></td>
        			<td><form:input path="idQuest.questionText" size="60" maxlength="150" /><br>
              <form:errors path="idQuest.questionText" cssClass="error" /></td>
          </tr>
          <tr>
              <td>Status</td>
			  			<td>			  
  	            <form:select path="idQuest.active">
	              <form:option value="1" label="ACTIVE" />
	              <form:option value="0" label="IN-ACTIVE" />
          		</form:select>
			  </td>
		  </tr>
        </TABLE>    
      <tr>
          <tr>
              <td colspan="2" align="right">
              <c:if test="${questionCmd.idQuest.identityQuestionId != null}" >
              <input type="submit" name="btn" value="Delete">  
              </c:if>
              <input type="submit" name="btn" value="Submit"> </td>
          </tr>
</table>

</form:form>
</td>
</tr>
</table>
