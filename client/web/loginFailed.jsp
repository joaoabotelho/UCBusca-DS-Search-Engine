<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: joaobotelho
  Date: 14/12/2019
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - UC Busca</title>
</head>
<body>
<h3>Login</h3>
<s:form action="loginAction">
    <s:textfield name="username" label="Username" />
    <s:password name="password" label="Password"/>
    <s:submit/>
    <p> Failed to login </p>
</s:form>

<s:a action="goRegister">Register</s:a>
<s:a action="goHome">Home</s:a>
</body>
</html>
