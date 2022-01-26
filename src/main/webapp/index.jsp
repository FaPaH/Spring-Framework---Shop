<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<a href="logout">logout</a> <br><br>

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

<br>
<a href="list">Login</a>
<br>
<a href="registrationPage">Registration</a>
<br>

Login as admin: admin/admin
<br>
Login as user: user/user

</body>
</html>