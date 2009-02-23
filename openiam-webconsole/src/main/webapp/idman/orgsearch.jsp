<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.common.org.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<html:html>

<%
	List orgTypeList = (List)session.getAttribute("orgTypeList");
	pageContext.setAttribute("orgTypeList",orgTypeList);

%>


<html:form action = "/idman/orgSearch.do?action=search">
  <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
        <td class="title" colspan="6">Organization Search</td>
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
         <td class="tddark" >Organization Name:</td>
         <td class="tdlight">
         	<html:text property="organizationName" size="30" maxlength="30" value="" />
         </td>    
     </tr>   
    <tr>
         <td class="tddark" >Organization Type:</td>
         <td class="tdlight">
              <html:select property="orgType">
        		<html:options collection="orgTypeList" property="value" labelProperty="label"/>
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
              <input type="submit" name="Submit" value="Submit">&nbsp;
        
          </td>
   </tr>
 
  
    </table>
   <br>
   <br>
  <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
    <%
       List companies = (List) request.getAttribute("companies");
       if (companies != null && companies.size() > 0 ){ 
       
    %>   
    
    <tr>
     <td colspan="2">
        <b>Search Results - <%=companies.size()%> Record(s) Found.</b>
     </td>
   </tr>
  <tr>
    <td class="tdheader">&nbsp;</td>
    <td class="tdheader">Company Name</td>
    
  </tr>   
    <%
          Iterator itr = companies.iterator();
          int x=0;
          while (itr.hasNext()) {
             OrganizationValue companyData = (OrganizationValue) itr.next();
          
          %>
          
          <%
           if ((x%2) != 0) {
       
				   %>
				   <tr class="tddark">
				   <% } else {%>
				   <tr class="tdlight">
				   <%}%>
				      <td>
<A HREF='#' onClick="self.opener.document.userForm.companyId.value='<%=companyData.getOrganizationId()%>';
                     self.opener.document.userForm.companyName.value='<%=companyData.getOrganizationName()%>';
                     window.close();">
				     
				      </td>
				      
				      <td align="left"><%=companyData.getOrganizationName()%></td>
				      
				 </tr>
          <%} 
       }
    %>
  </table>
</html:form>

</html:html>