<%@ page contentType="text/html;" language="java" import="java.util.*"  %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>     
<%
List resultList = (List)request.getAttribute("searchResult");
System.out.println("---- in searchlog.jsp -----");

%>   
   
<html:form action="/security/auditlog.do" >

	  <table width="90%" border="0" cellspacing="2" cellpadding="1" align="center">
	   <tr>
	        <td class="title" colspan="6">Search Log</td>
	   </tr>
	   <tr>
	 		<td colspan="6" align="center" bgcolor="8397B4" >
	 		  <font></font>
	 		</td>
	  </tr>
		<tr>
		  <td>&nbsp;</td>
	  </tr>
  <tr  > 
    <td class="tddark"> <div align="right">User Id:</div></td>
    <td class="tdlight">  
    	<html:text property = "personId" size="20" maxlength="20" /> 
    </td>
    <td class="tddark"><div align="right">Service:</div></td>
    <td class="tdlight"><select name="service">
    	<option></option>
    	<option>IDM</option>
      </select> </td>
  </tr>
  <tr  > 
    <td class="tddark"><div align="right">Start Date (MM-dd-yyyy):</div></td>
    <td class="tdlight"> <html:text property = "startDate" size="25" maxlength="30" /></td> 
    <td class="tddark"><div align="right">End Date (MM-dd-yyyy):</div></td>
    <td class="tdlight"><html:text property = "endDate" size="25" maxlength="30" /> </td>
  </tr>
  <tr> 
    <td class="tddark"><div align="right">IP Address:</div></td>
    <td class="tdlight"><html:text property = "IPAddress" size="25" maxlength="30" /> 
    </td>
    <td align="right">&nbsp;</td>
    <td> <html:hidden property="flag" value="true" /></td>
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
    <td colspan="4" align="right" > 
    	<html:submit property="Submit"/>
        &nbsp; 
        <html:reset property="Reset"/>
	</td>
  </tr>
</table>
</html:form>
<% if (resultList != null && resultList.size() > 0 ) { %>
<jsp:include page="/log/logsearchresult.jsp" flush="true" />
<% } %>
