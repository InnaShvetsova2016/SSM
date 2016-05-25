<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>

<html>
<head>
    <title>Salary calculation</title>

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
<form class="form-inline" action="/calculate" method="post">
    <div class="form-group">
        <input class="form-control" placeholder="from date" name="fromDate">
    </div>
    <div class="form-group">
        <input class="form-control" placeholder="to date" name="toDate">
    </div>
    <button type="submit" class="btn btn-primary">
        <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
        Calculate
    </button>
</form>

<hr>
<table class="main" align="center" width="100%">
    <tr>
        <td style="width: 200px;"><b>Master</b></td>
        <td style="width: 200px;"><b>Orders number</b></td>
        <td><b>Profit</b></td>
        <td><b>Salary</b></td>
    </tr>

    <c:forEach items="${salary}" var="s">
        <tr>
            <td><c:out value="${s.getMasterName()}"/></td>
            <td><c:out value="${s.getCount()}"/></td>
            <td><c:out value="${s.getProfit()}"/></td>
            <td><c:out value="${s.getProfit()*0.5}"/></td>
        </tr>
    </c:forEach>
    <tr>
        <td><b>Total</b></td>
        <td><b><c:out value="${totalCount}"/></b></td>
        <td><b><c:out value="${totalProfit}"/></b></td>
        <td><b><c:out value="${totalProfit*0.5}"/></b></td>
    </tr>
</table>
<hr>
</body>
</html>