<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>

<head>
<title><bean:message key="title"/></title>
  <html:base/>
</head>

<body bgcolor="white" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">

<table cellspacing=0 cellpadding=0 width="100%" >

   <TR>
     <td height="10%" colspan="3" align="left" valign="top"><tiles:insert attribute='header'/></td>
   </TR>
   <TR>
     <td height="4%" colspan="3" align="left" valign="top"><tiles:insert attribute='commonheader'/></td>
   </TR>
   

  
   <tr>
      <td height="4%" colspan="3" align="left" valign="top"><tiles:insert attribute='navbar'/></td>
   </TR>
   <tr>
     <td height="1%" valign="top" colspan="3"></td>
   </tr>
   <TR>
     <td height="80%" width="1" >&nbsp;</td>
     <td height="80%" valign="top" width="15%"><tiles:insert attribute='sidebar'/></td>
     <td height="80%" valign="bottom" width="84%" >
          <tiles:insert attribute='body'/><br>
          <tiles:insert attribute='tab'/>
     </td>
   </TR>
   <TR>
     <td height="10%" colspan="3" align="center" valign="top"><tiles:insert attribute='footer'/></td>
   </TR>

</table>

</body></html>