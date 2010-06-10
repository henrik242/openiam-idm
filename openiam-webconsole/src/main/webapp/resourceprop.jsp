<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<% //String resourceId = request.getParameter("resourceId");%>
		
		<br><br>
		<html:form action = "/security/resource.do?method=saveProperty" >

			<table cellspacing=2 cellpadding=2  width="100%" align="center">

				<html:hidden property="resourceId" />
				<html:hidden property="mode" />

  			<tr bgcolor="#cccccc">
        	<td align="center" class="th" colspan="2">
        		<strong>Property Details</strong>
        	</td>
				</tr>
  		  				
        <tr bgcolor="#eeeeee">
          <td class ="label" align="right">Property Id (auto generated)</td>
          <td><html:text property="resourcePropId" maxlength="20" readonly="true"/></td>           
        </tr>

		<tr bgcolor="#eeeeee">
        	<td class="label" align="right">MetaData Id</td>
          <td><html:text property="metadataId" maxlength="20"/></td>
        </tr>
        

        <tr bgcolor="#eeeeee">
          <td class ="label" align="right">Property Value</td>
          <td><html:text property="propValue" maxlength="20"/></td>           
        </tr>                       
        
        <tr bgcolor="#eeeeee">
	        <td align="center" colspan="2">
	        	<html:submit property="submit" value="Save Property"/>
          </td>
        </tr>

     	</table>
    </html:form> 

