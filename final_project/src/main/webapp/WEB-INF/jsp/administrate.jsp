<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>administrate</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="container">
    <ul class="nav nav-pills nav-fill tab">
        <li class="nav-item <c:if test="${tab == 1}">active</c:if>">
            <a class="nav-link h5 pink"
               href="<c:url value="/administrate.html?tab=1"/>">${text['administrate.user']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 2}">active</c:if>">
            <a class="nav-link h5 pink"
               href="<c:url value="/administrate.html?tab=2"/>">${text['header.appointment']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 3}">active</c:if>">
            <a class="nav-link h5 pink"
               href="<c:url value="/administrate.html?tab=3"/>">${text['header.procedure']}</a>
        </li>
        <li class="nav-item <c:if test="${tab == 4}">active</c:if>">
            <a class="nav-link h5 pink"
               href="<c:url value="/administrate.html?tab=4"/>">${text['header.schedule']}</a>
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
                            <td><c:forEach var="role" items="${user.roles}">
                                ${role.name},
                            </c:forEach></td>
                            <td>
                                <button type="button" class="btn btn-warning user-modal-btn"
                                        data-id="${user.id}" data-name="${user.name}"
                                        data-roles="${user.roles}" data-toggle="modal"
                                        data-phone="${user.phone}" data-target="#user-modal">Update
                                </button>
                            </td>
                            <td>
                                <form method="post" action="<c:url
                                    value="/administrate/user.html"/>">
                                    <input type="hidden" value="delete" name="method">
                                    <input type="hidden" value="${user.id}" name="userId">
                                    <button class="btn btn-danger"
                                            type="submit">${text['administrate.delete']}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <c:if test="${paginationPage>1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?paginationPage=${paginationPage-1}"/>">
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
                                       href="<c:url value="/administrate.html?paginationPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationPage < pageCount}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?paginationPage=${paginationPage+1}"/>">
                                    ${text['pagination.next']}</a>
                        </li>
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
                        <th>Status</th>
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
                            <td>${appointment.status}</td>
                            <td>
                                <button type="button" class="btn btn-warning appointment-modal-btn" data-toggle="modal"
                                        data-target="#appointment-modal"
                                        data-id="<c:out value="${appointment.id}"/>"
                                        data-status="<c:out value="${appointment.status}"/>"
                                        data-price="<c:out value="${appointment.price}"/>">
                                        ${text['administrate.update']}
                                </button>
                            </td>
                            <td>
                                <form method="post" action="<c:url
                                    value="/administrate/appointment.html"/>">
                                    <input type="hidden" value="delete" name="method">
                                    <input type="hidden" value="${appointment.id}" name="appointmentId">
                                    <button class="btn btn-danger" type="submit">${text['administrate.delete']}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">
                    <c:if test="${paginationPage>1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?paginationPage=${paginationPage-1}"/>">
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
                                       href="<c:url value="/administrate.html?paginationPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationPage < pageCount}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?paginationPage=${paginationPage+1}"/>">
                                    ${text['pagination.next']}</a>
                        </li>
                    </c:if>
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
                                <button type="button" class="btn btn-warning procedure-modal-btn" data-toggle="modal"
                                        data-target="#procedure-update-modal" data-id="${procedure.id}"
                                        data-name="${procedure.name}"
                                        data-description="${procedure.description}"
                                        data-time="${procedure.elapsedTime}">
                                    Update
                                </button>
                            </td>
                            <td>
                                <form method="post" action="<c:url
                                    value="/administrate/procedure.html"/>">
                                    <input type="hidden" value="delete" name="method">
                                    <input type="hidden" value="${procedure.id}" name="procedureId">
                                    <button class="btn btn-danger" type="submit">${text['administrate.delete']}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <button type="button" class="btn btn-success procedure-modal-btn" data-toggle="modal"
                        data-target="#procedure-create-modal">
                    Create
                </button>
                <ul class="pagination">
                    <c:if test="${paginationPage>1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?tab=3&paginationPage=${paginationPage-1}"/>">
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
                                       href="<c:url value="/administrate.html?tab=3&paginationPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationPage < pageCount}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?tab=3&paginationPage=${paginationPage+1}"/>">
                                    ${text['pagination.next']}</a>
                        </li>
                    </c:if>
                </ul>
            </c:when>
            <c:when test="${activeTab == '4'}">
                <table class="table">
                    <tr>
                        <th>id</th>
                        <th>employee</th>
                        <th>date</th>
                        <th>appointment id</th>
                        <td></td>
                    </tr>
                    <c:forEach var="schedule" items="${schedules}">
                        <tr>
                            <td>${schedule.id}</td>
                            <td>
                                <c:forEach var="employee" items="${employees}">
                                    <c:if test="${employee.id == schedule.employeeId}">
                                        ${employee.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${schedule.date}</td>
                            <c:if test="${schedule.appointmentId != 0}">
                                <td>${schedule.appointmentId}</td>
                            </c:if>
                            <c:if test="${schedule.appointmentId == 0}">
                                <td></td>
                            </c:if>
                            <td>
                                <form method="post" action="<c:url
                                    value="/administrate/schedule.html"/>">
                                    <input type="hidden" value="delete" name="method">
                                    <input type="hidden" value="${schedule.id}" name="scheduleId">
                                    <button class="btn btn-danger" type="submit">${text['administrate.delete']}</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <a class="btn btn-success"
                   href="<c:url value="/schedule/add.html"/>">${text['administrate.add']}</a>
                <ul class="pagination">
                    <c:if test="${paginationPage>1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?tab=4&paginationPage=${paginationPage-1}"/>">
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
                                       href="<c:url value="/administrate.html?tab=4&paginationPage=${i}"/>">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationPage < pageCount}">
                        <li class="page-item">
                            <a class="page-link"
                               href="<c:url value="/administrate.html?tab=4&paginationPage=${paginationPage+1}"/>">
                                    ${text['pagination.next']}</a>
                        </li>
                    </c:if>
                </ul>
            </c:when>
        </c:choose>
    </div>
</div>

<!--Modal window for update user-->
<div id="user-modal" class="modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <div class="h3"><c:out value="${text['user.change.title']}"/><span id="userId"></span></div>
                <button type="button" class="btn-close" data-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/administrate/user.html"/>" method="post">
                    <input type="hidden" name="method" value="update">
                    <input id="name" maxlength="40" minlength="4" pattern="([A-ZА-ЯЁa-zа-яё ]*)"
                           class="main-color-bg-f form-control" type="text" name="name" value=""
                           placeholder="<c:out value="${text['registration.name']}"/>"/>
                    <input id="phone" maxlength="20" minlength="4" pattern="([+0-9][0-9]*)"
                           class="main-color-bg-f form-control" type="text" name="phone" value=""
                           placeholder="<c:out value="${text['registration.phone']}"/>"/>
                    <c:forEach var="role" items="${allRoles}">
                        <label>
                            <input type="checkbox" value="${role.name}"
                                <c:forEach var="userRole" items="${userRoles}">
                                    <c:if test="${userRole.name == role.name}">checked</c:if>
                                </c:forEach>
                                   name="checkedRoles">
                            ${role.name}
                        </label>
                    </c:forEach>
                    </select>
                    <br/>
                    <div><span id="role"></span></div>
                    <br/>
                    <br/>
                    <input type="hidden" name="userId" id="hideId" value="">
                    <input class="btn-success" type="submit" value="<c:out value="${text['administrate.update']}"/>">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<!--Modal window for update appointment-->
<div class="modal" id="appointment-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="h3">Change appointment with id: <span id="appointmentId"></span></div>
                <button type="button" class="btn-close" data-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/administrate/appointment.html"/>" method="post">
                    <input id="appointmentPrice" maxlength="10" minlength="1" pattern="([0-9.]*)"
                           class="main-color-bg-f form-control" type="number" name="appointmentPrice" value=""
                           placeholder="${text['appointment.price']}"/>
                    <br/>
                    <select id="selectStatus" name="selectStatus">
                        <option value="1">in processing</option>
                        <option value="2">active</option>
                        <option value="3">archive</option>
                    </select>
                    <br/>
                    <br/>
                    <input type="hidden" name="appointmentId" id="hAppointmentId" value="">
                    <input type="hidden" name="method"value="update">
                    <input class="btn-success" type="submit" value="${text['administrate.update']}">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<!--Modal window for update procedure-->
<div class="modal" id="procedure-update-modal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="h3">Change appointment with id: <span id="procedureId"></span></div>
                <button type="button" class="btn-close" data-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/administrate/procedure.html"/>" method="post">
                    <input id="procedureName" maxlength="50" minlength="4" pattern="([А-ЯЁа-яё ]+)"
                           class="main-color-bg-f form-control" type="text" name="procedureName" value=""
                           placeholder="${text['procedure.name']}"/>
                    <br/>
                    <textarea rows="7" id="procedureDescription" maxlength="500"
                              class="main-color-bg-f form-control" type="text" name="procedureDescription" value=""
                              placeholder="${text['procedure.description']}"></textarea>
                    <br/>
                    <input id="procedureElapsedTime" maxlength="500"
                           class="main-color-bg-f form-control" type="number" name="procedureElapsedTime" value=""
                           placeholder="${text['procedure.elapsedTime']}"/>
                    <br/>
                    <input type="hidden" name="procedureId" id="hProcedureId" value="">
                    <input type="hidden" name="method" value="update">
                    <input class="btn-success" type="submit" value="${text['administrate.update']}">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<!--Modal window for create procedure-->
<div class="modal" id="procedure-create-modal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <div class="h3">Form to create new procedure</div>
                <button type="button" class="btn-close" data-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/administrate/procedure.html"/>" method="post">
                    <input maxlength="50" minlength="4" pattern="([А-ЯЁа-яё ]+)"
                           class="main-color-bg-f form-control" type="text" name="procedureName" value=""/>
                    <br/>
                    <textarea rows="7" maxlength="500"
                              class="main-color-bg-f form-control" type="text" name="procedureDescription"
                              value=""></textarea>
                    <br/>
                    <input maxlength="500"
                           class="main-color-bg-f form-control" type="number" name="procedureElapsedTime" value=""
                    />
                    <br/>
                    <input type="hidden" name="procedureId" value="">
                    <input type="hidden" name="method" value="create">
                    <input class="btn-success" type="submit">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(".user-modal-btn").click(
        function () {
            var userId = $(this).attr('data-id');
            var userName = $(this).attr('data-name');
            var userPhone = $(this).attr('data-phone');
            $("#userId").html(userId);
            $("#name").attr('value', userName);
            $("#phone").attr('value', userPhone);
            $("#hideId").attr('value', userId);
        });
    $(".appointment-modal-btn").click(
        function () {
            var appointmentId = $(this).attr('data-id');
            var appointmentPrice = $(this).attr('data-price');
            var appointmentStatus = $(this).attr('data-status')

            $("#appointmentId").html(appointmentId);
            $("#hAppointmentId").attr('value', appointmentId);
            $("#appointmentPrice").attr('value', appointmentPrice);
            const select = document.querySelector('#selectStatus').getElementsByTagName('option');
            for (let i = 0; i < select.length; i++) {
                if (select[i].value === appointmentStatus) select[i].selected = true;
            }
        });
    $(".procedure-modal-btn").click(
        function () {
            var procedureId = $(this).attr('data-id');
            var procedureName = $(this).attr('data-name');
            var procedureDescription = $(this).attr('data-description');
            var procedureTime = $(this).attr('data-time');

            $("#procedureId").html(procedureId)
            $("#hProcedureId").attr('value', procedureId);
            $("#procedureName").attr('value', procedureName);
            $("#procedureDescription").text(procedureDescription);
            $("#procedureElapsedTime").attr('value', procedureTime);
        });
</script>
</body>
</html>
