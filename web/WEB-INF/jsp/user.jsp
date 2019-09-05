<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.08.2019
  Time: 2:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
<c:out value="User: ${currentUser.getName()}"/><br>
<c:out value="Login: ${currentUser.getLogin()}"/><br>
<c:out value="Password: ${currentUser.getPassword()}"/><br>
<c:out value="Roles: ${currentUser.getRoles()}"/>
</body>
</html>
