<%--
  Created by IntelliJ IDEA.
  User: iwontae
  Date: 2021/11/10
  Time: 3:39 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Logout Page</h1>
<form action ="/customLogout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button>로그아웃</button>
    <%--post방식으로 로그아웃 하는 경우 스프링 시큐리티의 내부에서 동작한다.--%>
</form>
</body>
</html>
