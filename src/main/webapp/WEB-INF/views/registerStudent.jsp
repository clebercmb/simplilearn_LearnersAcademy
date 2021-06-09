<%--
  Created by IntelliJ IDEA.
  User: cleber
  Date: 6/7/21
  Time: 8:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register Student</title>
</head>
<body>
    <%@include file="header.jsp"%>

    <div align="center">
        <h1>Register Student</h1>
        <sform:form method="post" action="addStudent"  modelAttribute="studentCommand">

            <label>
                Name: <sform:input path="name"/>
            </label>

            <label>
                Email: <sform:input path="email"/>
            </label>

            <input type="submit"/>
        </sform:form>
    </div>
</body>
</html>
