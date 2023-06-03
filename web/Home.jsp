<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car rental</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <jsp:include page="Menu.jsp"></jsp:include>
    
    <div class="container">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="home">Home</a>
                        </li>
                        <c:if test="${sessionScope.acc == 1}"> 
                            <li class="breadcrumb-item">
                                <a href="display">Giỏ hàng</a>
                            </li> 
                        </c:if>     
                        <li class="breadcrumb-item" style="flex-grow: 1;"></li>
                        <li class="">
                            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                                <div class="input-group input-group-sm">
                                    <input oninput="searchByName(this)" value="${txtS}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Địa điểm">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-secondary btn-number">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </li>    
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">

            <jsp:include page="Left.jsp"></jsp:include>
            <div class="col-sm-9">
                <div id="content" class="row">
                    <c:forEach items="${listCar}" var="o">
                        <div class="product col-12 col-md-6 col-lg-4">
                            <div class="card">
                                <img class="card-img-top" src="images/${o.image}" alt="Img" height="240">
                                <div class="card-body">
                                    <input type ="hidden" name ="carID" value="${o.carId}">
                                    <h4 class="card-title show_txt"><a href="detail?carID=${o.carId}" title="View car">${o.name}</a></h4>
                                    <p class="card-text show_txt">${o.name}</p>
                                    <div class="row">
                                        <div class="col">
                                            <p class="btn btn-danger btn-block">${o.cost}k</p>
                                        </div>
                                        <div class="col">
                                            <c:if test="${sessionScope.acc == null}">
                                                <a href="Login.jsp" class="btn btn-success btn-block">Đặt xe</a>
                                            </c:if>
                                            <c:if test="${sessionScope.acc == 1}">
                                                <a href="bookcar?carId=${o.carId}"class="btn btn-success btn-block">Đặt xe</a>
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
                  
    <jsp:include page="Footer.jsp"></jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        function searchByName(param){
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
