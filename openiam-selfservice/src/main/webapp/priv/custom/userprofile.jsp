
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.user.dto.User" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("customProfileForm");
  String personId = (String) request.getAttribute("personId");
  String status = (String)userForm.get("status");
  String saved = (String)request.getAttribute("saved");
  
  String appsUid = (String)session.getAttribute("apps_uid");
  String appsPassword = (String)session.getAttribute("apps_password");
 

%>





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


<html:form action = "/priv/customProfileValidate.do?method=saveUser">



<table width="650pt" border="0" cellspacing="2" cellpadding="1" align="center">
	<% if (saved != null && saved.equals("1")){ %>	
	<tr>
		<td colspan="5"><font color="red">Profile information has been successfully saved.</font>
			
		</td>
	</tr>   
	<%	} %>
	<tr>
		<td colspan="5">
     		<table>
			<tr>
			  <td class="error">
			    <logic:messagesPresent>        
			         <html:messages id="error" header="errors.header">
			            <li><bean:write name="error"/></li>
			         </html:messages>    
			     </logic:messagesPresent> 
			  </td>
			</tr>
			</table>
		</td>
	</tr>

	<tr>
      <td colspan="3" class="title">         
          <strong>User Profile Information</strong>
          <br>
         <html:hidden property="personId" />
         <html:hidden property="mode" />
      </td>
      <td colspan="2" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 </tr>
  
   <tr>
       <td class="tddarknormal" align="right"> Name<font color=red>*</font></td>
       <td class="tdlight" colspan="3">
            <html:text property = "firstName" size="30" maxlength="40" readonly="false" />
            <html:text property = "middleName" size="1" maxlength="1" readonly="false" /> 
            <html:text property = "lastName" size="30" maxlength="40" readonly="false" />
       </td>
	   <td class="tdlight"></td>
    </tr>
    <tr>
				 <td class="tddarknormal" align="right">Company Name:</td>
				 <td class="tdlight" valign="middle">
				<%= JSPUtil.display( userForm.get("companyName") ) %>
				<html:hidden property="companyId" />
				 </td>

         <td class="tddarknormal" align="right">Agency<font size="1" color="red">*</font></td>
         <td class="tdlight">
       	<html:text property = "dept" maxlength="20" readonly="<%=false%>" />
         </td>
		 <td class="tdlight"></td>
     </tr>
        
    <tr >
         <td class="tddarknormal"  align="right">FCN Title<font size="1" color="red">*</font></td>
         <td class="tdlight">
         	<html:text property = "title" maxlength="30" readonly="<%=false%>"/>
         </td>

		 <td class="tddark"  align="right"></td>
         <td class="tdlight"></td>
         <td class="tdlight"></td>
    </tr>
    <tr >
         <td class="tddarknormal"  align="right">Manager</td>
         <td class="tdlight"><html:text property = "manager" maxlength="20" readonly="<%=false%>"/>
         </td>

		 <td class="tddarknormal"  align="right">Location</td>
         <td class="tdlightnormal"><html:text property = "location" maxlength="20" readonly="<%=false%>"/></td>
         <td class="tdlight"></td>
    </tr>    
   
    <tr >
         <td class="tddarknormal"  align="right">Status</td>
         <td class="tdlightnormal" colspan="3">
			<html:hidden property="status" /> <%= JSPUtil.display( userForm.get("status") ) %>
         </td>        
         <td class="tdlight"></td>
    </tr>
    <tr >
         <td class="tddarknormal"  align="right">Create Date:</td>
         <td class="tdlight"><%=userForm.get("createDate")%>
         	
         </td>

		 <td class="tddarknormal"  align="right">Created By:</td>
         <td class="tdlight"><%= JSPUtil.display( userForm.get("createdBy") )%></td>
         <td class="tdlight"></td>
    </tr>

   
   <tr>
   	<td colspan="5" class="tddark" align="center">Contact Information</td>
   	<td></td>
   </tr>
  
  <tr>
       <td class="tddarknormal" align="right">Address</td>
       <td class="tdlight">
<html:hidden property="addressId" />
<html:hidden property="emailId" />
<html:hidden property="workPhoneId" />       
<html:hidden property="cellPhoneId" />
<html:hidden property="faxPhoneId" />
       
       <html:text property = "address1" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">Work Phone <font size="1" color="red">*</font></td>
       <td class="tdlight"><html:text property = "phone_areacd" maxlength="3" size="3"/>
       						<html:text property = "phone_nbr" maxlength="10" size="10"/>
       </td>
 	  <td class="tdlight"></td>
   </tr>
 <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"><html:text property = "address2" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">Mobile Phone</td>
       <td class="tdlight"><html:text property = "cell_areacd" maxlength="3" size="3"/>
       						<html:text property = "cell_nbr" maxlength="10" size="10"/>
       	</td>
		 <td class="tdlight"></td>
   </tr>

  <tr>
       <td class="tddarknormal" align="right">City</td>
       <td class="tdlight"><html:text property = "city" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">Fax</td>
       <td class="tdlight"><html:text property = "fax_areacd" maxlength="3" size="3"/>
       						<html:text property = "fax_nbr" maxlength="10" size="10"/>
       </td>
	   <td class="tdlight"></td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">State</td>
       <td class="tdlight"><html:text property = "state" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">E-mail<font size="1" color="red">*</font></td>
       <td class="tdlight"><html:text property = "email" maxlength="30" size="30"/></td>
	    <td class="tdlight"></td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Postal Code</td>
       <td class="tdlight"><html:text property = "zip" maxlength="10" size="10"/></td>
       <td class="tddarknormal" align="right">Country</td>
       <td class="tdlight"><html:text property = "country" maxlength="30" size="30"/></td>
	    <td class="tdlight"></td>   
	</tr>
   <tr>
   	<td colspan="5" class="tddark" align="center">Application Properties</td>
   	<td></td>
   </tr>
  <tr>
       <td class="tddark" align="right">App Name</td>
       <td class="tddark">App URL</td>
       <td class="tddark" align="right">App Enabled</td>
       <td class="tddark">App UID</td>
	   <td class="tddark">App Password</td>
   </tr>
   <tr>
       <td class="tdlightNormal" align="right">CAPS-FS</td>
       <td class="tdlightNormal"><input type="text" name="app_url" value="staging.ocgov.com"></td>
       <td class="tdlightNormal" align="right"><input type="checkbox" name="app_enabled" checked></td>
       <td class="tdlightNormal"><input type="text" name="app_uid" value="<%=appsUid%>"></td>
	   <td class="tdlightNormal"><input type="text" name="app_password" value="<%=appsPassword%>"></td>
   </tr>

   <tr>
       <td class="tddarkNormal" align="right">Clarity</td>
       <td class="tddarkNormal"><input type="text" name="app_url2" value=""></td>
       <td class="tddarkNormal" align="right"><input type="checkbox" name="app_enabled2" ></td>
       <td class="tddarkNormal"><input type="text" name="app_uid2" value=""></td>
	   <td class="tddarkNormal"><input type="text" name="app_password2" value=""></td>
   </tr>
        
    <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>
  
    <tr >
       <td colspan="5" align ="right">

	   <% if (!status.equalsIgnoreCase("DELETED")) {  %>
             <html:submit property="submit" value="Save"/>
             <html:reset/>
        <% } %>
       </td>
    </tr>
    
 </table>
</html:form>




