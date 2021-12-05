<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <c:url value="/main.css" var="path"/>
    <link href="${path}" rel="stylesheet">
    <title>Procedure add</title>
</head>
<body style="background: url('<c:url value="/img/fon.jpg"/>')">
<div class="addpanel col-sm-12 col-md-10 col-lg-8 col-xl-6 main-color-bg-s main-color-f">
    <br/>
    <form name="LoginForm" method="post" action='<c:url value="/administrate_add_submit.html"/>'>
        <select required name="categorySelect">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <br/>
        <br/>
        <div>
            <input required minlength="4" maxlength="50" pattern="([A-Za-zА-Яа-яЁё ]+)"
                   class="main-color-bg-f" type="text"
                   name="name" value="" placeholder="${text['procedure.name']}"/>
        </div>
        <br/>
        <div>
        <textarea placeholder="${text['procedure.description']}"
                  name="description" class="main-color-bg-f col-12">
        </textarea>
        </div>
        <br/>
        <div>
            <input type="number" name="elapsedTime" required
                   placeholder="${text['procedure.elapsedTime']}"/>
        </div>
        <br/>
        <div>
            <input class="btn btn-success" type="submit" value="${text['administrate.add']}"/>
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
