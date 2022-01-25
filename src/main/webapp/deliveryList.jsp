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

    <title>Add delivery page</title>

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

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

<c:if test="${msg == null}">
<form action="${pageContext.request.contextPath}/createDelivery" method="post">

<pre>

    <h1>Create delivery</h1>

    Your name:
    <input type="text" name="name" required placeholder="Ivan Ivanon"/>

    Your delivery address
    <input type="text" name="address" required placeholder="Sumy, pr.Tarasa Shevchenko 22/13"/>

    Your phone number
    <input type="number" name="phoneNumber" required placeholder="0956887152"/>

    Total prise:
    <input type="number" name="totalPrise" readonly="readonly" value="${totalPrise}" required placeholder="${totalPrise}"/>

    Yours product:
    <table class="tg">
        <tr>
            <th width="30">ID</th>
            <th width="120">Product name</th>
            <th width="120">Prise</th>
            <th width="120">Category</th>
        </tr>
        <c:forEach items="${deliveryList}" var="product">
            <tr>
                <td>${product.productId}
                    <input type="checkbox" hidden checked name="checkedListId" value="${product.productId}">
                </td>
                <td>${product.productName}</td>
                <td>${product.productPrise}</td>
                <td>${product.productCategory}</td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="Deliver" />

</pre>
    </c:if>
    <security:csrfInput/>
</form>
</body>
</html>
