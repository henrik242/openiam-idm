<%@ page import="javax.naming.*,diamelle.util.Log,java.util.*" %>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

 
<%
 	List serviceList = (List)session.getAttribute("serviceList");
 System.out.println("serviceList = " + serviceList);
	pageContext.setAttribute("serviceList",serviceList);
%>


<html:form action="/changePassword?method=save">
<table border="0" width="80%" align="center">

  <tr>
    <td class="title">Change Password</td>
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
    <td class="tddark">
       Select Service<font color=red>*</font>
    </td>
    <td class="tdlight">
    	      <html:select property="service">
        		<html:options collection="serviceList" property="value" labelProperty="label"/>
        	  </html:select>
        	 
    </td>
  </tr>

  <tr>
    <td class="tddark">
       Login<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:text property="login" size="30" maxlength="30"/>
    </td>
  </tr>

  <tr>
    <td class="tddark">
       Current Password<font color=red>*</font>
    </td>
    <td class="tdlight">
      <html:password property="oldPassword" size="20" maxlength="20"/>
    </td>
  </tr>


  <tr>
    <td class="tddark">    
       New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="newPassword" size="20" maxlength="20"/>
    </td>
  </tr>
  <tr>
    <td class="tddark">    
       Confirm New Password<font color=red>*</font>
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
    <td colspan="2" align="right">
          <html:submit property="submit" value="Submit"/>
          &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>


