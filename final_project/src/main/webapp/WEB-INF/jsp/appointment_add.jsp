<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>procedure</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="box">
    <div class="h1 violet">${procedure.name}</div>
    <form class="form-group" name="dateForm" method="post" action="<c:url value="/appointment_add.html"/>">
        <div>
            <input class="form-control" class="date" type="date" name="dateSelect" value="${selectedDate}"/>
        </div>
        <div>
            <select class="form-control" id="employeeSelect" name="employeeSelect">
                <c:forEach items="${employeeList}" var="elem">
                    <option value="${elem.id}"
                            <c:if test="${selectedEmployee == elem.id}">selected</c:if>
                            role="option">${elem.name}</option>
                </c:forEach>
            </select>
        </div>
        <br/>
        <div>
            <input class="btn btn-sm btn-primary" type="submit" value="${text['appointment.schedule']}">
        </div>
    </form>
    <div>
        <form name="timeForm" class="form-group" method="post" action="<c:url value="/appointment_add.html"/>">
            <select class="form-control" id="timeSelect" name="timeSelect">
                <c:forEach items="${schedules}" var="elem">
                    <option value="${elem}" role="option">${elem}</option>
                </c:forEach>
            </select>
            <input class="btn btn-submit" type="submit" value="${text['appointment.add']}">
        </form>
    </div>
</div>
</body>
</html>
