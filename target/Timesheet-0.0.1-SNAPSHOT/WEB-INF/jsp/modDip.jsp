<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BEGEAR</title>
</head>
<body>
	<h1>Modifica Dipendente: ${formDip.nome} ${formDip.cognome}</h1>

	<form:form method="POST" commandName="formDip"
		action="/Timesheet/finalizzaModDip">
		<table>
			<tr>
				<td><form:label path="cf">CF</form:label></td>
				<td><form:input path="cf" readonly="true"/></td>
			</tr>
			<tr>
				<td><form:label path="nome">Nome</form:label></td>
				<td><form:input path="nome" /></td>
			</tr>
			<tr>
				<td><form:label path="cognome">Cognome</form:label></td>
				<td><form:input path="cognome" /></td>
			</tr>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="admin">Admin</form:label></td>
				<td><form:input path="admin" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Salva" /></td>
			</tr>
		</table>
		<br>
		<br>
		<a href="/Timesheet/">Torna al Main</a>
	</form:form>
</body>
</html>