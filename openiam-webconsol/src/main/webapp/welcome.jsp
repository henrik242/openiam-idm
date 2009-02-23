<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*,diamelle.security.auth.SSOSubject"  %>
 
<%
System.out.println("in welcome.jsp");

	SSOSubject sub = (SSOSubject)request.getAttribute("subject");
%>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <% if (sub.getDaysToPwdExp() > 0 ) { %>
    <tr>
    	<td> <font color="red">
    	<p>
    	Reminder: Your password this application expires in <%=sub.getDaysToPwdExp()%> days.
    	</font>
    	</td>
    	
    </tr>
    <% } %>

    </table>

