<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    $(document).ready(function (){
            $('#loginform').submit(function (event) {
                event.preventDefault();
                ajaxPost();
            });


            function ajaxPost(){
                    var name = $('#username').val();
                    var password = $('#password').val();
                    var user = {"username":name,"password":password};
                    $.ajax({
                        headers: {
                            "content-Type":"application/json",
                            'Accept': 'application/json',
                        },
                        url : 'register',
                        type : 'POST',
                        data : JSON.stringify(user),
                        dataType : 'json',
                        success : function(response) {
                            //请求成功
                            alert("success");
                        }
                    })
                }
        }
    );
  </script>
  <body>
  <div>
    <form id="loginform" action="/register" method="post">
      <table>
        <tr>
          <td>账号：<input type="text" name="username" id="username" value="${userPojo.username}"></td>
          <td>密码：<input type="text" name="password" id="password" value="${userPojo.password}"></td>
          <td>密码确认：<input type="text" name="password" id="password_again"></td>
          <td>提交<button type="submit" name="register" id="sub"></button></td>
        </tr>
      </table>
    </form>

    <form:form action="/register" method="post" modelAttribute="user">
      <table>
        <tr>
          <spring:bind path="username">
            <td>账号：<form:input path="username"/></td>
          </spring:bind>
          <spring:bind path="password">
            <td>密码：<form:input path="password"/></td>
          </spring:bind>
          <td>提交 <input type="submit"/></td>
        </tr>
      </table>
    </form:form>
  </div>
  </body>
</html>
