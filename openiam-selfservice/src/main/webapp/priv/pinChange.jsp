<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*,org.apache.struts.util.LabelValueBean" %>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
 	List serviceList = (List)session.getAttribute("serviceList");
	pageContext.setAttribute("serviceList",serviceList);

System.out.println("ServiceList => " + serviceList);
	
	String retUrl = (String) session.getAttribute("retTargetUrl");
	String lg = (String) session.getAttribute("login");
	String userId = (String) session.getAttribute("userId");
	String token = (String) session.getAttribute("token");
	
	String pwdExpFlag = (String)request.getAttribute("pwdexp");

	
%>


<html:form action="/priv/pin.do?method=save">
<%
	if (pwdExpFlag != null && pwdExpFlag.equals("1")) {
%>
		<input type="hidden" name="pwdexp" value="1">
<% 
	}
%>
<table border="0" width="600pt" align="center">

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
       <html:password property="newPassword" size="20" maxlength="20"/>
    </td>
  </tr>
  <tr>
    <td class="tddarknormal" align="right">    
       Confirm New PIN<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="confNewPassword" size="20" maxlength="20"/>
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
  	<td>

  	</td>
    <td align="right">
          <html:submit property="submit" value="Submit"/>
          &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>



