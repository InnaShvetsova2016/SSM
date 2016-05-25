<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=windows-1251" language="java" %>

<html>
<head>
    <title>Salon App</title>

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
<form class="form-inline" action="/add_order" method="post">
    <div class="form-group">
        <input class="form-control" placeholder="Surname" name="surname">
    </div>
    <div class="form-group">
        <input class="form-control" placeholder="Name" name="name">
    </div>
    <div class="form-group">
        <input class="form-control" placeholder="Phone" name="phone">
    </div>
    <select class="form-control" placeholder="Service" name="product">
        <c:forEach items="${services}" var="s">
            <option><c:out value="${s.getType()}"/></option>
        </c:forEach>
    </select>
    <select class="form-control" placeholder="Master" name="master">
        <c:forEach items="${masters}" var="m">
            <option><c:out value="${m.getName()}"/></option>
        </c:forEach>
    </select>
    <select class="form-control" placeholder="Date" name="date">
        <c:forEach items="${dates}" var="d">
            <option><c:out value="${d}"/></option>
        </c:forEach>
    </select>
    <select class="form-control" placeholder="Time" name="time">
        <c:forEach items="${time}" var="m">
            <option><c:out value="${m}"/></option>
        </c:forEach>
    </select>

    <button type="submit" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Order
    </button>
</form>
<hr>
<form class="form-inline" action="/choose_date" method="post">
    <select class="form-control" name="chooseDate" onchange="this.form.submit();">
        <option>Date</option>
        <c:forEach items="${dates}" var="d">
            <option value="${d}">${d}</option>
        </c:forEach>
    </select>
</form>
<table class="main" align="top" width="100%">
    <tr>
        <td>Date</td>
        <c:forEach items="${masters}" var="m">
            <td><c:out value="${m.getName()}"/></td>
        </c:forEach>
    </tr>
    <tr>
        <td style="width: 80px;">
            ${chooseDate}
        </td>
        <c:forEach items="${masters}" var="m">
        <td align="top">
            <table align="top" width="100%">
                <tr>
                    <td style="width: 61px;"><b>Time</b></td>
                    <td><b>Client</b></td>
                    <td><b>Phone</b></td>
                    <td><b>Service</b></td>
                </tr>
                <c:forEach items="${orders}" var="o">
                    <tr>
                        <c:if test="${m.getName() eq o.getMasterName()}">
                            <c:if test="${chooseDate eq o.getSDate()}">
                                <td><c:out value="${o.getTime()}"/></td>
                                <td><c:out value="${o.getClientSN()}"/></td>
                                <td><c:out value="${o.getClientPhone()}"/></td>
                                <td><c:out value="${o.getServiceType()}"/></td>
                            </c:if>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            </c:forEach>
        </td>
    </tr>
</table>
<hr>
<p style="color:#FF0000" align="center"><c:out value="${messageOrder}"/></p>
<table class="main" align="center" width="100%">
    <tr>
        <td style="width: 60px;"><b>Code</b></td>
        <td><b>Date</b></td>
        <td><b>Time</b></td>
        <td><b>Client</b></td>
        <td><b>Phone</b></td>
        <td><b>Service</b></td>
        <td><b>Master</b></td>
        <td><b>Status</b></td>
        <td><b>Amount</b></td>
        <td><b>Payment</b></td>
        <td><b>Delete</b></td>
    </tr>

    <c:forEach items="${orders}" var="o">
        <tr>
            <td><c:out value="${o.getId()}"/></td>
            <td><c:out value="${o.getSDate()}"/></td>
            <td><c:out value="${o.getTime()}"/></td>
            <td><c:out value="${o.getClientSN()}"/></td>
            <td><c:out value="${o.getClientPhone()}"/></td>
            <td><c:out value="${o.getServiceType()}"/></td>
            <td><c:out value="${o.getMasterName()}"/></td>
            <td><c:out value="${o.getStatus()}"/></td>
            <td><c:out value="${o.getAmount()}"/></td>
            <td>
                <form class="form-inline" action="/add_amount" method="post">
                    <input type = "hidden" class="form-control" placeholder="Code" name="code" value="${o.getId()}">
                    <input class="form-control" placeholder="Amount" name="amount">
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        Payment
                    </button>
                </form>
            </td>
            <td>
                <form class="form-inline" action="/delete_order" method="post">
                    <input type = "hidden" class="form-control" placeholder="Code" name="code" value="${o.getId()}">
                    <button type="submit" class="btn btn-warning">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        Delete
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>