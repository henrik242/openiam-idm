
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,diamelle.ebc.navigator.*" %>
<%@ page import="org.openiam.idm.srvc.user.dto.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<table border="0">
	<tr>
		<td><B>Access Management Center</B></td>
	</tr>
	
  <tr>
    <td>&nbsp;&nbsp;&nbsp;New Hire</td>
  </tr>
  <tr>
	<tr>
    <td>&nbsp;&nbsp;&nbsp;New Application</td>
  </tr>
  
  <tr>
    <td>&nbsp;&nbsp;&nbsp;Manage Request</td>
  </tr>

  <tr>
  	<td>&nbsp;&nbsp;&nbsp;Delete Authority</td>
	</tr>
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;Delete Access</td>
	</tr>	

  <tr>
  	<td><br><b>Self-Service Center</b></td>
	</tr>
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;<a href="pub/directory.do?method=view">Directory Lookup</a></td>
	</tr>	
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;Edit Your Profile</td>
	</tr>	
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;Challenge Response</td>
	</tr>	
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;Change Password</td>
	</tr>	
	
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;<a href="unLockUser.do?method=view">Forgot Password?</a></td>
	</tr>	

  <tr>
  	<td>&nbsp;&nbsp;&nbsp;<a href="reportIncident.selfserve">Report Security Incident</a></td>
	</tr>	
  <tr>
  	<td>&nbsp;&nbsp;&nbsp;<a href="contactAdmin.selfserve">Contact Admin</a></td>
	</tr>	
</table>


 


