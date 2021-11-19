<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<div>
    <c:url value="/login_submit.html" var="login"/>
    <form name="LoginForm" method="post" action='<c:out value="${login}"/>'>
        <c:out value="${text['login.login']}"/>:<br/>
        <input type="text" name="login" value=""/>
        <br/><c:out value="${text['login.password']}"/>:<br/>
        <input type="password" name="password" value=""/>
        <br/>
        <div class="text-danger">${errorLoginPassMessage}</div>
        <br/>
        <div class="text-danger">${wrongAction}</div>
        <br/>
        <div class="text-danger">${nullPage}</div>
        <br/>
        <input type="submit" value="${text['login.login']}"/>
    </form>
</div>
<c:url value="/registration.html" var="registration"/>
<a href="<c:out value="${registration}"/>"><c:out value="${text['registration.registration']}"/></a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
