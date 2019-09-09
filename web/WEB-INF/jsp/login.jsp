<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.08.2019
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<c:if test="${logout}">
    <c:out value="you are logout"/>
</c:if>
<c:if test="${error}">
    <c:out value="wrong login or password"/>
</c:if>
<form action="login" method="post">
    <table>
        <tr>
            <td>
                Login:<br>
                Password:
            </td>
            <td>
                <label>
                    <input type="text" name="username">
                </label><br>
                <label>
                    <input type="password" name="password">
                </label>
            </td>
        </tr>
    </table>
    <button type="submit">Sign in</button>
</form>
</body>
</html>
