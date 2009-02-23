<%@ page import="javax.naming.*,diamelle.util.Log" %>
<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>



<body bgcolor="white">

<br>
<br>
<br> 

<html:form action="/login">
<table border="0" width="400" align="center">

  <tr>
    <td class="title">
        Login
    </td>
    <td class="text" align="right">         
        <font size="1" color="red">*</font> Required       
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

  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->
    <html:hidden property="serviceId" value="IDM" />
  
  <tr>
    <td class="tddark" align="right">
       Enter Login Id:<font color=red>*</font>
    </td>
    <td class="tdlight" >
      <html:text property="login" size="30" maxlength="30"/>
    </td>
  </tr>
  <tr>
    <td class="tddark" align="right">    
       Enter Password:<font color=red>*</font>
    </td>
    <td class="tdlight">
       <html:password property="password" size="30" maxlength="30" redisplay="false"/>
    </td>
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
    <td colspan="2" align="right">
       	<html:submit property="submit" value="login"/>
          &nbsp;<html:reset/>
    </td>
  </tr>

</table>

</html:form>


