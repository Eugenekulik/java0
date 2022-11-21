<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>Login</title>
</head>
<body style="background: url('<c:url value="/img/fon.jpg"/>')">
<div class="box col-sm-8 col-md-6 col-lg-4 col-xl-3 main-color-bg-s violet">
    <div class="h1 violet">LOGIN</div>
    <br/>
    <c:url value="/login.html" var="login"/>
    <form name="LoginForm" method="post" action='<c:out value="${login}"/>'>
        <div class="">
            <input required minlength="6" maxlength="20" pattern="([A-Za-z0-9_.-]*)" class="main-color-bg-f" type="text" name="login" value="" placeholder="${text['user.login']}"/>
        </div>
        <div class="">
            <input required minlength="8" maxlength="50" pattern="([A-Za-z0-9_.-]*)" class="main-color-bg-f" type="password" name="password" placeholder="${text['user.password']}" value=""/>
        </div>
        <div class="text-danger">${errorLoginPassMessage}</div>
        <br/>
        <div class="">
            <input class="btn btn-success" type="submit" value="${text['user.login']}"/>
        </div>
    </form>
    <c:url value="/registration.html" var="registration"/>
    <a class="h5 violet" href="<c:out value="${registration}"/>"><c:out
            value="${text['registration.registration']}"/></a>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
