<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mixi vivu</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .no-car-message {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 200px;
                font-size: 20px;
                font-weight: bold;
            }
            .search-option {
                margin-bottom: 20px;
            }
            .product {
                margin-bottom: 20px;
            }
            .card-img-top {
                object-fit: cover;
                height: 240px;
            }
            .card-body {
                padding: 10px;
            }
            .card-title {
                margin-bottom: 10px;
            }
            .card-title a {
                text-decoration: none;
                color: #333;
            }
            .card-title a:hover {
                color: #000;
            }
            .btn-number {
                font-size: 14px;
            }
            .btn-block {
                width: 100%;
            }
        </style>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <jsp:include page="Menu.jsp"/>
        <div class="container">
            <div class="col">
                <nav aria-label="breadcrumb">
                    <div class="search-option">
                        <form action="SearchControl" method="get" class="search" onsubmit="return validateForm()">
                            <div class="input-group input-group-sm">
                                <select id="listlo" onchange="getselectValue();" name="locationId" class="form-control" style="height: 44px!important;">
                                    <c:forEach items="${listLocation}" var="o">
                                        <option value="${o.locationId}">${o.address}</option>
                                    </c:forEach>
                                </select>
                                <input name="startDate" type="date" class="form-control" value="${startDate}" required>
                                <input name="endDate" type="date" class="form-control" value="${endDate}" required>
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-secondary btn-number">Tìm xe</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <jsp:include page="Left.jsp"/>
                <div class="col-sm-9">
                    <div id="content" class="row">
                        <c:if test="${empty listCar}">
                            <div class="no-car-message">
                                <p>Không có xe phù hợp</p>
                            </div>
                        </c:if>
                        <c:forEach items="${listCar}" var="o">
                            <div class="product col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="images/cars/${o.image}" alt="Img">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt">
                                            <a href="detail?carId=${o.carId}&startDate=${startDate}&endDate=${endDate}" title="View car">${o.name}</a>
                                        </h4>
                                        <c:forEach items="${listLocation}" var="l">
                                            <c:if test="${o.locationId == l.locationId}">
                                                <p>${l.address}</p>
                                            </c:if>
                                        </c:forEach>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.pricePerDay}k/1 ngày</p>
                                            </div>
                                            <div class="col">
                                                <c:if test="${sessionScope.acc == 4}">
                                                    <a href="detail?carId=${o.carId}&startDate=${startDate}&endDate=${endDate}" class="btn btn-success btn-block">Đặt xe</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${searchError != null}">
            <script>
                window.addEventListener("load", function () {
                    alert("${searchError}");
                })
                function validateForm() {
                    var startDate = document.getElementById("startDate").value;
                    var endDate = document.getElementById("endDate").value;
                    var currentDate = new Date().toISOString().split("T")[0];
                    if (startDate < currentDate) {
                        alert("Ngày đặt xe không hợp lệ");
                        return false;
                    }
                    if (startDate > endDate) {
                        alert("Ngày đặt xe không hợp lệ");
                        return false;
                    }
                    return true;
                }
            </script>
        </c:if>
        <jsp:include page="Footer.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                function validateForm() {
                    var startDate = document.getElementById("startDate").value;
                    var endDate = document.getElementById("endDate").value;
                    var currentDate = new Date().toISOString().split("T")[0];
                    if (startDate < currentDate) {
                        alert("Ngày đặt xe đã qua");
                        return false;
                    }
                    if (startDate > endDate) {
                        alert("Ngày trả xe trước ngày đặt xe");
                        return false;
                    }
                    if (startDate == endDate) {
                        alert("Ngày trả xe trùng ngày đặt xe");
                        return false;
                    }
                    return true;
                }
        </script>
        <script>
    var selectedLocationId = "${locationId}";
    document.addEventListener("DOMContentLoaded", function () {
        var selectElement = document.getElementById("listlo");
        selectElement.value = selectedLocationId;
    });
        </script>
    </body>
</html>
