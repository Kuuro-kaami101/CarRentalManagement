<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<style>
    .navbar{
        line-height: 30px !important
    }
    .nav-link {
        color: white !important;
    }
</style>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home" style="color: white">
            <i class="fa fa-home"></i> Xin chào
        </a>
        <c:if test="${sessionScope.acc != null}">
            <a style="color: white; font-size: 20px;"><c:out value="${sessionScope.fullname}" /></a>
        </c:if>
        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav">
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
                            <li class="nav-item">
                                <a class="nav-link" href="ManageRentalControl">Đơn đặt hàng</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManageCustomerControl">Khách hàng</a>
                                <a class="nav-link" href="ManageStaffControl">Nhân viên</a>
                                <a class="nav-link" href="ManageStatisticControl">Thống kê</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc == 2}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManageCarControl">Quản lý xe</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc == 4}">
                            <li class="nav-item" style="display: flex">
                                <a class="nav-link" href="UpdateCustomer?cusId=${cus.customerId}">Tài khoản</a>
                                <a class="nav-link" href="CartControl?cusId=${cus.customerId}">Đơn thuê xe</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Đăng xuất</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
