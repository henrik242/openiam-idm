<!-- resources.jsp: list of branches for a selected category -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,diamelle.security.resource.*,diamelle.common.cat.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<SCRIPT LANGUAGE="JavaScript">
<!-- Begin

function openWin(url, title, form) {
    queryString = 'resourceId='+ form.resourceParent.value;
    url = url + '&' + queryString;
		win = window.open(url, title, 
    			"scrollbars=yes,status=yes,width=400,height=500");
}

function openEditWin(url, title, resourceId) {
    queryString = 'resourceId='+ resourceId;
    url = url + '&' + queryString;
		win = window.open(url, title, 
    			"scrollbars=yes,status=yes,width=400,height=500");
}

//  End -->

</script>


<style type="text/css">
.error {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #FF0000; text-decoration: none}
.link1 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #111111; text-decoration: none}
.link2 {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 8pt; color: #111111; text-decoration: none}
</style>


<%
	System.out.println("resources.jsp...");

  List resources = (List) request.getAttribute("resources");

  String selectAll = (String) request.getAttribute("selectAll");
  boolean markAll = false;  
  if (selectAll != null)
  	markAll = true;

  CategoryValue catVal = (CategoryValue) session.getAttribute("categoryValue");
  String catName = null;
  String categoryId = null;
  if (catVal != null) {
     catName = catVal.getCategoryName();
     categoryId = catVal.getCategoryId();
  } 
  System.out.println("Category value: " + catVal);
  
  ResourceValue parentResource = (ResourceValue) request.getAttribute("resourceValue");
  String parentResourceId = null;
  String parentBranchId = null;
  String parentDescription = null;
  String parentCategoryId = null;
  String parentResourceParent = null;

  if (parentResource != null) {
    parentResourceId = parentResource.getResourceId();
    parentBranchId = parentResource.getBranchId();
    parentDescription = parentResource.getDescription();
    parentCategoryId = parentResource.getCategoryId();
    parentResourceParent = parentResource.getResourceParent();
    if (parentResourceParent == null)
      parentResourceParent = "This is a root branch";
  }
  
    
%>




	<table cellpadding=2 align="center">
 
<!-- Heading -->
 
  
  	<tr bgcolor="#cccccc" class="th" align="center">
     	<td align="center" class="th" colspan="2">
			  <% 
        if (parentResource != null) {
           if(resources == null || resources.size() == 0) { %>
	             <strong>No resources for </strong>
			  <% }%> 

        <a href="/security/resourceTree.do?method=init&resourceId=<%=parentResourceId%>">                
        <strong>Node: <%=parentResourceId%></strong></a>
        <br>Description: <%=parentDescription%>
        <br>
        <a href="security/resourceTree.do?method=init&resourceId=<%=parentResourceParent%>">        
        Parent: <%=parentResourceParent%></a>
        <br>
        <a href="security/resourceTree.do?method=init&resourceId=<%=parentBranchId%>">                
        Branch: <%=parentBranchId%></a>
        <br>
        <a href="security/resourceTree.do?method=init&categoryId=<%=parentCategoryId%>">                        
        Category: <%=parentCategoryId%></a>
        
        <%
          } else {
            if ((categoryId == null)||(categoryId.equalsIgnoreCase("null"))) {
        %>
            <strong>Select a category to see root resources</strong>                
        <%
            } else {
        %>        
            <strong><%=categoryId%>: Root Resources for <%=catName%></strong>        
         <% }
          } %>
			</td>
  	</tr>
  	
<!-- Resources and Privileges -->
  	
    <tr bgcolor="#cccccc">  

  <!-- Resources -->

      <td valign="top" align="center">

    	<html:form method="POST" action="/security/resourceTree.do?method=removeResource" >

		<table width="100%" cellpadding=2>

           <html:hidden property="resourceParent" value="<%=parentResourceId%>" />
          
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" align="center" colspan="2">
             <b>Resource Tree</b></td>
           </tr>
					<% if ((resources != null)&&(!resources.isEmpty())) {
          	Iterator it = resources.iterator();
            while( it.hasNext()) {
            	diamelle.security.resource.ResourceValue val = (diamelle.security.resource.ResourceValue) it.next();
              String resourceId = val.getResourceId();
              String description = val.getDescription();
              
              int nodeLevel = val.getNodeLevel();
              StringBuffer indents = new StringBuffer();             
              for (int i=1;i<=nodeLevel;i++)
                indents.append("->");
           %>
            
  	         <tr bgcolor="#eeeeee"  valign="top" >
	            <td width="80%" bgcolor="#cccccc" class="normalTxtBlack">          
                   <input type="checkbox" name="resourceId" value="<%=resourceId%>"/>              
                   <%=indents.toString()%>
                   <a href="security/resourceTree.do?method=init&resourceId=<%=resourceId%>"><%=resourceId%></a>
                   <%=description%>
              </td>
              
              <td align="center">
              <a href="" 
              onclick="openEditWin('security/resource.do?method=init', 'Resource', <%=resourceId%>);return false;">
              edit</a>              
              </td>
            </tr>
						<% } %>
            
            <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" align="center" bgcolor="#eeeeee" colspan="2"> 
             Click on a Resource to view its hierarchy</td>
           </tr>
            
            
				<%	}  else { %>
					
           	<tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" colspan="2" align="center"> 
             <br>
             No child resources.<br>
             Select a category, if available, on the left.
             <br><br>
             </td>
           </tr>

			<% } %>		
            
         
          <tr bgcolor="#eeeeee">
            <td align="center" colspan="2">
              <html:submit property="submit" value="Add" onclick="openWin('/webconsole/security/resource.do?method=newResource', 'Resource', this.form);return false;"/>
 
 			<% if ((resources != null)&&(!resources.isEmpty())) { %>
 
              <html:submit property="submit" value="Delete"/>
              <BR><BR>
                	Mark resources to be deleted. Child resources will be automatically deleted.
                	Note that you must first remove privileges of the resource and its descendents.
              
            <% } %>  
            
             </td>
           </tr>

		</table>

        </html:form>

    	</td>
    	
    	<td valign="top" width="40%">
    <!-- Privileges -->
		 	<html:form method="POST" action="/security/resourceTree.do?method=removePrivilege" >
           <html:hidden property="resourceParent" value="<%=parentResourceId%>" />
    	    <TABLE width="100%">
           <tr bgcolor="#eeeeee">
               <td bgcolor="eeeeee" align = "center" valign="top">
                 <b>Role - Privilege</b>
              </td>
           </tr>
 
               <%
               
               List privileges = (List) request.getAttribute("privileges");
               
               if (privileges != null) {
  	             Iterator xit = privileges.iterator();
          	     while (xit.hasNext()) {
	                 ResourceRoleValue rr = (ResourceRoleValue)xit.next();

        	
          	  if (markAll) {
        	      

	             %>
                <tr bgcolor="#eeeeee"   >
                  <td class="normalTxtBlack" valign="top">          
                      <input type="checkbox" name="roleResourcePrivileges" checked="checked" value="<%=rr.getRoleId()+"-"+rr.getPrivilegeId()%>"/>
                       <%=rr.getRoleId() + ": " + rr.getPrivilegeId()%><br>
                   </td>
                 </tr> 
                 
               <% } else { %>   

                <tr bgcolor="#eeeeee"   >
                  <td class="normalTxtBlack" valign="top">          
                      <input type="checkbox" name="roleResourcePrivileges" value="<%=rr.getRoleId()+"-"+rr.getPrivilegeId()%>"/>
                       <%=rr.getRoleId() + ": " + rr.getPrivilegeId()%><br>
                   </td>
                 </tr> 
                 
				<%}}} else {%>
				
	           <tr bgcolor="#eeeeee">
            	   <td bgcolor="eeeeee" align = "center" valign="top">
                		No privileges defined for this resource. Go to
                		Role/Access Control to define privileges.
        	      </td>
    	       </tr>
				
				<% } %>
           		
           		
		          <tr bgcolor="#eeeeee">
        		    <td align="center" colspan="2">
              			<html:submit property="submit" value="DeletePrivilege"/>
              			<html:submit property="submit" value="SelectAll"/>
             		</td>
           		</tr>
	           <tr bgcolor="#eeeeee">
            	   <td bgcolor="eeeeee" align = "center" valign="top">
                		Mark privileges to be deleted 
        	      </td>
    	       </tr>
    		</TABLE>
	        </html:form>
    	</td>
    	
 	  </tr>
  </table>

 


