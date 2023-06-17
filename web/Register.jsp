<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>Đăng ký khách hàng</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-image: url(images/Background.jpg);
                background-size: cover;
                background-repeat: no-repeat;
            }

            .container {
                width: 400px;
                margin: 0 auto;
                margin-top: 150px;
                padding: 20px;
                border: 1px solid #ccc;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5%;
            }

            .form-group {
                margin-bottom: 10px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
            }

            .form-group input {
                width: 100%;
                padding: 5px;
                border: 1px solid #ccc;
            }

            .form-group button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Đăng ký</h2>
            <form action="register" method="post">
                <div class="form-group">
                    <label for="fullName">Họ và tên:</label>
                    <input type="text" id="fullName" name="fullName" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required>
                </div>
                <div class="form-group">
                    <label for="driverLicenseNumber">Giấy phép lái xe:</label>
                    <input type="text" id="driverLicenseNumber" name="driverLicenseNumber" required>
                </div>
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <button type="submit">Đăng ký</button>
                </div>
            </form>
        </div>
    </body>
</html>
