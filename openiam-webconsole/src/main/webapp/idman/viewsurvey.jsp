<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,diamelle.security.idquest.* ,org.apache.struts.util.LabelValueBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<% 
 String idMissing = (String)request.getAttribute("identityMissing");

 if (idMissing != null) { %>
 	<div align="center">
 	<p>
 	<font size="2">Identity questions not found.<br></font>
	<font color="red" size="2"> <b><%= idMissing %></b> </font>
	</p>
	</div>
	
<% } else { 

	List answerList = (List)request.getAttribute("answerList");
	List questionDropdownList = (List)request.getAttribute("questionDropdown");
	String strcount = (String)request.getAttribute("questCount");
	int count = Integer.parseInt(strcount);
	String mode = (String)request.getAttribute("mode");
	String personId = (String)request.getAttribute("personId");
	
	String errMsg = (String)request.getAttribute("errMsg");
	//pageContext.setAttribute("questionDropdownList", questionDropdownList);	

%>


<form action="idquest.do">
<input type="hidden" name="mode" value="<%=mode%>">
<input type="hidden" name="personId" value="<%=personId%>">
<table border="0" width="90%" align="center">


		
	<tr>
	  <td colspan=2>&nbsp;</td>
    </tr>
  <% 
  	if (errMsg != null && errMsg.length() > 0 ) {
  %>
  <br><font color="red" size="2"><%=errMsg %></font>
  <% }
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
    	  <td colspan=2>&nbsp;</td>
    </tr>
 



   <tr>
    <td align="right" colspan=2>
       	<html:submit property="submit" value="Save"/>  &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</form>
<% } %>