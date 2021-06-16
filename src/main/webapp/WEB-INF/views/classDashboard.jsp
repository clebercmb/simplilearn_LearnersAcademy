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
    <title>Class Dashboard</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <h1>Class Dashboard</h1>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Action 1</th>
                <th>Action 2</th>
                <th>Action 3</th>
                <th>Action 4</th>
                <th>Action 5</th>
            </tr>
        </thead>
        <tbody>
            <!--jsp:useBean id="$aClasses" scope="request" type="java.util.List"/-->
            <c:forEach var="aClass"  items="${classes}">
                <tr>
                    <td><c:out value="${aClass.classId}"/></td>
                    <td><c:out value="${aClass.name}"/></td>
                    <td><a href="<%=request.getContextPath()%>/showAssignSubjectToClass?id=<c:out value="${aClass.classId}"/>">Assign Subjects</a></td>
                    <td><a href="<%=request.getContextPath()%>/showAssignTeacherToClass?id=<c:out value="${aClass.classId}"/>">Assign Teachers</a></td>
                    <td><a href="<%=request.getContextPath()%>/showAssignStudentToClass?id=<c:out value="${aClass.classId}"/>">Assign Students</a></td>
                    <td><a href="<%=request.getContextPath()%>/classReport?id=<c:out value="${aClass.classId}"/>">Class Report</a></td>
                    <td><a href="<%=request.getContextPath()%>/deleteClass?id=<c:out value="${aClass.classId}"/>">Delete</a></td>
                </tr>
            </c:forEach>

        </tbody>

    </table>


</body>
</html>
