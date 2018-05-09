<%--
  Created by IntelliJ IDEA.
  User: minogump
  Date: 2018/4/15
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <script type="text/javascript">
    $().ready(
        function (){
            $("#sub").click(
                function () {
                    var name = $("#username").val();
                    var password = $("password").val();
                    var user = {"username":name,"password":password};
                    $.ajax({
                        url : 'register',
                        type : 'POST',
                        data : JSON.stringify(user), // Request body
                        contentType : 'application/json; charset=utf-8',
                        dataType : 'json',
                        success : function(response) {
                            //请求成功
                            alert("你好" + response.username + "，当前时间是：" + response.time + "，欢迎访问：http://www.m-diary.cn");
                        },
                        error : function(msg) {
                            alert(msg);
                        }
                    })
                }
            )
        }
    );
  </script>
  <body>
  <form action="${baseController.register}" method="post">
    <input type="text" name="账号" id="username">
    <input type="text" name="密码" id="password">
    <input type="text" name="密码确认" id="password_again">
    <button type="submit" name="注册" id="sub"></button>
  </form>
  </body>
</html>
