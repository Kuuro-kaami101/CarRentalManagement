<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car rental</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <style>
            .form-inline.my-2.my-lg-0 {
                display: flex;
                justify-content: center;
            }

            .input-group.input-group-sm {
                width: 600px; /* Adjust the width to your desired size */
                border: 2px solid #007bff;
                border-radius: 5px;
                overflow: hidden;
            }

            .input-group.input-group-sm select,
            .input-group.input-group-sm input {
                flex: 1;
                height: 40px;
                border: none;
                padding: 10px;
            }

            .input-group-append .btn.btn-secondary.btn-number {
                background-color: #007bff;
                color: #fff;
                border: none;
            }

            .input-group-append .btn.btn-secondary.btn-number:hover {
                background-color: #0056b3;
            }

            .search-option {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin: 80px 0;
            }

            .search {
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 16px;
                border-radius: 16px;
                background-color: #fff;
                box-shadow: 0 -3px 12px rgba(0,0,0,.1), 0 9px 12px rgba(75,75,75,.1);
                -webkit-backdrop-filter: blur(16px);
                backdrop-filter: blur(16px);
            }
            .background.space.sec{
                background-position:50% 50%;
                background-size:cover;
                border-radius:16px;
                line-height:24px;
                padding:80px 0px;
            }
        </style>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page contentType="text/html" pageEncoding="UTF-8" %>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="background space sec" style="background-image: url(images/Logo.png);">
                    <h1 class="bottom">Mioto&nbsp;-&nbsp;Cùng&nbsp;Bạn Đến Mọi Hành&nbsp;Trình</h1>
                </div>
            </div>
            <div class="container">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <div class="search-option">
                            <form action="search" method="post" class="search">
                                <div class="input-group input-group-sm">
                                    <select name="locationId" class="form-control">
                                        <option value="1">Quận Thanh Xuân, Hà Nội</option>
                                        <option value="2">Quận 1, Hồ Chí Minh</option>
                                        <option value="3">Quận Ngũ Hành Sơn, Đà Nẵng</option>
                                        <option value="4">Quận Sơn Trà, Đà Nẵng</option>
                                        <option value="5">Quận Thanh Khê, Đà nẵng</option>
                                    </select>
                                    <input name="startDate" type="date" class="form-control" placeholder="Start Date">
                                    <input name="endDate" type="date" class="form-control" placeholder="End Date">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-secondary btn-number">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </nav>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h2 class="text-center">Địa điểm nổi tiếng</h3>
                            <div class="row">
                                <div class="col-sm-4">
                                    <img class="img-fluid square-image" src="images/place1.jpg" alt="Image 1">
                                </div>
                                <div class="col-sm-4">
                                    <img class="img-fluid square-image" src="images/place2.jpg" alt="Image 2">
                                </div>
                                <div class="col-sm-4">
                                    <img class="img-fluid square-image" src="images/place3.jpg" alt="Image 3">
                                </div>
                            </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h2 class="text-center">Xe dành cho bạn</h3>
                            <div id="content" class="row">
                            <c:forEach items="${listCar}" var="o" varStatus="carIndex">
                                <c:if test="${carIndex.index < 8}">
                                    <div class="product col-12 col-md-6 col-lg-3">
                                        <div class="card">
                                            <img class="card-img-top" src="images/${o.image}" alt="Img" height="240">
                                            <div class="card-body">
                                                <input type="hidden" name="carID" value="${o.carId}">
                                                <h4 class="card-title show_txt"><a href="detail?carID=${o.carId}" title="View car">${o.name}</a></h4>
                                                <p class="card-text show_txt">${o.name}</p>
                                                <div class="row">
                                                    <div class="col">
                                                        <p class="btn btn-danger btn-block">${o.cost}k/1 ngày</p>
                                                    </div>
                                                    <div class="col">
                                                        <c:if test="${sessionScope.acc == 4}">
                                                            <a href="addItem?carId=${o.carId}" class="btn btn-success btn-block">Đặt xe</a>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                    url: "/Project/searchAjax",
                    type: "get", //send it through get method
                    data: {
                        txt: txtSearch
                    },
                    success: function (data) {
                        var row = document.getElementById("content");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
        </script>  
    </body>
</html>
