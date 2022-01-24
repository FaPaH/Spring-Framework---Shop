<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="false" %>

<html>
<head>
    <%@ page isELIgnored="false" %>

    <title>Admin page</title>

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

<a href="${pageContext.request.contextPath}index">Back to main menu</a>

<br/>
<br/>

<h1>Product List</h1>

<form action="${pageContext.request.contextPath}/createProduct">
    <input type="submit" value="Add product" />
</form>

<form action="${pageContext.request.contextPath}/categoryList">
    <input type="submit" value="Go to category list" />
</form>

<form action="${pageContext.request.contextPath}/checkList" method="post">

    <table class="tg">
        <tr>
            <th width="60">Delivery?</th>
            <th width="30">ID</th>
            <th width="120">Product name</th>
            <th width="120">Prise</th>
            <th width="120">Category</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>
                    <input type="checkbox" name="checkedProductId" value="${product.productId}">
                </td>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.productPrise}</td>
                <td>${product.productCategory}</td>
                <td><a href="edit/<c:out value='${product.productId}'/>">Edit</a></td>
                <td><a href="delete/<c:out value='${product.productId}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="Create delivery">
</form>

</body>
</html>
