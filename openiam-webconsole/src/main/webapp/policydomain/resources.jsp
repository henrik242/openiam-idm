<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree" %>


<table cellspacing="0" cellpadding="5" style="border: 1 solid black;" width="600">
<tr>
	<td valign="top">

<table cellspacing="0" cellpadding="0" border="0" width="250">
	<tree:tree  tree="tree.model" node="tree.node" includeRootNode="true">
	<tr>
		<td>
			<table cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td>
					<tree:nodeIndent node="tree.node" indentationType="type">
						<tree:nodeIndentVerticalLine indentationType="type" >
						<img src="images/verticalLine.gif">
					</tree:nodeIndentVerticalLine>
					<tree:nodeIndentBlankSpace indentationType="type" >
						<img src="images/blankSpace.gif">
					</tree:nodeIndentBlankSpace>
					</tree:nodeIndent>
				</td>
				<td>
					<tree:nodeMatch node="tree.node" hasChildren="true" expanded="false" isLastChild="false">
						<a href="resourceTreeView.do?method=view&tab=2&expand=<tree:nodeId node="tree.node"/>">
						<img src="images/collapsedMidNode.gif" border="0"></a>
					</tree:nodeMatch>
					<tree:nodeMatch node="tree.node" hasChildren="true" expanded="true" isLastChild="false">
						<a href="resourceTreeView.do?method=view&tab=2&collapse=<tree:nodeId node="tree.node"/>">
						<img src="images/expandedMidNode.gif" border="0"></a>
					</tree:nodeMatch>
					<tree:nodeMatch node="tree.node" hasChildren="true" expanded="false" isLastChild="true">
						<a href="resourceTreeView.do?method=view&tab=2&expand=<tree:nodeId node="tree.node"/>">
						<img src="images/collapsedLastNode.gif" border="0"></a>
					</tree:nodeMatch>
					<tree:nodeMatch node="tree.node" hasChildren="true" expanded="true" isLastChild="true">
						<a href="resourceTreeView.do?method=view&tab=2&collapse=<tree:nodeId node="tree.node"/>">
						<img src="images/expandedLastNode.gif" border="0"></a>
					</tree:nodeMatch>
					<tree:nodeMatch node="tree.node" hasChildren="false" isLastChild="false">
						<img src="images/noChildrenMidNode.gif" border="0">
					</tree:nodeMatch>
					<tree:nodeMatch node="tree.node" hasChildren="false" isLastChild="true">
						<img src="images/noChildrenLastNode.gif" border="0">
					</tree:nodeMatch>
					
					<tree:nodeMatch node="tree.node" selected="true"><td><input type="checkbox" name="selectOnly" value="<tree:nodeId node="tree.node"/>" checked/></td></tree:nodeMatch>
            		<tree:nodeMatch node="tree.node" selected="false"><td><input type="checkbox" name="selectOnly" value="<tree:nodeId node="tree.node"/>"/></td></tree:nodeMatch>
					
					
				</td>
				<td><tree:nodeName node="tree.node"/></td>
			</tr>
			</table>
		</td>
	</tr>
</tree:tree>
	<tr>
		<td><br>
			<html:form action = "/resourceTreeView.do?method=detail">
			<html:submit property="submit" value="Add"/> <html:submit property="submit" value="Update"/> <html:submit property="submit" value="Delete"/>
			</html:form>
		</td>
	</tr>
</table>

</td>
<td width="2px"><img src="images/blankSpace.gif"></td>
<td valign="top" style="border-left: 2 solid black;">

	<% 
		String detail = (String)request.getAttribute("detail");
		if (detail != null && detail.equalsIgnoreCase("new")) {
	%>
		<jsp:include page="resource.jsp" flush="true" />
	<%
		}
	 %>



</td>
</tr>
</table>