
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.UserAttribute" %>
<%@ page import="org.openiam.idm.srvc.meta.dto.MetadataElement" %>

<%! 
      public UserAttribute getAttribute(String name, Map attrMap) {
      	if (attrMap == null || attrMap.isEmpty())
      	return null;
      	
		Collection col = attrMap.values();
		Iterator it = col.iterator();
		while (it.hasNext()) {
			UserAttribute cmp = (UserAttribute)it.next();
			if (cmp.getName().equals(name)) {
				return cmp;
			}
		}
		return null;
     }
%>
<% 
	MetadataElement[] elementAry = (MetadataElement[])request.getAttribute("metadata");
	Map attrMap = (Map)request.getAttribute("userAttr");
	
	if (elementAry != null && elementAry.length > 0) {
%>
   <tr>
   	<td colspan="5" class="tddark" align="center">Custom User Attributes</td>
   </tr>
<%

		int size = elementAry.length;
		int ctr = 0;
		while (ctr < size) {
			MetadataElement val = elementAry[ctr];		
			System.out.println("metadata jsp = " + val + " typeId =" + val.getMetadataTypeId());
			ctr++;
%>
 	<tr>
       <td class="tddark" align="right"><%= val.getAttributeName() %></td>
       <td class="tdlight"">
       <% 
       UserAttribute comp = getAttribute(val.getAttributeName(), attrMap);
       	if (comp == null) { %>
       		<input type="text" name="*<%=val.getAttributeName()%>-<%=val.getMetadataElementId()%>" size="30"> 
      <% }else { 
      %>
      		<input type="text" name="*<%=val.getAttributeName()%>-<%=val.getMetadataElementId()%>"  value="<%=comp.getValue() %>" size="30"> 
      <% } %>
      </td>
 


<%		
			if (ctr < size) {
				
				val = elementAry[ctr];			
				ctr++;		
%>
       <td class="tddark" align="right"><%= val.getAttributeName() %></td>
       	    <% 
       	    comp = getAttribute(val.getAttributeName(), attrMap);
       	    if (comp == null) { 
       	    %>
       	    <td class="tdlight" colspan="3">
        	 <input type="text" name="*<%=val.getAttributeName()%>-<%= val.getMetadataElementId() %>" size="30"> </td>

	<%
			}else {
 	%>
 		<td class="tdlight" colspan="3">
      		<input type="text" name="*<%=val.getAttributeName()%>-<%=val.getMetadataElementId()%>"  value="<%=comp.getValue() %>" size="30"> 
      		</TD>
      		<% } %>

		<% } else { %>
      <td class="tddark" align="right"></td>
       <td class="tdlight" colspan="3"></td>
	<%		} %>
	</tr>
	<%
		}
	} %>