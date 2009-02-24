<%@ page import="javax.naming.*,java.util.*,diamelle.util.Log" %>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
List domainList = (List)session.getAttribute("domainList");
pageContext.setAttribute("domainList", domainList);
%>

<html:form action="/login">
<table border="0" width="600pt" align="center">

  <tr>
    <td class="title">
        Login
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
	 <td>&nbsp;</td>
   </tr>

  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->
     <html:hidden property="serviceId" value="USR_SEC_DOMAIN" />
  <tr>
    <td class="tddark" align="right">
       Select Domain:<font color=red>*</font>
    </td>
    <td class="tdlight">
		<html:select property="domainId" >
        	<html:options collection="domainList" property="value" labelProperty="label"/>
     	</html:select> 
    </td>
  </tr>
  
  <tr>
    <td class="tddark" align="right">
       Enter Login Id:<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="login" size="40" maxlength="40"/>
    </td>
  </tr>
  <tr>
    <td class="tddark" align="right">    
       Enter Password:<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="password" size="40" maxlength="40" redisplay="false"/>
    </td>
  </tr>

  
  
    <tr>
	    <td></td>
	    <td></td>
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
       	<html:submit property="submit" value="login"/>
          &nbsp;<html:reset/>
    </td>

  </tr>

</table>

</html:form>


