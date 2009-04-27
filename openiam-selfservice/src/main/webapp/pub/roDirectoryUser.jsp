
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*, org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.user.dto.*" %>
<%@ page import="org.openiam.idm.srvc.continfo.dto.*" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<% 
  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("searchForm");
  String personId = (String) request.getAttribute("personId");
  String saved = (String)request.getAttribute("saved");
  User usr = (User)request.getAttribute("personData");
  
  Set<Address> adrSet = usr.getAddresses();
  Set<Phone> phoneSet = usr.getPhone();
  Set<EmailAddress> emailSet =  usr.getEmailAddress();
 



	List groupList = (List)session.getAttribute("groupList");
	if (groupList == null)
		groupList = new ArrayList();
	pageContext.setAttribute("groupList", groupList);

	List serviceList = (List)session.getAttribute("services");
	if (serviceList == null)
		serviceList = new ArrayList();
	pageContext.setAttribute("serviceList", serviceList);	
	
	Supervisor superVis = (Supervisor)request.getAttribute("supervisor");

	List<Supervisor> directReports = (List<Supervisor>)request.getAttribute("directReports");

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
         <td class="tddark"  align="right" width="25%">Title</td>
         <td class="tdlightnormal"><%= JSPUtil.display( userForm.get("title") ) %>
         </td>
        <td class="tddark"  align="right" width="25%">Employee Type</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("employeeType") ) %> </td>

        
    </tr>
    <tr>
				 <td class="tddark" align="right" width="25%">Company Name</td>
				 <td class="tdlight" valign="middle" width="25%">
					<%= JSPUtil.display( userForm.get("companyName") ) %>
					<html:hidden property="companyId" />
				 </td>

        <td class="tddark"  align="right" width="25%">Division</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("division") ) %> </td>


     </tr>
        
    <tr>
		 <td class="tddark"  align="right" width="25%">Manager</td>
		 <td class="tdlight" width="25%">
		 <% if (superVis != null) {%>
         <a href="pub/directory.do?method=detail&personId=<%=superVis.getSupervisor().getUserId() %>"%>
		<%= superVis.getSupervisor().getFirstName() %> <%= superVis.getSupervisor().getLastName() %></a>   
	     <%} %>
		 </td>

         <td class="tddark" align="right" width="25%">Dept</td>
         <td class="tdlightnormal" width="25%">
         	<%= JSPUtil.display( userForm.get("deptName") ) %>
         </td>
		</tr>
    <tr>
         <td class="tddark"  align="right" width="25%">Job Function</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("jobCode") ) %></td>
		 <td class="tddark"  align="right" width="25%">Cost Center</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("costCenter") ) %></td>      
    </tr>    
      
    <tr>
         <td class="tddark"  align="right" width="25%">Support Staff Contact</td>
         <td class="tdlightnormal" width="25%"> </td>
		 <td class="tddark"  align="right" width="25%">Mail Code</td>
         <td class="tdlightnormal" width="25%"><%= JSPUtil.display( userForm.get("mailCode") ) %></td>      
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
       <td class="tddark" align="right" rowspan="3">Phones</td>
       <td class="tdlightnormal" rowspan="3">
       	<table>
       		<% if (phoneSet != null) {
       			Iterator<Phone> phoneIt = phoneSet.iterator();
       			while (phoneIt.hasNext()) {
       				Phone ph = phoneIt.next();
       		%> 
       		<tr>
       			<td><%= ph.getName() %></td>
       			<td><%= ph.getAreaCd() %> - <%= ph.getPhoneNbr() %></td>
       		</tr>
       		<% 	}
       		}  
       		%>
       	</table>
       
       </td>
   </tr>
 <tr>
       <td class="tddark" align="right"></td>
       <td class="tdlightnormal">
       		<%= JSPUtil.display( userForm.get("address2") ) %>
   </tr>

  <tr>
       <td class="tddark" align="right">City</td>
       <td class="tdlightnormal">
       	<%= JSPUtil.display( userForm.get("city") ) %>
	   </td>
   </tr>
  <tr>
       <td class="tddark" align="right">State</td>
       <td class="tdlightnormal">
       <%= JSPUtil.display( userForm.get("state") ) %>
       </td>
       <td class="tddark" align="right">E-mail</td>
       <td class="tdlightnormal">
              	<table>
       		<% if (emailSet != null) {
       			Iterator<EmailAddress> emailIt = emailSet.iterator();
       			while (emailIt.hasNext()) {
       				EmailAddress em = emailIt.next();
       		%> 
       		<tr>
       			<td><%= em.getName() %></td>
       			<td><%= em.getEmailAddress() %> </td>
       		</tr>
       		<% 	}
       		}  
       		%>
       	</table>
       
       
       <%= JSPUtil.display( userForm.get("email") ) %>
       </td>
   </tr>
  <tr>
       <td class="tddark" align="right">Postal Code</td>
       <td class="tdlightnormal"><%= JSPUtil.display( userForm.get("zip") ) %> </td>
       <td class="tddark" align="right">Country</td>
       <td class="tdlightnormal">
       	<%= JSPUtil.display( userForm.get("country") ) %>
       </td>
   </tr>


   <tr>
   	<td colspan="4" class="tddark" align="center">Direct Reports</td>
   	<td></td>
   </tr>    
	<% if (directReports != null) {
		int listSize = directReports.size();
		for (int i=0; i<listSize; i++) {
				Supervisor emp = directReports.get(i);
	%>
		<tr>
		 <td colspan="2"> 
	         <a href="pub/directory.do?method=detail&personId=<%=emp.getEmployee().getUserId() %>"%>
			<%= emp.getEmployee().getFirstName() %> <%= emp.getEmployee().getLastName() %></a>   
		</td>
		<td>
			<%
				EmailAddress em = null;
			 	Set emSet = emp.getEmployee().getEmailAddress();
			 	Iterator<EmailAddress> it = emSet.iterator();
			 	if (it.hasNext()) {
			 		em = it.next();
			 	}
			 %>
				<%= em.getEmailAddress() %>
	
		</td>
		<td>
			<%
				Phone ph = null;
			 	Set<Phone> phSet = emp.getEmployee().getPhone();
			 	Iterator<Phone> phoneIt = phSet.iterator();
			 	if (phoneIt.hasNext()) {
			 		ph = phoneIt.next();
			 	}
			 %>
				(<%= JSPUtil.display(ph.getAreaCd()) %>)-<%= ph.getPhoneNbr() %>
	
		</td>
		</tr>
	<%	
		}
		}else { %>
	<tr>
		<td colspan="4"> No Direct Reports Found. </td>
	</tr>
	<% } %>
		 </td>


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




