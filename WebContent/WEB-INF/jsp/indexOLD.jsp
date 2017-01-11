<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${initParam['TimesheetRoot']}/resources/css/stile.css"/>
<title>BEGEAR</title>
</head>
<body>
	<h1>BEGEAR Login</h1>
	<c:out value="${errore}"/>
	<form:form method="POST" commandName="formDip" action="/Timesheet/login">
<table>
    <tr>
        <td><form:label path="username">Username</form:label></td>
        <td><form:input path="username" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td><input type="submit" value="Login"/></td>
    </tr>
</table>  
<br>
<br>
 <a href="/Timesheet/">Torna al Main</a> 
</form:form>
</body>
</html>