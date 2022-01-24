<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Add page</title>

</head>
<body>

<a href="${pageContext.request.contextPath}/list">Back to list</a>

<form action="${pageContext.request.contextPath}/create" method="post">

<pre>

    <h1>Create product</h1>

    Name
    <input type="text" name="name" required placeholder="Product name"/>

    Prise
    <input type="number" name="prise" required placeholder="Product prise"/>

    Category
    <select required name="categoryId">
       <c:forEach items="${categoryList}" var="category">
           <option value="${category.categoryId}">${category.categoryName}</option>
       </c:forEach>
    </select><br>

    <input type="submit" value="create" />

</pre>

</form>

</body>
</html>
