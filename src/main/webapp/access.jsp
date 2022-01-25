<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><strong>You do not have permission</strong></h1>
<a href="${pageContext.request.contextPath}/"><h1>Back to main menu</h1></a>
</body>
</html>
