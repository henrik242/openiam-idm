<%@ page contentType="text/html" language="java" import="java.util.*,diamelle.security.log.*" %>


<table border="0" cellpadding=1 cellspacing = 2 width="100%">   

<tr >
    <td class="tdheader" align="left" colspan="4">Audit Policy</td>
</tr>

<tr >
    <td class="tdheader" align="left">Resource</td>
    <td class="tdheader" align="left">Type</td>
    <td class="tdheader" align="left">URL / Name</td>
    <td class="tdheader" align="left">Events</td>
</tr>

   <tr class="tdlight">

      <td align="left">Authentication</td>
      <td align="left"><input type="text" value="Web Service" size="30"/></td>
      <td align="left"><input type="text" value="http://localhost:8080/ws/authenticator" size="30"/></td>
      <td align="left">
      	<table>
      		<tr>
      			<td><input type="checkbox" checked>Access</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0">Create</td>
      			<td>Method:<input type="text" value=""></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0">Update</td>
      			<td>Method:<input type="text" value=""></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0">Delete</td>
      			<td>Method:<input type="text" value=""></td>
      		</tr>
      	</table>
      </td>
 	</tr>
  <tr class="tdlight">

      <td align="left">UserDataService</td>
      <td align="left"><input type="text" value="Web Service" size="30"/></td>
      <td align="left"><input type="text" value="http://localhost:8080/ws/userDataService" size="30"/></td>
      <td align="left">
      	<table>
      		<tr>
      			<td><input type="checkbox">Access</td>
      			<td></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0" checked>Create</td>
      			<td>Method:<input type="text" value="addUser"></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0" checked>Update</td>
      			<td>Method:<input type="text" value="updateUser"></td>
      		</tr>
      		<tr>
      			<td><input type="checkbox" value="0" checked>Delete</td>
      			<td>Method:<input type="text" value="removeUser"></td>
      		</tr>
      	</table>
      </td>
 	</tr>

</table>

