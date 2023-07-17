<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý tài khoản</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 500px;
                height: 320px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Tài khoản của bạn<b></b></h2>
                        </div>
                        <a href="home" class="btn btn-success" style = "background-color: white; color: black">Trang chủ</a>	
                        <button class="btn btn-primary" style="background-color: white; color: black" data-toggle="modal" data-target="#changePasswordModal">Đổi mật khẩu</button>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editAccount" method="post" onsubmit="return confirmChange();" enctype="multipart/form-data">
                            <div class="modal-body">	
                                <p class="text-danger" style="color: red;">${mess}</p>
                                <input value="${cus.customerId}" name="customerId" type="text" hidden>
                                <div class="form-group">
                                    <label>Họ tên</label>
                                    <input value="${cus.fullName}" name="fullName" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input value="${cus.email}" name="email" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Số điện thoại</label>
                                    <input value="${cus.phone}" name="phone" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>GPLX</label>
                                    <input value="${cus.driverLicenseNumber}" name="driverLicenseNumber" type="text" class="form-control" required>
                                    <br>
                                    <img src="images/driver_licenses/${cus.driverLicensePicture}" alt="Img" onclick="toggleNewPictureInput();">
                                    <input name="newPicture" id="newPictureInput" type="file" accept="image/*" class="form-control" disabled style="display: none;">
                                    <input name="updatePicture" id="updatePictureInput" type="hidden">
                                </div>
                                <div class="form-group">
                                    <label>Tài khoản</label>
                                    <input value="${cus.username}" name="username" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Mật khẩu</label>
                                    <input value="${cus.password}" name="password" type="password" class="form-control" readonly required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Lưu">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="changePasswordModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="changePassword" method="post" onsubmit="return confirmChangePassword();">
                            <input value="${cus.customerId}" name="customerId" type="text" hidden>
                            <div class="modal-header">
                                <h4 class="modal-title" id="changePasswordModalLabel">Đổi mật khẩu</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Mật khẩu hiện tại</label>
                                    <input name="currentPassword" type="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Mật khẩu mới</label>
                                    <input name="newPassword" type="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Nhập lại mật khẩu mới</label>
                                    <input name="confirmNewPassword" type="password" class="form-control" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                                <input type="submit" class="btn btn-primary" value="Lưu">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
        <script>
                        function confirmChange() {
                            return confirm("Bạn có muốn thay đổi thông tin không?");
                        }

                        function toggleNewPictureInput() {
                            var newPictureInput = document.getElementById("newPictureInput");
                            if (newPictureInput.style.display === "none") {
                                newPictureInput.disabled = false;
                                newPictureInput.style.display = "block";
                                updatePictureInput.value = "yes";
                            } else {
                                updatePictureInput.value = null;
                                newPictureInput.disabled = true;
                                newPictureInput.style.display = "none";
                            }
                        }
        </script>
    </body>
</html>