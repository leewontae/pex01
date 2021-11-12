<%--
  Created by IntelliJ IDEA.
  User: iwontae
  Date: 2021/11/10
  Time: 1:26 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>allpage</h1>

<sec:authorize access="isAnonymous()">
    <a href="/customLogin">로그인</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a href="/customLogout">로그아웃</a>
</sec:authorize>
</body>
</html>
