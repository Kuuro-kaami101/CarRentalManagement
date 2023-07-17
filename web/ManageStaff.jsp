<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Customer</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img {
                width: 200px;
                height: 120px;
                object-fit: cover;
                border-radius: 5px;
            }

            h2 {
                text-align: center;
                margin-top: 20px;
                margin-bottom: 30px;
                font-size: 28px;
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
        </style>
        <script type="text/javascript">
            function deleteStaff(id){
                if(confirm("Do you want to delete Staff have Staff_ID: "+id)){
                    window.location="deleteStaff?id="+id;
                }
            }
        </script>
    </head>
    <body>
        <h2>Quản lý nhân viên</h2>
        <div style="text-align: right; margin-top: -40px; margin-bottom: 10px; margin-right: 15px;">
            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm nhân viên</span></a>

        </div>
        <table class="table table-striped table-hover">
            <tr>
                <th>ID</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Tên đăng nhập</th>
                <th>Mật khẩu</th>
                <th>Thao tác</th>
            </tr>
            <c:forEach items="${listS}" var="o">
                <tr>
                    <td>${o.personnelId}</td>
                    <td>${o.fullName}</td>
                    <td>${o.email}</td>
                    <td>${o.phone}</td>
                    <td>${o.username}</td>
                    <td>${o.password}</td>
                    <td class="button-container">
                        <button style="background-color: pink;color: white; margin-top: 20px;" class="edit-button"><a href="editStaff?staff_id=${o.personnelId}">Chỉnh sửa</a></button>
                        <button style="background-color: pink;color: white; margin-top: 20px;" class="delete-button"><a href="deleteStaff?staff_id=${o.personnelId}" onclick="return confirmCancel()">Xóa</a></button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addStaff" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Thêm nhân viên</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Họ tên</label>
                                <input name="fullName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input name="email" type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Điện thoại</label>
                                <input name="phone" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Tên đăng nhập</label>
                                <input name="username" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input name="password" type="password" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Thêm">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Huỷ">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <a href="home" class="btn btn-success" style = "background-color: white; color: black;">Trang chủ</a>
    <script src="js/manager.js" type="text/javascript"></script>
    <script>
        function confirmCancel() {
                return confirm("Bạn có muốn xoá nhân viên này không?");
            }
    </script>
    </body>
</html>
