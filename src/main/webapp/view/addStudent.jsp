<%--
  Created by IntelliJ IDEA.
  User: maksim
  Date: 17/03/19
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student Page</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
<div class="container main-container">
    <div class="row">
        <form action="/" method="get" id="seachEmployeeForm" role="form" >
            <div class="form-group col-xs-5">
                <input type="text" name="studentFullName" id="studentFullName" class="form-control" required="true"
                       placeholder="ФИО"/>
            </div>
            <div class="form-group col-xs-5">
                <input type="text" name="studentGroup" id="studentGroup" class="form-control" required="true"
                       placeholder="№ группы"/>
            </div>
            <div class="form-group col-xs-5">
                <input type="text" name="studentCity" id="studentCity" class="form-control" required="true"
                       placeholder="Город"/>
            </div>
            <div class="form-group col-xs-5">
                <input type="text" name="studentStreet" id="studentStreet" class="form-control" required="true"
                       placeholder="Улица"/>
            </div>
            <div class="form-group col-xs-5">
                <input type="text" name="studentHouse" id="studentHouse" class="form-control" required="true"
                       placeholder="Дом"/>
            </div>
            <div class="form-group col-xs-5">
                <input type="text" name="studentFlat" id="studentFlat" class="form-control" required="true"
                       placeholder="Квартира"/>
            </div>
            <button type="submit" class="btn btn-info">
                <span class="glyphicon glyphicon-search">Добавить</span>
            </button>
            <br />
        </form>
    </div>
</div>
</body>
</html>
