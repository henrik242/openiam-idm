<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="java.util.*"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.User"  %>
<%@ page import="org.openiam.idm.srvc.user.dto.UserAttribute"  %>
<% 

	String firstName = (String)session.getAttribute("firstname");
	String lastName = (String)session.getAttribute("lastname");
	Map<String,UserAttribute> usrAttrMap = (Map)session.getAttribute("userattr");
	
	String challengeSet = (String)request.getAttribute("challengeSet");
	

%>


    <table width="60%" border="0" cellspacing="0" cellpadding="0">
      <tr align="center" valign="top">
        <td>Welcome <%=firstName %> <%=lastName %></td>
      </tr>
      <tr align="center" valign="top">
        <td></td>
      </tr>
     <% if (challengeSet != null && challengeSet.equalsIgnoreCase("false")) { %>
      <tr  valign="top" align="center">
        <td colspan="2"><font color="red"><b>Warning: Before you continue, please complete the "Challenge Response" from the Self-Service section
        on the right. This will enable your password self-service options.</b></font></td>
         <td> </td>
      </tr>      
      <% } %>
      

    </table>

