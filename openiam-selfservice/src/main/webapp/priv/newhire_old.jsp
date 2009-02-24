
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*, org.openiam.webadmin.busdel.base.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% 
  String personId = (String) request.getAttribute("personId");
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


<html:form action = "/priv/newHireValidate.do?method=saveUser">



<table width="600pt" border="0" cellspacing="2" cellpadding="1" align="center">
	<% if (saved != null && saved.equals("1")){ %>	
	<tr>
		<td colspan="4"><font color="red">New hire information has been successfully saved.</font>
			
		</td>
	</tr>   
	<%	} %>
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
       <td class="tddarknormal" align="right"> Name<font color=red>*</font></td>
       <td class="tdlight" colspan="3">
            <html:text property = "firstName" size="30" maxlength="40" readonly="false" />
            <html:text property = "middleName" size="1" maxlength="1" readonly="false" /> 
            <html:text property = "lastName" size="30" maxlength="40" readonly="false" />
       </td>
    </tr>

    <tr>
				 <td class="tddarknormal" align="right">Company Name:<font color=red>*</font></td>
				 <td class="tdlight" valign="middle">
					<select>
						<option>Orange County Government</option>
					</select>
				<html:hidden property="companyId" />
				 </td>

         <td class="tddarknormal" align="right">Agency<font color=red>*</font></td>
         <td class="tdlight">
       	<html:text property = "dept" maxlength="20" readonly="<%=false%>" />
         </td>
     </tr>

        
    <tr >
         <td class="tddarknormal"  align="right">FCN Title<font color=red>*</font></td>
         <td class="tdlight">
         	<html:text property = "title" maxlength="30" readonly="<%=false%>"/>
         </td>

		 <td class="tddark"  align="right"></td>
         <td class="tdlight"></td>
        
    </tr>
    
   
    <tr >
         <td class="tddarknormal"  align="right">User Login<font color=red>*</font></td>
         <td class="tdlightnormal" colspan="3">
         	<html:text property = "login" maxlength="30" readonly="<%=false%>"/>
         </td>        
    </tr>
    <tr >
         <td class="tddarknormal"  align="right">Password<font color=red>*</font></td>
         <td class="tdlightnormal" colspan="3">
         	<html:password property = "password" maxlength="30" readonly="<%=false%>"/>
         </td>        
    </tr>
    <tr >
         <td class="tddarknormal"  align="right">Confirm Password<font color=red>*</font></td>
         <td class="tdlightnormal" colspan="3">
         	<html:password property = "confirmpassword" maxlength="30" readonly="<%=false%>"/>
         </td>        
    </tr>

   
   <tr>
   	<td colspan="4" class="tddark" align="center">Contact Information</td>
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
       <td class="tddarknormal" align="right">Work Phone<font color=red>*</font></td>
       <td class="tdlight"><html:text property = "phone_areacd" maxlength="3" size="3"/>
       						<html:text property = "phone_nbr" maxlength="10" size="10"/>
       </td>
   </tr>
 <tr>
       <td class="tddarknormal" align="right"></td>
       <td class="tdlight"><html:text property = "address2" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">Mobile Phone</td>
       <td class="tdlight"><html:text property = "cell_areacd" maxlength="3" size="3"/>
       						<html:text property = "cell_nbr" maxlength="10" size="10"/>
       	</td>
   </tr>

  <tr>
       <td class="tddarknormal" align="right">City</td>
       <td class="tdlight"><html:text property = "city" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">Fax</td>
       <td class="tdlight"><html:text property = "fax_areacd" maxlength="3" size="3"/>
       						<html:text property = "fax_nbr" maxlength="10" size="10"/>
       </td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">State</td>
       <td class="tdlight"><html:text property = "state" maxlength="30" size="30"/></td>
       <td class="tddarknormal" align="right">E-mail <font color=red>*</font></td>
       <td class="tdlight"><html:text property = "email" maxlength="30" size="30"/></td>
   </tr>
  <tr>
       <td class="tddarknormal" align="right">Postal Code</td>
       <td class="tdlight"><html:text property = "zip" maxlength="10" size="10"/></td>
       <td class="tddarknormal" align="right">Country</td>
       <td class="tdlight"><html:text property = "country" maxlength="30" size="30"/></td>
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
		   <td colspan="4"><input type="submit" align="right" name="Submit"></td>
    </tr>
    

    
 </table>
</html:form>




