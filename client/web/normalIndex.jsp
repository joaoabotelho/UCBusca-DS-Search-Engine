<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>UCbusca</title>
</head>
<body>
<h3>Welcome to UC Busca</h3>
<s:a action="logoutAction">Logout</s:a>

<s:form action="search">
    <s:textfield name="search" label="Search" />
    <s:submit/>
</s:form>

</body>
</html>
