<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

		<table  width="700pt">
			<tr>
				<td>
					<table width="100%">


						<tr>
							<td class="pageTitle" width="70%">
								<h2 class="contentheading">Connection Test Result</h2>
						</td>
						</tr>
					   <tr>
					 		<td align="center" class="msg">
					 		  <b>${msg}</b>
					 		</td>
					  </tr>  
					   <tr>
					 		<td><a href="connectorDetail.cnt?sysId={sysId}">Click here</a> to return to connection details</td>
					  </tr>  
					</table>
			</td>
		</tr>   
		</table>


 