
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*, org.openiam.webadmin.busdel.base.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("userForm");
  String personId = (String) request.getAttribute("personId");
  String status = (String)userForm.get("status");
  String saved = (String)request.getAttribute("saved");
 

	//List statusList = (List)session.getAttribute("statusList");
	//pageContext.setAttribute("statusList",statusList);

	List groupList = (List)session.getAttribute("groupList");
	if (groupList == null)
		groupList = new ArrayList();
	pageContext.setAttribute("groupList", groupList);

	List serviceList = (List)session.getAttribute("services");
	if (serviceList == null)
		serviceList = new ArrayList();
	pageContext.setAttribute("serviceList", serviceList);	

%>




<script language ="javascript" src="js/date-picker.js"></script>

<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function openWin(url, title, form) { 
     window.name="parentwindow";
     win = window.open(url, title, 
          "scrollbars=no,status=yes,width=410,height=280,resizable=no,top=180,left=350"); 
} 

function openNoteWin(url, title, form, width, height) { 
	
     window.name="parentwindow";
     win = window.open(url, title, 
          "scrollbars=no,status=yes,width=" + width + ",height=" + height + ",resizable=no,top=180,left=350"); 
} 

function confirmMsg(msg) {
    return confirm(msg);  
}

//  End --> 

</script> 


<html:form action = "/priv/profileValidate.do?method=saveUser">



<table width="600pt" border="0" cellspacing="2" cellpadding="1" align="center">

	<tr>
      <td colspan="3" class="title">         
          <strong>User Information</strong>
          <br>
         <html:hidden property="personId" />
         <html:hidden property="mode" />
      </td>
      <td colspan="2" class="text" align="right">         
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
       <td class="tddark" align="right" width="25%"> Name</td>
       <td class="tdlightnormal" colspan="3">
       <%= JSPUtil.display( userForm.get("firstName") ) %> <%= JSPUtil.display( userForm.get("middleName") ) %> <%= JSPUtil.display( userForm.get("lastName") ) %>
       </td>
    </tr>
    <tr>
				 <td class="tddark" align="right" width="25%">Company Name:</td>
				 <td class="tdlight" valign="middle" width="25%">
				<%= JSPUtil.display( userForm.get("companyName") ) %>
				<html:hidden property="companyId" />
				 </td>

         <td class="tddark" align="right" width="25%">Dept</td>
         <td class="tdlightnormal" width="25%">
         	<%= JSPUtil.display( userForm.get("dept") ) %>
         </td>
     </tr>
        
    <tr >
         <td class="tddark"  align="right" width="25%">Title</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("title") ) %>
         </td>

		 <td class="tddark"  align="right" width="25%"></td>
         <td class="tdlight" width="25%"></td>
        
    </tr>


   
   <tr>
   	<td colspan="4" class="tddark" align="center">Contact Information</td>
   	<td></td>
   </tr>
  
  <tr>
       <td class="tddark" align="right">Address</td>
       <td class="tdlightnormal">
<html:hidden property="addressId" />
<html:hidden property="emailId" />
<html:hidden property="workPhoneId" />       
<html:hidden property="cellPhoneId" />
<html:hidden property="faxPhoneId" />
       <%= JSPUtil.display( userForm.get("address1") ) %>
       
       </td>
       <td class="tddark" align="right">Work Phone</td>
       <td class="tdlightnormal">
       (<%= JSPUtil.display( userForm.get("phone_areacd") ) %> ) <%= JSPUtil.display( userForm.get("phone_nbr") ) %>
       
       </td>
   </tr>
 <tr>
       <td class="tddark" align="right"></td>
       <td class="tdlightnormal">
       		<%= JSPUtil.display( userForm.get("address2") ) %>
       <td class="tddark" align="right">Mobile Phone</td>
       <td class="tdlightnormal">
       (<%= JSPUtil.display( userForm.get("cell_areacd") ) %>) <%= JSPUtil.display( userForm.get("cell_nbr") ) %>
       	</td>
   </tr>

  <tr>
       <td class="tddark" align="right">City</td>
       <td class="tdlightnormal">
       	<%= JSPUtil.display( userForm.get("city") ) %>
	   </td>
       <td class="tddark" align="right">Fax</td>
       <td class="tdlightnormal">
       	(<%= JSPUtil.display( userForm.get("fax_areacd") ) %>) <%= JSPUtil.display( userForm.get("fax_nbr") ) %>
       </td>
   </tr>
  <tr>
       <td class="tddark" align="right">State</td>
       <td class="tdlightnormal">
       <%= JSPUtil.display( userForm.get("state") ) %>
       </td>
       <td class="tddark" align="right">E-mail</td>
       <td class="tdlightnormal">
       <%= JSPUtil.display( userForm.get("email") ) %>
       </td>
   </tr>
  <tr>
       <td class="tddark" align="right">Postal Code</td>
       <td class="tdlightnormal">
       <%= JSPUtil.display( userForm.get("zip") ) %>
       </td>
       <td class="tddark" align="right">Country</td>
       <td class="tdlightnormal">
       <%= JSPUtil.display( userForm.get("country") ) %>
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
  
     
 </table>
</html:form>




