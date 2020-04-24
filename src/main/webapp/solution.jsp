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
    <title>Rozwiązanie</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Solution details</h1>
<div>Created: ${requestScope.solution.created}</div>
<div>Updated: ${requestScope.solution.updated}</div>
<pre>${requestScope.solution.description}</pre>
<a href="/edit-solution?id=${requestScope.solution.id}">Edytuj</a>
<%@include file="footer.jsp" %>
</body>
</html>
