<!--list of groups and list of groups to which a user belongs (if any) -->

<%@ page language="java" %>
<%@ page session="true" %>

<%@ page import="java.util.*,org.apache.struts.validator.*" %>
<%@ page import="org.openiam.idm.srvc.grp.dto.Group" %>
<%@ page import="org.openiam.webadmin.busdel.base.JSPUtil" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<%

System.out.println("In usergrplist.jsp");
  DynaValidatorForm userForm = (DynaValidatorForm) request.getAttribute("userForm");

  List groupList = (List) request.getAttribute("groupList");
  List userList = (List) request.getAttribute("userList");
  String personId = (String) request.getAttribute("personId");
  

  System.out.println("userList is jsp = " + userList);
  
%>

<html:form action="/idman/userGroup.do?method=edit">
<table cellspacing=0 cellpadding=0  width="100%" height="100%">

  <% if (groupList != null && groupList.size() != 0) {
  %>
  
  <tr>
    <td valign="top">
      <html:errors/>
         <table border="0" align="center" cellspacing=2 cellpadding=1 width="98%">
             
              <html:hidden property="personId" value="<%=personId%>" />
             
              <tr>
              <% if (groupList.size() > 0) {%>
                <td class="tddark"  align = "center" valign="top" class="normalTxtBold">
                       List of Groups
                </td>
                <td width="5%" bgcolor="#98ABCE">&nbsp</td>
            <%}%>

                 <td class="tddark"  align = "center" valign="top" >
                     <b>Groups for User:</b> <%=userForm.get("firstName")%> <%=userForm.get("lastName")%>
                 </td>
              </tr>
              <tr>
                 <td class="tdtab">
                 <%
                   // if (!groupList.isEmpty()) {
                       Iterator groupListIterator = groupList.iterator();
                       while (groupListIterator.hasNext()) {
                    	 org.openiam.idm.srvc.grp.dto.Group gv = (org.openiam.idm.srvc.grp.dto.Group)groupListIterator.next();
                    	 if(userList!=null && userList.contains(gv)){
                 %>
		                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <%=gv.getGrpName()%><br>

                 <%
                    } else {
                 %>
                   <input type="checkbox" name="groupId" value="<%=gv.getGrpId()%>"/>
                        <%=gv.getGrpName()%><br>
                        <html:hidden property="personId" />


                 <%
                         }
                    }
                 //}
                 %>
                 </td>
                 <td>
                <table>
                     <tr>
                  <td align="center" >
                    <% if (groupList.size() > 0 )  {%>
                      <html:submit property="submit" value=">" />

                    <%}%>
                  </td>
                  </tr>
                  <tr>
                  	<td align = "center">
	                  <% if (userList !=null && userList.size() > 0 ) { %>
	                      <html:submit property="submit" value="<" />
	                  <%}%>
                	</td>
              	</tr>
              	</table>
              </td>

                  <td class="tdtab">
 <%
                           if (userList != null &&  !userList.isEmpty()) {
                        	     int size = userList.size();
                        
                            	//Iterator userIter = userList.iterator();
                            	for (int i=0; i<size;i++) {
                             		org.openiam.idm.srvc.grp.dto.Group grpData = (org.openiam.idm.srvc.grp.dto.Group)userList.get(i);
                       %>	
                          <input type = checkbox name ="grouproleId" value="<%=grpData.getGrpId()%>"/>
                               <%=grpData.getGrpName()%><br>
                      <% }} else {%>
                        &nbsp;
                        <%}%>

                      </td>
              </tr>

          
          </table>
       </td>
    </tr>
    <% } else { %>
    <tr>
       <td valign="top"  align="center" bgcolor="#cccccc"> No groups available</td>
    </tr>
    <% } %>
</table>
</html:form>
