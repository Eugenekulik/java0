<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>schedule</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="panel">
    <table class="table">
        <tr>
            <th>Id</th>
            <th>date</th>
        </tr>
        <c:forEach var="schedule" items="${schedules}">
            <tr>
                <td>${schedule.id}</td>
                <td>${schedule.date}</td>
            </tr>
        </c:forEach>
    </table>
    <ul class="pagination">
        <c:if test="${paginationPage>1}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/schedule.html?paginationPage=${paginationPage-1}"/>">
                        ${text['pagination.previous']}</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${pageCount}" var="i">
            <c:choose>
                <c:when test="${paginationPage eq i}">
                    <li class="page-item">
                        <a href="#" class="page-link">${i}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="<c:url value="/schedule.html?paginationPage=${i}"/>">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${paginationPage < pageCount}">
            <li class="page-item">
                <a class="page-link"
                   href="<c:url value="/schedule.html?paginationPage=${paginationPage+1}"/>">
                        ${text['pagination.next']}</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>
