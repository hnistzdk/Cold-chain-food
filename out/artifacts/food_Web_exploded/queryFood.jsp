<%--
  Created by IntelliJ IDEA.
  User: zdk
  Date: 2021/4/12
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询食品</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/queryFood">点击查询</a>

<form action="${pageContext.request.contextPath}/queryFoodById" method="post">
    请输入查询ID号：<input name="id" type="text">
    <input type="submit">
    <span>${isnull}</span>
</form>
</body>
</html>
