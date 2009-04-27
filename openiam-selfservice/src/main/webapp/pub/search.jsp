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
	
	List orgList = (List)session.getAttribute("orgList");
	if (orgList == null)
		orgList = new ArrayList();
	pageContext.setAttribute("orgList", orgList);

	List roleList = (List)session.getAttribute("roleList");
	if (roleList == null)
		roleList = new ArrayList();
	pageContext.setAttribute("roleList", roleList);
	
%>

<br>

<html:form action = "/pub/directory.do?method=search">
  <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
        <td class="title" colspan="6">Directory Lookup</td>
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
         <td class="tdlight" colspan="3">
             <input name="lastName" type="text" size="30" maxlength="40" />
         </td>         

   </tr>
   <tr>
         <td class="tddark" align="right">First Name:</td>
         <td class="tdlight" colspan="3">
             <input name="firstName" type="text" id="firstName" size="30" maxlength="40" />
         </td>         

   </tr>
   <tr>
         <td class="tddark" align="right">Phone Number (Desk Phone):</td>
         <td class="tdlight" colspan="3">
         	 <input name="phone_areaCd" type="text" size="8" />
             <input name="phone_nbr" type="text" size="18" maxlength="30" />
         </td>         
   </tr>
      
   <tr>
         <td class="tddark" align="right">Department / Agency</td>
         <td class="tdlight" colspan="3">
	         <html:select property="dept" >
	        	<html:options collection="orgList" property="value" labelProperty="label"/>
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
    <tr>
          <td colspan="6" align ="right"  >
              <input type="submit" name="Submit" value="Lookup">&nbsp;
              <input type="reset" name="Reset" value="Cancel">
          </td>
   </tr>
 
  
    </table>
</html:form>


<jsp:include page="searchresult.jsp" flush="true" />


<table>
	<tr>
		<td><font face="Arial" color="#ff9f00" size="4">USER Directory Information</font></td>
	</tr>
	<tr>
		<td>
			This on-line Directory represents an approach to provide continuous, accurate, self-service information to those who need it for their work. Thus avoiding cost printing an annual phonebook or having and large team of Directory coordinators to monthly track down changes. Please click on the suggestion button below, if you see additional features or other cost saving opportunities. 	</tr>
	<tr>
		<td><font face="Arial" color="#ff9f00" size="4">Online Changes</font></td>
	</tr>
	<tr>
		<td>Users are empowered to update their own personal profile information by using Single Sign-On (SSO) application to reflect the current contact information.</td>
	</tr>
	<tr>
		<td><font face="Arial" color="#ff9f00" size="4">User / Telephone Directory Lookup</font></td>
	</tr>
	
	<tr>
		<td>You may SEARCH the directory for staff by:</td>
	</tr>
	<tr>
		<td><li>Last Name - Complete last name, a few letters of last name, a phonetic search or you can refine your search by selecting an agency with any search</td>
	</tr>
	<tr>
		<td><li>First Name - By complete first name, a few letters of first name, a phonetic search or you can refine your search by selecting an agency with any search  </td>
	</tr>
	<tr>
		<td><li>Agency Name – Select an agency to refine search or to see an A-Z list of employees for a particular agency  </td>
	</tr>
		
</table>