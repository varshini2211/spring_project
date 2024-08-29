<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>${message}</h1>

    <p>The time on the server is ${serverTime}.</p>

    <form action="user" method="post">
        <input type="text" name="firstname" placeholder="Employee Name" /> <br>
        <input type="submit" value="Login" />
    </form>
</body>
</html>
