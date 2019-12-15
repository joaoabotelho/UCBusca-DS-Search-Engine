<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: joaobotelho
  Date: 14/12/2019
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h3>Search result Records:</h3>
<s:iterator  value="result">
    <fieldset>
        <s:property value="title"/><br/>
        <s:property value="url"/><br/>
        <s:property value="link_count"/><br/>
    </fieldset>
</s:iterator>

<s:a action="goHomeNormal">Home</s:a>
</body>
</html>
