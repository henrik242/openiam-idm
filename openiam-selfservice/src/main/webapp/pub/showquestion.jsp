<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,diamelle.security.idquest.*" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
 List questionList = (List)request.getAttribute("questionList");

 String strcount = (String)request.getAttribute("questCount");
 int count = Integer.parseInt(strcount);
 String message = (String)request.getAttribute("message");

%>

<html:form action="/unLockUser.do?method=processAnswers">
<table border="0" width="80%" align="center">

	<tr>
		<td colspan="2">
			<% if (message !=null && message.length() > 0) { %>
				<font color="red"><%=message %></font>
			<% } %>
		</td>
	</tr>
  <tr>
    <td class="title" colspan="2">
        Un-Lock Card
    </td>
  </tr> 
  <tr>
 		<td align="center" bgcolor="8397B4" colspan="2">
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td colspan="2">&nbsp;</td>
 	</tr>

	<% if (questionList == null || questionList.isEmpty()) { %>
	  <tr>
		<td>
			<font color="red">Password unlock cannot be used. Please complete identity questions to use this utility.<br>
			For immediate assistance, please call the help desk.</font>
		</td>
	  </tr>
	<% }else { 
		for (int i=0; i < questionList.size(); i++) { 
			QuestionValue questVal = (QuestionValue)questionList.get(i);
    %>
		  <tr>
				<td class="tddarknormal" align="right">Question</td>
		  		<td class="tdlightnormal"><%= questVal.getQuestionText() %>
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Answer:</td>
				<td class="tdlightnormal"><input type="text" name="answer_<%=questVal.getQuestionId()%>" size="60"></td>
		  </tr> 

  <% 	}

	}
	%>
 
 	
  <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td align="center" bgcolor="8397B4" colspan="2">
 		    <font></font>
 		   </td>
    </tr>

  <tr>

  <% if (questionList != null && !questionList.isEmpty()) { %>

    <td align="right" colspan="2">
       	<html:submit property="submit" value="Next"/>  &nbsp;<html:reset/>
    </td>
  <% }  %>
  </tr>
</table>

</html:form>


