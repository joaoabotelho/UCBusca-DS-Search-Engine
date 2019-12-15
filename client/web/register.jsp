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
    <title>Register - UC Busca</title>
</head>
<body>
<h3>Register</h3>
<s:form action="registerAction">
    <s:textfield name="user.username" label="Username" />
    <s:password name="user.password" label="Password"/>
    <s:submit/>
</s:form>
</body>
</html>
