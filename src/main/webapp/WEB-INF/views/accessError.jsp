<%--
  Created by IntelliJ IDEA.
  User: iwontae
  Date: 2021/11/10
  Time: 2:27 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>accessError</title>
</head>
<body>
<h1>Access Denied Page</h1>
<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"/> </h2>
<%--httpservlet request안에 SPRING_SECURITY_403_EXCEPTION 이라는 이름으로 accessDeniedException 객페가 전달된다. --%>
<h2><c:out value="${msg}"/></h2>
</body>
</html>
