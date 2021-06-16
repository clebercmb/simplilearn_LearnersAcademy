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
    <title>Assign Subject to Class</title>
</head>
<body>
    <%@include file="header.jsp"%>

    <div align="center">
        <h1>Assign Subject to Class</h1>
        <sform:form method="post" action="assignTeacherToSubject"  modelAttribute="assignTeacherToSubjectCommand">

            <fieldset style="width: 500px">
                <legend>Choose Subjects for:  <strong>${assignTeacherToSubjectCommand.teacher.teacherId}-${assignTeacherToSubjectCommand.teacher.name} </strong></legend>

                <sform:hidden path="idTeacher" value="${assignTeacherToSubjectCommand.teacher.teacherId}"  />
                <c:forEach items="${assignTeacherToSubjectCommand.subjectList}" var="subject">
                    <label>
                        <sform:checkbox path="subjectListIds" value="${subject.id}"    checked="${subject.checked}"  /> ${subject.name}
                    </label><br/>
                </c:forEach>
                <br/> <br/>
                <input type="submit"/>
            </fieldset>
        </sform:form>

    </div>

</body>
</html>
