<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Student Catalog</title>
    <%@ include file="/view/include.jsp" %>
</head>

<body>
    <%@ include file="/view/menu.jsp" %>
    <div class="container table-container">
        <div class="row">
            <table class="table student-table">
                <tr>
                    <td rowspan="2" class="huge-title">№</td>
                    <td rowspan="2" class="huge-title">ФИО</td>
                    <td rowspan="2" class="huge-title">Группа</td>
                    <td colspan="4" style="font-size:18px;font-weight:800">Адрес прописки</td>
                    <td rowspan="2"></td>
                    <td rowspan="2"></td>
                </tr>
                <tr class="address-titles">
                    <td>Город</td>
                    <td>Улица</td>
                    <td>Дом</td>
                    <td>Квартира</td>
                </tr>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td class="student-info"><span>${student.id}</span></td>
                        <td class="student-info"><span>${student.surname} ${student.name} ${student.secondName}</span></td>
                        <td class="student-info"><span>${student.groupNum}</span></td>
                        <td class="student-info"><span>${student.city}</span></td>
                        <td class="student-info"><span>${student.address.street}</span></td>
                        <td class="student-info"><span>${student.address.house}</span></td>
                        <td class="student-info"><span>${student.address.flat}</span></td>
                        <td>
                            <form action="" method="POST">
                                <button type="submit" class="editBtn btn student-button"
                                        name="studentButton" value="editStudent${student.id}">Изменить</button>
                            </form>
                        </td>
                        <td>
                            <form action="" method="POST">
                                <button type="submit" class="delBtn btn student-button"
                                        name="studentButton" value="delStudent${student.id}">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
