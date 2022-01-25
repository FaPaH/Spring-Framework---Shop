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

<a href="${pageContext.request.contextPath}/list">Back to list</a>

<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>

<br/>
<br/>

<security:authorize access="hasRole('ROLE_ADMIN')">

    <form action="${pageContext.request.contextPath}/checkAllDelivery">
        <input type="submit" value="Load all delivery's" />
    </form>

</security:authorize>
<security:csrfInput/>

<c:if test="${msg == null}">

    <h1>Product List</h1>

    <table class="tg">
        <tr>
            <th width="30">ID</th>
            <th width="120">username</th>
            <th width="120">Product ids</th>
            <th width="120">Delivery address</th>
            <th width="120">Phone number</th>
            <th width="120">Total prise</th>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <th width="60">Delete</th>
            </security:authorize>
        </tr>
        <c:forEach items="${deliveryList}" var="delivery">
            <tr>
                <td>${delivery.deliveryId}</td>
                <td>${delivery.username}</td>
                <td>${delivery.productIds}</td>
                <td>${delivery.deliveryAddress}</td>
                <td>${delivery.numberPhone}</td>
                <td>${delivery.totalPrise}</td>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="deleteDelivery/<c:out value='${delivery.deliveryId}'/>">Delete</a></td>
                </security:authorize>
                <security:csrfInput/>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
