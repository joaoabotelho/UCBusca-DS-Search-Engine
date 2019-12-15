<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: joaobotelho
  Date: 14/12/2019
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - UC Busca</title>
</head>
<body>
<h3>Register</h3>
<s:form action="registerAction">
    <s:textfield name="username" label="Username" />
    <s:password name="password" label="Password"/>
    <s:submit/>
    <p> Failed to register </p>
</s:form>
<s:a action="goHome">Home</s:a>
<s:a action="goLogin">Login</s:a>
</body>
</html>

