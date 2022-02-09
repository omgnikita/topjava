<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 45.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new meal</title>
</head>
<body>
<script>
    $(function () {
        $('input[name=dob]').datepicker();
    });
</script>

<form method="post" action='meals' name="frmAddMeal">
    ID : <input type="text" readonly="readonly" name="mealId"
                value="<c:out value="${meal.id}" />"/> <br/>
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    Calories : <input
        type="number" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    Date :<input type="datetime-local" name="date" value="${meal.dateTime}"
    /> <br/>
    <input type="submit" value="Submit"/>
    <a href="meals?action=getAll">Cancel</a>
    <input type="button" onclick="">
    <p><a href="meals?action=getAll">Cancel</a></p>
</form>
</body>
</html>