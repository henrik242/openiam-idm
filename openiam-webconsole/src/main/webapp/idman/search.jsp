<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*,javax.servlet.http.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
	List statusList = (List)session.getAttribute("statusList");
	pageContext.setAttribute("statusList",statusList);
	
	List secondaryStatusList = (List)session.getAttribute("secondaryStatusList");
	pageContext.setAttribute("secondaryStatusList",secondaryStatusList);
	
	//List groupList = (List)session.getAttribute("groupList");
	List groupList = (List)request.getAttribute("groupList");
	pageContext.setAttribute("groupList",groupList);
	List roleList = (List)request.getAttribute("roleList");
	pageContext.setAttribute("roleList",roleList);
	String msg = (String)request.getAttribute("msg");

	List orgList = (List)request.getAttribute("orgList");
	pageContext.setAttribute("orgList",orgList);

	List elementList = (List)request.getAttribute("elementList");
	pageContext.setAttribute("elementList",elementList);
	
%>
<html:html>
		<table  width="900pt">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">User Search</h2>
						</td>
						<td class="tabnav" >
						</td>
						<td></td>
						</tr>
					</table>
			</td>
		</tr>
<% if (msg != null) { %> 
		<tr>
			<td class="msg">

 		  <b><%=msg %></b>

			</td>
		</tr>
<% } %>

		</td>
			
		<tr>
			<td>

<html:form action = "/idman/userSearch.do?action=search">
  <table width="800pt"  class="bodyTable" height="100%"  >


<tr>
	<td>    <fieldset class="userformSearch" >
			<legend>SEARCH</legend>
				<table class="fieldsetTable"  width="100%" >
						  
			   <tr>
			         <td><label for="username" class="attribute">Last Name</label></td>
			         <td  class="userformInput" for="username" class="labelValue">
			             <html:text property="lastName"  size="30" maxlength="30" />		             
			         </td>         
			         <td><label for="username" class="attribute">Organization</label></td>
			         <td  class="userformInput" for="username" class="labelValue">
			              <html:select property="companyName">
			        		<html:options collection="orgList" property="value" labelProperty="label"/>
			        	  </html:select>
			            
			         </td>
			   </tr>
			  <tr>
			         <td><label for="username" class="attribute">E-mail</label></td>
			         <td  class="userformInput" for="username" class="labelValue">
				         	 <html:text property="email"  size="30" maxlength="50" />	
			        </td>
			           
			        <td><label for="username" class="attribute">Phone Nbr</label></td>
			        <td  class="userformInput" for="username" class="labelValue">
			          <html:text property="areaCode" size="3" maxlength="3" />
			          <html:text property="phoneNumber" size="10" maxlength="10" />
			        </td>
			        
			   </tr>
  			  <tr>
			         <td><label for="username" class="attribute">Principal Name</label></td>
			         <td colspan="3"  class="userformInput" for="username" class="labelValue">
				         	 <html:text property="principalName"  size="60" maxlength="1000" />
			        </td>


			   </tr>
			   <tr>
			          <td><label for="username" class="attribute">Role</label></td>
			          <td class="userformInput" for="username" class="labelValue">
			          	  <html:select property="role">
			        		<html:options collection="roleList" property="value" labelProperty="label"/>
			        	  </html:select>
			          </td>
			
			         <td><label for="username" class="attribute">Group</label></td>
			          <td class="userformInput" for="username" class="labelValue">
			              <html:select property="group">
			        		<html:options collection="groupList" property="value" labelProperty="label"/>
			        	  </html:select>
			           </td>      
			
			   </tr>
			   <tr>
			         <td><label for="username" class="attribute">User Status</label></td>
			         <td class="userformInput" for="username" class="labelValue">
			              <html:select property="status">
			        		<html:options collection="statusList" property="value" labelProperty="label"/>
			        	  </html:select>
			         </td>         
			         <td><label for="username" class="attribute">Account Status</label></td>
			         <td class="userformInput" for="username" class="labelValue">
			              <html:select property="secondaryStatus">
			        		<html:options collection="secondaryStatusList" property="value" labelProperty="label"/>
			        	  </html:select>
			         </td>             
			   </tr>
			   <tr>
			         <td><label for="username" class="attribute">Extended Attributes</label></td>
			         <td class="userformInput" for="username" class="labelValue" colspan="3">
			              <html:select property="attributeName">
			        		<html:options collection="elementList" property="value" labelProperty="label"/>
			        	  </html:select>
			        	   <html:text property="attributeValue" size="40" maxlength="40" />
			         </td>                    
			   </tr>
			   <tr>
			          <td colspan="4" align ="right"  >
			              <input type="submit" name="Search" value="Search">
			          </td>
			   </tr>
			    </table>
	</fieldset>
	</td>
								
  </tr>
</table>
			</html:form>
		</td>
	</tr>
</table>
</html:html>