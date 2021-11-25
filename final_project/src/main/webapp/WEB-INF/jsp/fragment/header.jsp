<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport=" content="width-device-width, initial-scale = 1.0">
    <title>header</title>
</head>
<body style="background: url('<c:url value="/img/fon.jpg"/>')">
<nav class="navbar navbar-expand-lg navbar-inverse navbar-fixed-top">
    <div class="container">
            <a href="<c:url value="/main.html"/>" class="navbar-brand main-color-t">Beauty Parlor</a>
            <button class="navbar-toggler" data-toggle="collapse" aria-controls="nv-menu"
                    data-target="#nv-menu" aria-expanded="false" aria-label="Toggle Navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        <div class="collapse navbar-collapse" id="nv-menu">
            <ul class="nav navbar-nav mr-auto navbar-right">
                <li class="nav-item active">
                    <a class="nav-link main-color-t" href="<c:url value="/main.html" />"
                       role="button">${text["header.main"]}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link main-color-t" href="<c:url value="/procedure.html"/>"
                       role="button">${text["header.procedure"]}</a>
                </li>
                <c:if test="${role == 'admin'}"><li class="nav-item">
                    <a class="nav-link main-color-t" href="<c:url value="/administrate.html"/>"
                       role="button">${text["header.administrate"]}</a>
                </li></c:if>
                <c:if test="${role == 'client'}"><li class="nav-item">
                    <a class="nav-link main-color-t" href="<c:url value="/appointment.html"/>"
                       role="button">${text["header.appointment"]}</a>
                </li></c:if>
                <c:if test="${role == 'employee'}"><li class="nav-item">
                    <a class="nav-link main-color-t" href="<c:url value="/graphic.html"/>"
                       role="button">${text["header.graphic"]}</a>
                </li></c:if>
                <li class="nav-item">
                    <a class="nav-link main-color-t" href="<c:url value="/about.html"/>"
                       role="button">${text["header.about"]}</a>
                </li>
                <li class="nav-item log">
                    <c:choose>
                        <c:when test="${pageContext.session.getAttribute('user') == null}">
                            <a href="<c:url value="/login.html"/>"
                               class="nav-link main-color-t">${text['login.login']}</a>
                        </c:when>
                        <c:when test="${pageContext.session.getAttribute('user') != null}">
                            <a href='<c:url value="/logout.html"/>'
                               class="nav-link main-color-t">${text['header.logout']}</a>
                        </c:when>
                    </c:choose>
                </li>
                <li>
                    <c:if test="${pageContext.session.getAttribute('action') == null}">
                        <c:set value="/main" scope="session" var="action"/>
                    </c:if>
                    <c:url value="${pageContext.session.getAttribute('action')}.html" var="thisPage"/>
                    <form action="${thisPage}" method="${pageContext.request.method}">
                        <select id="language" onchange="this.form.submit()"
                                class="main-color-t select" name="language">
                            <option value="en_US"
                                    <c:if test="${selectedLang == text['language.english']}">selected</c:if>
                                    role="option">${text["language.english"]}</option>
                            <option value="ru_Ru"
                                    <c:if test="${selectedLang == text['language.russian']}">selected</c:if>
                                    role="option">${text["language.russian"]}</option>
                            <option value="pl_PL"
                                    <c:if test="${selectedLang == text['language.polski']}">selected</c:if>
                                    role="option">${text["language.polski"]}</option>
                        </select>
                    </form>
                </li>
                </ul>
        </div>
    </div>
</nav>
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
