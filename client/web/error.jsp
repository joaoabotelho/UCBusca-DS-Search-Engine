<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: joaobotelho
  Date: 14/12/2019
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not Authorized</title>
</head>

<body>
You can't do that. Please Login first.
<s:a action="goLogin">Login</s:a>
<s:a action="goHome">Home</s:a>
</body>
</html>
