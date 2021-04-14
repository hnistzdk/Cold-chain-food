<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
<%--    <c:forEach items="${foods}" var="foods">--%>
<%--&lt;%&ndash;        取得单一字段&ndash;%&gt;--%>
<%--        <h1>${foods.getFood_name()}</h1>--%>
<%--        <h2>${foods}</h2>--%>
<%--    </c:forEach>--%>
<%--&lt;%&ndash;    不用循环的情况&ndash;%&gt;--%>
<%--        <h1>${foods.get(0).getAddress()}</h1>--%>
<%--   <h1></h1>--%>
    <h1>${food.getFoodName()}</h1>
    <h2>${food}</h2>
</div>


</body>
</html>
