<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý xe</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://static.elfsight.com/platform/platform.js" data-use-service-core defer></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img {
                width: 200px;
                height: 120px;
                object-fit: cover;
                border-radius: 5px;
            }
            .title{
                display: flex;
                justify-content: center;

            }

            .title h2 {
                text-align: center;
                margin-top: 20px;
                margin-bottom: 30px;
                font-size: 28px;
            }
            .title add-button{
                padding: 8px 12px;
                margin: 0 5px;
                font-size: 14px;
                font-weight: 500;
                text-transform: uppercase;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s ease;
                background-color: #007bff;
                border: none;
            }

            table {
                width: 100%;
                margin-bottom: 20px;
                border-collapse: collapse;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }

            th, td {
                padding: 12px 15px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #343a40;
                color: #fff;
                font-weight: 600;
                text-transform: uppercase;
            }

            tr:nth-child(even) {
                background-color: #f8f8f8;
            }

            .button-container {
                display: flex;
                justify-content: center;
            }

            .edit-button, .delete-button {
                padding: 8px 12px;
                margin: 0 5px;
                font-size: 14px;
                font-weight: 500;
                text-transform: uppercase;
                border-radius: 5px;
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .edit-button {
                background-color: #007bff;
                border: none;
            }

            .delete-button {
                background-color: #dc3545;
                border: none;
            }

            .edit-button:hover, .delete-button:hover {
                background-color: #0056b3;
            }
            body{
                overflow-x:hidden;

            }
            .table-title{
                margin-top: 10px;
            }


        </style>
        <c:if test="${deletecar != null}">
            <script>
                window.location("load", function () {
                    alert("${deletecar}");
                })
            </script>
        </c:if>
        <c:if test="${deletecarbook != null}">
            <script>
                window.addEventListener("load", function () {
                    alert("${deletecarbook}");
                })
            </script>
        </c:if> 
        <script>
            function confirmDelete() {
                return confirm("Bạn có muốn xoá xe này không?");
            }
        </script>
    </head>
    <body>
        <div class="table-title ">
            <div class="row" style="width:auto">
                <div class="col-sm-6">
                    <h2>Quản lý xe</h2>

                </div>
                <div class="col-sm-6">
                    <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm xe</span></a>
                    <a href="home" class="btn btn-success" style = "background-color: white; color: black;">Trang chủ</a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Loại xe</th>
                <th>Mô tả</th>
                <th>Biển số xe</th>
                <th>Địa điểm</th>
                <th>Ảnh</th>
                <th>Giá</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
            <c:forEach items="${listCar}" var="car">
                <tr>
                    <td>${car.carId}</td>
                    <td>${car.name}</td>
                    <c:forEach items="${listCategory}" var='cate'>
                        <c:if test="${car.categoryId == cate.categoryId}">
                            <td>${cate.title}</td>
                        </c:if>
                    </c:forEach>
                    <td>${car.detail}</td>
                    <td>${car.registrationNumber}</td>
                    <c:forEach items="${listLocation}" var='loca'>
                        <c:if test="${car.locationId == loca.locationId}">
                            <td>${loca.address}</td>
                        </c:if>
                    </c:forEach>
                    <td><img src="images/cars/${car.image}" alt="Car Image"></td>
                    <td>${car.pricePerDay}</td>
                    <td>${car.status}</td>
                    <td>
                        <a href="loadCar?carId=${car.carId}" class="edit-button" >Sửa</a>
                        <br><br>
                        <a href="deleteCar?carId=${car.carId}" class="delete-button" onclick="return confirmDelete();">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="post" enctype="multipart/form-data">
                        <div class="modal-header">						
                            <h4 class="modal-title">Thêm xe</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Tên xe</label>
                                <input  name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Chi tiết</label>
                                <select name="carinfo" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listCarinfo}" var="o">
                                        <option value="${o.carinfoId}">${o.transmission}, chạy ${o.fuel}, ${o.consumption}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Loại xe</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listCategory}" var="o">
                                        <option value="${o.categoryId}">${o.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Giới thiệu</label>
                                <textarea name="detail" class="form-control" required></textarea>
                            </div> 
                            <div class="form-group">
                                <label>Biển số xe</label>
                                <input name="registrationNumber" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Địa điểm</label>
                                <select name="address" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listLocation}" var="l">
                                        <option value="${l.locationId}">${l.address}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Ảnh xe</label>
                                <input type="file" name="image" class="form-control" required/>
                            </div>
                            <div class="form-group">
                                <label>Giá/1 ngày</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Huỷ">
                            <input type="submit" class="btn btn-success" value="Thêm">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
