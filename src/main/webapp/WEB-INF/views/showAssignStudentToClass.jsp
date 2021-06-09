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
    <title>Assign Student to Class</title>
</head>
<body>
    <%@include file="header.jsp"%>

    <div align="center">
        <h1>Assign Student to Class</h1>
        <sform:form method="post" action="addAssignStudentToClass"  modelAttribute="assignStudentToClassCommand">

            <fieldset style="width: 500px; display: flex; flex-direction: column; align-items: start">
                <legend>Choose Students for:  <strong>${assignStudentToClassCommand.aClass.id}-${assignStudentToClassCommand.aClass.name} </strong></legend>

                <sform:hidden path="idClass" value="${assignStudentToClassCommand.aClass.id}"  />
                <c:forEach items="${assignStudentToClassCommand.studentList}" var="student">
                    <label>
                        <sform:checkbox path="studentListIds" value="${student.id}"  checked="${student.checked}"  /> ${student.name}
                    </label><br/>
                </c:forEach>
                <br/> <br/>
                <input type="submit"/>
            </fieldset>
        </sform:form>

    </div>

</body>
</html>
