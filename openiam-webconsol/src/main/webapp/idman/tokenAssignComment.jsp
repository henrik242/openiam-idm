 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<%
	String url = (String)request.getAttribute("url");
%>
<form action ="/assignToken.do?method=rejectUserList" method="post">

<table width="100%" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="2" class="title">         
          <strong>Please enter a reason for rejecting these assignment requests</strong>
        
      </td>

   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
		
		
	<tr>
	  <td>&nbsp;</td>
 	</tr>

 	<tr>
		<td class="tddark" align="right">Comment:</td>
		<td class="tdlight" valign="left">
		<textarea nam="description">
		</textarea>
		</TD>
	</tr>
   <tr>
    	  <td>&nbsp;</td>
    </tr>
 
    <tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

 	<tr>
		<td class="tdlight" valign="left" colspan="2">
		<input type="submit" name="Submit"> <input type="reset">
		</TD>
	</tr> 	
 </table>
 </form>
 
 
