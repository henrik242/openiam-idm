
<!-- category -->

<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, org.openiam.idm.srvc.cat.dto.Category,org.openiam.idm.srvc.menu.dto.Menu;" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<table width="140">
    
 
<%
  String nextAction = (String)session.getAttribute("nextAction");
  
  String oper = "?";
  if (nextAction != null) {
    if (nextAction.indexOf(oper) >= 0){
      oper = "&";
    } 
  }


  Collection categoryList = (Collection) session.getAttribute("categories");
  int size = 0;
  if (categoryList != null && !categoryList.isEmpty() ) {
    size = categoryList.size();
    Iterator it = categoryList.iterator();
    while (it.hasNext()) {
      Category cv = (Category)it.next();
%>
  <TR>
    <TD bgcolor="#F7F7F7" class="link">

      <font face="Verdana, Arial, Helvetica, sans-serif" size="1">&nbsp;
        <a href="<%=nextAction + oper%>categoryId=<%=cv.getCategoryId()%>&showList=<%=cv.getShowList()%>">
        <%=cv.getCategoryName()%></a>
      </font>

    </TD>
  </TR>
	  
<%
    }
  }
  if (size > 0) {
%>

  <TR>
    <TD>
      <img src="images/lowernotch.jpg" height="10" width="140" />
    </TD>
  </TR>
<% 
	}
%>
</table>
<br>