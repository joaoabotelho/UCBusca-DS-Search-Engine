<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: joaobotelho
  Date: 15/12/2019
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged Admin</title>
</head>
<body>
<s:a action="logoutAction">Logout</s:a>
<s:a action="goAdminUrl">Add new URL</s:a>
<s:a action="goAdminPromote">Promote User to Admin</s:a>
<s:a action="previousSearchAdmin">Last Searches</s:a>

<s:form action="searchAdmin">
    <s:textfield name="search" label="Search" />
    <s:submit/>
</s:form>
</body>
</html>
