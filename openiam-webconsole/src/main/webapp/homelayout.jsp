<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<head>
<title>OpenIAM - Administration Console</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<% 

	String uri = request.getRequestURI();
	String queryParam = request.getQueryString();
	int indx = uri.indexOf("/ui/");
	int indx2 = -1;
	if (queryParam != null ) {
		indx2 = queryParam.indexOf("/ui/")  ;
	}
	if (indx != -1 || indx2 != -1) {
%>
<!-- EXT Styleshteets -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/shared/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/shared/forms.css"/>

<!-- EXT LIBS -->
<script type="text/javascript" src="<%=request.getContextPath()%>/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext-all.js"></script>

<%	}  %>

<!-- OpenIAM Legacy style sheets -->
<link href="<%=request.getContextPath()%>/diamelleapp.css" rel="stylesheet" type="text/css">


<!-- ENDLIBS -->


</head>

<html:base/>


<body bgcolor="white" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<% 
if (indx != -1 || indx2 != -1) {
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/shared/common.js"></script>

<%	}  %>


<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<table width="980" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <img src="images/openiamlog.jpg">
    </td>
  </tr>
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
</table>

<table width="980" border="0" cellspacing="0" cellpadding="0">
<!--  BANNER ==========================================================================  -->  

  <tr>
  
<!--  LEFT SIDEBAR ==========================================================================  -->  
  <td width="140" valign="top">
	   <table border="0" width="140" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	          <td valign="top">
	          	<tiles:insert attribute='category'/>
	          </td>
          </tr>
       </table>
	   <table border="0" width="140" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	          <td valign="top">
	          	<tiles:insert attribute='sidemenu'/>
	          </td>
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
			   <td align="left" valign="top">
			   	<tiles:insert attribute='navbar'/>
			   </td>
			 </tr>
			 
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

