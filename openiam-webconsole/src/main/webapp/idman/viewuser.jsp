
<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,org.openiam.webadmin.busdel.base.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("userForm");
  String personId = (String) request.getAttribute("personId");
  String status = (String)userForm.get("status");
 
%>
<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function openNoteWin(url, title, form, width, height) { 
	
     window.name="parentwindow";
     win = window.open(url, title, 
          "scrollbars=no,status=yes,width=" + width + ",height=" + height + ",resizable=no,top=200,left=375"); 
} 

//  End --> 
</script> 
<html:html>
<html:form action = "/idman/userValidate.do?method=saveUser" >

<table width="700pt" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="4" class="title">         
          <strong>User Information</strong>
          <br>
   <html:hidden property="personId" />
   <html:hidden property="mode" />

   <html:hidden property = "firstName"  />
   <html:hidden property = "middleName" /> 
   <html:hidden property = "lastName" />
<html:hidden property = "companyId" />
<html:hidden property = "dept" />
<html:hidden property = "title" />
<html:hidden property="status"  />
<html:hidden property="createDate" />
<html:hidden property="createdBy" />
<html:hidden property="group" />

<html:hidden property="addressId" />
<html:hidden property="emailId" />
<html:hidden property="workPhoneId" />       
<html:hidden property="cellPhoneId" />
<html:hidden property="faxPhoneId" />

<html:hidden property = "phone_areacd" />
<html:hidden property = "phone_nbr" />

<html:hidden property = "cell_areacd" />
<html:hidden property = "cell_nbr" />

<html:hidden property = "fax_areacd" />
<html:hidden property = "fax_nbr" />

<html:hidden property = "state" />
<html:hidden property = "address1" />
<html:hidden property = "address2" />

<html:hidden property = "email" />
<html:hidden property = "zip" />

      </td>
      <td class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="6" align="center" bgcolor="8397B4" height="1pt">
 		</td>
  </tr>
	
	<tr>
	  <td height="2pt" colspan="6">&nbsp;</td>
 	</tr>
  
   <tr>
       <td class="tddark" align="right" width="20%"> Name</td>
       <td class="tdlight" colspan="4">
	   	<%=JSPUtil.display(userForm.get("firstName"))%> <%=JSPUtil.display(userForm.get("middleName"))%> <%=JSPUtil.display(userForm.get("lastName"))%> 
       </td>
       <td class="tdlight">Picture</td>
    </tr>
    <tr>
				 <td class="tddark" align="right" width="20%">Company Name:</td>
				 <td class="tdlight" valign="middle" width="40%" colspan="2">
				 <%=userForm.get("companyName")%>

				 </td>

         <td class="tddark" align="right" width="20%">Dept</td>
         <td class="tdlight" width="20%">
		 <%=JSPUtil.display(userForm.get("dept"))%>
         </td>
         <td class="tdlight" rowspan="4">
         <!-- 
         	<img src="" alt="No image found" width="150" height="150" />
       	-->
         </td>
     </tr>
        
    <tr >
         <td class="tddark"  align="right" width="20%">Title</td>
         <td class="tdlight" width="20%"  colspan="2">
		 <%=JSPUtil.display( userForm.get("title") )%>
         </td>

		 <td class="tddark"  align="right" width="20%"></td>
         <td class="tdlight" width="20%"></td>
        
    </tr>
    
   
    <tr >
         <td class="tddark"  align="right" width="20%">Status</td>
         <td class="tdlight" colspan="4">
		 <%=JSPUtil.display( userForm.get("status") )%>
         </td>        
         
    </tr>
    <tr >
         <td class="tddark"  align="right" width="20%">Create Date:</td>
         <td class="tdlight" width="40%"  colspan="2"><%=JSPUtil.display(userForm.get("createDate"))%>
         	
         </td>

		 <td class="tddark"  align="right" width="20%">Created By:</td>
         <td class="tdlight" width="20%"><%=JSPUtil.display(userForm.get("createdBy"))%></td>
        
    </tr>

	<jsp:include page="vwmetadata.jsp" flush="true" />

   
  <tr>
   	<td colspan="5" class="tddark" align="center">Contact Information</td>
   </tr>

  <tr>
       <td class="tddark" align="right" width="20%">Address</td>
       <td class="tdlight" width="40%"  colspan="2"><%= JSPUtil.display( userForm.get("address1") )%>
       <td class="tddark" align="right" width="20%">Work Phone</td>
       <td class="tdlight" width="20%">
       (<%=JSPUtil.display(userForm.get("phone_areacd"))%>)<%=JSPUtil.display(userForm.get("phone_nbr"))%> </td>
   </tr>
 <tr>
       <td class="tddark" align="right" width="20%"></td>
       <td class="tdlight" colspan=2><%=JSPUtil.display( userForm.get("address2") )%></td>
       <td class="tddark" align="right" width="20%">Cell Phone</td>
       <td class="tdlight" width="20%">
       (<%=JSPUtil.display(userForm.get("cell_areacd"))%>)<%=JSPUtil.display(userForm.get("cell_nbr"))%></td>
   </tr>

  <tr>
       <td class="tddark" align="right">City</td>
       <td class="tdlight"  colspan="2"><%= JSPUtil.display(userForm.get("city"))%></td>
       <td class="tddark" align="right">Fax</td>
       <td class="tdlight">
       (<%=JSPUtil.display(userForm.get("fax_areacd"))%>)<%=JSPUtil.display(userForm.get("fax_nbr"))%>
       </td>
   </tr>
  <tr>
       <td class="tddark" align="right">State</td>
       <td class="tdlight"  colspan="2"><%= JSPUtil.display( userForm.get("state") )%></td>
       <td class="tddark" align="right">E-mail</td>
       <td class="tdlight"><%=JSPUtil.display(userForm.get("email"))%></td>
   </tr>
  <tr>
       <td class="tddark" align="right">Postal Code</td>
       <td class="tdlight"  colspan="2"><%= JSPUtil.display( userForm.get("zip") )%></td>
       <td class="tddark" align="right">Country</td>
       <td class="tdlight"><%=JSPUtil.display(userForm.get("country"))%></td>
   </tr>

    
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" height="1pt">

 		   </td>
    </tr>
  
    <tr >
       <td colspan="5" align ="right">
       <% if ( !status.equalsIgnoreCase("DELETED")) {%>
       <html:submit property="submit" value="Delete User" onclick="openNoteWin('userNotes.do?method=newNote&mode=DL', 'Address', this.form,425,225);return false;"/>
       <% } %>
       <% if ( !status.equalsIgnoreCase("BLACK LISTED") && !status.equalsIgnoreCase("DELETED")) {%>
       <html:submit property="submit" value="Blacklist User" onclick="openNoteWin('userNotes.do?method=newNote&mode=BL', 'Address', this.form,425,225); return false;"/>
      <% } %>
      <% if ( status.equalsIgnoreCase("BLACK LISTED") && !status.equalsIgnoreCase("DELETED")) {%> 
       <html:submit property="submit" value="Un-Blacklist User" onclick="openNoteWin('userNotes.do?method=newNote&mode=UB', 'Address', this.form,425,225); return false;"/>
      <% } %>

       </td>
    </tr>
    
 </table>
</html:form>
</html:html>

<% 
	if (personId != null && personId.length() > 0) { 
%>
	<jsp:include page="detailtab.jsp" flush="true" />
<% } %>
