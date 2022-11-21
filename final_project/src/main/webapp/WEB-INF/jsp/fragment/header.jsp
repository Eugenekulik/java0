<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../../main.css"/>
    <meta charset="UTF-8">
    <meta name="viewport=" content="width-device-width, initial-scale = 1.0">
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/uicons-bold-rounded/css/uicons-bold-rounded.css'>
</head>
<c:if test="${pageContext.session.getAttribute('action') == null}">
    <c:set value="/main.html" scope="session" var="action"/>
</c:if>
<c:url value="${pageContext.session.getAttribute('action')}.html" var="thisPage"/>
<body style="background: url('<c:url value="/img/fon.jpg"/>')">
<div class="container-fluid pink-bg">
    <nav class="navbar navbar-expand-lg navbar-inverse navbar-fixed-top">

        <a href="<c:url value="/main.html"/>" class="navbar-brand dark-blue">Beauty Parlor</a>
        <button class="navbar-toggler" data-toggle="collapse" aria-controls="nv-menu"
                data-target="#nv-menu" aria-expanded="false" aria-label="Toggle Navigation">
            <span class="navbar-toggler-icon"><i class="fi fi-br-menu-burger"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="nv-menu">
            <ul class="nav navbar-nav col-12 d-flex">
                <li class="flex-fill">
                    <ul class="nav navbar-nav d-flex col-12">
                        <li class="nav-item active">
                            <a class="nav-link dark-blue" href="<c:url value="/main.html" />"
                               role="button">${text["header.main"]}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link dark-blue" href="<c:url value="/procedure.html"/>"
                               role="button">${text["header.procedure"]}</a>
                        </li>

                        <!--/////////////////////////////////////////////////////////////////-->
                        <c:forEach var="role" items="${roles}">
                            <c:if test="${role.name == 'admin'}">
                                <li class="nav-item">
                                    <a class="nav-link dark-blue" href="<c:url value="/administrate.html"/>"
                                       role="button">${text["header.administrate"]}</a>
                                </li>
                            </c:if></c:forEach>

                        <!--/////////////////////////////////////////////////////////////////-->
                        <c:forEach var="role" items="${roles}">
                            <c:if test="${role.name == 'client'}">
                                <li class="nav-item">
                                    <a class="nav-link dark-blue" href="<c:url value="/appointment.html"/>"
                                       role="button">${text["header.appointment"]}</a>
                                </li>
                            </c:if></c:forEach>

                        <!--/////////////////////////////////////////////////////////////////-->
                        <c:forEach var="role" items="${roles}">
                            <c:if test="${role.name == 'employee'}">
                                <li class="nav-item">
                                    <a class="nav-link dark-blue" href="<c:url value="/schedule.html"/>"
                                       role="button">${text["header.schedule"]}</a>
                                </li>
                            </c:if></c:forEach>
                    </ul>
                </li>
                <li class="flex-fill">
                    <div class="col-12 d-flex align-items-stretch">
                        <div class="navbar-nav ms-auto col-12 d-flex justify-content-end align-item-center">
                            <div class="nav-item label d-flex align-item-center justify-content-center"><c:if test="${not empty user}">
                                <span>user name: ${user.name}</span>
                            </c:if>
                            </div>
                            <!--/////////////////////////////////////////////////////////////////-->
                            <div class="nav-item dropdown d-flex align-item-center justify-content-center">
                                <button type="button" style="min-width: 100px"
                                        class="btn btn-primary dropdown-toggle btn-pink"
                                        data-bs-toggle="dropdown">
                                    ${pageContext.session.getAttribute('language')}</button>
                                <ul class="pink-bg dropdown-menu dropdown-menu">
                                    <li class="dropdown-item pink-bg">
                                        <form class="d-grid" action="<c:url value="${thisPage}"/>"
                                              method="${pageContext.request.method}">
                                            <input type="hidden" value="en_US" name="language">
                                            <input type="submit" class="btn btn-primary btn-block"
                                                   value="${text["language.english"]}">
                                        </form>
                                    </li>
                                    <li class="dropdown-item pink-bg">
                                        <form class="d-grid" action="<c:url value="${thisPage}"/>"
                                              method="${pageContext.request.method}">
                                            <input type="hidden" value="ru_RU" name="language">
                                            <input type="submit" class="btn btn-primary"
                                                   value="${text["language.russian"]}">
                                        </form>
                                    </li>
                                    <li class="dropdown-item pink-bg">
                                        <form class="d-grid" action="<c:url value="${thisPage}"/>"
                                              method="${pageContext.request.method}">
                                            <input type="hidden" value="pl_PL" name="language">
                                            <input type="submit" class="btn btn-primary"
                                                   value="${text["language.polski"]}">
                                        </form>
                                    </li>
                                </ul>
                            </div>


                            <!--/////////////////////////////////////////////////////////////////-->
                            <div class="nav-item">
                                <c:choose>
                                    <c:when test="${pageContext.session.getAttribute('user') == null}">
                                        <a href="<c:url value="/login_form.html"/>"
                                           class="btn btn-primary btn-lg btn-dark-blue">${text['user.login']}</a>
                                    </c:when>
                                    <c:when test="${pageContext.session.getAttribute('user') != null}">
                                        <a href='<c:url value="/logout.html"/>'
                                           class="btn btn-primary btn-lg btn-dark-blue">${text['header.logout']}</a>
                                    </c:when>
                                </c:choose>
                            </div>

                        </div>
                    </div>
                </li>
                <!--/////////////////////////////////////////////////////////////////-->
            </ul>
        </div>
    </nav>
</div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
