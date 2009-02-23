 
<%@ page language="java"%>

<%@ page import="java.util.*,javax.servlet.http.*,org.apache.struts.validator.*,diamelle.ebc.user.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<SCRIPT LANGUAGE="JavaScript"> 
<!-- Begin 

function loadParent(url, closeSelf){
	self.opener.location = url;
	if(closeSelf) self.close();
}
function checkState(url, closeSelf) {
	if (userNotesForm.savedStatus.value == '1') {
		loadParent(url,closeSelf);
	}
}

//  End --> 

</script> 
<%
	List operationList = (List)session.getAttribute("operationList");
	pageContext.setAttribute("operationList",operationList);
	String savedStatus = (String)request.getAttribute("saved");
	
%>
<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<html:html>
<body onload="checkState('idman/user.do?method=history',true);" >

<html:form action = "/userNotes.do?method=saveNote" >
<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="3" class="title">         
          <strong>Enter Comments</strong>
         <html:hidden property="personId" />
         <input type="hidden" name="savedStatus" value="<%=savedStatus%>">
         
      </td>
      <td colspan="1" class="text" align="right">         
          <font size="1" color="red">*</font> Required         
      </td>
   </tr>
   
   <tr>
 		<td colspan="4" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>
 	<tr>
		<td class="tddark" align="right" colspan="2">Note Type:</td>
		<td class="tdlight" valign="left" colspan="2">
		 <html:select property="noteType" >
        	<html:options collection="operationList" property="value" labelProperty="label"/>
        </html:select>	
		</TD>
	</tr>
 	<tr>
		<td class="tddark" align="right" colspan="2">Comment:</td>
		<td class="tdlight" valign="left" colspan="2">
			<html:textarea property="description" cols="60" rows="4"/>
		</TD>
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
		<td class="tdlight" valign="left" colspan="4">
			<html:submit/> <html:reset/>
		</TD>
	</tr> 	
 </table>
 </html:form>
 </body>
 </html:html>
 
