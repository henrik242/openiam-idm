<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="javax.jms.*, javax.naming.*" %>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>jmstest</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>

Jms Test</br>

<%

try {
Context ctx = new InitialContext();

System.out.println("Got context..");
TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)ctx.lookup ("java:comp/env/jms/diamelleTopicFactory");
System.out.println("Got connection factory..");
Topic topic = (Topic)ctx.lookup("java:comp/env/jms/diamelleTopic");
System.out.println("Got topic..");
	     
	     TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
	     TopicSession topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
	     TopicPublisher topicPublisher = topicSession.createPublisher(topic);
	     
	     TextMessage msg = topicSession.createTextMessage();
	     msg.setText("Test message received...");
	     
	     topicPublisher.publish(msg);
	     topicPublisher.close();

 
	     
	     }catch(Exception e) {
	     e.printStackTrace();
	     }

 %>
Jms Test2
</body>
</html>
