<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>header</title>
    <meta charset="UTF-8">
</head>
<body>
<c:if test="${text == null}">
    <% ResourceBundle resourceBundle = ResourceBundle.getBundle("text_en_US");
    request.setAttribute("text",resourceBundle);%>
</c:if>
<nav class="navbar navbar-expand-sm bg-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <c:url value="/index.html" var="main"/>
            <a href="<c:out value="${main}"/>" class="navbar-brand text-info">Beauty Parlor</a>
        </div>
        <div class="btn-group btn-group-lg col-sm-8">

            <a class="btn btn-dark" href="<c:out value="${main}"/>" role="button">${text["header.main"]}</a>
            <c:url value="/procedure.html" var="procedure"/>
            <a class="btn btn-dark" href="<c:out value="${procedure}"/>" role="button">${text["header.procedure"]}</a>
            <c:url value="/index.html" var="reception"/>
            <a class="btn btn-dark" href="<c:out value="${reception}"/>" role="button">${text["header.reception"]}</a>
            <c:url value="/index.html" var="about"/>
            <a class="btn btn-dark" href="<c:out value="${about}"/>" role="button">${text["header.about"]}</a>
        </div>
        <div>
            <c:url value="/login.html" var="login"/>
            <c:url value="/logout.html" var="logout"/>
            <c:choose>
                <c:when test="${pageContext.session.getAttribute('user') == null}">
                    <a href="<c:out value="${login}"/>" class="btn btn-info">${text['login.login']}</a>
                </c:when>
                <c:when test="${pageContext.session.getAttribute('user') != null}">
                    <a href='<c:out value="${ logout }"/>' class="btn btn-info">${text["header.logout"]}</a>
                </c:when>
            </c:choose>
        </div>
        <div>
            <c:if test="${pageContext.session.getAttribute('action') == null}">
                <c:set value="/index" scope="session" var="action"/>
            </c:if>
            <c:url value="${pageContext.session.getAttribute('action')}.html" var="thisPage"/>
            <form action="${thisPage}" method="post">
                <select id="language" onchange="this.form.submit()"
                        class="form-control bg-dark text-info" name="language">
                    <option value="en_US" <c:if test="${selectedLang == text['language.english']}">selected</c:if>
                            role="option">${text["language.english"]}</option>
                    <option value="ru_Ru" <c:if test="${selectedLang == text['language.russian']}">selected</c:if>
                            role="option">${text["language.russian"]}</option>
                    <option value="pl_PL" <c:if test="${selectedLang == text['language.polski']}">selected</c:if>
                            role="option">${text["language.polski"]}</option>
                </select>
            </form>
        </div>
    </div>
</nav>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
