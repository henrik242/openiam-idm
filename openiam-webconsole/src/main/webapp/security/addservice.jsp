<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>




<%
String serviceId = (String)request.getAttribute("serviceid");
String serviceName = (String)request.getAttribute("servicename");
String mode = (String)request.getAttribute("mode");
%>
<html:form action="/serviceform.do">
        <input type="hidden" name="mode" value="<%=mode%>">

<table border="0" cellspacing=2 cellpadding=1  width="400" >
         <tr >
              <td  colspan="2" class="error"><html:errors/></td>
         </tr>
  <tr>
           <td align="center" bgcolor="cccccc" colspan="2" >
             <font face="verdana" size="2"><b>Add New Service</b> <br>
                (Fields marked with * are required)</font>
             </td>
           </tr>
         <tr bgcolor="#eeeeee">
              <td class = "link2" >Service Id<font color="red">*</font></td>
              <td><html:text property="serviceId" maxlength="20" value="<%=serviceId%>" /></td>
         </tr>
         <tr bgcolor="#eeeeee">
                <td class = "link2">Service Name<font color="red">*</font></td>
                <td><html:text property="serviceName" maxlength="20" value="<%=serviceName%>" /></td>
        </tr>
        <tr bgcolor="#eeeeee">
               <td align="center" colspan="2">
                    <html:submit property="submit" value="Save" />
                </td>
        </tr>

</table>
</html:form>


