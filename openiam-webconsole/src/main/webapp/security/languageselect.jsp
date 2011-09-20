
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<link href="diamelleapp.css" rel="stylesheet" type="text/css">

<html:html>
<HEAD>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE></TITLE>
</HEAD>

<BODY onunload='window.opener.location.href = window.opener.location.href'>
<%
 	Map languages=(Map) request.getAttribute("languages");
%>

<html:form action="/languageSelect.do?method=setLanguage">
	<CENTER>
	<TABLE width="80%" border="0" cellspacing="1" cellpadding="1">

		<TR>
			<TD class="error" align="left" colspan="2">
			<logic:messagesPresent>
				<bean:message key="errors.header" />
				<html:messages id="error">
					<LI><bean:write name="error" /></LI>
				</html:messages>
			</logic:messagesPresent></TD>
		</TR>


		<TR>
   		<td class="tdheader" align="center" colspan="2">	
			Select Language for which
			Menus are displayed</TD>
		</TR>

		<%
		if ((languages != null)&&(languages.size() > 0)) {
		 Set codesSet = languages.keySet(); 	
	     Iterator codesIter = codesSet.iterator();
	     String languageCd;
	     while (codesIter.hasNext()) { 
	    	  languageCd = (String) codesIter.next();
%>
		<TR>
			<TD bgcolor="#eeeeee" colspan="2"><html:radio property="languageCd"
				value="<%=languageCd%>">
				<%=languages.get(languageCd)%>
			</html:radio></TD>
		</TR>
		<% 			} 
	   } else {
%>
		<TR bgcolor="#eeeeee">
			<TD align="center" colspan="2">No languages entered in the system.</TD>
		</TR>
		<%	   
	   }
%>
      	     <tr>
		    	  <td colspan="2">&nbsp;</td>
		     </tr>
		 
		     
		     <tr>
		 		   <td colspan="2" align="center" bgcolor="8397B4" >
		 		    
		 		   </td>
		     </tr>  

      	     <tr>
		    	  <td colspan="2">&nbsp;</td>
		     </tr>
		<TR>
			<TD align="center" colspan="2"><html:submit property="submit"
				value="submit" onclick='window.close()'></html:submit></TD>
		</TR>
	</TABLE>
	</CENTER>

</html:form>

</BODY>
</html:html>
