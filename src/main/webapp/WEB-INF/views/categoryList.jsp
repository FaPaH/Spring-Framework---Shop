<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <%@ page isELIgnored="false" %>

    <title>Caregory list</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/list">Back to list</a>

<br/>
<br/>

<form action="${pageContext.request.contextPath}/createCategory" method="post">
<pre>
    <h1>Create category</h1>

    Name
    <input type="text" name="categoryName" required placeholder="Category name"/>

    <input type="submit" value="create" />

</pre>

</form>

<h1>Category List</h1>

<table class="tg">
    <tr>
        <th width="30">ID</th>
        <th width="120">Category name</th>
        <%--        <th width="60">Edit</th>--%>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${categoryList}" var="category">
        <tr>
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
                <%--            <td><a href="edit/<c:out value='${category.categoryId}'/>">Edit</a></td>--%>
            <td><a href="deleteCategory/<c:out value='${category.categoryId}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
