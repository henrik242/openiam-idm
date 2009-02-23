
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
 
	List userTypeList = (List)session.getAttribute("userTypes");
	if (userTypeList == null)
		userTypeList = new ArrayList();
	pageContext.setAttribute("userTypes", userTypeList);	

%>


<html:html>




<html:form action = "/idman/user.do?method=showNewUserForm&phase=userType">



<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="3" class="title">         
          <strong>User Information</strong>
          <br>
         <html:hidden property="personId" />
         <html:hidden property="mode" />
      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 </tr>
  <tr>
       <td class="tddark" align="right">Select User Object Class:<font color=red>*</font></td>
       <td class="tdlight" colspan="3">
             <html:select property="typeId" >
        		<html:options collection="userTypes" property="value" labelProperty="label"/>
        	 </html:select>
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
  
    <tr >
       <td colspan="4" align ="right">
       <html:submit property="submit" value="Next" />  <html:reset/>
       </td>
    </tr>
    
 </table>
</html:form>
</html:html>


