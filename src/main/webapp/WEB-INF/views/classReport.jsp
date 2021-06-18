<%--
  Created by IntelliJ IDEA.
  User: cleber
  Date: 6/8/21
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Class Report</title>
</head>
<body>
<%@include file="header.jsp"%>

<div align="center">
    <h1>Class Report</h1>


        <fieldset style="width: 600px; display: flex; flex-direction: column; align-items: start">
            <legend><strong>${aClass.classId}-${aClass.name} </strong></legend>

            <c:forEach items="${aClass.subjects}" var="subject">
                <fieldset style="width: 600px; display: flex; flex-direction: column; align-items: start">
                    <legend>Choose Teacher for:  <strong>${subject.subjectId}-${subject.name} </strong></legend>

                    <c:forEach items="${subject.teachers}" var="teacher">
                        <label>
                            ${teacher.name}
                        </label><br/>
                    </c:forEach>

                </fieldset>

            </c:forEach>

            <br/> <br/>

        </fieldset>

</div>

</body>
</html>
