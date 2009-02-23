<!-- Resource selection for a role  -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.resource.*,diamelle.security.auth.*,diamelle.common.cat.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<body>
<%

  List availResources = (List) request.getAttribute("availResources");
  //List rolePrivileges = (List) request.getAttribute("rolePrivileges");
  List privileges = (List) request.getAttribute("privileges");
  List allPrivileges = (List) request.getAttribute("allPrivileges");
  String categoryId = (String) session.getAttribute("resCategoryId");
    
  String selectAll = (String) request.getAttribute("selectAll");
  boolean markAll = false;  
  if (selectAll != null)
  	markAll = true;

  CategoryValue cv = null;
  String catName = null;
  
  RoleValue roleValue = (RoleValue) request.getAttribute("roleValue");
  String roleId = roleValue.getRoleId();  
  
  String privilegeTitle = (String) request.getAttribute("privilegeTitle");
  if (privilegeTitle == null)
  	privilegeTitle = "All Resource Privileges";
  
%>

  <html:form action = "/security/roleResource.do?method=setResources">
  <table width="100%" align="center" border="0" cellspacing=2 cellpadding=1  >
          
<!-- Headings -->
   <tr>
               <td bgcolor="eeeeee" align = "center" valign="top">
                 <b>Categories</b>
              </td>
               <td align = "center" valign = "top" bgcolor = "#eeeeee">
                 <b>Available Resources</b>
                 <html:hidden property="roleId" value="<%=roleId%>" />
               </td>
               <td bgcolor="eeeeee" align = "center" valign="top">
                 <b><%=privilegeTitle%></b>
              </td>
   </tr>

<!-- Data -->          
    <tr>
          
    <td bgcolor="eeeeee" valign="top" width="120">
            <TABLE width="100%">
            <tr><td>

    <!-- Categories -->          
            <%
              Collection categoryList = (Collection) session.getAttribute("resCategories");
              if (categoryList != null && !categoryList.isEmpty() ) {
                Iterator cit = categoryList.iterator();
                while (cit.hasNext()) {
                  cv = (CategoryValue)cit.next();
                  catName = cv.getCategoryName();
            %>
                  <font face="Verdana, Arial, Helvetica, sans-serif" size="1">&nbsp;
                    <a href="security/role.do?method=acl&resCategoryId=<%=cv.getCategoryId()%>&resShowList=<%=cv.getShowList()%>">
                    <%=catName%></a>
                  </font>
                <br>
            <%
                }
              }
            %>
            
            <br>Click on a category above to see its resources.<br><br>

            </td></tr>
            
            
                          <!-- All Privileges -->
              <tr>            
               <td bgcolor="eeeeee" align = "center" valign="top">
                 <b>Privilege List</b>
              </td>
              </tr>
                          
                          
                <%
              if (allPrivileges != null) {
  	             Iterator pit = allPrivileges.iterator();
          	     while (pit.hasNext()) {
	                 PrivilegeValue pv = (PrivilegeValue)pit.next();
	             %>
                <tr bgcolor="#eeeeee"  valign="top" >
                  <td bgcolor="#cccccc" class="normalTxtBlack" colspan="2">          
                      <input type="checkbox" name="privileges" value="<%=pv.getPrivilegeId()%>"/>
                       <%=pv.getDescription()%><br>
                   </td>
                 </tr>  
                     <%
                      }
                    } else {  
                     %> 
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" colspan="2">No privileges set</td>
           </tr>
 
                      <%
                   }  
                     %>

           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" colspan="2" > 
             Select privileges to assign above, 
             select resources in the center column and click button below.                        
             </td>
           </tr>
            
            </TABLE>
            
            
    </td>
          
    <td bgcolor="eeeeee" width="290" valign="top">
    	<!-- Available resources -->

    	<table width="100%" cellspacing=2 cellpadding=2>
    		<%
    			diamelle.security.resource.ResourceValue parentResource = (diamelle.security.resource.ResourceValue) request.getAttribute("resourceValue"); String resourceParentString; if (parentResource != null) { resourceParentString = parentResource.getResourceParent(); if (resourceParentString == null) resourceParentString = "";


    		%>


          <html:hidden property="resourceParent" value="<%=parentResource.getResourceId()%>" />
          
           
  	       <tr bgcolor="#eeeeee"  valign="top" >
	            <td bgcolor="#cccccc" class="normalTxtBlack" colspan="2" valign="top" > 
        <a href="security/roleResource.do?method=getTree&resourceId=<%=parentResource.getResourceId()%>">                
        <strong>Node: <%=parentResource.getResourceId()%></strong></a>
        &nbsp; &nbsp; &nbsp; <%=parentResource.getDescription()%>
        <br>
        <a href="security/roleResource.do?method=getTree&resourceId=<%=parentResource.getResourceParent()%>">        
        Parent: <%=resourceParentString%></a>
        <br>
        <a href="security/roleResource.do?method=getTree&resourceId=<%=parentResource.getBranchId()%>">                
        Branch: <%=parentResource.getBranchId()%></a>
        <br>
        <a href="security/roleResource.do?method=getTree&categoryId=<%=parentResource.getCategoryId()%>">                        
        Category: <%=parentResource.getCategoryId()%></a>
        <%       
        
            } else {
        %>        
            <strong><%=categoryId%>: Root Resources</strong>        
         <% }
           %>
        
             </td>
           </tr>  
           
        <%  
          if (availResources != null) {
         	Iterator rit = availResources.iterator();
            while( rit.hasNext()) {
            	diamelle.security.resource.ResourceValue val = (diamelle.security.resource.ResourceValue) rit.next();
              String resourceId = val.getResourceId();
              String description = val.getDescription();
              int n = val.getNodeLevel();
              StringBuffer indents = new StringBuffer(); 
              
              for (int i=0; i<n ; i++) 
                indents.append("->");
        %>
            
  	         <tr bgcolor="#eeeeee">
  	         
        	<%
          	  if (markAll) {
        	%>      
	            <td bgcolor="#cccccc" class="normalTxtBlack" valign="top">          
                   <input type="checkbox" name="resourceId" value="<%=resourceId%>" checked="checked" />
                   <%=indents.toString()%>
                   <a href="security/roleResource.do?method=getTree&resourceId=<%=resourceId%>&resCategoryId=<%=cv.getCategoryId()%>&resShowList=<%=cv.getShowList()%>"><%=resourceId%></a>
                   <%=description%> <br>
                   Event: <select>
                   				<option>--</option>
                   				<option>Authenticate Attempt</option>
                   				<option>Authenticate Accept</option>
                   				<option>Authenticate Fail</option>
                   				<option>Authorize Attempt</option>
                   				<option>Authorize Accept</option>
                   				<option>Authorize Fail</option>
                   		  </select><br>
                   Policy Script:<TEXTAREA col="60" rows="5"></TEXTAREA>
                   
                </td>
            <%
              } else {
            %>    
	            <td bgcolor="#cccccc" class="normalTxtBlack" valign="top">          
                   <input type="checkbox" name="resourceId" value="<%=resourceId%>"/>              
                   <%=indents.toString()%>
                   <a href="security/roleResource.do?method=getTree&resourceId=<%=resourceId%>&resCategoryId=<%=cv.getCategoryId()%>&resShowList=<%=cv.getShowList()%>"><%=resourceId%></a>
                   <b><%=description%></b> <br>
  					Event:<select>
  								<option>--</option>
                   				<option>Authenticate Attempt</option>
                   				<option>Authenticate Accept</option>
                   				<option>Authenticate Fail</option>
                   				<option>Authorize Attempt</option>
                   				<option>Authorize Accept</option>
                   				<option>Authorize Fail</option>
                   		  </select><br>
                   Authentication: Order<select>
                   						<option>--</option>
                   						<option>1</option>
                   						<option>2</option>
                   				   </select>	
                   			  Type<select>
  								<option>--</option>
                   				<option>Password</option>
                   				<option>Gemalto Smart Card</option>
                   				<option>Physical Access Badge</option>
                   				<option>Diamelle Two Factor</option>
                    		  </select><br>		  
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Order<select>
                   						<option>--</option>
                   						<option>1</option>
                   						<option>2</option>
                   				   </select>	
                   			  Type<select>
  								<option>--</option>
                   				<option>Password</option>
                   				<option>Diamelle Two Factor</option>
                    		  </select><br>		  
 					Time:&nbsp;<select>
                   						<option>--</option>
                   						<option>Access</option>
                   						<option>Deny</option>
                   				   </select>
                   				   <input type="text" name="start" size="10">
								 - <input type="text" name="end" size="10"> <input type="button" value="Add"/>
                   			  <br>                  		  
                   Policy:<TEXTAREA cols="60" rows="5"></TEXTAREA>
                   </td>
              
        	<%
        	  }
        	  
          	  if (parentResource != null) {
        	%>      
              
              <TD width="10" align="right">
              	<a href="security/roleResource.do?method=getTree&resourceId=<%=parentResource.getResourceId()%>&privResourceId=<%=resourceId%>&resCategoryId=<%=cv.getCategoryId()%>&resShowList=<%=cv.getShowList()%>">
              	<img src="images/button_more.gif"></a> 
              </TD>
              
             <%
             }
             %> 
              
            </tr>
            
            
						<% } 
            }
					 %>
            

          <%  if (availResources == null || availResources.size() == 0) { %>
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" colspan="2"> 
	           No resources for this node.
             </td>
           </tr>
	           
		  <% } else { %>  
		              
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee" colspan="2"> 
               
               Click on a Resource to view its descendents. 
               Click an arrow to see privileges for a single resource or
               on the Node to see privileges for all node resources.
             </td>
           </tr>
		  <% }%>              
           
           
           
           
         </table>
    </td>
                            
    <td bgcolor="eeeeee" width="180">
    <!-- Privileges -->
              <table >

               <%
               if ((privileges != null)&&(!privileges.isEmpty())) {
  	             Iterator xit = privileges.iterator();
          	     while (xit.hasNext()) {
	                 ResourceRoleValue rr = (ResourceRoleValue)xit.next();
	             %>
                <tr bgcolor="#eeeeee" valign="top">
                  <td class="normalTxtBlack" valign="top">          
                      <input type="checkbox" name="roleResourcePrivileges" value="<%=rr.getResourceId()+"-"+rr.getPrivilegeId()%>"/>
                       <%=rr.getResourceId()+": "+rr.getPrivilegeId()%><br>
                   </td>
                 </tr>  
                 
                 
                 
                <%
                      } 
                      
               %>
               
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee">
             <br>
             Check privileges to delete and
             click button below</td>
           </tr>
               
                      
               <%       
                    }  else {
                %> 
           <tr bgcolor="#eeeeee">
             <td class="normalTxtBlack" bgcolor="#eeeeee"> No privileges assigned </td>
           </tr>
                
                <%
                }
                %>
                
                
                               
               </table>   
     </td>
              
   </tr>

            <tr>                

             	<td align="center" colspan="2"> 
                            <html:submit property="submit" value="AssignPrivileges" />
                         
                        <html:submit property="submit" value="SelectAll" />
                </td>

                <td align="center">
                         <% if ((privileges != null) && (privileges.size() > 0 )) { %>
                            <html:submit property="submit" value="RemovePrivileges" />
                         <% } %>
                </td>
            </tr>

  </table>
  </html:form>

</body>


