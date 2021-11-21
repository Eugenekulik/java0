<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>Registration</title>
</head>
<body style="background: url('<c:url value="/img/fon.jpg"/>')">
<c:url value="/registration_submit.html" var="register"/>
<div class="box col-sm-8 col-md-6 col-lg-4 col-xl-3 main-color-bg-s main-color-f">
    <div class="h1 main-color-f">REGISTRATION</div>
    <br/>
    <form name="RegistrationForm" method="post" action='<c:out value="${register}"/>'>
        <div>
            <input class="main-color-bg-f"type="text" name="login" value="" placeholder="${text['login.login']}"/>
        </div>
        <div >
            <input class="main-color-bg-f" type="password" name="password" value="" placeholder="${text['login.password']}"/>
        </div>
        <div>
            <input class="main-color-bg-f" type="text" name="name" value="" placeholder="${text['registration.name']}"/>
        </div>
        <br/>
        <div class="text-danger">${userExist}</div>
        <input type="submit" value="<c:out value="${text['registration.registration']}"/>"/>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>

