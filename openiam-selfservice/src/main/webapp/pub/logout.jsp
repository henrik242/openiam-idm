<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<body bgcolor="white">
<table border="0" width="40%" align="center">
   <tr>
    <td>You have successfully logged out</td>
   </tr>
   
    <tr>
    	<td><a href="<%=request.getContextPath() %>/index.do">Click here to login again.</a></td>
    </tr> 
</table>
</body>

