
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.idm.srvc.user.dto.UserAttribute, org.openiam.webadmin.busdel.base.*" %>
<%@ page import="org.openiam.idm.srvc.meta.dto.MetadataElement" %>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%! 
      public UserAttribute getAttribute(String name, Map attrMap) {
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
	

	if (elementAry != null && elementAry.length > 0 && attrMap != null && !attrMap.isEmpty()) {
%>
   <tr>
   	<td colspan="5" class="tddark" align="center">Custom User Attributes</td>
   </tr>
<%

		int size = elementAry.length;
		int ctr = 0;
		while (ctr < size) {
			MetadataElement val = elementAry[ctr];			
			ctr++;

%>
 	<tr>
       <td class="tddark" align="right"><%= val.getAttributeName() %></td>
       <td class="tdlight" colspan="2">
      <%  
     		 UserAttribute comp = getAttribute(val.getAttributeName(), attrMap);
      		if (comp != null) {
      %>
 		<%= JSPUtil.display( comp.getValue() ) %>
        </td>
      <%	}  %>
 
<%		
	if (ctr < size) {
				val = elementAry[ctr];			
				ctr++;
%>
       <td class="tddark" align="right"><%= val.getAttributeName() %></td>
       <td class="tdlight" colspan="3">
      <%  
      		comp = getAttribute(val.getAttributeName(), attrMap);
      		if (comp != null) {
      %>
      		 <%= JSPUtil.display( comp.getValue() ) %>
      <%	}  %>	
 			</td>
		<% } else { %>
      <td class="tddark" align="right"></td>
       <td class="tdlight" colspan="3"></td>
	<%		} %>
	</tr>
	<%
		}
	} %>