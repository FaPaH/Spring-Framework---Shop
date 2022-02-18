<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 17/02/2022
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>

    <title>Message</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/message" method="get">
<pre>
    <input type="text" name="movieName" required placeholder="Movie name"/>

    <input type="submit" value="Get movie" />
</pre>
</form>

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

</body>
</html>
