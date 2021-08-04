<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含base标签、Css样式、JQuery文件--%>
    <%@ include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            $("#username").blur(function(){

                $.getJSON("http://localhost:8080/DynamicBookStore/userServlet","action=userExist&username=" + this.value,function(msg){
                    if (msg.b){
                        $("span.errorMsg").text("用户名已存在");
                    }else{
                        $("span.errorMsg").text("用户名可用");
                    }
                });

            });


            $("#sub_btn").click(function () {
                //用户名验证
                var usernameText = $("#username").val();
                var userReg = /^\w{5,12}$/;
                if (!userReg.test(usernameText)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                //密码验证
                var passwordText = $("#password").val();
                var passwordReg = /^\w{5,12}$/;
                if (!passwordReg.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                //确认密码
                var confirmPass = $("#repwd").val();
                if (confirmPass != passwordText) {
                    $("span.errorMsg").text("两次密码不一致");
                    return false;
                }
                //邮箱验证
                var emailText = $("#email").val();
                var emailReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
                if (!emailReg.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法");
                    return false;
                }
                //验证码
                var codeText = $("#code").val();
                if ($.trim(codeText) == "") {
                    $("span.errorMsg").text("不能为空");
                    return false;
                }

            });
            $("#code_img").click(function (){
               this.src = "${basePath}kaptcha.jpg?=" + new Date();
            });

        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" name="code" id="code" value="abcd"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px;width: 100px ;height: 38px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态引入页脚--%>
<%@include file="/pages/common/foot.jsp" %>
</body>
</html>