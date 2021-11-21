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
<div class="box col-sm-8 col-md-6 col-lg-4 col-xl-3 main-color-bg-s main-color-f">
    <div class="h1 main-color-f">LOGIN</div>
    <br/>
    <c:url value="/login_submit.html" var="login"/>
    <form name="LoginForm" method="post" action='<c:out value="${login}"/>'>
        <div class="">
            <input class="main-color-bg-f" type="text" name="login" value="" placeholder="${text['login.login']}"/>
        </div>
        <div class="">
            <input class="main-color-bg-f" type="password" name="password" placeholder="${text['login.password']}" value=""/>
        </div>
        <div class="text-danger">${errorLoginPassMessage}</div>
        <br/>
        <div class="">
            <input class="btn btn-success" type="submit" value="${text['login.login']}"/>
        </div>
    </form>
    <c:url value="/registration.html" var="registration"/>
    <a class="h5 main-color-f" href="<c:out value="${registration}"/>"><c:out
            value="${text['registration.registration']}"/></a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
