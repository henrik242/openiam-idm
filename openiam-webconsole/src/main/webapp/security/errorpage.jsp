<%@ page language="java" %>
<%@ page session="true" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<head>
<title>Diamelle Technologies</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<html:base/>
<html:html locale="true">

<body bgcolor="white" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>


    <table width="80%" border="0" cellspacing="0" cellpadding="0">
								<tr>
								  <td class="error">
								    <logic:messagesPresent>        
								         <html:messages id="error" header="errors.header">
								            <li><bean:write name="error"/></li>
								         </html:messages>    
								     </logic:messagesPresent> 
								  </td>
								</tr>
    </table>

</body>
</html:html>
