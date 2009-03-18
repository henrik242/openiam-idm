
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>
<%@ page import="org.openiam.idm.srvc.secdomain.dto.SecurityDomain" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 


  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("userForm");
  String personId = (String) request.getAttribute("personId");
  String status = (String)userForm.get("status");
 

	List groupList = (List)request.getAttribute("groupLabelList");

	//List groupList = (List)session.getAttribute("groupList");
	if (groupList == null)
		groupList = new ArrayList();
	pageContext.setAttribute("groupList", groupList);
	List companyList = (List)session.getAttribute("companyList");
	if (companyList == null)
		companyList = new ArrayList();
	pageContext.setAttribute("companyList", companyList);	



	List domainList = (List)session.getAttribute("domains");
	if (domainList == null) {
		domainList = new ArrayList();
	}
	pageContext.setAttribute("domainList", domainList);
	
	
	List countryList = (List)session.getAttribute("countryList");
	if (countryList == null)
		countryList = new ArrayList();
	pageContext.setAttribute("countryList", countryList);	

	List userTypeList = (List)session.getAttribute("userTypes");
	if (userTypeList == null)
		userTypeList = new ArrayList();
	pageContext.setAttribute("userTypes", userTypeList);	

	String typeId = (String)request.getAttribute("typeId");
	
	String confirmmsg = (String)request.getAttribute("confirmmsg");

%>


<html:html>

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
          "scrollbars=no,status=yes,width=" + width + ",height=" + height + ",resizable=no,top=200,left=375"); 
} 

function confirmMsg(msg) {
    return confirm(msg);  
}

//  End --> 

</script> 


<html:form action = "/idman/userValidate.do?method=saveUser">



<table width="700pt" border="0" cellspacing="2" cellpadding="1" align="center">
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
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>
 <% if (confirmmsg != null && confirmmsg.length() > 0 ) { %>
  <tr>
       <td colspan="5" class="body"><font color="red"><%=confirmmsg %></font></td>
       
    </tr>
   <% } %>
  <tr>
       <td class="tddark" align="right">User Type<font color=red>*</font></td>
       <td class="tdlight" colspan="3">
       	<html:text property = "typeId" size="30" maxlength="40" readonly="true" value="<%= typeId %>" />
        </td>
    </tr>
  
   <tr>
       <td class="tddark" align="right"> Name<font color=red>*</font></td>
       <td class="tdlight" colspan="3">
            <html:text property = "firstName" size="30" maxlength="40" readonly="false" />
            <html:text property = "middleName" size="1" maxlength="1" readonly="false" /> 
            <html:text property = "lastName" size="30" maxlength="40" readonly="false" />
       </td>
    </tr>
    <tr>
				 <td class="tddark" align="right">Company Name:</td>
				 <td class="tdlight" valign="middle">
             <html:select property="companyId" >
        		<html:options collection="companyList" property="value" labelProperty="label"/>
        	 </html:select>
				 </td>

         <td class="tddark" align="right">Dept</td>
         <td class="tdlight">
       	<html:text property = "dept" maxlength="20" readonly="<%=false%>" />
         </td>
     </tr>
        
    <tr >
         <td class="tddark"  align="right">Title</td>
         <td class="tdlight">
         	<html:text property = "title" maxlength="30" readonly="<%=false%>"/>
         </td>

		 <td class="tddark"  align="right"></td>
         <td class="tdlight"></td>
        
    </tr>
    
   
    <tr >
         <td class="tddark"  align="right">Status</td>
         <td class="tdlight" colspan="3">
             <% 
              if (personId == null || personId.length() == 0 ) {        
             %> 
             <html:text property="status" value="PENDING" readonly="true" />             
             <% } else { %>
			 <html:text property="status" readonly="true"  />          
             <% } %>
         </td>        
         
    </tr>
    <tr >
         <td class="tddark"  align="right">Create Date:</td>
         <td class="tdlight"><%=userForm.get("createDate")%>
         	
         </td>

		 <td class="tddark"  align="right">Created By:</td>
         <td class="tdlight"><%=userForm.get("createdBy")%></td>
        
    </tr>
	<jsp:include page="metadata.jsp" flush="true" />
   
    <% 
     if (personId == null || personId.length() == 0 ) {        
   %> 
   <tr>
   	<td colspan="4" class="tddark" align="center">Login Information</td>
   </tr>
   <tr>
       <td class="tddark" align="right">Login Id<font color=red>*</font></td>
       <td class="tdlight" colspan="3"><html:text property = "login" maxlength="20" /></td>
   </tr>
   <tr>
			 <td class="tddark" align="right">Password<font color=red>*</font></td>
       <td class="tdlight" colspan="3"><html:password property = "password" maxlength="20" /></td>
   </tr>   
   <tr>
			 <td class="tddark" align="right">Confirm Password<font color=red>*</font></td>
       <td class="tdlight" colspan="3"><html:password property = "confirmpassword" maxlength="20" /></td>
   </tr>    
   <tr>
       <td class="tddark" align="right">Default Group<font color=red>*</font></td>  
       <td class="tdlight" colspan="3">
            <html:select property="group" >
        		<html:options collection="groupList" property="value" labelProperty="label"/>
        	 </html:select>
       </td>
         
   </tr>
   <tr>
       <td class="tddark" align="right">Default Security Domain<font color=red>*</font></td>  
       <td class="tdlight" colspan="3">
           <html:select property="service" >
        		<html:options collection="domainList" property="value" labelProperty="label"/>
        	 </html:select>

         </td>
         
   </tr>
   <% } %>  
  <tr>
   	<td colspan="4" class="tddark" align="center">Contact Information</td>
   	<td></td>
   </tr>
  
  <tr>
       <td class="tddark" align="right">Address</td>
       <td class="tdlight">
<html:hidden property="addressId" />
<html:hidden property="emailId" />
<html:hidden property="workPhoneId" />       
<html:hidden property="cellPhoneId" />
<html:hidden property="faxPhoneId" />
       
       <html:text property = "address1" maxlength="30" size="30"/></td>
       <td class="tddark" align="right">Work Phone</td>
       <td class="tdlight"><html:text property = "phone_areacd" maxlength="3" size="3"/>
       						<html:text property = "phone_nbr" maxlength="10" size="10"/>
       </td>
   </tr>
 <tr>
       <td class="tddark" align="right"></td>
       <td class="tdlight"><html:text property = "address2" maxlength="30" size="30"/></td>
       <td class="tddark" align="right">Mobile Phone</td>
       <td class="tdlight"><html:text property = "cell_areacd" maxlength="3" size="3"/>
       						<html:text property = "cell_nbr" maxlength="10" size="10"/>
       	</td>
   </tr>

  <tr>
       <td class="tddark" align="right">City</td>
       <td class="tdlight"><html:text property = "city" maxlength="30" size="30"/></td>
       <td class="tddark" align="right">Fax</td>
       <td class="tdlight"><html:text property = "fax_areacd" maxlength="3" size="3"/>
       						<html:text property = "fax_nbr" maxlength="10" size="10"/>
       </td>
   </tr>
  <tr>
       <td class="tddark" align="right">State</td>
       <td class="tdlight"><html:text property = "state" maxlength="30" size="30"/></td>
       <td class="tddark" align="right">E-mail</td>
       <td class="tdlight"><html:text property = "email" maxlength="40" size="40"/></td>
   </tr>
  <tr>
       <td class="tddark" align="right">Postal Code</td>
       <td class="tdlight"><html:text property = "zip" maxlength="10" size="10"/></td>
       <td class="tddark" align="right">Country</td>
       <td class="tdlight">
             <html:select property="country" >
        		<html:options collection="countryList" property="value" labelProperty="label"/>
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
     <% if (personId  != null ) {%>
      <% if ( !status.equalsIgnoreCase("BLACK LISTED") && !status.equalsIgnoreCase("DELETED")) {%>
       <html:submit property="submit" value="Blacklist User" onclick="openNoteWin('userNotes.do?method=newNote&mode=BL', 'Address', this.form,400,200); return false;"/>
      <% } %>
        <% if ( !status.equalsIgnoreCase("DELETED")) {%>
       <html:submit property="submit" value="Delete User" onclick="openNoteWin('userNotes.do?method=newNote&mode=DL', 'Address', this.form,400,200);return false;"/>
       <%  }
       } 
       %>
	   <% if (!status.equalsIgnoreCase("DELETED")) {  %>
             <html:submit property="submit" value="Save"/>
             <html:reset/>
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

