<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
List domainList = (List)session.getAttribute("domainList");
pageContext.setAttribute("domainList", domainList);

String strSyncServices = (String)request.getAttribute("displaySyncServices");
boolean syncServices = Boolean.parseBoolean(strSyncServices);
String strDomainList = (String)request.getAttribute("displayDomainList");
boolean domList = Boolean.parseBoolean(strDomainList);
String secDomain = (String)request.getAttribute("secDomain");

%>



<html:form action="/pub/unLockUser.do?method=showQuestions">
<table border="0" width="70%" align="center">

	<tr>
		<td class="title" align="center" colspan="2">Forgot Password Wizard</td>
	</tr>
  <tr>
    <td class="title">
        Step 1: Enter User Id
    </td>
    <td class="text" align="right">         
        <font size="1" color="red">*</font> Required       
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
		<% if (domList) {%>
		  <tr>
		    <td class="tddarknormal" align="right">
		       Select Domain<font color=red>*</font>
		    </td>
 		    <td class="tdlight">
				<html:select property="domainId" >
		        	<html:options collection="domainList" property="value" labelProperty="label"/>
		     	</html:select> 
		    </td>
		  </tr>
		<% }else { %>
 			<html:hidden property="domainId" value="<%=secDomain %>" />
		<% }%>

  <tr>
    <td class="tddark" align="right">
       Enter Login:<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="login" size="30" maxlength="30"/>
    </td>
  </tr>

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
       	<html:submit property="submit" value="Next"/>  &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>


