<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Đăng kí</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" href="css/register.css">
        <style>
            *{
                margin: 0;
                padding: 0;
                font-family: 'poppins',sans-serif;
            }
            section{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                width: 100%;
                background-position: center;
                background-size: cover;
            }
            .signup-content{
                position: relative;
                width: 400px;
                height: 950px;
                background: transparent;
                border: 2px solid rgb(255,255,255, .5);
                border-radius: 20px;
                backdrop-filter: blur(20px);
                display: flex;
                justify-content: center;
                align-items: center;
                overflow: hidden;
            }
            .form-group{
                position: relative;
                margin: 30px 0;
                width: 310px;
                border-bottom: 2px solid #fff;
            }
            .register{
                font-size: .9em;
                color: #000;
                text-align: center;
                margin: 25px 0 10px;
            }
            .register p a{
                text-decoration: none;
                color: #fff;
                font-weight: 600;
            }
            .register  p a:hover{
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <form action="register" method="post" enctype="multipart/form-data" id="signup-form" class="signup-form">
                            <h2 class="form-title">Đăng kí</h2>
                            <p class="text-danger" style="color: red;">${mess}</p>
                            <p class="text-danger" style="color: red;">${email}</p>
                            <p class="text-danger" style="color: red;">${phone}</p>
                            <div class="form-group">
                                <input type="text"  name="name" id="name" placeholder="Họ tên" required/>
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" id="email" placeholder="Email" required/>
                            </div>
                            <div class="form-group">
                                <input type="tel" name="phone" id="phone" pattern="0[0-9]{9}" placeholder="Số điện thoại" required>
                            </div>
                            <div class="form-group">
                                <input type="text" id="driverLicense" name="driverLicense" required  placeholder="Giấy phép lái xe"/>
                            </div>
                            <div class="form-group">
                                <label>Ảnh giấy phép lái xe</label>
                                <input type="file" id="driverLicensePicture" name="driverLicensePicture" required class="form-input" />
                            </div>
                            <div class="form-group">
                                <input type="text" id="username" name="username" required  placeholder="Tên đăng nhập"/>
                            </div>
                            <div class="form-group">
                                <input type="password" id="password" name="password" required placeholder="Mật khẩu"/>
                                <span class="toggle-password" onclick="togglePasswordVisibility('password')"></span>
                            </div>
                            <button type="submit" name="submit" id="submit" value="Đăng kí">Đăng ký</button>
                            <div class="register">
                                <p style="text-align: center">Đã có tài khoản ? <a href="Login.jsp" class="loginhere-link">Đăng nhập</a></p>
                                <p style="text-align: center">Về trang chủ ? <a href="home" class="loginhere-link">Trang chủ</a></p>
                            </div>
                        </form>


                    </div>
                </div>
            </section>
        </div>
        <script>
            function togglePasswordVisibility(inputId) {
                const passwordInput = document.getElementById(inputId);
                const toggleIcon = passwordInput.nextElementSibling;
                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                    toggleIcon.classList.remove("zmdi-eye");
                    toggleIcon.classList.add("zmdi-eye-off");
                } else {
                    passwordInput.type = "password";
                    toggleIcon.classList.remove("zmdi-eye-off");
                    toggleIcon.classList.add("zmdi-eye");
                }
            }
        </script>
    </body>
</html>