<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/uicons-regular-rounded/css/uicons-regular-rounded.css'>
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/uicons-solid-rounded/css/uicons-solid-rounded.css'>
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
                <div class="h2 pink">${text['procedure.title']}</div>
            </div>
            <br/>
            <c:forEach var="category" items="${categories}" varStatus="status">
                <div class="category">
                    <c:out value="${category.name}"/>
                </div>
                <c:forEach var="elem" items="${procedureList}">
                    <c:if test="${status.count == elem.categoryId}">
                        <li>
                            <a href="<c:url value="/procedure.html?current=${elem.id}"/>"
                               class="pink">${elem.name}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-9 bord">
        <div class="h2 pink">
            <c:out value="${procedure.name}"/>
        </div>
        <p class="description">
            <c:out value="${procedure.description}"/>
        </p>
        <div>
            <c:forEach var="role" items="${roles}">
                <c:if test="${role.name == 'client'}">
                    <c:set var="add_access" value="true"/>
                </c:if>
            </c:forEach>
            <c:if test="${add_access == 'true'}">
                <form action="<c:url value="/appointment.html"/>"
                      method="post">
                    <input type="hidden" name="method" value="create">
                    <input type="submit" class="btn btn-success btn-lg" value="${text['label.sign']}"/>
                </form>
            </c:if>
        </div>
        </br>
        <c:if test="${not empty scores}">
            <div class="container comment col-10">
                <c:forEach var="score" items="${scores}">
                    <div class="d-flex flex-row">
                        <c:forEach items="${users}" var="usr">
                            <div class="d-flex align-items-center m-2">
                                <c:if test="${score.userId == usr.id }">
                                    ${usr.name}:
                                </c:if>
                            </div>
                            <div class="d-flex flex-row align-items-center">
                                <c:forEach begin="1" end="5" varStatus="status">
                                    <c:if test="${score.value<status.index}">
                                        <i class="fi fi-rr-star pink"></i>
                                    </c:if>
                                    <c:if test="${score.value>=status.index}">
                                            <i class="fi fi-sr-star pink"></i>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:if test="${user.id == usr.id}">
                                <div class="d-flex flex-fill justify-content-end">
                                    <form action="<c:url value='/score.html'/>" method="post"
                                          id="score-delete">
                                        <input type="hidden" name="id" value="${score.id}">
                                        <input type="hidden" name="method" value="delete">
                                        <button class="btn-delete" onclick="document.getElementById('score-delete').submit()"><i
                                                class="fi fi-rr-trash"></i></button>
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <hr>
                    <div>
                            ${score.comment}
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
