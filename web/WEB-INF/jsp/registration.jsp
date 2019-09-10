<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.09.2019
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
<body>
<c:if test="${error}">
    <c:out value="The user with the same login is already exist"/>
</c:if>
<c:if test="${matchingError}">
    <c:out value="Passwords do not match"/>
</c:if>
<form action="<c:url value="/registration"/>" method="post">
    <table>
        <tr>
            <td>
                Login:<br>
                Name:<br>
                Password:<br>
                Confirm password:
            </td>
            <td>
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                <input name="roles" value="user" type="hidden">
                <label>
                    <input type="text" name="login">
                </label><br>
                <label>
                    <input type="text" name="name">
                </label><br>
                <label>
                    <input type="password" name="password">
                </label><br>
                <label>
                    <input type="password" name="passwordConfirm">
                </label>
            </td>
        </tr>
    </table>
    <button type="submit">Sign Up</button>
</form>
</body>
</html>
