<%--
  Created by IntelliJ IDEA.
  User: 木子
  Date: 2021/7/2
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">${requestScope.page.pageNo - 1}</a>
    </c:if>
    【${requestScope.page.pageNo}】

    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">${requestScope.page.pageNo + 1}</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="pageButton" type="button" value="确定">
    <script type="text/javascript">
        $(function (){
            $("#pageButton").click(function(){
                var pageNo = $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};
                if ( pageNo > pageTotal || pageNo < 1){
                    return false;
                }
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        })
    </script>
</div>
</body>
</html>
