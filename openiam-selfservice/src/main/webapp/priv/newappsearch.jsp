<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
	List statusList = (List)session.getAttribute("statusList");
	pageContext.setAttribute("statusList",statusList);
	List groupList = (List)session.getAttribute("groupList");
	pageContext.setAttribute("groupList",groupList);
%>
<html:html>

<html:form action = "/pub/directory.do?method=search">
  <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
        <td class="title" colspan="6">Find User</td>
   </tr>
   <tr>
 		<td colspan="6" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
	<tr>
	  <td>&nbsp;</td>
  </tr>
  
   <tr>
         <td class="tddark" align="right">Last Name:</td>
         <td class="tdlight">
             <input name="lastName" type="text" size="30" maxlength="40" />
         </td>         
         <td class="tddark" align="right">First Name:</td>
         <td class="tdlight">
             <input name="firstName" type="text" id="firstName" size="30" maxlength="40" />
        </td>
   </tr>

  <tr>
	  <td>&nbsp;</td>
 </tr>
 
  <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
    <tr>
          <td colspan="6" align ="right"  >
              <input type="submit" name="Submit" value="Submit">&nbsp;
              <input type="reset" name="Reset" value="Reset">
          </td>
   </tr>
 
  
    </table>
</html:form>

</html:html>