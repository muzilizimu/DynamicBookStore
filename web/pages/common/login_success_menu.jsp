<%--
  Created by IntelliJ IDEA.
  User: 木子
  Date: 2021/6/29
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临小李书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</body>
</html>
