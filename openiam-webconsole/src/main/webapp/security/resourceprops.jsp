<%@ page import="java.util.*,javax.servlet.http.*,diamelle.base.prop.*,diamelle.app.base.*"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

  <%
    //passed thru jsp include tag
    String resourceId = request.getParameter("resourceId");
    
 %>
  

  <table cellspacing=2 cellpadding=2 width="100%" align="center">

		<html:form action = "/resource.do?method=handleProperties" >

		<html:hidden property="resourceId" value="<%=resourceId%>" />
		<tr>  			
			 <td align="center" bgcolor="#cccccc" class="th" width="20%">	
					<strong>Resource Prop Id</strong>
   		 </td>
 
   		 <td align="center" bgcolor="#cccccc" class="th" width="20%">	
					<strong>MetaData ID</strong>
   		 </td>   		 
  			
   		 <td align="center" bgcolor="#cccccc" class="th" width="20%">	
					<strong>Property Name</strong>
   		 </td>   		 

   		 <td align="center" bgcolor="#cccccc" class="th" width="40%">	
					<strong>Property Value</strong>
   		 </td>
		</tr>			
     	
     	<%
        List resourceProp = (List) request.getAttribute("resourceProp");
     		if(resourceProp != null) {
      			Iterator itr = resourceProp.iterator();
     			while(itr.hasNext()) {
     				Property p= (Property) itr.next();	
            String metadataId = p.getMetaDataId();
            String attributeValue = p.getValueAsString();
            if (attributeValue == null)
            	attributeValue = "";
            System.out.println("attrib name is " + p.getName());	
     	%>
    <input type="hidden"  name="metadataId" value="<%=metadataId%>" />
    <input type="hidden"  name="propId" value="<%=p.getId()%>" />

		<tr bgcolor="#eeeeee">
			<td class="normalTxtBlack" align="left">
         <input type="checkbox" name="listResourcePropId" value="<%= p.getId() %>"/>
         <a href="resource.do?method=editProperty&resourcePropId=<%=p.getId()%>&resourceId=<%=resourceId%>">
         <%= p.getId() %></a>
      </td>
			     		
      <td class="normalTxtBlack" align="left"><%= metadataId %></td>

      <td class="normalTxtBlack" align="left"><%=JSPUtil.display( p.getName() ) %></td>


			<td class="normalTxtBlack" align="left">
            <input type="text" name="attributeValue" size="20" maxlength="80" value="<%=attributeValue%>"/>			
			</td>
		</tr>	
			   	<% }}	else {
          %>
          
	 	<tr bgcolor="#eeeeee">
	 		<td class="normalTxtBlack" align="center" colspan="4">No properties defined
	 		</td>
   	</tr>	

          <%
          }          
          %>
  
    <tr bgcolor="#eeeeee">
       <td align="center" colspan="4">
       	  <html:submit property="submit" value="Delete"/>
       	  <html:submit property="submit" value="Save"/>
       </td>
    </tr>

     </html:form> 
 
  
   	<tr bgcolor="#eeeeee">
 	     		<td class="normalTxtBlack" align="center" colspan="4">
          <a href="resource.do?method=addProperty&resourceId=<%=resourceId%>">Add Property</a>
          </td>
    </tr>
  </table>
    

