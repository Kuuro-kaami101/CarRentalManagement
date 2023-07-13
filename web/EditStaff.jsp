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
                            <h2>Chỉnh sửa thông tin</h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                            <div class="modal-header">						
                                <h4 class="modal-title">Nhân viên</h4>
                            </div>                                
                            <form action="editStaff" method="post">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>ID</label>
                                        <input value="${staff.personnelId}" name="id" type="text" class="form-control" readonly required>
                                    </div>

                                    <div class="form-group">
                                        <label>Họ tên</label>
                                        <input value="${staff.fullName}" name="fullName" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input value="${staff.email}" name="email" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <input value="${staff.phone}" name="phone" type="text" class="form-control" pattern="[0-9]{10}" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Tài khoản</label>
                                        <input value="${staff.username}" name="username" type="text" class="form-control"  required>
                                    </div>
                                    <div class="form-group">
                                        <label>Mật khẩu</label>
                                        <input value="${staff.password}" name="password" type="text" class="form-control"  required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <p class="text-danger">${alert}</p>
                                    <input type="submit" class="btn btn-success" value="Lưu">
                                    <button class="btn btn-success"><a href="ManageStaffControl">Trở về</a></button>
                                </div>
                            </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>