<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý xe</h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="edit" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Thông tin xe</h4>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${car.carId}" name="carId" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Tên xe</label>
                                    <input value="${car.name}" name="name" type="text" class="form-control" readonly required>
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
                                    <label>Địa điểm</label>
                                    <select name="address" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listLocation}" var="o">
                                            <option value="${o.locationId}">${o.address}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Registration Number</label>
                                    <input value="${car.registrationNumber}" name="registrationNumber" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Ảnh xe</label><br>
                                    <img src="images/cars/${car.image}" alt="alt"/>
                                    <input value="${car.image}" name="image" type="text" class="form-control" required readonly>
                                </div>
                                <div class="form-group">
                                    <label>Giá/1 ngày</label>
                                    <input value="${car.pricePerDay}" name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Trạng thái</label>
                                    <textarea name="status" class="form-control" required>${car.status}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Giới thiệu</label>
                                    <textarea name="detail" class="form-control" required>${car.detail}</textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Lưu">
                                <button class="btn btn-success"><a href="ManageCarControl">Trở về</a></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
