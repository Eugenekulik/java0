<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <c:url value="/main.css" var="path"/>
  <link href="${path}" rel="stylesheet">
  <title>procedure create page</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="box">
  <div class="h1 violet">${procedure.name}</div>
  <form class="form-group" name="dateForm" method="post" action="<c:url value="/administrate/procedure.html"/>">
    <input type="text" name="name" placeholder="${text['procedure.name']}">
    <select name="category">
      <c:forEach var="category" items="${categories}">
        <option value="${category.id}">${category.name}</option>
      </c:forEach>
    </select>
    <textarea rows="7" maxlength="500" class="main-color-bg-f form-control"
              type="text" name="description" value="$"
              placeholder="${text['procedure.description']}"></textarea>
    <input maxlength="500"
           class="main-color-bg-f form-control"
           type="number" name="elapsedTime"
           placeholder="${text['procedure.elapsedTime']}" value=""/>
    <input type="hidden" name="method" value="create">
    <input class="btn btn-sm btn-primary" type="submit" value="${text['label.add']}">
  </form>
</div>
</body>
</html>
