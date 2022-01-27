<%@page isErrorPage="true" %>
<%@page import="java.io.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> An error occured, thus you were redirected to this page.</h2>

Message : <%= exception.getMessage() %>

<h3> StackTrace : </h3>
<%
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	
	exception.printStackTrace(pw);
	out.println("<pre>");
	out.println(sw);
	out.println("</pre>");
	pw.close();
	sw.close();
%>
</body>
</html>