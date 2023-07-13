<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Rental Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
            text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e9e9e9;
        }

        tr:first-child:hover {
            background-color: #f2f2f2;
        }

        tr:last-child td {
            border-bottom: none;
        }

        img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 0 auto;
            border-radius: 6px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        @media screen and (max-width: 600px) {
            table {
                box-shadow: none;
            }
            th, td {
                padding: 10px;
            }
            tr:nth-child(even), tr:hover {
                background-color: transparent;
            }
        }

        @media screen and (max-width: 400px) {
            h1 {
                font-size: 24px;
            }
            th, td {
                padding: 8px;
            }
        }
    </style>
</head>
<body>
    <h1>Đơn thuê xe của bạn</h1>
    
    <table>
        <tr>
            <th>Xe được thuê</th>
            <th>Ngày nhận xe</th>
            <th>Ngày trả xe</th>
            <th>Địa điểm</th>
            <th>Đơn giá</th>
            <th>Trạng thái</th>
        </tr>
        <c:forEach var="rental" items="${listRental}">
            <tr>
                <c:forEach items="${listCar}" var="car">
                    <c:if test="${car.carId == rental.carId}">
                        <td>
                            <div>
                                <img src="images/cars/${car.image}" alt="Car Image">
                            </div>
                        </td>
                    </c:if>
                </c:forEach>
                <td>${rental.rentalStartDate}</td>
                <td>${rental.rentalEndDate}</td>
                <c:forEach items="${listLoca}" var="loca">
                    <c:if test="${loca.locationId == rental.locationId}">
                        <td>${loca.address}</td>
                    </c:if>
                </c:forEach>
                <td>${rental.totalCost}</td>
                <td>${rental.rentalStatus}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
