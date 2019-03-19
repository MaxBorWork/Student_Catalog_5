<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Student Catalog</title>
    <%@ include file="/view/include.jsp" %>
</head>

<body>
    <div class="container menu-bar">
        <div class="row">
            <ul>
                <li><a href="view/addStudent.jsp">Add student</a></li>
                <li>Search Student</li>
            </ul>
        </div>
    </div>
    <div class="container table-container">
        <div class="row">
            <table class="student-table">
                <tr>
                    <td>№</td>
                    <td>ФИО</td>
                    <td>Группа</td>
                    <td>Город</td>
                    <td>Улица</td>
                    <td>Дом</td>
                    <td>Квартира</td>
                </tr>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.surname} ${student.name} ${student.secondName}</td>
                        <td>${student.groupNum}</td>
                        <td>${student.city}</td>
                        <td>${student.address.street}</td>
                        <td>${student.address.house}</td>
                        <td>${student.address.flat}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
