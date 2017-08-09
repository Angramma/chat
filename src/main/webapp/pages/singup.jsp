<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Зарегестрироваться</title>
    <link href="<c:url value="/styles/main.css" />" rel="stylesheet">
</head>
<body>
<security:csrfInput/>
<!-- TODO: валидация надежности паролей, ограничение длины полей, сравнение подтверждения паролей, активация по почте\телефону -->
<form method="POST" action="/singup">
    <table>
        <tr>
            <td>Email:</td>
            <!-- TODO: отрефакторить login на email-->
            <td><input type="text" name="login" required /></td>
        </tr>
        <tr>
            <td>Nickname:</td>
            <td><input type="text" name="nickname" required /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" id="password" name="password" required /></td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td><input type="password" id="confirmPassword" name="confirmPassword" required /></td>
        </tr>

        <tr>
            <td align="right" colspan="2">
                <input name="singUp" value="Зарегестрироваться"  type="submit">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
