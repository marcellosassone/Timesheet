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
	<h1>Gestione Dipendenti</h1>

	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>CF</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Username</th>
			<th>Password</th>
			<th>Admin</th>
			<th>Modifica</th>
			<th>Cancella</th>
		</tr>
		<c:forEach var="dip" items="${listaDip}">
			<tr>
				<td>${dip.cf}</td>
				<td>${dip.nome}</td>
				<td>${dip.cognome}</td>
				<td>${dip.username}</td>
				<td>${dip.password}</td>
				<td>${dip.admin}</td>
				<td><a href="/Timesheet/updateDip/${dip.cf}">Modifica</a></td>
				<td><a href="/Timesheet/deleteDip/${dip.cf}"
					onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Elimina</a></td>
			</tr>
		</c:forEach>
	</table>

	<form:form method="POST" commandName="formDip"
		action="/Timesheet/inserisciDip">
		<table>
			<tr>
				<td><form:label path="cf">CF</form:label></td>
				<td><form:input path="cf" /></td>
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
				<td><input type="submit" value="Inserisci" /></td>
			</tr>
		</table>
		<br>
		<br>
		<a href="/Timesheet/">Torna al Main</a>
	</form:form>
</body>
</html>