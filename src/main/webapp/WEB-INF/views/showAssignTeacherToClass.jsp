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
    <title>Assign Teacher to Class</title>
</head>
<body>
    <%@include file="header.jsp"%>

    <div align="center">
        <h1>Assign Teacher to Class</h1>
        <sform:form method="post" action="addAssignTeacherToClass"  modelAttribute="assignTeacherToClassCommand">

            <fieldset style="width: 600px; display: flex; flex-direction: column; align-items: start">
                <legend>Choose Teacher for:  <strong>${assignTeacherToClassCommand.aClass.classId}-${assignTeacherToClassCommand.aClass.name} </strong></legend>

                <sform:hidden path="idClass" value="${assignTeacherToClassCommand.aClass.classId}"  />

                <c:forEach items="${assignTeacherToClassCommand.aClass.subjectList}" var="subject">
                    <fieldset style="width: 600px; display: flex; flex-direction: column; align-items: start">
                        <legend>Choose Teacher for:  <strong>${subject.subjectId}-${subject.name} </strong></legend>

                        <c:forEach items="${subject.teacherList}" var="teacher">
                            <label>
                                <sform:checkbox path="teacherListId" value="${subject.subjectId}|${teacher.teacherId}" checked="${teacher.isChecked}" /> ${teacher.name}
                            </label><br/>
                        </c:forEach>

                    </fieldset>

                 </c:forEach>

                <br/> <br/>
                <input type="submit"/>
            </fieldset>
        </sform:form>

    </div>

</body>
</html>
