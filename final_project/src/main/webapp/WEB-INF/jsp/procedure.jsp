<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>procedure</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="container-fluid">
    <div class="col-sm-2">
        <div>
            <c:forEach var="elem" items="${procedureList}">
                <div>
                    <c:url value="/procedure.html?current=${elem}" var="element"/>
                    <a href="<c:out value="${element}"/>" class="main-color">${elem}</a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-10">
        <div>
            <c:out value="${procedure.description}"/>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
