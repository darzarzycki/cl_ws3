<%--
  Created by IntelliJ IDEA.
  User: darza
  Date: 24.04.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edytuj Rozwiązanie</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Edit solution</h1>
<form method="post">
    <label>
        Opis:
        <textarea name="description">${requestScope.solution.description}</textarea>
    </label>
    <input type="submit" />
</form>
<%@include file="footer.jsp" %>
</body>
</html>
