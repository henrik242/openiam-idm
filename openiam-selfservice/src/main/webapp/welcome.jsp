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
	

%>


    <table width="60%" border="0" cellspacing="0" cellpadding="0">
      <tr align="center" valign="top">
        <td>Welcome <%=firstName %> <%=lastName %></td>
      </tr>
      <tr align="center" valign="top">
        <td></td>
      </tr>
	  <% 
	  	if (usrAttrMap != null && !usrAttrMap.isEmpty()) {
	  		Set<String> keySet = usrAttrMap.keySet();
	  		Iterator it = keySet.iterator();
	  		while (it.hasNext()) {
	  			String key = (String)it.next();
	  			UserAttribute userAtr = usrAttrMap.get(key);
	  %>
      <tr align="center" valign="top">
        <td><%= userAtr.getValue() %></td>
      </tr>
	  <% 
	  		}
	  	}
	  %>
    </table>

