<!-- dispatcher -->
<%@ page language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.*, diamelle.common.cat.*,diamelle.ebc.navigator.*" %>

<%

  String userId = (String)session.getAttribute("userId");
  String token = (String)session.getAttribute("token");

  String queryString = null;

  if (userId != null) {
    if (token != null) {
      queryString = "userId=" + userId + "&token=" + token;
    } else {
      queryString = "userId=" + userId;
    }
  }
 
  MenuData md = (MenuData) request.getAttribute("menu");
  String menuUrl = md.getMainUrl();
  String redirectUrl = null;

  if (queryString != null) {
    if (menuUrl.indexOf("?") == -1) {
      redirectUrl = menuUrl + "?" + queryString;
    } else {
      redirectUrl = menuUrl + "&" + queryString;
    }
  } else {
    redirectUrl = menuUrl;
  }
  response.sendRedirect( redirectUrl );
%>
