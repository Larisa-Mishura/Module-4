<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose id</title>
</head>

<body>

<form action="/users/${param.id}" method="get">
    <input type="hidden" name="id" value="${param.id}">

    <input type="submit" value="Submit">
</form>

</body>