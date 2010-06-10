<!-- a list of all available menus -->

<%@ page language="java" %>
<%@ page import="java.util.*,javax.servlet.http.*,diamelle.security.auth.*,diamelle.ebc.navigator.*,org.apache.struts.validator.DynaValidatorForm"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<style type="text/css">
.error {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt; color: #FF0000; text-decoration: none}
.link {	color: #111111; font-family: Verdana; font-size: 8pt;	text-decoration: none; }
a:hover {	color: #111111; font-family: verdana; font-size: 8pt; font-weight: normal; font-style: normal; 
					line-height: normal; font-variant: normal; text-transform: none; background-color: #C9CDD2;}
</style>

	<SCRIPT LANGUAGE = "Javascript" >
		<!--
		  function openWin(URL,title) {
			  window.name="parentwindow";
  	    		Win = window.open(URL,title,
		       "width=400,height=300,status=yes,resizable=no,top=100,left=200,scrollbars=no"); 
		  }
		  //-->
	</SCRIPT>

<%

	List menuList = (List) request.getAttribute("menuList");
	String menuGroup = (String) request.getAttribute("menuGroup");
	String menu = (String) request.getAttribute("menu");
	DynaValidatorForm menuForm = (DynaValidatorForm) request.getAttribute("menuForm");
	String mode = (String) menuForm.get("mode");
	String parent = (String) menuForm.get("parent");
	String langCd = (String) session.getAttribute("languageCd");
	if (langCd == null) langCd = "";
	
%>

		<table width="100%">
		<html:form action = "/security/menu.do?method=removeMenu" >
		<html:hidden property="parent" value="<%=parent%>"/>		
		<html:hidden property="mode" value="<%=mode%>" /> 
			<tr>
				<td>
					<b>Menus:</b>
					<%
			     NavigatorBar navigationMenuBar = (NavigatorBar) session.getAttribute("navigationMenuBar");
			     NavigatorBarItem item = null;
			      if (navigationMenuBar != null) {
			        List navList = navigationMenuBar.getNavigatorList();
			        int size = navList.size();
			
			        for (int i = 0; i < size; i++) {
			          item = (NavigatorBarItem) navList.get(i);
			          if ( i > 0)
			            out.print(" | ");
			    %>
			 
			        <a href="<%=item.getUrl()%>"><%=item.getMenuName()%></a>
			
			    <%
			        }
			      }
					%>
					
				</td>
			</tr>
			</table>
			
			<table align="center" width="650">
			<tr>
    		<td class="tdheader" align="center" colspan="4">	
					<%if ((menuGroup == null)||(menuGroup.length()==0)) {%>
						<strong>Root Menus</strong>
					<% } else { %>
						<strong>Menus for &nbsp;<%=menuGroup%></strong>
					<%}%>
   			</td>
			</tr>
			<tr class="tdheader">
				<td width="25%">Menu Name</td>
				<td width="30%">Menu Description</td>
				<td colspan="2" width="45%">Menu URL / Action</td>
			</tr>			

			<%	if  ((menuList != null)&&(!menuList.isEmpty())) {
			      int x=0;
						Iterator menuListItr = menuList.iterator();
						while (menuListItr.hasNext()) {
							MenuData menuData = (MenuData) menuListItr.next();
			%>
					
					<%
					     if ((x%2) != 0) {
					       
					   %>
					   <tr class="tddark">
					   <% } else {%>
					   <tr class="tdlight">
					   <%}%>
			      <td width="25%">
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menu" value="<%=menuData.getMenuId()%>"/>
            	<a href="security/menu.do?method=subMenu&menu=<%=menuData.getMenuId()%>" class="link">
            		<%=menuData.getMenuName()%>
            	</a>
            </td>
            <td width="30%"><%=menuData.getMenuDesc()%></td>
           <td width="30%"><%=menuData.getMainUrl()%></td>
            
            <td width="15%">
            	<a href="" target="_blank" onClick="openWin('security/menu.do?method=editMenu&menu=<%=menuData.getMenuId()%>&parent=<%=parent%>','Menu');return false;">Edit</a>
						</td>
					</tr>
					
      	<% x++;
      	}%>
      	
      	     <tr>
		    	  <td colspan="4">&nbsp;</td>
		     </tr>
		 
		     
		     <tr>
		 		   <td colspan="4" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>  
      	
      		<tr>
      	  		<td align="left">
	    				<a href="" target="_blank"  onClick="openWin('menu.do?method=addMenu&menuGroup=<%=menuGroup%>&parent=<%=parent%>','Menu');return false;">New Menu</a>					
  				</td>
      	  		<td align="right">
	    				<a href="" target="_blank"  onClick="openWin('languageSelect.do?method=viewLanguages','Languages');return false;">Language</a> <%=langCd%>					
  				</td>
			  	<td align="right" colspan="2">
			      	<html:submit property="submit" value="Delete" />
			    </td>
			  </tr>
      			 			 
			<%} else {%>
				<tr >
					<td colspan="4">No Menus Found</td>
				</tr>

      	     <tr>
		    	  <td colspan="4">&nbsp;</td>
		     </tr>
		 
		     
		     <tr>
		 		   <td colspan="4" align="center" bgcolor="8397B4" >
		 		    <font></font>
		 		   </td>
		     </tr>  
      	
      		<tr>
      	  		<td align="left" >
	    				<a href="" target="_blank"  onClick="openWin('security/menu.do?method=addMenu&menuGroup=<%=menuGroup%>&parent=<%=parent%>','Menu');return false;">New Menu</a>					
  				</td>
      	  		<td align="right" >
	    				<a href="" target="_blank"  onClick="openWin('security/languageSelect.do?method=viewLanguages','Languages');return false;">Language</a> <%=langCd%>					
  				</td>
		    	  <td colspan="2">&nbsp;</td>
			  </tr>



			<%}%>
			
			
  	</table>
	</html:form>    

			