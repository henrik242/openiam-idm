 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>

<%
	List operationList = (List)session.getAttribute("operationList");
	pageContext.setAttribute("operationList",operationList);
	String savedStatus = (String)request.getAttribute("saved");
%>

<html:form action = "/assignTokenFail.do?method=rejectUser" >
<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="3" class="title">         
          <strong>Enter Reason for Rejecting User(s) From Token Assignment</strong>      
      </td>
      <td colspan="1" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>
 	<tr>
		<td class="tddark" align="right" colspan="2">Note Type:</td>
		<td class="tdlight" align="left" colspan="2">
		<html:hidden property="noteType" value="RJ" />
		<input type="text" value="REJECTED" readonly="true">
		</TD>
	</tr>
 	<tr>
		<td class="tddark" align="right" colspan="2">Comment:</td>
		<td class="tdlight" align="left" colspan="2">
			<html:textarea property="description" cols="60" rows="4"/>
		</TD>
	</tr>
   <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="4" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

 	<tr>
		<td class="tdlight" align="right" colspan="4">
			<html:submit/> <html:reset/>
		</TD>
	</tr> 	
 </table>
 
</html:form>