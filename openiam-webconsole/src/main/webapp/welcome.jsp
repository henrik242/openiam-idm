<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.*,org.openiam.idm.srvc.auth.dto.Subject"  %>
 
 <% System.out.println("in welcome.jsp"); %>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="tdlight">
    	<td>Welcome ${subject.principal}</td>
    </tr>

    </table>

