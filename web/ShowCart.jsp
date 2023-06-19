<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer Cart Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        .cart-table {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .cart-table div {
            flex-basis: calc(50% - 10px);
            padding: 10px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        .cart-table th {
            font-weight: normal;
        }

        .cart-image {
            display: flex;
            justify-content: center;
        }

        .cart-image img {
            max-width: 200px;
            height: auto;
        }

        .pay-btn {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            border-radius: 4px;
        }

        .pay-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Customer Cart Information</h1>
    <div class="cart-table">
        <c:forEach items="${list}" var="cart">
            <div>
                <table>
                    <tr>
                        <th>Start Date:</th>
                        <td>${cart.startDate}</td>
                    </tr>
                    <tr>
                        <th>End Date:</th>
                        <td>${cart.endDate}</td>
                    </tr>
                    <tr>
                        <th>Address:</th>
                        <td>${cart.address}</td>
                    </tr>
                    <tr>
                        <th>Car Name:</th>
                        <td>${cart.carName}</td>
                    </tr>
                    <tr>
                        <th>Cost:</th>
                        <td>$${cart.cost}</td>
                    </tr>
                    <tr>
                        <th>Status:</th>
                        <td>${cart.status}</td>
                    </tr>
                </table>
                <div class="cart-image">
                    <img src="images/${cart.carPicture}" alt="Car Picture">
                </div>
            </div>
            <div>
                <c:if test="${cart.status eq 'checked'}">
                    <a href="payment.jsp" class="pay-btn">Thanh toán</a>
                </c:if>
            </div>
        </c:forEach>
    </div>
</body>
</html>
