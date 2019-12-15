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
    <title>Promote User - UC Busca</title>
</head>
<body>
<h3>Admin - Promote User</h3>
<s:form action="adminPromoteUserAction">
    <s:textfield name="username" label="Username" />
    <s:submit/>
</s:form>
</body>
</html>