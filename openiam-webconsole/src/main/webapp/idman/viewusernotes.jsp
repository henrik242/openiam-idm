<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.ebc.user.*,diamelle.base.composite.*,diamelle.common.user.*" %>


<table align="center" width="98%" border="0" cellspacing="2" cellpadding="1">
  <br>
  <tr class="tddark">
    <td  align="center">Date</td>
    <td  align="center">Created By</td>
    <td  align="center">Type</td>
    <td  align="center">Description</td>

  </tr>
  
  <%
    //Component comp = null;
    List historyList = (List)request.getAttribute("detailView");
    String activeTab = (String)request.getAttribute("activetab");
    String updatedetail = (String)request.getAttribute("updatedetail");
    String userId = (String)request.getAttribute("userId");
    
    if (historyList != null) {
    	int size = historyList.size();
    	for (int i=0; i<size; i++) {
    		UserNoteValue val = (UserNoteValue)historyList.get(i); 
    		
     		String typeDesc = null;
    		if (val.getNoteTypeId().equals("DL")) {
    			typeDesc = "DELETED";
    		} else if (val.getNoteTypeId().equals("RJ")) {
    			typeDesc = "REJECTED";
    		}else if (val.getNoteTypeId().equals("FT")) {
    			typeDesc = "FORGOT TOKEN";
    		}else if (val.getNoteTypeId().equals("ST")) {
    			typeDesc = "STOP TOKEN";
    		}else if (val.getNoteTypeId().equals("UB")) {
    			typeDesc = "UN-BLACKLISTED";
    		} else
    			typeDesc = "BLACK LISTED";
   			
  %>
  	<tr class="tdtab">
  		<td><%=val.getDateCreated()%></td>
  		<td><%=val.getCreatedBy()%></td>
  		<td><%=typeDesc%></td>
  		<td><%=val.getDescription()%></td>

	</tr>
  <%
  		}

   } else {
  %>
  
  <tr>
    <td colspan="6">No Notes found.
   </td>
  </tr>
  
  <%
   }
  %>
  
   <tr>
 		<td colspan="3" align="center" >
 		  &nbsp;
 		</td>
  </tr>
   
   
</table>

