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
  <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
  <script type="text/javascript">
    $(document).ready(
        function (){
            $("#sub").click(
                function () {
                    var name = $('#username').val();
                    var password = $('#password').val();
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
  <form action="">
    账号：<input type="text" name="username" id="username"><br/>
    密码：<input type="text" name="password" id="password"><br/>
    密码确认：<input type="text" name="password" id="password_again"><br/>
    提交<button type="submit" name="register" id="sub"></button>
  </form>

  <br/>
  <br/>

  <form action="/register1" method="post" enctype="application/x-www-form-urlencoded">
    账号：<input type="text" name="username" id="username1"><br/>
    密码：<input type="text" name="password" id="password1"><br/>
    密码确认：<input type="text" name="password_again" id="password_again1"><br/>
    提交<button type="submit" name="register" id="sub1"></button>
  </form>
  </body>
</html>
