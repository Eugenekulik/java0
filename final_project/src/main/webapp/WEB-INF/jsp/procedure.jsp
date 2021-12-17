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
<div class="nav">
    <div class="menu col-sm-3">
        <div>
            <div>
                <div class="h2 main-color-s">${text['procedure.title']}</div>
            </div>
            <br/>
            <c:forEach var="category" items="${categories}" varStatus="status">
                <div class="category">
                    <c:out value="${category.name}"/>
                </div>
                <c:forEach var="elem" items="${procedureList}">
                    <c:if test="${status.count == elem.categoryId}">
                        <li>
                            <a href="<c:url value="/procedure.html?current=${elem.name}"/>"
                               class="main-color-s">${elem.name}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-9 bord">
        <div class="h2 main-color-s">
            <c:out value="${procedure.name}"/>
        </div>
        <p class="description">
            <c:out value="${procedure.description}"/>
        </p>
        <div>
            <form action="<c:url value="/appointment_add.html"/>"
            method="post" name="AddAppointmentForm">
                <input type="submit" class="btn btn-success btn-lg" value="${text['procedure.add']}"/>
            </form>

        </div>
    </div>
</div>
</body>
</html>
