<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%--
  Created by IntelliJ IDEA.
  User: Boss
  Date: 15.01.2022
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>

    <title>Edit page</title>

</head>
<body>

<a href="${pageContext.request.contextPath}/list">Back to list</a>

<security:authorize access="hasRole('ROLE_ADMIN')">

<form action="${pageContext.request.contextPath}/update" method="post">

<pre>

    <h1>Update product with id ${productId}</h1>

    ID:
    <input type="text" name="productId" readonly="readonly" value="${product.productId} "/>

    Name
    <input type="text" name="name" value="${product.productName}"/>

    Prise
    <input type="number" name="prise" value="${product.productPrise}"/>

    Category
    <select required name="categoryId">
       <c:forEach items="${categoryList}" var="category">
           <option value="${category.categoryId}">${category.categoryName}</option>
       </c:forEach>
    </select><br>

    <input type="submit" value="update" />

</pre>
    </security:authorize>
    <security:csrfInput/>
</form>
</body>
</html>
