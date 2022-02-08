<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <th>Id</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ml}" var="ml">
        <tr
                <c:if test="${ml.excess}">
                    style="color: red"
                </c:if>
                <c:if test="${!ml.excess}">
                    style="color: green"
                </c:if>>
            <td><c:out value="${ml.id}"/></td>
            <td><fmt:parseDate value="${ml.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
            <td><c:out value="${ml.description}"/></td>
            <td><c:out value="${ml.calories}"/></td>
                <%--            <td><input type="button" value="Update" onclick="window.location.href = ">--%>
            <td><a href="meals?action=edit&mealId=<c:out value="${ml.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${ml.id}"/>">Delete</a></td>
                <%--            <td><form method="post" action="<c:url value="/meals"/>" >Users</form></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="meals?action=insert">Add User</a></p>
</body>
</html>
