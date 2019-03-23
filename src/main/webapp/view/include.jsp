<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/css/style.css" />" >

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<style>
    .menu-bar {
        text-align: center;
        margin-top: 20px;
    }

    .menu-bar ul {
        display: -webkit-box;
        margin-top: 10px;
    }

    .menu-bar ul li {
        text-decoration: none;
        list-style: none;
        color: #000000;
        margin-right: 50px;
        font-size: 18px;
        font-weight: 600;
    }

    .menu-bar ul li a {
        text-decoration: none;
        color: #000000;
    }

    .menu-bar ul li a:hover {
        text-decoration: none;
        color: #000000;
    }

    .menu-bar ul li a.focus, .menu-bar ul li a:focus {
        text-decoration: underline;
        color: #000000;
    }

    .addStudentForm {
        display: grid;
        text-align: center;
    }

    .addStudentForm button {
        background: #000;
        color: #fff;
        font-size: 18px;
        font-weight: 700;
    }

    .addStudentForm .btn:hover {
        color: #fff;
    }

    .addStudentForm .btn.focus, .addStudentForm .btn:focus {
        color: #fff;
        font-size: 20px;
        font-weight: 700;
    }

    .student-table {
        text-align: center;
    }

    .student-table .huge-title {
        padding-top: 25px;
        font-weight: 800;
        font-size: 18px;
    }

    .address-titles {
        font-size: 16px;
        font-weight: 700;
    }

    .table-container .student-table tr td.student-info {
        padding-top: 15px;
    }
</style>
