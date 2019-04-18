<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student Page</title>
    <%@ include file="/view/include.jsp" %>
</head>
<body>
    <%@ include file="/view/menu.jsp" %>
    <div class="container main-container">
        <div class="row">
            <div class="add-form col-sm-6 col-sm-offset-3">
                <form action="<c:url value="/addStudent"/>" method="post" id="addStudentForm" role="form" class="addStudentForm">
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" id="studentFullName"
                               name="studentFIO" value="${studentFIO}"
                               class="form-control" required placeholder="ФИО"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <select name="studentGroup" id="studentGroup"
                                class="form-control" required placeholder="№ группы">
                            <c:forEach items="${groupsList}" var="groupNum">
                                <option>${groupNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentCity" value="${studentCity}" id="studentCity"
                               class="form-control" required placeholder="Город"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentStreet" value="${studentStreet}" id="studentStreet"
                               class="form-control" required placeholder="Улица"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentHouse" value="${studentHouse}" id="studentHouse"
                               class="form-control" required placeholder="Дом"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="text" name="studentFlat" value="${studentFlat}" id="studentFlat"
                               class="form-control" required placeholder="Квартира"/>
                    </div>
                    <div class="form-group col-xs-10 col-xs-offset-1">
                        <input type="hidden" name="studentID" value="${studentID}" id="studentID"
                               class="form-control" placeholder="ID"/>
                    </div>
                    <button type="submit" class="btn submit-button col-sm-6 col-sm-offset-3">
                        <span>Добавить</span>
                    </button>
                    <br />
                </form>
            </div>
        </div>
    </div>
</body>
<script>

</script>
</html>
