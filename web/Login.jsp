<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
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

                background: url(images/login.jpg)no-repeat;
                background-position: center;
                background-size: cover;
            }
            .form-box{
                position: relative;
                width: 400px;
                height: 450px;
                background: transparent;
                border: 2px solid rgb(255,255,255, .5);
                border-radius: 20px;
                backdrop-filter: blur(20px);

                display: flex;
                justify-content: center;
                align-items: center;
                overflow: hidden;

            }
            .form-box .icon-close{
                position: absolute;
                margin-left: 350px; 
                top: 0;
                height: 0;
                width: 45px;
                height: 45px;
                font-size: 2em;
                color: #fff;
                display: flex;
                justify-content: center;
                align-items: center;
                background: white;
                border-bottom-left-radius: 20px;
            }
            h1{
                font-size: 2em;
                color: #fff;
                text-align: center;
            }
            .inputbox{
                position: relative;
                margin: 30px 0;
                width: 310px;
                border-bottom: 2px solid #fff;
            }
            .inputbox input {
                width: 100%;
                height: 50px;
                background: transparent;
                border: none;
                outline: none;
                font-size: 1em;
                padding:0 35px 0 5px;
                color: #fff;
            }
            .inputbox ion-icon{
                position: absolute;
                right: 8px;
                color: #fff;
                font-size: 1.2em;
                top: 20px;
            }
            button{
                width: 100%;
                height: 40px;
                border-radius: 40px;
                background: #fff;
                border: none;
                outline: none;
                cursor: pointer;
                font-size: 1em;
                font-weight: 600;
            }
            .register{
                font-size: .9em;
                color: #fff;
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
            .backhome{
                font-size: .9em;
                color: #fff;
                text-align: center;
                margin: 25px 0 10px;
            }
            .backhome p a{
                text-decoration: none;
                color: #fff;
                font-weight: 600;
            }
            .backhome  p a:hover{
                text-decoration: underline;
            }

        </style>
        <c:if test="${loginnow!=null}">
            <script>
                window.addEventListener("load", function () {
                    alert("${loginnow}");
                })
            </script>
        </c:if>
    </head>
    <body>
        <section>
            <div class="form-box">
                <span class="icon-close">
                    <a href="home"><ion-icon name="close"></ion-icon></a>
                </span>
                <div class="form-value">
                    <form action="login" method="post">
                        <h1>Đăng nhập</h1>
                        <p class="text-danger">${mess}</p>
                        <div class="inputbox">
                            <ion-icon name="person-circle-outline"></ion-icon>
                            <input name="user"  type="text" id="inputEmail" placeholder="Tên đăng nhập" required="">
                        </div>
                        <div class="inputbox">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                            <input name="pass"  type="password" id="inputPassword" placeholder="Mật khẩu" required="">
                        </div>
                        <button>Đăng nhập</button>
                        <div class="register">
                            <p>Chưa có tài khoản? <a href="Register.jsp" >Đăng kí</a></p>
                        </div>
                        <div class="register">
                            <p>Về trang chủ? <a href="home" >Trang chủ</a></p>
                        </div>
                    </form>

                </div>
            </div>
        </section>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    </body>
</html>