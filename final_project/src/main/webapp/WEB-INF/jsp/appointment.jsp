<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>appointment</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="container">
    <ul class="nav nav-pills nav-fill tab">
        <li class="nav-item">
            <a class="nav-link h4 main-color-s" href="<c:url value="/appointment.html?tab=2"/>">${text['appointment.active']}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link h4 main-color-s" href="<c:url value="/appointment.html?tab=1"/>">${text['appointment.inprocessing']}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link h4 main-color-s" href="<c:url value="/appointment.html?tab=3"/>">${text['appointment.archive']}</a>
        </li>
    </ul>
    <div class="panel col-10">
        <table class="table">
            <tr>
                <th class="main-color-f">Procedure</th>
                <th class="main-color-f">Employee</th>
                <th class="main-color-f">Price</th>
                <th class="main-color-f">Date</th>
                <th class="main-color-f"></th>
            </tr>
            <c:forEach var="elem" items="${appointments}">
                <c:if test="${elem.status == tab}">
                    <tr>
                        <td>
                            <c:forEach var="proc" items="${procedures}">
                                <c:if test="${proc.id == elem.procedureEmployeeId}">${proc.name}</c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="empl" items="${employees}">
                                <c:if test="${empl.id == elem.procedureEmployeeId}">${empl.name}</c:if>
                            </c:forEach>
                        </td>
                        <td>${elem.price}BYN</td>
                        <td>${elem.date}</td>
                        <td><a class="btn btn-danger" href="<c:url value="/appointment.html?delete=${elem.id}"/>">${text['appointment.delete']}</a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
