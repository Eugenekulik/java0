<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Error Page</title></head>
<body>
<h1 style="text-align: center; color: darkred">${error}</h1>
<div style="text-align: center">
    <a style="text-align: center" href="<c:url value="/main.html"/>">try to return to home page</a>
</div>
</body>
</html>
