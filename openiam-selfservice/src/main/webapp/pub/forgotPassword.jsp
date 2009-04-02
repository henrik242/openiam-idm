 
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>
 
 <%
 System.out.println(" in forgot password.jsp"); 
 	List serviceList = (List)session.getAttribute("service");
	pageContext.setAttribute("serviceList",serviceList);
 %>

<html:form action="/forgotPassword?method=getPassword">

<table width="50%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td class="title">         
          <strong>Forgot PIN</strong>         
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
	  <td colspan="2">&nbsp;</td>
 	</tr>
 	<tr>
		<td class="tddark" align="right">Service:<font color="red">*</font></td>
		<td class="tdlight" align="left" >
			<html:select property="service" >
				<html:options collection="serviceList" property="value" labelProperty="label"/>
			</html:select>	
        </TD>
	</tr>
 	<tr>
    <td class="tddark" align="right">
       Login Id:<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="login" size="30" maxlength="30"/>
    </td>
	</tr>
    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

 	<tr>
		<td align="right" colspan="2">
			<html:submit/> <html:reset/>
		</TD>
	</tr> 	
</table>
</html:form>
