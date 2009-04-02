<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,org.apache.struts.util.LabelValueBean" %>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%

	List domainList = (List)session.getAttribute("domainList");
	pageContext.setAttribute("domainList", domainList);


 	List serviceList = (List)session.getAttribute("serviceList");
	pageContext.setAttribute("serviceList",serviceList);

	
	String retUrl = (String) session.getAttribute("retTargetUrl");
	String lg = (String) session.getAttribute("login");
	String userId = (String) session.getAttribute("userId");
	String token = (String) session.getAttribute("token");
	
	String pwdExpFlag = (String)request.getAttribute("pwdexp");
	String domainId = (String)session.getAttribute("domainId");
	
	String strSyncServices = (String)request.getAttribute("displaySyncServices");
	boolean syncServices = Boolean.parseBoolean(strSyncServices);
	String strDomainList = (String)request.getAttribute("displayDomainList");
	boolean domList = Boolean.parseBoolean(strDomainList);
	String secDomain = (String)request.getAttribute("secDomain");

	
%>


<html:form action="/priv/password.do?method=save">
<%
	if (pwdExpFlag != null && pwdExpFlag.equals("1")) {
%>
		<input type="hidden" name="pwdexp" value="1">
<% 
	}
%>
<table border="0" width="600pt" align="center">
	<tr>
	  <td>
 
<font color="red">
<logic:messagesPresent property="error">
   Validation Errors:
   <html:messages id="error" header="errors.header">
     <li><bean:write name="error"/></li>
   </html:messages>
</logic:messagesPresent>
</font>

	  </td>
	</tr>
  <tr>
    <td class="title">Change PIN</td>
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

  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->
    <html:hidden property="serviceId" value="DIAMELLE" />
  <%// if (syncServices) { %>

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
        	 
    </td>
  </tr>
 <% //} %>

  <tr>
    <td class="tddarknormal" align="right">
       Login<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="login" size="30" maxlength="30" value="<%= lg%>" readonly="true"/>
    </td>
  </tr>


  <tr>
    <td class="tddarknormal" align="right">    
       New PIN<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="newPassword" size="30" maxlength="30"/>
    </td>
  </tr>
  <tr>
    <td class="tddarknormal" align="right">    
       Confirm New PIN<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="confNewPassword" size="30" maxlength="30"/>
    </td>
  </tr>  
  <% if (syncServices) { %>
 
  	  <tr>
			<td class="tddark" align="center" colspan="2">Select Services to Synchronize Password</td>
	  		
	   </tr>
  
  		<% 
  			int size = serviceList.size();
  			for (int i=1; i< size; i++) {
  				LabelValueBean bean = (LabelValueBean)serviceList.get(i);
  				String value = bean.getValue();
  				String label = bean.getLabel();
  		 %>
  		 <tr>
  		 	<td class="tddarknormal"></td>
  		 	<td class="tdlightnormal"> 
  		 		<input type="checkbox" name="syncService" value="<%=value%>" > <%=label%>
  		 	</td>
  		 </tr>
  		 <% 
  		 	}
  		 %>
 
 <% } %>
  
  <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

  <tr>
  	<td>

  	</td>
    <td align="right">
          <html:submit property="submit" value="Submit"/>
          &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>



