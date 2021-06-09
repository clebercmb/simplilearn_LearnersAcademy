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
    <title>Subject Dashboard</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <h1>Subject Dashboard</h1>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!--jsp:useBean id="$aClasses" scope="request" type="java.util.List"/-->
            <c:forEach var="subject"  items="${subjects}">
                <tr>
                    <td><c:out value="${subject.id}"/></td>
                    <td><c:out value="${subject.name}"/></td>
                    <td><a href="<%=request.getContextPath()%>/deleteSubject?id=<c:out value="${subject.id}"/>">Delete</a></td>

                </tr>
            </c:forEach>

        </tbody>

    </table>


</body>
</html>
