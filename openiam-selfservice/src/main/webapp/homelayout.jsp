<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*, org.openiam.selfsrvc.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


 <%
       	String userId = (String)session.getAttribute("userId");
    		String bodypage = request.getParameter("bodyjsp");
				if (bodypage == null) {
  				bodypage = (String)request.getAttribute("bodyjsp");
  			}
  			
  			  String login = (String)session.getAttribute("login");

 %>
         
<%
//AppConfiguration appConfig = (AppConfiguration)session.getAttribute("appConfig");
// put the configuration object in session
String url = (String)session.getAttribute("logoUrl");
String title = (String)session.getAttribute("title");
String welcomePageUrl = (String)session.getAttribute("welcomePageUrl");

if (url == null) {
	url = (String)request.getAttribute("logoUrl");
	title = (String)request.getAttribute("title");	
}


%>


<head>
<title><%=title%></title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%= request.getContextPath() %>/diamelleapp.css" rel="stylesheet" type="text/css">

</head>

<html:base/>

<body bgcolor="white" leftMargin="0" topMargin="0" marginheight="0" marginwidth="0">


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<table width="980" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <% if (userId != null) { %>
    <a href="<%= welcomePageUrl %>"><img src="<%=url%>" /> </a>
    <% }else { %>
    <a href="<%=request.getContextPath()%>/index.do"><img src="<%=url%>" /> </a>
    <% } %>
    </td>
  </tr>

</table>

<table width="1000" border="0">
<!--  BANNER ==========================================================================  -->  

  <tr>
  
<!--  LEFT SIDEBAR ==========================================================================  -->  
   <td width="170" valign="top">
	   <table border="0" width="170" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	          <td valign="top">
	          	<tiles:insert attribute='sidemenu'/>
	          </td>
          </tr>
       </table>
    </td>
<!-- start MID SECTION ===================================================================== -->
	
    <td valign="top"> 
	  <table border="0">

	<!-- Row 1: start Header ====================================================================== -->	
		
	
	<!-- Row 2: start body - need to split into 3 rows ============================================ -->			
       <tr> 
         <td valign="top">		  
		   <table width="100%" border="0">
			 
			 <tr>
			   <td align="left" valign="top"><tiles:insert attribute='navbar'/></td>
			 </tr>
			 
    			 
   			<tr>
   			<td valign="top" width="620">
   			<table border="0" width="620">
   			<tr>
              <td align="center" valign="top"  >
              <tiles:insert attribute='body'/>
              </td>
              <%
            			if (userId != null) {
 
              %>
              <td align="center" valign="top" width="180">
			        		<tiles:insert attribute='menubar'/>
			         </td>
						  <%
						  	}
						  %>      

  
            </tr>
        </table>
      </td>
              <%
            			if (userId == null) {
 
              %>
		  <td width="180" valign="top">

			   <table border="0" width="180" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			          <td valign="top">
			          	<tiles:insert attribute='rightsidemenu'/>
			          </td>
		          </tr>
		     </table>

		  </td>
						  <%
						  	}
						  %> 
	   </tr>
   		
   		<tr>
     			<td align="center" valign="top"><tiles:insert attribute='footer'/></td>
   			 </tr>
			</table>  		
	     </td>
	    </tr>
      </table>
	</td>
  </tr>	  
</table>

</body>

