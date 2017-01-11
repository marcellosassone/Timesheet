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
	<h1>Gestione Documenti</h1>

	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>ID</th>
			<th>Data</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Download</th>
			<th>Modifica</th>
			<th>Cancella</th>
		</tr>
		<c:forEach var="doc" items="${listaDoc}">
			<tr>
				<td>${doc.id}</td>
				<td>${doc.data}</td>
				<td>${doc.nome}</td>
				<td>${doc.descrizione}</td>
				<td><a href="/Timesheet/downloadDoc/${doc.id}">Download</a></td>
				<td><a href="/Timesheet/updateDoc/${doc.id}">Modifica</a></td>
				<td><a href="/Timesheet/deleteDoc/${doc.id}"
					onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Elimina</a></td>
			</tr>
		</c:forEach>
	</table>

	<form:form method="POST" commandName="formDoc"
		action="/Timesheet/inserisciDoc">
		<table>
			<tr>
				<td><form:label path="nome">Nome</form:label></td>
				<td><form:input path="nome" /></td>
			</tr>
			<tr>
				<td><form:label path="descrizione">Descrizione</form:label></td>
				<td><form:input path="descrizione" /></td>
			</tr>
			<tr>
				<td><input type="file" name="file"/></td>
				<td><input type="submit" value="Inserisci" /></td>
			</tr>
		</table>
		<br>
		<br>
		<a href="/Timesheet/">Torna al Main</a>
	</form:form>
</body>
</html>