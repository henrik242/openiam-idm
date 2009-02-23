     <!-- list of services and roles and for the service selected and adding new role -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,org.openiam.idm.srvc.service.dto.*,diamelle.security.auth.*,org.apache.struts.validator.DynaValidatorForm" %>
<%@ page import="org.openiam.idm.srvc.secdomain.dto.SecurityDomain" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<%
 // Map services = (Map) request.getAttribute("services");
	SecurityDomain domainAry[] = (SecurityDomain[])request.getAttribute("services");

	List roleList = (List) request.getAttribute("roleList");
  String serviceId = (String) session.getAttribute("serviceId");
  
  DynaValidatorForm roleForm = (DynaValidatorForm) request.getAttribute("roleForm");
  String mode = (String) roleForm.get("mode");  
  
  
%>

<body>
  <br>
	<table cellspacing=2 cellpadding=2 align="center">
		<html:hidden property="mode" value="<%=mode%>" />
  	<tr align="center">
    	<td class="tdheader" colspan="2"  align="center" width="30%">
      	  <strong>Security Domains</strong>
    	</td>
    
			<td class="tdheader" align="center" colspan="2" >
				
      		
      		<% if (serviceId == null) { %>
		      		Select a Domain to get a list of Roles 
	        <% } else { %>
				 	    Roles for the Domain : <%=serviceId%>
         	<% } %>
				
			</td>
		</tr>  	
 	
  	<tr class="tdlight">
			<td colspan="2" width="30%" align="center" valign="top">
				<table width="100%" cellspacing=2 cellpadding=2>
      	<html:form action = "/security/role.do?method=removeRole" >

					<tr>
          	<td>&nbsp;</td>
          </tr>
		<%  //if (services != null) {
			if (domainAry != null) {
				int size = domainAry.length;	
				for (int i=0; i<size; i++) {
					SecurityDomain secDom = domainAry[i];				
	
			//Iterator serviceItr = services.keySet().iterator();
            //Iterator Iter = services.values().iterator();
            //while( serviceItr.hasNext()) {
            	//String servId  = (String) serviceItr.next();
            	//System.out.println("Service object type = " + services.get(servId));
            	//Service serv = (Service)services.get(servId);
            	
              //String serviceName = (String) Iter.next();
          %>
            
            <tr>
                <td width="80%">
                   <a href="security/role.do?method=viewRoles&serviceId=<%=secDom.getDomainId()%>"><%=secDom.getName()%></a>
                </td>
            </tr>
						<% } 
					} %>
            
          <tr>
          	<td>&nbsp;</td>
          </tr>
        </table>
      </td>
      
      <td valign="top" align="center">
        <table width="100%">
           <tr>
              <td colspan="2">&nbsp;</td>
           </tr>
           
           <% if (roleList != null && !roleList.isEmpty()) {
                Iterator roleListItr = roleList.iterator();
                while (roleListItr.hasNext()) {
                    RoleValue roleValue = (RoleValue) roleListItr.next();
           %>
  	         <tr valign="top" >
	            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="roleId" value="<%=roleValue.getRoleId()%>"/>
                 <a href="security/role.do?method=editRole&roleId=<%=roleValue.getRoleId()%>&serviceId=<%=serviceId%>" class ="link2">
                     <%= roleValue.getRoleName()%>
                 </a>
      	       </td>
    	       </tr>
           <% } %>
        
          <%} else { 
               
               if (serviceId != null) {
               
              
          %> 
              <tr valign="top" >
	            <td colspan="2">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 No Role for Domain:	<%=serviceId%>
      	       </td>
    	       </tr>
           
           <% }} %>
          
          <tr>
		    	  <td colspan="2">&nbsp;</td>
		     </tr>
		 
		      <%  if (serviceId != null) { %>
		     <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>  
         <% } %> 
         
          <tr >  
              <%  if (serviceId != null) { %>           
             <td>
                 <a href = "security/role.do?method=addRole&serviceId=<%=serviceId%>">New Role</a>
             </td>
             <% } %>
          

		  		<% if (serviceId != null && (roleList != null && roleList.size() > 0) ) { %>
						
             <td align="right" >
                 <html:submit property="submit" value="Delete"/>
             </td>
           </tr>
		   		<% } %>
				</table>
    	</td>
 		</tr>
 </html:form>
 </table>
 
	<table align="center" width="50%">
		<% if(mode.equalsIgnoreCase("add") || mode.equalsIgnoreCase("edit")) { %>     
			<tr><td colspan="2">
    		<jsp:include page="role.jsp">
    			<jsp:param name="mode" value="<%=mode%>" />
    		</jsp:include>
  		</td></tr>
 		<%}%>

</table>

 </body>


