<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" style="color: white">Xin chào </a>
        <c:if test="${sessionScope.acc != null}">
            <a style="color: white"><c:out value = " ${sessionScope.fullname}"/></a>
        </c:if>
        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav" style="text-align: right">
                <c:choose>
                    <c:when test="${sessionScope.acc == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="Login.jsp">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Register.jsp">Đăng kí</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.acc == 3}">
                            <li class="nav-item" style="display: flex">
                                <a class="nav-link" href="ManagerRentalControl">Đơn đặt hàng</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc == 1}">
                            <li class="nav-item" style="display: flex">
                                <a class="nav-link" href="ManageCustomerControl">Khách hàng</a>
                                <a class="nav-link" href="ManageStaffControl">Nhân viên</a>
                                <a class="nav-link" href="ManageStatisticControl">Thống kê</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc == 2}">
                            <li class="nav-item" style="display: flex">
                                <a class="nav-link" href="ManageCarControl">Quản lý xe</a>
                            </li>
                        </c:if>    
                        <c:if test="${sessionScope.acc == 4}"> 
                            <li class="nav-item" style="display: flex" >
                                <a class="nav-link" href="editAccount.jsp">Tài khoản</a>
                            </li> 
                        </c:if>
                        <li class="nav-item" style="display: flex">
                            <a class="nav-link" href="logout">Đăng xuất</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<section class="jumbotron" style = "background-image: url(images/Logo.jpg); background-size: 100% 100%; height: 500px; border-radius: 0%;">
    <div class="container">
    </div>
</section>
