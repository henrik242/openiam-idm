<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,diamelle.security.idquest.*, org.openiam.idm.srvc.service.dto.*" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 

List serviceList = (List)session.getAttribute("userServices");
String message = (String)request.getAttribute("message");

String strSyncServices = (String)request.getAttribute("displaySyncServices");
boolean syncServices = Boolean.parseBoolean(strSyncServices);
String strDomainList = (String)request.getAttribute("displayDomainList");
boolean domList = Boolean.parseBoolean(strDomainList);
String secDomain = (String)request.getAttribute("secDomain");

%>

<html:form action="/unLockUser.do?method=syncPassword">
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
        Un-Lock Account
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
	  <tr>
			<td class="tddarknormal" align="right">New Password:</td>
	  		<td class="tdlightnormal"><html:password property="password" size="30"/>	</td>
	   </tr>

	  <tr>
			<td class="tddarknormal" align="right">Confirm New Password:</td>
	  		<td class="tdlightnormal"><html:password property="confpassword" size="30" />	</td>
	   </tr>
 
<%   if (syncServices) { %>

 	  <tr>
			<td class="tddark" align="center" colspan="2">Select Services to Synchronize Password</td>
	 </tr>
  
  		<% 
  			int size = serviceList.size();
  			for (int i=0; i< size; i++) {
  			//Set keyset = serviceMap.keySet();
  			//Iterator it = keyset.iterator();
  			//while (it.hasNext()) {
  				Service srv = (Service)serviceList.get(i);
  				String key = srv.getServiceId();
  				String value = srv.getServiceName();
  		 %>
  		 <tr>
  		 	<td class="tddarknormal"></td>
  		 	<td class="tdlightnormal"> <input type="checkbox" name="service" value="<%=key%>"> 
  		 	<%=value%>
  		 	</td>
  		 </tr>
  		 <% 
  		 	}
  		 %>

	<% } %>
  <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td align="center" bgcolor="8397B4" colspan="2">
 		    <font></font>
 		   </td>
    </tr>

  <tr>


    <td align="right" colspan="2">
       	<html:submit property="submit" value="Save"/>  &nbsp;<html:reset/>
    </td>

  </tr>
</table>

</html:form>


