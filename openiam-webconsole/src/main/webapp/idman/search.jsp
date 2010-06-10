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
	
	List secondaryStatusList = (List)session.getAttribute("secondaryStatusList");
	pageContext.setAttribute("secondaryStatusList",secondaryStatusList);
	
	//List groupList = (List)session.getAttribute("groupList");
	List groupList = (List)request.getAttribute("groupList");
	pageContext.setAttribute("groupList",groupList);
	List roleList = (List)request.getAttribute("roleList");
	pageContext.setAttribute("roleList",roleList);
	String msg = (String)request.getAttribute("msg");
	
%>
<html:html>

<html:form action = "/idman/userSearch.do?action=search">
  <table width="600pt" border="0" cellspacing="2" cellpadding="1" align="center">
<% if (msg != null) { %> 
   <tr>
 		<td class="msg" colspan="3" align="center"  >
 		  <b><%=msg %></b>
 		</td>
  </tr> 
<% } %>

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
             <input name="lastName" type="text" size="30" maxlength="30" />
         </td>         
         <td class="tddark" align="right">Organization</td>
         <td class="tdlight">
            <html:text property="companyName" size="30" maxlength="30" />
         </td>
   </tr>
  <tr>
         <td class="tddark" align="right">E-Mail</td>
         <td class="tdlight">
             <input name="email" type="text" id="email" size="20" maxlength="50" />
        </td>
           
        </td>
       
          <td class="tddark" align="right"></td>
         <td class="tdlight"></td>
   </tr>
   <tr>
        <td class="tddark" align="right">Phone<br>(AreaCode- Phone):</td>
        <td class="tdlight">
          <html:text property="areaCode" size="3" maxlength="3" />
          <html:text property="phoneNumber" size="10" maxlength="10" />
        </td>

         <td class="tddark" align="right">Group</td>
          <td class="tdlight">
              <html:select property="group">
        		<html:options collection="groupList" property="value" labelProperty="label"/>
        	  </html:select>
           </td>      

   </tr>
   <tr>
         <td class="tddark" align="right" >User Status</td>
         <td class="tdlight">
              <html:select property="status">
        		<html:options collection="statusList" property="value" labelProperty="label"/>
        	  </html:select>
         </td>
         
          <td class="tddark" align="right">Role</td>
          <td class="tdlight">
          	  <html:select property="role">
        		<html:options collection="roleList" property="value" labelProperty="label"/>
        	  </html:select>
          </td>
             
   </tr>
   <tr>
         <td class="tddark" align="right" >Secondary Status</td>
         <td class="tdlight">
              <html:select property="secondaryStatus">
        		<html:options collection="secondaryStatusList" property="value" labelProperty="label"/>
        	  </html:select>
         </td>
         
          <td class="tddark" align="right"></td>
          <td class="tdlight">

          </td>
             
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