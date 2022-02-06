<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border=1>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ml}" var="ml" step="1" varStatus="status">
        <tr>
            <td><c:out value="${ml.getDateTime}" /></td>
            <td><c:out value="${status.step}" /></td>
            <td><c:out value="${status.current}" /></td>
        </tr>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Meals</h2>
<p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>
