<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<% 
System.out.println("unlockVerifyIdentity.jsp");
%>
<br> 
<form:form commandName="unlockUserCmd">

<table border="0" width="500" align="center">

  <tr>
    <td class="title" colspan="2">
        Unlock Account - Verify User Identity
    </td>
  </tr>
  
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
	<tr>
	  <td colspan="2">&nbsp;
	  	<form:errors path="domainId" cssClass="error" />
	  </td>
 	</tr>

  <tr>
 		<td colspan="2" align="center" >
 		  <b>Please answer ${unlockUserCmd.requiredCorrect} of ${unlockUserCmd.questionCount} questions correctly.</b>
 		</td>
  </tr>
<c:forEach items="${unlockUserCmd.answerList}" var="identityAnswer" varStatus="answer">
	  <tr>
	    <td class="tddark" align="right">
	       Question:<font color="red">*</font>
	    </td>
	    <td class="tddark" >
	        ${identityAnswer.questionText}
	        <form:hidden path="answerList[${answer.index}].identityQuestionId"/>
	        
		 </td>
	  </tr>
	  <tr>
	    <td class="tdlight" align="right"></td>
	    <td class="tdlight" ><form:input path="answerList[${answer.index}].questionAnswer" size="60"/> </td>
	  </tr>

</c:forEach>    
    
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td colspan="2" align="right"> 
    	  <input type="submit" name="_target0" value="Previous"/>   
    	  <input type="submit" name="_target2" value="Next"/>   
    	  <input type="submit" name="_cancel" value="Cancel" />  
    </td>
  </tr>

</table>
</form:form>


