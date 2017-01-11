<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  
  
  
      <link rel="stylesheet" href="/Timesheet/resources/css/stile.css">

  
</head>

<body>
<h1>BEGEAR Login</h1>
  <div class="main-wrap">
        <div class="login-main">
        <c:out value="${errore}"/>
		<form:form method="POST" commandName="formDip" action="/Timesheet/login">
            <form:input type="text" path="username" class="box1 border1"/>
            <form:input type="password" path="password" class="box1 border2"/>
            <input type="submit" class="send" value="Go">
            <p>BEGEAR Development</p>   
             </form:form> 
        </div>
       
    </div>
  
  
</body>
</html>