<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <title>Login</title>
    <link href="<c:url value="/styles/main.css" />" rel="stylesheet">
</head>
<body style="
    height: 150px;
    overflow: hidden;
    width: 260px;">
<sec:authorize access="isAuthenticated()">
    <c:redirect url="chat.jsp"></c:redirect>
</sec:authorize>
<security:csrfInput/>

  <table >
    <form:form action="/to_singup"  id="login_form" method="POST">
      <tr>
        <span>Don't have a Chat account?  <input value="SinUp"  type="submit"></span>
      </tr>
    </form:form>
    <form:form action="/login"  id="login_form" method="POST">
    <tr>
      <td>Login:</td>
      <td><input type="text" name="login" required /></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type="password" name="password" required /></td>
    </tr>

    <tr>
      <td align="right" colspan="2">
        <input value="Войти"  type="submit">
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>