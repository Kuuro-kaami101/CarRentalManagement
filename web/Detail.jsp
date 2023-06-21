<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Car</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/detail.css" rel="stylesheet" type="text/css"/> 
    </head>       

    <body>
        <jsp:include page="Menu.jsp" ></jsp:include>
            <div class="container">
                <div class="layout" style="width:auto;height:auto " >
                    <section class="body">
                        <div class="cover-car">
                            <div class="m-container">
                                <div class="cover-car-container">
                                    <div class="main-image">
                                        <div class="cover-car-item">
                                            <img src="images/${detail.image}">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="detail-car">
                        <div class="m-container">
                            <div class="detail-container">
                                <div class="content-detail">
                                    <div class="infor-car-basic">
                                        <div class="group-tag">
                                            <span class="tag-item transmission">
                                                Số tự động
                                            </span>
                                            <span class="tag-item delivery">
                                                Miễn phí giao xe
                                            </span>
                                            <span class="tag-item non-mortgage">
                                                Miễn thế chấp
                                            </span>
                                        </div>
                                        <div class="group-name">
                                            <h3>${detail.name}</h3>
                                            <div class="group-action d-flex-center-btw">
                                                <div class="share">
                                                    <div class="wrap-svg wrap-ic share">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="group-total">
                                            <span class="wrap-svg">
                                                <svg class="star-rating" width="16" height="17" viewBox="0 0 16 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M14.6667 7.23331C14.7333 6.89998 14.4667 6.49998 14.1333 6.49998L10.3333 5.96665L8.59999 2.49998C8.53333 2.36665 8.46666 2.29998 8.33333 2.23331C7.99999 2.03331 7.59999 2.16665 7.39999 2.49998L5.73333 5.96665L1.93333 6.49998C1.73333 6.49998 1.59999 6.56665 1.53333 6.69998C1.26666 6.96665 1.26666 7.36665 1.53333 7.63331L4.26666 10.3L3.59999 14.1C3.59999 14.2333 3.59999 14.3666 3.66666 14.5C3.86666 14.8333 4.26666 14.9666 4.59999 14.7666L7.99999 12.9666L11.4 14.7666C11.4667 14.8333 11.6 14.8333 11.7333 14.8333C11.8 14.8333 11.8 14.8333 11.8667 14.8333C12.2 14.7666 12.4667 14.4333 12.4 14.0333L11.7333 10.2333L14.4667 7.56665C14.6 7.49998 14.6667 7.36665 14.6667 7.23331Z" fill="#FFC634">
                                                </path>
                                                </svg>
                                            </span>
                                            <span class="info">4.6</span>
                                            <span class="dot">•</span>
                                            <span class="info">${detail.address}</span>
                                        </div>

                                    </div>
                                </div>
                                <div class="sidebar-detail">

                                    <form action="BookCarControl" method="get">
                                        <input type="hidden" name="cusID" value="${cus.customerId}">
                                        <input name="carId" value="${detail.carId}" type="hidden">
                                        <div class="rent-box" id="cardetail" style="position: relative;">
                                            <div class="date-time-form error" style="width:287px">
                                                <div class="form-item">
                                                    <label for="check-in" class="form-label">Nhận xe </label>
                                                    <div class="wrap-date-time">
                                                        <div class="wrap-date">                                                      
                                                            <input class="form-control" id="checkin" name="check-in" type="date" style="width:132px" placeholder="${startDate}" value="${startDate}" required>        
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="line"></div>
                                                <div class="form-item">
                                                    <label for="check-out" class="form-label">Trả xe</label>
                                                    <div class="wrap-date-time">
                                                        <div class="wrap-date">
                                                            <input class="form-control" id="checkout" name="check-out" type="date" style="width:132px" placeholder="${endDate}" value="${startDate}" required> 
                                                        </div>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="calendar d-flex">
                                                <div class="wrap-svg" style="margin-bottom: 12px; margin-right: 5px;">
                                                    <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M6.98665 3.29624L2.12572 11.6364C1.73993 12.4061 2.2886 13.3332 3.13733 13.3332L12.8592 13.3332C13.7079 13.3332 14.2652 12.4148 13.8708 11.6364L9.00988 3.29624C8.5898 2.45659 7.40673 2.45659 6.98665 3.29624Z" stroke="#F04438" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    <path d="M8 6.61328V9.05328" stroke="#F04438" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    <path d="M8 10.6665H8.00599" stroke="#F04438" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    </svg>
                                                </div>
                                                <!--                                            <p class="note text-danger">Xe bận trong khoảng thời gian trên. Vui lòng đặt xe khác hoặc thay đổi lịch trình thích hợp.</p>-->
                                            </div>
                                            <div class="dropdown-form ">
                                                <label>Địa điểm giao nhận xe</label>
                                                <div class="wrap-form has-arrow">
                                                    <input name="address" value="${detail.address}" type="hidden"> 
                                                    <span>${detail.address}</span>
                                                </div>
                                            </div>
                                            <div class="line-page"></div>
                                            <div class="line-page"></div>
                                            <div class="price-container">
                                                <div class="price-item">
                                                    <p class="df-align-center">Đơn giá thuê
                                                        <span class="tooltip  ">
                                                            <span class="wrap-svg">
                                                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                                <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                <path d="M9.08984 9.00008C9.32495 8.33175 9.789 7.76819 10.3998 7.40921C11.0106 7.05024 11.7287 6.91902 12.427 7.03879C13.1253 7.15857 13.7587 7.52161 14.2149 8.06361C14.6712 8.60561 14.9209 9.2916 14.9198 10.0001C14.9198 12.0001 11.9198 13.0001 11.9198 13.0001" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                <path d="M12 17H12.01" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                </svg>
                                                            </span>
                                                            <p class="cost">
                                                                <input name="cost" value="${detail.cost}" type="hidden"> 
                                                                <span>${detail.cost} 000đ/ngày</span>
                                                            </p>
                                                </div>

                                                <div class="line-page"></div>
                                                <div class="promotion">
                                                    <div class="wrap-svg">
                                                        <svg style="margin-top:8px" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                                        <path  d="M21.3 10.8394C21.69 10.8394 22 10.5294 22 10.1394V9.20938C22 5.10938 20.75 3.85938 16.65 3.85938H7.35C3.25 3.85937 2 5.10938 2 9.20938V9.67938C2 10.0694 2.31 10.3794 2.7 10.3794C3.6 10.3794 4.33 11.1094 4.33 12.0094C4.33 12.9094 3.6 13.6294 2.7 13.6294C2.31 13.6294 2 13.9394 2 14.3294V14.7994C2 18.8994 3.25 20.1494 7.35 20.1494H16.65C20.75 20.1494 22 18.8994 22 14.7994C22 14.4094 21.69 14.0994 21.3 14.0994C20.4 14.0994 19.67 13.3694 19.67 12.4694C19.67 11.5694 20.4 10.8394 21.3 10.8394ZM9 8.87938C9.55 8.87938 10 9.32938 10 9.87938C10 10.4294 9.56 10.8794 9 10.8794C8.45 10.8794 8 10.4294 8 9.87938C8 9.32938 8.44 8.87938 9 8.87938ZM15 15.8794C14.44 15.8794 13.99 15.4294 13.99 14.8794C13.99 14.3294 14.44 13.8794 14.99 13.8794C15.54 13.8794 15.99 14.3294 15.99 14.8794C15.99 15.4294 15.56 15.8794 15 15.8794ZM15.9 9.47937L9.17 16.2094C9.02 16.3594 8.83 16.4294 8.64 16.4294C8.45 16.4294 8.26 16.3594 8.11 16.2094C7.82 15.9194 7.82 15.4394 8.11 15.1494L14.84 8.41938C15.13 8.12938 15.61 8.12938 15.9 8.41938C16.19 8.70938 16.19 9.18937 15.9 9.47937Z" fill="#263D2A">                                                       
                                                        </path>
                                                        </svg>
                                                    </div><p style="margin:0px">Sử dụng mã khuyến mãi</p></div><div class="line-page"></div><div class="price-item total">
<!--                                                            <p>Tổng cộng</p><p class="cost"><span>${detail.cost} 000đ</span></p></div></div>-->
                                                    <button class="btn btn-outline-primary" type="submit">Đặt xe</button>   
                                                </div> 
                                            </div>
                                        </div>
                                    </form>


                                </div>
                                </section>
                            </div>
                        </div>
                        <c:if test="${bookError != null}">
                            <script>
                                window.addEventListener("load", function () {
                                    alert("${bookError}");
                                })
                            </script>
                        </c:if>
                        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                        <script>
                                function showPopup() {
                                    // Display the pop-up notice
                                    alert("Thêm xe vào đơn thuê xe thành công. Ấn ok để về trang chủ.");
                                    // Redirect to the home page
                                    window.location.href = "home";
                                    // Prevent the form from submitting
                                    return false;
                                }
                        </script>                        
                        <jsp:include page="Footer.jsp"></jsp:include>
                        </body>
                        </html>