<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



        
<div>        

<table width="100%">
	<tr>
      <td colspan="2" class="title">         
          <strong>Delegated Administration</strong>
      </td>
   </tr>   
		   <tr>
		 		<td colspan="2" align="center" bgcolor="8397B4" >
		 		  <font></font>
		 		</td>
		  </tr> 
          <tr>
			  <td class="tddark">Select User</td>
              <td class="tdlightnormal"><input type="text" size="30"><a href="">Select User</a></td>
          </tr>
          <tr>
              <td class="tddark" >Role</td>
			  <td class="tdlightnormal">
			  		<select>
			  			<option>Security Admin</option>
			  			<option>User Admin</option>
		            </select>
			  </td>
		  </tr>
		  
          <tr>
			  <td class="tddark">Group</td>
              <td class="tdlightnormal">
              		<select>
			  			<option>End User Group</option>
			  			<option>Admin Group</option>
		            </select>
		            </td>
          </tr>
          <tr>
			  <td class="tddark">Organization</td>
              <td class="tdlightnormal">
              		<select multiple="multiple" >
			  			<option>OpenIAM</option>
			  			<option>Company 1</option>
			  			<option>Company 2</option>

		            </select>
		            </td>
          </tr>
          <tr>
			  <td class="tddark">Department</td>
              <td class="tdlightnormal">
              		<select multiple="multiple" >
			  			<option>Dept 1</option>
			  			<option>Dept 2</option>
			  			<option>Dept 3</option>

		            </select>
		            </td>
          </tr>
          <tr>
			  <td class="tddark">Division</td>
              <td class="tdlightnormal">
              		<select multiple="multiple" >
			  			<option>Division 1</option>
			  			<option>Division 2</option>
			  			<option>Division 3</option>

		            </select>
		            </td>
          </tr>
                    
  
        

         <tr>
    	  <td colspan="2">&nbsp;</td>
    	</tr>
    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
              <td colspan="2" align="right">

              <input type="submit" name="btn" value="Save"> </td>
          </tr>
</table>

</div>