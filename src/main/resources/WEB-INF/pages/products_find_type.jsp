<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Services</title>

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
<form action="/add_product" class="form-inline" method="post">
    <div class="form-group">
        <label>Type</label>
        <input class="form-control" placeholder="Type" name="type">
    </div>
    <div class="form-group">
        <label>Price</label>
        <input class="form-control" placeholder="Price" name="price">
    </div>
    <div class="form-group">
        <label>Time limit</label>
        <select class="form-control" placeholder="Time limit" name="timeLimit">
            <option value="30 min">30 min</option>
            <option value="1 hour">1 hour</option>
            <option value="2 hours">2 hours</option>
            <option value="3 hours">3 hours</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add
        service
    </button>
</form>
<hr>
<form class="form-inline" action="/product_find_type" method="post">
    <div class="form-group">
        <input class="form-control" placeholder="Type" name="type">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
</form>
<hr>

<table class="main" align="center" width="100%">
    <tr>
        <td style="width: 60px;"><b>Code</b></td>
        <td><b>Type</b></td>
        <td><b>Price</b></td>
        <td><b>Time limit</b></td>
    </tr>
    <p style="color:#FF0000" align="center"><c:out value="${message2}"/></p>
    <c:forEach items="${products_find_type}" var="p">
        <tr>
            <td><c:out value="${p.getId()}"/></td>
            <td><c:out value="${p.getType()}"/></td>
            <td><c:out value="${p.getPrice()}"/></td>
            <td><c:out value="${p.getTimeLimit()}"/></td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>