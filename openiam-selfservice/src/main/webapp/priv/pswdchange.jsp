<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<% 
	String msg = (String)request.getAttribute("msg");
%>

<form:form commandName="pswdChangeCmd">
<table border="0" width="600pt" align="center">
	<tr>
	  <td>
 

	  </td>
	</tr>
  <tr>
    <td class="title">Change Password</td>
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

	<tr>
	  <td class="error" colspan="2">
	  <% if (msg != null ) { %>
		<%=msg %>
	<% }  %>
	</td>
 	</tr>
 	
  
    <!-- ENTER THE SERVICE FOR THIS LOGIN HERE AS A HIDDEN FIELD -->

	<form:hidden path="domainId"    />


  <tr>
    <td class="tddarknormal" align="right">
       Login
    </td>
    <td class="tdlight">
    	<form:input path="principal" size="30"  maxlength="30" readonly="true"   />
    </td>
  </tr>


  <tr>
    <td class="tddarknormal" align="right">    
       New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
    	<form:password path="password" size="30"  maxlength="30"   /><br>
    	 <form:errors path="password" cssClass="error" />
       
    </td>
  </tr>
  <tr>
    <td class="tddarknormal" align="right">    
       Confirm New Password<font color=red>*</font>
    </td>
    <td class="tdlight">
       <form:password path="confPassword" size="30"  maxlength="30"   /><br>
        <form:errors path="confPassword" cssClass="error" />
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
  	<td>

  	</td>
    <td align="right">
    		<input type="submit" value="Save" name="btnSave"> <input type="submit" name="_cancel" value="Cancel" />
    </td>
  </tr>

</table>
</form:form> 




