<%--
  Created by IntelliJ IDEA.
  User: cleber
  Date: 6/7/21
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Student Dashboard</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <h1>Student Dashboard</h1>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student"  items="${$students}">
                <tr>
                    <td><c:out value="${student.studentId}"/></td>
                    <td><c:out value="${student.name}"/></td>
                    <td><c:out value="${student.email}"/></td>
                    <td><a href="<%=request.getContextPath()%>/deleteStudent?id=<c:out value="${student.studentId}"/>">Delete</a></td>
                </tr>
            </c:forEach>

        </tbody>

    </table>


</body>
</html>
