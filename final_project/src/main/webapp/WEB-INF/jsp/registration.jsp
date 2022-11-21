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
<div class="box col-sm-8 col-md-6 col-lg-4 col-xl-3 main-color-bg-s violet">
    <div class="h1 violet">REGISTRATION</div>
    <br/>
    <form name="RegistrationForm" method="post" action='<c:out value="${register}"/>'>
        <div>
            <input required maxlength="20" minlength="6" pattern="([A-Za-z0-9-_.]*)" class="main-color-bg-f"type="text" name="login" value="" placeholder="${text['user.login']}"/>
        </div>
        <div >
            <input required maxlength="50" minlength="8" pattern="([A-Za-z0-9-_.]*)" class="main-color-bg-f" type="password" name="password" value="" placeholder="${text['user.password']}"/>
        </div>
        <div>
            <input required maxlength="40" minlength="4" pattern="([A-ZА-ЯЁa-zа-яё ]*)" class="main-color-bg-f" type="text" name="name" value="" placeholder="${text['registration.name']}"/>
        </div>
        <div >
            <input required maxlength="20" minlength="4" pattern="([+0-9][0-9]*)" class="main-color-bg-f" type="text" name="phone" value="" placeholder="${text['registration.phone']}"/>
        </div>
        <br/>
        <div class="text-danger">${userExist}</div>
        <input type="submit" value="<c:out value="${text['registration.registration']}"/>"/>
    </form>
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

