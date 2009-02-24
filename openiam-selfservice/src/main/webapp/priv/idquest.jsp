<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,diamelle.security.idquest.* ,org.apache.struts.util.LabelValueBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	List answerList = (List)request.getAttribute("answerList");
	List questionDropdownList = (List)request.getAttribute("questionDropdown");
	String strcount = (String)request.getAttribute("questCount");
	String errmsg = (String)request.getAttribute("errmsg");
	if (strcount == null || strcount.length() ==0)
		strcount  = "3";
	int count = Integer.parseInt(strcount);
	String mode = (String)request.getAttribute("mode");

	//pageContext.setAttribute("questionDropdownList", questionDropdownList);	

%>

<html:form action="/priv/idquest.do?method=save">
<input type="hidden" name="mode" value="<%=mode%>">
<table border="0" width="600pt" align="center">

	<tr>
		<td class="title" align="left" colspan="2">User Identity Questions</td>
	</tr>
  <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
	<tr>
	  <td colspan="2">&nbsp;</td>
    </tr>
    
 <%
	if (errmsg != null) {
 %>
	<tr>
	  <td colspan="2"><font color="red"><%=errmsg%></font></td>
    </tr> 
 <%
 	}
 %>
    
  <% 
	if (mode.equalsIgnoreCase("edit")) { 
		for (int i=0; i < answerList.size(); i++) { 
			QuestionValue questVal = (QuestionValue)answerList.get(i);
  %>
		  <tr>
				<td class="tddarknormal" align="right">Select Question <%= i+1 %></td>
		  		<td class="tdlight">	
					<select name="question_<%=questVal.getAnswerId()%>">
					<% 
						for (int x=0; x < questionDropdownList.size() ; x++  )  {
							LabelValueBean bean = (LabelValueBean)questionDropdownList.get(x); 
							if (bean.getValue().equalsIgnoreCase( questVal.getQuestionId() )) {
					%>
						<option selected="selected" value="<%=bean.getValue()%>"><%=bean.getLabel()%></option>

					<% 		}else {	%>
						<option value="<%=bean.getValue()%>"><%=bean.getLabel()%></option>
					<% 		} 
						} %>

					</select>
				</td>
		   </tr>
		  <tr>
				<td class="tddarknormal" align="right">Answer:</td>
				<td class="tdlight"><input type="text" name="answer_<%=questVal.getAnswerId()%>" value="<%=questVal.getAnsText() %>" size="60"></td>
		  </tr> 

  <% 	}
	} else { 
		for (int i=0; i < count; i++) { %>
		  <tr>
				<td class="tddarknormal" align="right">Select Question <%= i+1 %></td>
				<td class="tdlight">
					<select name="question_<%=i%>">
					<% 
						for (int x=0; x < questionDropdownList.size() ; x++  )  {
							LabelValueBean bean = (LabelValueBean)questionDropdownList.get(x); 
					%>
						<option value="<%=bean.getValue()%>"><%=bean.getLabel()%></option>
					<% } %>
					</select>
		 		</td>
		  </tr> 
		  <tr>
				<td class="tddarknormal" align="right">Answer:</td>
				<td class="tdlight"><input type="text" name="answer_<%=i%>" size="60"></td>
		  </tr> 
	<%  } 
	}
	%>

  <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
    <td align="left">
    </td>

    <td align="right">
       	<html:submit property="submit" value="Save"/>  &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>
