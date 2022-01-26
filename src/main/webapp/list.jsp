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

<a href="logout">logout</a> <br><br>

<a href="${pageContext.request.contextPath}/">Back to main menu</a>

<br/>
<br/>

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

<h1>Product List</h1>

Search product by name:
<form action="${pageContext.request.contextPath}/searchByName" method="post">
<pre>
    <input type="text" name="searchName" placeholder="Product name"/> <input type="submit" value="search" />
</pre>
    <security:csrfInput/>
</form>

<security:authorize access="hasRole('ROLE_ADMIN')">

<form action="${pageContext.request.contextPath}/createProduct">
    <input type="submit" value="Add product" />
</form>

<form action="${pageContext.request.contextPath}/categoryList">
    <input type="submit" value="Go to category list" />
</form>

</security:authorize>
<security:csrfInput/>

<form action="${pageContext.request.contextPath}/checkList" method="post">

    <table class="tg">
        <tr>
            <th width="60">Delivery?</th>
            <th width="30">ID</th>
            <th width="120">Product name</th>
            <th width="120">Prise</th>
            <th width="120">Category</th>
            <security:authorize access="hasRole('ROLE_ADMIN')">
            <th width="60">Edit</th>
            <th width="60">Delete</th>
            </security:authorize>
            <security:csrfInput/>
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
                <security:authorize access="hasRole('ROLE_ADMIN')">
                <td><a href="edit/<c:out value='${product.productId}'/>">Edit</a></td>
                <td><a href="delete/<c:out value='${product.productId}'/>">Delete</a></td>
                </security:authorize>
                <security:csrfInput/>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="Create delivery">
</form>

<form action="${pageContext.request.contextPath}/checkDelivery">
    <input type="submit" value="Check my delivery" />
</form>

</body>
</html>
