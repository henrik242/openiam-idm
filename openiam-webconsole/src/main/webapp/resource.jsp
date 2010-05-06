<!-- adding new resource -->
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.resource.*,org.apache.struts.action.*"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<script language="JavaScript">
<!--
function refreshParent() {
  window.opener.location.href = window.opener.location.href;
  if (window.opener.progressWindow) {
    window.opener.progressWindow.close()
  }  
  window.close();
}

//-->
</script>

<head>
<title>Resource</title>
<link href="diamelleapp.css" rel="stylesheet" type="text/css">
</head>
  <body onunload='window.opener.location.href = window.opener.location.href'>

  <%
    String mode = "";  
    DynaActionForm f = (DynaActionForm) request.getAttribute("resourceForm");
    if (f != null)
      mode = (String) f.get("mode");
    System.out.println("resource.jsp ...mode is " + mode);  
    String resourceId = (String)f.get("resourceId");  
    
    if ((resourceId == null)||(resourceId.length()==0))    
      resourceId = request.getParameter("resourceId");
      
    String opTitle = "Editing ";
    if ((mode != null)&& (mode.equalsIgnoreCase("addResource")) )
      opTitle = "Adding a ";
    if ((mode != null)&& (mode.equalsIgnoreCase("addRoot")) )
      opTitle = "Adding Root ";
      
 		List resourceTypes = (List)request.getAttribute("resourceTypes");
    pageContext.setAttribute("resourceTypes", resourceTypes); 
      
  %>
  
		<br><br>
<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

    <table width="80%" border="0" cellspacing="0" cellpadding="0">
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


	<table cellspacing=2 cellpadding=2  width="80%" align="center">
  <tr><td>

		<html:form action="/security/resource.do?method=saveResource" >
   
			<table cellspacing=2 cellpadding=2  width="100%" align="center">
				
				<tr bgcolor="#cccccc">
        	<td align="center" colspan="2" class="th" valign="top">

          <html:hidden property="mode" value="<%=mode%>" />

	          	<strong><%=opTitle%>&nbsp; Resource</strong>
           <br>(Fields marked with  *  are compulsory)
					</td>
				</tr>
<% 
	if ((resourceId != null) && (resourceId.length() > 0)) {
%>				 
		<tr bgcolor="#eeeeee">
        	<td class="label" align="right">Resource Id (auto generated)</td>
          <td><html:text property="resourceId" maxlength="20" readonly="true"/></td>
        </tr>

<%
	}
  // not showing this field if we are adding a root
  if (!mode.equalsIgnoreCase("addRoot")) {
%>
         
        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Resource Parent</td>
          <td><html:text property="resourceParent" maxlength="25" /></td>
        </tr>
        
        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Branch Id</td>
          <td><html:text property="branchId" maxlength="25" /></td>
        </tr>

        
<%}%>        
               
        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Description</td>
          <td><html:text property="description" maxlength="25" /></td>
        </tr>

        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Resource Type Id</td>
          <td>
      			<html:select property="resourceTypeId">
           		<html:options collection="resourceTypes" property="value" labelProperty="label"/>
     				</html:select> 
          </td>  
        </tr>

        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Category Id<font color="red">*</font></td>
          <td><html:text property="categoryId" maxlength="25" /></td>
        </tr>

        <tr bgcolor="#eeeeee">
        	<td class="label" align="right">Provision Resource</td>
          <td><input type="checkbox"> Workflow: <select>
          											<option>Select workflow to provision resource</option>
          										</select>
          </td>
        </tr>
        
        
        <tr bgcolor="#eeeeee">
	        <td align="center" colspan="2">
<!-- do not want to close the popup on button click, since there is more to add
 	        	<html:submit property="submit" value="Save" onclick='self.close(); return true;' />
-->
 	        	<html:submit property="submit" value="Save" />
         </td>
        </tr>
     	</table>     
		</html:form>
 
 </td></tr> 
 <tr><td align="center">

 	<% 
    if (mode != null) {
		if (!((mode.equalsIgnoreCase("addResource"))||(mode.equalsIgnoreCase("addRoot")))) {
 	%>
    <jsp:include page="resourceprops.jsp">
       <jsp:param name="resourceId" value="<%= resourceId %>" />
    </jsp:include>
    <%} } %>
    
 </td></tr> 
 <tr><td align="center">
    
 		<% 
    if (mode != null)
      if ((mode.equalsIgnoreCase("addprop"))||(mode.equalsIgnoreCase("editprop"))) { %>     
    		<jsp:include page="resourceprop.jsp">
    			<jsp:param name="resourceId" value="<%= resourceId %>" />
    		</jsp:include>
 		<%}%>
  </td></tr>
  
 </table>
 
	</body>
