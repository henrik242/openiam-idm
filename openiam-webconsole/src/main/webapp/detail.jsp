<!-- permissions -->


<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, diamelle.common.cat.*,diamelle.ebc.navigator.*" %>
 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html locale="true">
<body>

<table align="center" width="100%">
<br>
<%
  List menuList = (List) session.getAttribute("permissions");

  if (menuList != null && !menuList.isEmpty() ) {

  
  int size = menuList.size();
  int counter = 0;
  Iterator it = menuList.iterator();
%>
 
  
<%

    while (it.hasNext()) {
      MenuData md = (MenuData)it.next();
      String url = md.getMainUrl();
      String desc = md.getMenuDesc();
      System.out.println("desc is " +desc);
      /*if (url.indexOf("?") == -1) {
           url = url + "?" + queryString;
       } else {    
           url = url + "&"  + queryString;
       }*/  

       //System.out.println("Permission for: " + md.getMenuName() + " url: " + url);
      
%>

          <!--a href="<%=url%>"><%=md.getMenuName()%></a-->

 <tr>
      
      <td>
        <table  valign="top" width="85%" cellspacing="0" cellpadding="0">
          <tr>
            <td width="70%" bgcolor="CCCCCC">
              <font face="Verdana, Arial, Helvetica, sans-serif" size="2">
                <b><%=md.getMenuName()%></b>
              </font>
            </td>
            <td width="30%">&nbsp;</td>
          </tr>
          <tr>
             <td colspan="2">
                <table valign="top" width="70%"  border="1" bordercolor="CCCCCC" cellspacing="0" cellpadding="0">
             <td>
                <font face="Verdana, Arial, Helvetica, sans-serif" size="2">
                 <%=md.getMenuDesc()%>
                </font>
             </td>
                </table>
             </td>
          </tr>
          </table>
          <br>
      </td>   

    <%  }%>    
    </tr>
  
    <%}
  
%>
    

</table>

</body>
</html:html>


