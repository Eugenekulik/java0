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
            <a class="nav-link h4 pink"
               href="<c:url value="/appointment.html?tab=2"/>">${text['appointment.active']}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link h4 pink"
               href="<c:url value="/appointment.html?tab=1"/>">${text['appointment.inprocessing']}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link h4 pink"
               href="<c:url value="/appointment.html?tab=3"/>">${text['appointment.archive']}</a>
        </li>
    </ul>
    <div class="panel col-10">
        <table class="table">
            <tr>
                <th class="violet">Procedure</th>
                <th class="violet">Employee</th>
                <th class="violet">Price</th>
                <th class="violet">Date</th>
                <th class="violet">Status</th>
                <th class="violet"></th>
                <th></th>
            </tr>
            <c:forEach var="elem" items="${appointments}">
                <tr>
                    <c:if test="${elem.status == tab || elem.status == 4 && tab == 3}">
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
                        <td>${elem.status}</td>
                        <td>
                        <c:if test="${tab != 3}">
                            <form action="<c:url value="/appointment.html"/>" method="POST">
                                <input type="hidden" name="method" value="delete">
                                <input type="hidden" name="id" value="${elem.id}">
                                <input class="btn btn-danger" type="submit" value="CANCEL">
                            </form>
                        </c:if>
                        <c:if test="${elem.status == 3}">
                            <td>
                                <button type="button" class="btn btn-warning modal-btn" data-toggle="modal"
                                        data-id="${elem.id}" data-target="#modal">add score
                                </button>
                            </td>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="modal" class="modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form action="<c:url value="/score.html"/>" method="post">
                    <input id="comment" maxlength="500" minlength="0"
                           class="main-color-bg-f form-control" type="text" name="comment" value=""/>
                    <br/>
                    <div>Score</div>
                    <select id="score" name="score">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <br/>
                    <br/>
                    <input type="hidden" name="appointmentId" id="appointmentId" value="">
                    <input type="hidden" name="method" value="create">
                    <input class="btn-success" type="submit" value="<c:out value="${text['label.add']}"/>">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(".modal-btn").click(
        function () {
            var appointmentId = $(this).attr('data-id');
            $("#appointmentId").attr('value', appointmentId);
        });
</script>
</body>
</html>
