<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page session="true" %>
<%@page import="fr.inti.formation.entity.Employee"%>
<%@page import="fr.inti.formation.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	Employee e = (Employee) session.getAttribute("Employee");
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		%>
		
		<h1> Hello Employee : <%=e %>}</h1>
		<h1> Date de connexion : <%= formatter.format(date) %> </h1>


</body>
</html>