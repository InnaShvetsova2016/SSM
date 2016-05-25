<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Masters</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<style>
    body {
        background: #87CEEB;
    }

    hr {
        border: none; /* Убираем границу */
        background-color: #4169E1;
        height: 4px;
    }

    th, td:first-child {
        background: #225588;
        color: white;
        padding: 10px 20px;
        font-size: 14px;
        text-align: center;
    }

    th, td {
        border-style: solid;
        border-width: 0 1px 1px 0;
        border-color: white;
        font-size: 14px;
        text-align: center;
    }

    td {
        background: #7EC0EE;
    }
</style>
<body>
<div style="float: left; margin-right: 3px">
    <form action="/">
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-time" aria-hidden="true"></span> Timetable/Add order
        </button>
    </form>
</div>
<div style="float: left; margin-right: 3px">
    <form action="/clients">
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Clients
        </button>
    </form>
</div>
<div style="float: left; margin-right: 3px">
    <form action="/masters">
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-scissors" aria-hidden="true"></span> Masters
        </button>
    </form>
</div>
<div style="float: left; margin-right: 3px">
    <form action="/products">
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Services
        </button>
    </form>
</div>
<div style="float: left; margin-right: 80px">
    <form action="/salary">
        <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Salary
        </button>
    </form>
</div>
<button class="btn btn-info">
    <c:out value="${currentDate}"/>
</button>
<hr>
<form action="/add_master" class="form-inline" method="post">
    <div class="form-group">
        <label>Surname</label>
        <input class="form-control" placeholder="Surname" name="surname">
    </div>
    <div class="form-group">
        <label>Name</label>
        <input class="form-control" placeholder="Name" name="name">
    </div>
    <div class="form-group">
        <label>Phone</label>
        <input class="form-control" placeholder="Phone" name="phone">
    </div>
    <div class="form-group">
        <label>Status</label>
        <select class="form-control" placeholder="Status" name="status">
            <option value="active">active</option>
            <option value="non-active">non-active</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add
        master
    </button>
</form>
<hr>
<form class="form-inline" action="/master_find_surname" method="post">
    <div class="form-group">
        <input class="form-control" placeholder="Surname" name="surname">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
</form>
<hr>
<form class="form-inline" action="/master_find_phone" method="post">
    <div class="form-group">
        <input class="form-control" placeholder="Phone" name="phone">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
</form>
<hr>

<table class="main" align="center" width="100%">
    <tr>
        <td style="width: 60px;"><b>Code</b></td>
        <td><b>Surname</b></td>
        <td><b>Name</b></td>
        <td><b>Phone</b></td>
        <td><b>Status</b></td>
        <td><b>Change status</b></td>
        <td><b>Change phone</b></td>
        <td><b>Delete</b></td>
    </tr>
    <p style="color:#FF0000" align="center"><c:out value="${message2}"/></p>
    <c:forEach items="${masters_find_phone}" var="m">
        <tr>
            <td><c:out value="${m.getId()}"/></td>
            <td><c:out value="${m.getSurname()}"/></td>
            <td><c:out value="${m.getName()}"/></td>
            <td><c:out value="${m.getPhone()}"/></td>
            <td><c:out value="${m.getStatus()}"/></td>
            <td><form class="form-inline" action="/change_status" method="post">
                <div class="form-group">
                    <input type = "hidden" class="form-control" placeholder="# master" name="id_change" value="${m.getId()}">
                </div>
                <button type="submit" class="btn btn-warning">change status</button>
            </form></td>
            <td>
                <form class="form-inline" action="/changeMasterPhone" method="post">
                    <input type = "hidden" class="form-control" placeholder="# master" name="id_change" value="${m.getId()}">
                    <input class="form-control" placeholder="New phone" name="newPhone">
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        change phone
                    </button>
                </form>
            </td>
            <td><form class="form-inline" action="/delete_master" method="post">
                <div class="form-group">
                    <input type = "hidden" class="form-control" placeholder="# master" name="id_delete" value="${m.getId()}">
                </div>
                <button type="submit" class="btn btn-danger">delete master</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>