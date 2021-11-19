<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Registration</title>
</head>
<body>
<c:url value="/registration_submit.html" var="register"/>
<form name="RegistrationForm" method="post" action='<c:out value="${register}"/>'>
    <c:out value="${text['login.login']}"/>:<br/>
    <input type="text" name="login" value=""/>
    <br/><c:out value="${text['login.password']}"/>:<br/>
    <input type="password" name="password" value=""/>
    <br/><c:out value="${text['registration.name']}"/><br/>
    <input type="text" name="name" value=""/>
    <br/>
    <div class="text-danger">${userExist}</div>
    <br/>
    <div class="text-danger">${wrongAction}</div>
    <br/>
    <div class="text-danger">${nullPage}</div>
    <br/>
    <input type="submit" value="<c:out value="${text['registration.registration']}">"/>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
