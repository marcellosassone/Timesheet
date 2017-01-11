<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="/Timesheet/resources/css/stileAll.css">

<title>BEGEAR</title>
</head>
<body>
	<h1>Documenti di ${dipendente.nome} ${dipendente.cognome}</h1>
	<form method="POST"
		action="${initParam['TimesheetRoot']}/user/filtraDocumenti">
		<table>
			<tr>
				<td><input type="text" name="ricerca" placeholder="Cerca..." /></td>
				<td><input type="submit" value="Cerca" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="/Timesheet/user/finalizzaModificaDoc">
		<table border="2" width="70%" cellpadding="2">
			<tr>
				<th>ID</th>
				<th><a
					href="${initParam['TimesheetRoot']}/user/sortDoc/date/${dateSort eq null or dateSort eq true ? 'ASC' : 'DESC'}">Data</a></th>
				<th><a
					href="${initParam['TimesheetRoot']}/user/sortDoc/name/${nameSort eq null or nameSort eq true ? 'ASC' : 'DESC'}">Nome</a></th>
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
					<td><c:choose>
							<c:when test="${editable eq true && id_editable eq doc.id}">
								<input type=text name=descr />
							</c:when>
							<c:otherwise>
								<c:out value="${doc.descrizione}" />
							</c:otherwise>
						</c:choose></td>
					<td><a href="/Timesheet/user/downloadDoc/${doc.id}">Download</a></td>
					<td><a href="/Timesheet/user/updateDoc/${doc.id}">Modifica</a></td>
					<td><a href="/Timesheet/user/deleteDoc/${doc.id}"
						onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Elimina</a></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="id_editabile" value="${id_editable}" /> <input
			type="submit" value="Salva Modifiche" />
	</form>
		<br>
		<a href="/Timesheet/">Torna al Main</a>
</body>
</html>