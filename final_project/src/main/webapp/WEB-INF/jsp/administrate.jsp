<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="container">
    <ul class="nav nav-pills nav-fill tab">
        <li class="nav-item <c:if test="${tab == 1}">active</c:if>">
            <a class="nav-link h5 main-color-s"
               href="<c:url value="/administrate.html?tab=1"/>">${text['administrate.user']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 2}">active</c:if>">
            <a class="nav-link h5 main-color-s"
               href="<c:url value="/administrate.html?tab=2"/>">${text['header.appointment']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 3}">active</c:if>">
            <a class="nav-link h5 main-color-s"
               href="<c:url value="/administrate.html?tab=3"/>">${text['header.procedure']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 4}">active</c:if>">
            <a class="nav-link h5 main-color-s"
               href="<c:url value="/administrate.html?tab=4"/>">${text['header.graphic']}</a>
        </li>
    </ul>
    <div class="panel">
        <c:choose>
            <c:when test="${activeTab == '1'}">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Login</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.login}</td>
                            <td>${user.phone}</td>
                            <td>${user.role}</td>
                            <c:if test="${user.role != 'admin'}">
                                <td>
                                    <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                            data-bs-target="#">
                                        Update
                                    </button>
                                </td>
                                <td>
                                    <form method="post" action="<c:url
                                    value="/administrate.html?tab=1&delete=${user.id}"/>">
                                        <input type="submit" class="btn btn-danger" value="Delete"/>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <c:if test="">
                        <li class="page-item"><a class="page-link" href="#">${text['pagination.previous']}</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${pageCount}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="employee.do?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="">
                        <li class="page-item"><a class="page-link" href="#">${text['pagination.next']}</a></li>
                    </c:if>
                </ul>
            </c:when>
            <c:when test="${activeTab == '2'}">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Client</th>
                        <th>Procedure</th>
                        <th>Employee</th>
                        <th>Price</th>
                        <th>Date</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td>${appointment.id}</td>
                            <td>
                                <c:forEach var="client" items="${clients}">
                                    <c:if test="${client.id == appointment.userId}">${client.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="proc" items="${procedures}">
                                    <c:if test="${proc.id == appointment.procedureEmployeeId}">${proc.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="empl" items="${employees}">
                                    <c:if test="${empl.id == appointment.procedureEmployeeId}">${empl.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>${appointment.price}</td>
                            <td>${appointment.date}</td>
                            <td>
                                <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                        data-bs-target="#">
                                    Update
                                </button>
                            </td>
                            <td>
                                <form method="post" action="<c:url
                                    value="/administrate.html?tab=2&delete=${appointment.id}"/>">
                                    <input type="submit" class="btn btn-danger" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </c:when>
            <c:when test="${activeTab == '3'}">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Elapsed time</th>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="procedure" items="${procedures}">
                        <tr>
                            <td>${procedure.id}</td>
                            <td>${procedure.name}</td>
                            <td>
                                <c:forEach var="category" items="${categories}">
                                    <c:if test="${category.id == procedure.categoryId}">
                                        ${category.name}</c:if>
                                </c:forEach>
                            </td>
                            <td>${procedure.elapsedTime}${text["time.minute"]}</td>
                            <td>
                                <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                        data-bs-target="#">
                                    Update
                                </button>
                            </td>
                            <td>
                                <form action="<c:url value="/administrate.html?tab=3&delete=${procedure.id}"/>">
                                    <input type="submit" class="btn btn-danger" value="Delete"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </c:when>
            <c:when test="${activeTab == '4'}">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Login</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:forEach var="graphic" items="${graphics}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.login}</td>
                            <td>${user.phone}</td>
                            <td>${user.role}</td>
                            <c:if test="${user.role != 'admin'}">
                                <td>
                                    <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                                            data-bs-target="#">
                                        Update
                                    </button>
                                </td>
                                <td>
                                    <form action="<c:url value="/administrate.html?tab=1&delete=${user.id}"/>">
                                        <input type="submit" class="btn btn-danger" value="Delete"/>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </c:when>
        </c:choose>
    </div>
</div>
<c:import url="fragment/footer.jsp"/>
</body>
</html>
