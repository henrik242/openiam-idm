
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<% 
String policyId = request.getParameter("policyId");
%>

<html:form action="/security/rulesPolicy.do?method=rules">

<table width="700" border="0" cellspacing="2" cellpadding="1" align="center">
   <tr>
      <td colspan="5" class="title">         
          <strong>Audit Policy</strong>
      </td>     
</tr>
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
  		<html:hidden property="policyId" value="<%= policyId %>"/>
 		</td>
  </tr>
    <tr>
    	  <td colspan="5">&nbsp;</td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Authentication</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Authenticate</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/authenticateService">
    	  </td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="LOGON_EVENT"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">User Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Create</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/userDataServiceService">
    	  </td>
    	  <td class="tdlight" align="right">
    	  <input type="checkbox" />
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">User Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Update</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/userDataService">
    	  </td>
    	  <td class="tdlight" align="right">
				<input type="checkbox" />
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">User Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Delete</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/userDataService">
    	  </td>
    	  <td class="tdlight" align="right">
				<input type="checkbox" />
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Group Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Create</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/groupDataService">
    	  </td>
    	  <td class="tdlight" align="right">
				<input type="checkbox" />
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Group Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Update</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/groupDataService">
    	  </td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="PWD_CHANGE"/>
    	  </td>
    </tr>
     <tr>
    	  <td class="tddarknormal" align="right">Group Manager</td>
    	  <td class="tdlight" align="right">Object Type:
    	  	<select>
    	  		<option></option>
    	  		<option selected="selected">Service</option>
    	  		<option>Java Object</option>
    	  		<option>URL</option>
    	  	</select>
    	  </td>
    	  <td class="tdlight" align="right">Event:Delete</td>
    	  <td class="tdlight" align="right">URL:<input type="text" size="30" value="/ws/groupDataService">
    	  </td>
    	  <td class="tdlight" align="right">
				<html:checkbox property="OBJ_ACCESS_MANAGEMENT"/>
    	  </td>
    </tr>                


    <tr>
    	  <td colspan="5">&nbsp;</td>
    </tr>
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr>
    <tr>
       <td colspan="5" align ="right">
       <html:submit property="submit" value="Save"/>
       </td>
    </tr>  

    
 </table>

</html:form>