
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 
String policyId = request.getParameter("policyId");
%>

<html:form action="/security/rulesPolicy.do?method=rules">

<table width="500" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="2" class="title">         
          <strong>Account Policy</strong>
      </td>     
</tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		<html:hidden property="policyId" value="<%= policyId %>"/>
 		</td>
  </tr>
    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Failed Authentication Attempts</td>
    	  <td class="tdlight" align="right">
			  <html:text property="FAILED_AUTH_COUNT" size="5"/>
		  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Lock Duration</td>
    	  <td class="tdlight" align="right">
			  <html:text property="LOCK_DURATION" size="5"/>
		  </td>
    </tr>

    <tr>
    	  <td colspan="2">&nbsp;</td>
    </tr>
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
    <tr>
       <td colspan="2" align ="right">
       <html:submit property="submit" value="Save"/>
       </td>
    </tr>  
   
 </table>

</html:form>
