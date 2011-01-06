<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="en" xml:lang="en">

<head>
<title>OpenIAM Identity Manager 2.1.0 - Administration Console</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<% 
	String uri = request.getRequestURI();
	String queryParam = request.getQueryString();
	String login = (String)session.getAttribute("login");
%>

<!-- Ajax libraries - Dojo and Dijit -->

<!-- OpenIAM Legacy style sheets -->
<!--  link href="<%=request.getContextPath()%>/diamelleapp.css" rel="stylesheet" type="text/css" -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/screen.css">
 


<!-- ENDLIBS -->


</head>

<html:base/>


<body bgcolor="white" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<table width="980" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <img src="images/openiamlogo.jpg"  width="150" height="50">
    </td>
  </tr>
  <% if (login != null && !login.isEmpty()) { %>
             <tr> 
              <td align="left" valign="middle" height="18" > 
			        <tiles:insert attribute='permissions'/>
			  </td>
            </tr>
            <tr> 
              <td align="left" valign="middle" height="18" > 
              		<tiles:insert attribute='menubar'/>
			  		</td>
            </tr>
   <% } %>
</table>

<table width="980" border="0" cellspacing="0" cellpadding="0">
<!--  BANNER ==========================================================================  -->  

  <tr>
  
<!--  LEFT SIDEBAR ==========================================================================  -->  
  <td width="140" valign="top">
	   <table border="0" width="140" cellspacing="0" cellpadding="0">
	      <tr>
	          <td valign="top">
	          	<tiles:insert attribute='category'/>
	          </td>
          </tr>
       </table>
	   <table border="0" width="140"  cellspacing="0" cellpadding="0">
	      <tr>
	      		<td width="10"></td
	          <td valign="top"><tiles:insert attribute='sidemenu'/></td>
          </tr>
       </table>
    </td>

<!-- start MID SECTION ===================================================================== -->
	
    <td valign="top"> 
	  <table border="0" id="body_table">

	<!-- Row 1: start Header ====================================================================== -->	
		
	
	<!-- Row 2: start body - need to split into 3 rows ============================================ -->			
       <tr> 
         <td valign="top">		  
		    <table width="100%" border="0">
			
			 
			 <tr>
			    <td>
	       			<table>
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
			    </td>
			 </tr>
   			 
	   		<tr>
	              <td align="center" valign="top" width="800" id="body_cell">
	              	<tiles:insert attribute='body'/>
	              </td>
	   		</tr>
   		
   			<tr>
     			<td align="center" valign="top">
     				<!--
     				OpenIAM LLC - Copyright 2008
     				-->
     			</td>
   			 </tr>
			</table>  		
	     </td>
	   </tr>
      </table>
	</td>
  </tr>	  
</table>

</body>
</html>
