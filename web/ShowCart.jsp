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

            .rental-form {
                max-width: 800px;
                margin: 0 auto;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                border-radius: 6px;
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .row {
                display: flex;
                margin-bottom: 10px;
            }

            .col {
                flex-basis: 50%;
                padding: 8px;
            }

            .car-image {
                width: 100%;
                height: auto;
                border-radius: 6px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .rental-label {
                color: #3366cc;
            }

            .rental-value {
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
                padding: 8px;
            }

            .button-group {
                display: flex;
                justify-content: center;
            }

            .button {
                padding: 10px 20px;
                border-radius: 4px;
                font-weight: bold;
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .button-home {
                background-color: #33cc33;
            }

            .button-edit {
                margin-right: 20px;
                background-color: #ff9900;
            }

            .button-cancel {
                background-color: #cc3333;
            }

            .button-payment {
                background-color: #33cc33;
            }

            .button:hover {
                background-color: #555;
            }

            .payment-section {
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 6px;
                margin-top: 20px;
                display: flex;
            }

            .payment-column {
                flex-basis: 50%;
                margin-right: 20px;
            }

            .payment-label {
                font-weight: bold;
            }

            .payment-options {
                display: flex;
                margin-top: 10px;
            }

            .payment-option {
                margin-right: 10px;
            }

            .payment-button-group {
                display: flex;
                justify-content: center;
                margin-top: 10px;
            }

            /* Popup Form */
            .popup-overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 9999;
                visibility: hidden;
            }

            .popup-form {
                background-color: #fff;
                border-radius: 6px;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .popup-form input[type="text"],
            .popup-form input[type="date"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            .popup-form .button-group {
                display: flex;
                justify-content: flex-end;
            }

            .popup-form .button {
                margin-left: 10px;
            }

            .popup-overlay.show {
                visibility: visible;
            }
        </style>
        <script>
            function confirmCancel() {
                return confirm("Bạn có muốn huỷ đơn thuê này không?");
            }

            function openPopupForm(locationId, startDate, endDate) {
                var popupOverlay = document.querySelector(".popup-overlay");
                var carIdInput = document.querySelector("#carIdInput");
                dao.getAvailableCars(locationId, startDate, endDate, function (carList) {
                    // Clear previous options
                    carIdInput.innerHTML = "";

                    for (var i = 0; i < carList.length; i++) {
                        var option = document.createElement("option");
                        option.value = carList[i].carId;
                        option.text = carList[i].name;
                        carIdInput.appendChild(option);
                    }
                    popupOverlay.classList.add("show");
                });
            }

            function closePopupForm() {
                var popupOverlay = document.querySelector(".popup-overlay");
                popupOverlay.classList.remove("show");
            }
        </script>
    </head>
    <body>
        <h1>Đơn thuê xe của bạn</h1>
        <c:forEach var="rental" items="${listRental}">
            <br>
            <div class="rental-form">
                <c:forEach items="${listCar}" var="car">
                    <c:if test="${car.carId == rental.carId}">
                        <div class="row">
                            <div class="col">
                                <img class="car-image" src="images/cars/${car.image}" alt="Car Image">
                            </div>
                            <div class="col">
                                <div class="rental-field">
                                    <div class="rental-label">Xe được thuê:</div>
                                    <div class="rental-value">${car.name}</div>
                                </div>
                                <div class="rental-field">
                                    <div class="rental-label">Ngày nhận xe:</div>
                                    <div class="rental-value">${rental.rentalStartDate}</div>
                                </div>
                                <div class="rental-field">
                                    <div class="rental-label">Ngày trả xe:</div>
                                    <div class="rental-value">${rental.rentalEndDate}</div>
                                </div>
                                <div class="rental-field">
                                    <div class="rental-label">Địa điểm:</div>
                                    <div class="rental-value">
                                        <c:forEach items="${listLoca}" var="loca">
                                            <c:if test="${loca.locationId == rental.locationId}">
                                                ${loca.address}
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="rental-field">
                                    <div class="rental-label">Đơn giá:</div>
                                    <div class="rental-value">${rental.totalCost} 000</div>
                                </div>
                                <div class="rental-field">
                                    <div class="rental-label">Trạng thái:</div>
                                    <div class="rental-value">${rental.rentalStatus}</div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <form action="PaymentControl">
                                    <div class="payment-section" style="margin: 0px 0px 0px 0px;">
                                        <input name="rentalId" value="${rental.rentalId}" hidden>
                                        <input name="cusId" value="${cusId}" hidden>
                                        <div class="payment-column" style="flex-basis: 90%; /* Updated width */">
                                            <h3 style="color: #3366cc;">Thanh toán</h3>
                                            <div class="rental-field">
                                                <div class="payment-label" style="font-weight: bold;">Phương thức thanh toán:</div>
                                                <div class="payment-options">
                                                    <div class="payment-option">
                                                        <input type="radio" id="payment-online" name="paymentMethod" value="1">
                                                        <label for="payment-online" style="color: #555;">Online</label>
                                                    </div>
                                                    <div class="payment-option">
                                                        <input type="radio" id="payment-direct" name="paymentMethod" value="2">
                                                        <label for="payment-direct" style="color: #555;">Trực tiếp</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="payment-column" style="display: flex; justify-content: flex-end;">
                                            <div class="button-group">
                                                <c:if test="${rental.rentalStatus == 'Chưa thanh toán'}">
                                                    <button type="submit" class="button button-payment" style="background-color: #33cc33;">Thanh toán</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col">
                                <div class="button-group" style="margin: 0px;">
                                    <c:if test="${rental.rentalStatus == 'Chưa xác nhận'}">
                                        <a href="CancelRental?rentalId=${rental.rentalId}&cusId=${cusId}" class="button button-cancel" onclick="return confirmCancel();">Huỷ đơn</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
        <br>
        <div style="text-align: center;">
            <a href="home" class="button button-home">Trang chủ</a>
        </div>
    </body>
</html>
