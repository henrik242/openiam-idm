
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

  
  <body>
    <html:form action="/idman/org.do?method=saveOrg" >
    
      <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
		    <tr>
		        <td class="title" colspan="2">Add / Update Organization </td>
		   </tr>
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr>
			<tr>
			  <td>&nbsp;</td>
		  </tr>
        <tr>
          <td class="tddark" align="right">Organization ID:</td>
          <td class="tdlight"><html:text property="orgId" readonly="true" /></td>
        </tr> 
 
        <tr>
          <td class="tddark" align="right">Organization Name:</td>
          <td class="tdlight"><html:text property="orgName" /></td>
        </tr>

        <tr>
          <td class="tddark" align="right">Status</td>
          <td class="tdlight"><html:text property="status" /></td>
        </tr>
        <tr>
          <td class="tddark" align="right">Alias</td>
          <td class="tdlight"><html:text property="alias" /></td>
        </tr>

        <tr>
    	  <td>&nbsp;</td>
   	 	</tr>
	    <tr>
	 		   <td colspan="4" align="center" bgcolor="8397B4" >
	 		    <font></font>
	 		   </td>
	    </tr>
        
        <tr>
          <td colspan="2" align="right"><html:submit /> <html:reset/> </td>
        </tr>
      </table>
    </html:form>
  </body>

