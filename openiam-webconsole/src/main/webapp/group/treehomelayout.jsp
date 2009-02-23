<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<head>
<title>OpenIAM - Opensource Identity and Access Management</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

    <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="../extjs/resources/css/xtheme-gray.css" />
	
	<ink href="diamelleapp.css" rel="stylesheet" type="text/css">

 	<script type="text/javascript" src="../extjs/lib/ext/ext-base.js"></script>
    <script type="text/javascript" src="../extjs/lib/ext-all.js"></script>

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
    <td bgcolor="#F7F7F7"><a href="index.jsp?nav=reset">
    <img src="../images/HEADER.jpg" border="0" width="980"></a></td>
  </tr>
             <tr> 
              <td bgcolor="#F7F7F7" align="left" valign="middle" height="18" > 
			        <tiles:insert attribute='permissions'/>
			  </td>
            </tr>
            <tr> 
              <td bgcolor="#F7F7F7" align="left" valign="middle" height="18" > 
              		<tiles:insert attribute='menubar'/>
			  </td>
            </tr>
</table>

<table width="980" border="0" cellspacing="0" cellpadding="0">
<!--  BANNER ==========================================================================  -->  

  <tr>
  
<!--  LEFT SIDEBAR ==========================================================================  -->  
  <td width="300" valign="top">
	   <table border="0" width="300" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	          <td valign="top">
	          	<tiles:insert attribute='sidetreepanel'/>
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
              <td align="center" valign="top" width="550">
              <tiles:insert attribute='body'/>
              </td>
   		</tr>
   		
   		<tr>
     			<td align="center" valign="top">
     				<tiles:insert attribute='footer'/>
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

