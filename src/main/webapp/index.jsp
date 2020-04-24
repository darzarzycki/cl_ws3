<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<%@include file="header.jsp" %>
<table>
    <tr>
        <th>Exercise name</th>
        <th>Solution author</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${requestScope.recentSolutions}" var="solution">
        <tr>
            <td>${solution.exerciseTitle}</td>
            <td>${solution.username}</td>
            <td>${solution.updated}</td>
            <td><a href="/solution?id=${solution.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
