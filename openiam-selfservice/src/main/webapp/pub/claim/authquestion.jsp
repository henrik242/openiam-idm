<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("authquestion.jsp");
%>
<br> 
<form:form commandName="claimAccountCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Account Claim Wizard - Set Authentication Questions
        <br><form:errors path="principal" cssClass="error" />
    </td>
  </tr>
  
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		

    <tr>
    	  <td>&nbsp;</td>
    </tr>

  <c:forEach items="${claimAccountCmd.answerList}" var="identityAnswer" varStatus="answer">
	  <tr>
	    <td class="tddark" align="right">
	       Question:<font color="red">*</font>
	    </td>
	    <td class="tddark" >
 			<form:select path="answerList[${answer.index}].identityQuestionId">
				  			<form:option value=""  label="-Select a value-" />
				  			<form:options items="${claimAccountCmd.questionList}" itemValue="identityQuestionId" itemLabel="questionText"  />
			 </form:select>
		 </td>
	  </tr>
	  <tr>
	    <td class="tdlight" align="right"></td>
	    <td class="tdlight" ><form:input path="answerList[${answer.index}].questionAnswer" size="60"/>
	    	<form:hidden path="answerList[${answer.index}].objectState"/>
	    </td>
	  </tr>

</c:forEach>

    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="2" align="right">

    	  <input type="submit" name="_target1" value="Previous"/>
          <input type="submit" name="_target3" value="Next"/>
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


