<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 17.01.2022
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>

    <title>Registration page</title>

</head>
<body>

<a href="${pageContext.request.contextPath}/">Back to main menu</a>

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

<form action="${pageContext.request.contextPath}/registrationUser" method="post">

<pre>

    <h1>Create account</h1>

    Login
    <input type="text" name="userLogin"/>

    Password
    <input type="password" name="userPassword"/>

    <input type="submit" value="create" />

</pre>
    <security:csrfInput/>
</form>

</body>
</html>
