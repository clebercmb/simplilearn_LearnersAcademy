<%--
  Created by IntelliJ IDEA.
  User: cleber
  Date: 6/7/21
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register Classes</title>
</head>
<body>
    <%@include file="header.jsp"%>


    <div align="center">
        <h1>Register Class</h1>
        <sform:form method="post" action="addClass"  modelAttribute="classCommand">

            <label>
                Name: <sform:input path="name"/>
            </label>

            <input type="submit"/>
        </sform:form>

    </div>

</body>
</html>
