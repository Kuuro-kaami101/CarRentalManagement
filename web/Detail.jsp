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
        <style>
            .detail-car {
                height: 2425px;
                width: 1060px;
            }
            .detail-car .m-container {
                width: 960px;
                height: 2602px;
            }
            .detail-car .detail-container .content-detail {
                width: calc(68% - 16px);
                float: left;
            }
            .detail-car .detail-container .content-detail .info-car-desc {
                display: flex;
                flex-direction: column;
                margin: 24px 0;
            }
            .detail-car .detail-container .content-detail .info-car-desc .outstanding-features {
                display: flex;
                grid-gap: 20px;
                gap: 20px;
                width: 100%;
                justify-content: space-between;
            }
            .detail-car .detail-container .content-detail .info-car-desc .outstanding-features__item {
                display: flex;
                align-items: center;
                grid-gap: 16px;
                gap: 16px;
            }
            .detail-car .detail-container .content-detail .info-car-desc {
                display: flex;
                flex-direction: column;
                margin: 24px 0;
            }
            .detail-car .detail-container .content-detail .info-car-desc .features-car ul {
                grid-gap: 16px 8px;
                gap: 16px 8px;
                display: flex;
                flex-wrap: wrap;
                list-style:none ;
            }
            .detail-car .detail-container .content-detail .info-car-desc .features-car ul li {
                width: calc(33.33% - 5.33px);
                display: flex;
                grid-gap: 16px;
                gap: 16px;
            }
            .detail-car .detail-container .content-detail .info-car-desc .features-car ul li img {
                width: 24px;
                height: 24px;
                display: block;
            }
            .detail-car .detail-container .content-detail .info-car-desc {
                display: flex;
                flex-direction: column;
                margin: 24px 0;
            }
            .detail-car .detail-container .content-detail .info-car-desc h6 {
                margin-bottom: 24px;
            }
            .df-align-center {
                display: flex!important;
                align-items: center!important;
            }

            h6 {
                font-size: 1.25rem;
                font-weight: 600;
            }
            .required-papers {
                display: flex;
                grid-gap: 24px;
                gap: 24px;
                align-items: flex-start;
                padding: 16px 24px;
                background: #fef7f4;
                border-left: 8px solid #f26a2b;
                border-radius: 8px;
            }
            .required-papers .required-papers__item {
                width: calc(50% - 12px);
                display: flex;
                flex-direction: column;
                grid-gap: 16px;
                gap: 16px;
            }
            .sub{
                font-weight: 700;
            }
            .required-papers .required-papers__item .type__item {
                display: flex;
                align-items: center;
                grid-gap: 4px;
                gap: 4px;
            }
            .required-papers .required-papers__item .type-content {
                display: flex;
                grid-gap: 16px;
                gap: 16px;
            }
            .required-papers .required-papers__item .type-content img {
                width: 24px;
                height: 24px;
            }
            .detail-car .detail-container .content-detail .info-car-desc {
                display: flex;
                flex-direction: column;
                margin: 24px 0;
            }
            .info-car-desc input[name="readmore"]{
                display: none;
            }
            .info-car-desc .des{
                text-align: justify;
                position: relative;
                overflow: hidden;
                max-height: 180px;
                transition: 0.5s ease-in-out;
            }
            .info-car-desc .des::before{
                position: absolute;
                bottom: 0;
                left:0;
                width: 100%;
                height: 50%;
                transition: 0.5s ease-in-out;
                content: '';
                background-image: linear-gradient(to top,#fff,transparent);
            }
            .info-car-desc .button {
                margin-top: 30px;
                text-align: center;
            }
            .info-car-desc label{
                position: relative;
                cursor: pointer;

            }
            .info-car-desc label::before{
                content: attr(data-more);
                left: 0;
                color: #5fcf86!important;
                font-weight: 700;
                display: flex;
                justify-content: left;
            }
            .info-car-desc input[name="readmore"]:checked~ .content label::before{
                content: attr(data-less);
            }
            .info-car-desc input[name="readmore"]:checked~ .content .des{
                max-height: 240px;
            }
            .info-car-desc input[name="readmore"]:checked~ .content .des::before{
                height:0px;
            }

            .detail-car .detail-container .content-detail .info-car-desc p.read-more {
                cursor: pointer;
                color: #5fcf86;
                font-weight: 700;
            }
            .cancel-policy .column {
                display: flex;
                border: 1px solid #c6c6c6;
            }
            .cancel-policy .column.title .column__item {
                font-size: .875rem;
                font-weight: 700;
                text-transform: capitalize;
            }
            .cancel-policy .column .column__item.case {
                justify-content: center;
                text-align: left;
                padding-left: 24px;
            }
            .cancel-policy .column .column__item {
                display: flex;
                flex-direction: column;
                width: 100%;
                padding: 16px 0;
            }
        </style>
    </head>       

    <body>
        <jsp:include page="Menu.jsp" ></jsp:include>
        <c:set var="trans" value="" />
                                                    <c:set var="fuels" value="" />
                                                    <c:set var="consumptions" value="" />
                                                    <c:set var="shouldBreak" value="false" />
                                                    <c:forEach items="${listinfo}" var="li">
                                                        <c:if test="${!shouldBreak}">
                                                            <c:if test="${li.infoid == detail.infoid}">
                                                                <c:set var="trans" value="${li.transmission}" />
                                                                <c:set var="fuels" value="${li.fuel}" />
                                                                <c:set var="consumptions" value="${li.consumption}" />
                                                                <c:set var="shouldBreak" value="true" />
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
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
                                                ${trans}
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
                                <div class="content-detail">
                                    <div class="line-page"></div>
                                    <div class="info-car-desc">
                                        <h6 class="df-align-center">Đặc điểm</h6>
                                        <div class="outstanding-features">
                                            <div class="outstanding-features__item">
                                                <div class="wrap-svg">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M10.914 23.3289C10.9148 23.3284 10.9156 23.3279 10.9163 23.3274C10.9155 23.3279 10.9148 23.3284 10.914 23.3289ZM10.914 23.3289C10.914 23.3289 10.914 23.3289 10.914 23.3289L11.3128 23.9114M10.914 23.3289L11.3128 23.9114M11.3128 23.9114L10.9807 23.2882L20.6697 23.9458C20.6682 23.9484 20.6656 23.9496 20.6631 23.9479C20.655 23.9424 20.6343 23.9284 20.6014 23.9074C20.6014 23.9073 20.6014 23.9073 20.6013 23.9073C20.5141 23.8516 20.3413 23.7468 20.0921 23.6208C20.0919 23.6207 20.0918 23.6206 20.0917 23.6206C19.3397 23.2404 17.8926 22.6674 16.0003 22.6674C14.1715 22.6674 12.7584 23.2026 11.9869 23.5817L11.9929 23.5929M11.3128 23.9114L11.331 23.9456C11.3324 23.9483 11.3352 23.9495 11.3377 23.9478C11.3444 23.9432 11.3592 23.9332 11.3821 23.9184L11.9929 23.5929L11.9929 23.5929M11.9929 23.5929C11.9909 23.5892 11.9889 23.5855 11.9868 23.5818C11.6767 23.7342 11.4702 23.8614 11.3821 23.9184L11.9929 23.5929ZM10.6691 24.2983L10.6691 24.2983C10.7406 24.4324 10.8728 24.5792 11.0793 24.6538C11.3072 24.7361 11.5609 24.7039 11.7614 24.5667L11.7614 24.5667C11.7978 24.5418 13.4597 23.4174 16.0003 23.4174C18.5426 23.4174 20.205 24.5432 20.2393 24.5667L20.2393 24.5667C20.4389 24.7034 20.6938 24.7372 20.9245 24.6528C21.1293 24.5779 21.2557 24.4338 21.3233 24.3136L22.4886 22.2427L24.3242 23.0447L21.6934 28.584H9.99882L7.65051 23.0635L9.57427 22.2435L10.6691 24.2983ZM24.4348 22.8117L24.4345 22.8124L24.4348 22.8117Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M12.75 4.66675C12.75 3.97639 13.3096 3.41675 14 3.41675H18C18.6904 3.41675 19.25 3.97639 19.25 4.66675V7.00008C19.25 7.13815 19.1381 7.25008 19 7.25008H13C12.8619 7.25008 12.75 7.13815 12.75 7.00008V4.66675Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M9.33398 22.6668L9.90564 11.2336C9.95887 10.1692 10.8374 9.3335 11.9031 9.3335H20.0982C21.1639 9.3335 22.0424 10.1692 22.0957 11.2336L22.6673 22.6668" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M14.667 7.35815V9.8901" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M17.334 7.35815V9.8901" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    </svg>
                                                </div>
                                                <div class="title">
                                                    <p class="sub">Số ghế</p>
                                                    <p class="main">${detail.category_title}</p>
                                                </div>
                                            </div>
                                            <div class="outstanding-features__item">
                                                <div class="wrap-svg">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M25.9163 7.99992C25.9163 9.05846 25.0582 9.91659 23.9997 9.91659C22.9411 9.91659 22.083 9.05846 22.083 7.99992C22.083 6.94137 22.9411 6.08325 23.9997 6.08325C25.0582 6.08325 25.9163 6.94137 25.9163 7.99992Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <circle cx="23.9997" cy="23.9999" r="1.91667" stroke="#5FCF86" stroke-width="1.5"></circle>
                                                    <path d="M17.9163 7.99992C17.9163 9.05846 17.0582 9.91659 15.9997 9.91659C14.9411 9.91659 14.083 9.05846 14.083 7.99992C14.083 6.94137 14.9411 6.08325 15.9997 6.08325C17.0582 6.08325 17.9163 6.94137 17.9163 7.99992Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M17.9163 23.9999C17.9163 25.0585 17.0582 25.9166 15.9997 25.9166C14.9411 25.9166 14.083 25.0585 14.083 23.9999C14.083 22.9414 14.9411 22.0833 15.9997 22.0833C17.0582 22.0833 17.9163 22.9414 17.9163 23.9999Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <circle cx="7.99967" cy="7.99992" r="1.91667" stroke="#5FCF86" stroke-width="1.5"></circle>
                                                    <path d="M10.1025 26.6666V21.3333H7.99837C7.59559 21.3333 7.25184 21.4053 6.96712 21.5494C6.68066 21.6918 6.46278 21.894 6.31348 22.1562C6.16244 22.4166 6.08691 22.723 6.08691 23.0754C6.08691 23.4296 6.1633 23.7343 6.31608 23.9895C6.46886 24.243 6.69021 24.4374 6.98014 24.5728C7.26834 24.7083 7.6173 24.776 8.02702 24.776H9.43587V23.8697H8.20931C7.99403 23.8697 7.81521 23.8402 7.67285 23.7812C7.53049 23.7221 7.42459 23.6336 7.35514 23.5155C7.28396 23.3975 7.24837 23.2508 7.24837 23.0754C7.24837 22.8984 7.28396 22.7491 7.35514 22.6275C7.42459 22.506 7.53136 22.414 7.67546 22.3515C7.81782 22.2872 7.9975 22.2551 8.21452 22.2551H8.97493V26.6666H10.1025ZM7.22233 24.2395L5.89681 26.6666H7.1416L8.43848 24.2395H7.22233Z" fill="#5FCF86"></path>
                                                    <path d="M24 10.6665V15.9998M24 21.3332V15.9998M16 10.6665V21.3332M8 10.6665V15.4998C8 15.776 8.22386 15.9998 8.5 15.9998H24" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    </svg>
                                                </div>
                                                <div class="title">
                                                    
                                                    <p class="sub">Truyền động</p>
                                                    <p class="main">${trans}</p>
                                                </div>
                                            </div>
                                            <div class="outstanding-features__item">
                                                <div class="wrap-svg">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M24.3337 27.2499H7.66699C7.52892 27.2499 7.41699 27.138 7.41699 26.9999V12.4599C7.41699 12.3869 7.44888 12.3175 7.5043 12.27L14.652 6.14344L14.1639 5.574L14.652 6.14344C14.6973 6.1046 14.755 6.08325 14.8147 6.08325H24.3337C24.4717 6.08325 24.5837 6.19518 24.5837 6.33325V26.9999C24.5837 27.138 24.4717 27.2499 24.3337 27.2499Z" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    <path d="M12.0001 5.33325L7.42285 9.46712" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    <path d="M17.888 19.5212L16.7708 15.93C16.5378 15.1812 15.4785 15.1798 15.2436 15.928L14.1172 19.5164C13.7178 20.7889 14.6682 22.0833 16.0019 22.0833C17.3335 22.0833 18.2836 20.7927 17.888 19.5212Z" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    <path d="M23.2503 3.66675V5.66675C23.2503 5.80482 23.1384 5.91675 23.0003 5.91675H14.667C14.5827 5.91675 14.5365 5.8916 14.5072 5.86702C14.4721 5.83755 14.44 5.78953 14.4245 5.72738C14.4089 5.66524 14.4147 5.60775 14.4318 5.56523C14.4461 5.52975 14.4749 5.48584 14.5493 5.44616L18.2993 3.44616C18.3356 3.42685 18.376 3.41675 18.417 3.41675H23.0003C23.1384 3.41675 23.2503 3.52868 23.2503 3.66675Z" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    </svg>
                                                </div>
                                                <div class="title">
                                                    <p class="sub">Nhiên liệu</p>
                                                    <p class="main">${fuels}</p>
                                                </div>
                                            </div>
                                            <div class="outstanding-features__item">
                                                <div class="wrap-svg">
                                                    <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M7.41667 24V23.25H6.66667H4.75V18.0833H6.66667H7.41667V17.3333V15.4167H9.33333H9.64399L9.86366 15.197L12.3107 12.75H24.5833V23.25H22.6667H22.356L22.1363 23.4697L19.6893 25.9167H7.41667V24Z" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    <path d="M24 21.3333H28" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M24 18.6665H28" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M15.417 7.33325C15.417 6.6429 15.9766 6.08325 16.667 6.08325H20.667C21.3573 6.08325 21.917 6.6429 21.917 7.33325V8.58325H15.417V7.33325Z" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M17.333 9.33325V11.9999M19.9997 9.33325V11.9999" stroke="#5FCF86" stroke-width="1.5"></path>
                                                    <path d="M4.66699 26.01L4.66699 15.3308" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    <path d="M27.3291 23.3384L27.3291 16.6704" stroke="#5FCF86" stroke-width="1.5" stroke-linecap="round"></path>
                                                    </svg>
                                                </div>
                                                <div class="title">
                                                    <p class="sub">NL tiêu hao</p>
                                                    <p class="main">${consumptions}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="line-page"></div>
                                    <div class="info-car-desc">
                                        <h6 class="df-align-center">Mô tả</h6>
                                        <p class="df-align-center">${detail.detail}</p>
                                    </div>
                                    <div class="line-page"></div>
                                    <div class="info-car-desc">
                                        <h6>Các tiện nghi khác</h6>
                                        <div class="features-car">
                                            <ul>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/map-v2.png" alt="Mioto - Thuê xe tự lái"> Bản đồ</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/bluetooth-v2.png" alt="Mioto - Thuê xe tự lái"> Bluetooth</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/360_camera-v2.png" alt="Mioto - Thuê xe tự lái"> Camera 360</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/parking_camera-v2.png" alt="Mioto - Thuê xe tự lái"> Camera cập lề</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/dash_camera-v2.png" alt="Mioto - Thuê xe tự lái"> Camera hành trình</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/reverse_camera-v2.png" alt="Mioto - Thuê xe tự lái"> Camera lùi</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/tpms-v2.png" alt="Mioto - Thuê xe tự lái"> Cảm biến lốp</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/impact_sensor-v2.png" alt="Mioto - Thuê xe tự lái"> Cảm biến va chạm</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/head_up-v2.png" alt="Mioto - Thuê xe tự lái"> Cảnh báo tốc độ</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/sunroof-v2.png" alt="Mioto - Thuê xe tự lái"> Cửa sổ trời</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/gps-v2.png" alt="Mioto - Thuê xe tự lái"> Định vị GPS</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/babyseat-v2.png" alt="Mioto - Thuê xe tự lái"> Ghế trẻ em</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/usb-v2.png" alt="Mioto - Thuê xe tự lái"> Khe cắm USB</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/spare_tire-v2.png" alt="Mioto - Thuê xe tự lái"> Lốp dự phòng</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/dvd-v2.png" alt="Mioto - Thuê xe tự lái"> Màn hình DVD</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/etc-v2.png" alt="Mioto - Thuê xe tự lái"> ETC</li>
                                                <li> <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/features/airbags-v2.png" alt="Mioto - Thuê xe tự lái"> Túi khí an toàn</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="line-page"></div>
                                    <div class="info-car-desc">
                                        <h6 class="df-align-center">Giấy tờ thuê xe
                                            <!--                                                        <span class="tooltip tooltip--m  icon--m">
                                                                                                        <span class="wrap-svg">
                                                                                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                                                                            <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                                                            <path d="M9.08984 9.00008C9.32495 8.33175 9.789 7.76819 10.3998 7.40921C11.0106 7.05024 11.7287 6.91902 12.427 7.03879C13.1253 7.15857 13.7587 7.52161 14.2149 8.06361C14.6712 8.60561 14.9209 9.2916 14.9198 10.0001C14.9198 12.0001 11.9198 13.0001 11.9198 13.0001" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                                                            <path d="M12 17H12.01" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                                                                            </svg>
                                                                                                        </span>
                                                                                                        <span class="tooltip-text">
                                                                                                            <b>Bạn đã có CCCD gắn chip </b>
                                                                                                            <br>Giấy tờ thuê xe gồm có: <br>- Giấy phép lái xe &amp; CCCD (chủ xe đối chiếu và gửi lại bạn) <br>
                                                                                                            <b>Bạn chưa có CCCD gắn chip </b><br>Giấy tờ thuê xe gồm có: <br>- Giấy phép lái xe &amp; CMND (chủ xe đối chiếu và gửi lại bạn) <br>- Kèm theo Passport (chủ xe đối chiếu, giữ lại và hoàn trả khi bạn trả xe) <br>Lưu ý: Khách thuê vui lòng chuẩn bị đầy đủ BẢN GỐC tất cả giấy tờ thuê xe khi làm thủ tục nhận xe.
                                                                                                        </span>
                                                                                                    </span>-->
                                        </h6>
                                        <div class="required-papers">
                                            <div class="required-papers__item">
                                                <div class="type__item">
                                                    <div class="wrap-svg">
                                                        <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M7.99967 1.33325C4.32634 1.33325 1.33301 4.32659 1.33301 7.99992C1.33301 11.6733 4.32634 14.6666 7.99967 14.6666C11.673 14.6666 14.6663 11.6733 14.6663 7.99992C14.6663 4.32659 11.673 1.33325 7.99967 1.33325ZM7.99967 6.05325C7.72634 6.05325 7.49967 5.83325 7.49967 5.55325C7.49967 5.27992 7.72634 5.05325 7.99967 5.05325C8.27967 5.05325 8.49967 5.27992 8.49967 5.55325C8.49967 5.83325 8.27967 6.05325 7.99967 6.05325ZM8.49967 10.3866C8.49967 10.6666 8.27301 10.8866 7.99967 10.8866C7.72634 10.8866 7.49967 10.6666 7.49967 10.3866V7.27992C7.49967 6.99992 7.72634 6.77992 7.99967 6.77992C8.27301 6.77992 8.49967 6.99992 8.49967 7.27992V10.3866Z" fill="#F26A2B"></path>
                                                        </svg>
                                                    </div>
                                                    <p class="font-12 subject">Bắt buộc</p>
                                                </div>
                                                <div class="type-content">
                                                    <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/papers/cmnd.png" alt="">
                                                    <div class="type-name"><p>CMND và GPLX (đối chiếu)</p></div>
                                                </div>
                                            </div>
                                            <div class="required-papers__item">
                                                <div class="type__item">
                                                    <div class="wrap-svg">
                                                        <svg width="17" height="16" viewBox="0 0 17 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M8.49967 1.33325C4.82634 1.33325 1.83301 4.32659 1.83301 7.99992C1.83301 11.6733 4.82634 14.6666 8.49967 14.6666C12.173 14.6666 15.1663 11.6733 15.1663 7.99992C15.1663 4.32659 12.173 1.33325 8.49967 1.33325ZM8.49967 6.05325C8.22634 6.05325 7.99967 5.83325 7.99967 5.55325C7.99967 5.27992 8.22634 5.05325 8.49967 5.05325C8.77967 5.05325 8.99967 5.27992 8.99967 5.55325C8.99967 5.83325 8.77967 6.05325 8.49967 6.05325ZM8.99967 10.3866C8.99967 10.6666 8.77301 10.8866 8.49967 10.8866C8.22634 10.8866 7.99967 10.6666 7.99967 10.3866V7.27992C7.99967 6.99992 8.22634 6.77992 8.49967 6.77992C8.77301 6.77992 8.99967 6.99992 8.99967 7.27992V10.3866Z" fill="#666666"></path>
                                                        </svg>
                                                    </div>
                                                    <p class="font-12">Chọn 1 trong 2 hình thức</p>
                                                </div>
                                                <div class="type-content">
                                                    <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/papers/passport.png">
                                                    <div class="type-name">
                                                        <p>Passport (giữ lại)</p>
                                                    </div>
                                                </div>
                                                <div class="type-content">
                                                    <img src="https://n1-cstg.mioto.vn/v4/p/m/icons/papers/cccd.png">
                                                    <div class="type-name"><p>CCCD gắn chip (đối chiếu)</p></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="info-car-desc">
                                        <h6 class="df-align-center">Tài sản thế chấp
                                            <span class="tooltip tooltip--m  icon--m">
                                                <span class="wrap-svg">
                                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    <path d="M9.08984 9.00008C9.32495 8.33175 9.789 7.76819 10.3998 7.40921C11.0106 7.05024 11.7287 6.91902 12.427 7.03879C13.1253 7.15857 13.7587 7.52161 14.2149 8.06361C14.6712 8.60561 14.9209 9.2916 14.9198 10.0001C14.9198 12.0001 11.9198 13.0001 11.9198 13.0001" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    <path d="M12 17H12.01" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    </svg>
                                                </span>
                                                <span class="tooltip-text">Bạn sẽ để lại tài sản thế chấp (tiền mặt/chuyển khoản hoặc xe máy kèm cà vẹt gốc) cho chủ xe khi làm thủ tục nhận xe <br>Chủ xe sẽ gửi lại tài sản thế chấp khi bạn hoàn trả xe theo như nguyên trạng ban đầu lúc nhận xe</span>
                                            </span>
                                        </h6>
                                        <div class="required-papers">
                                            <p>15 triệu (tiền mặt/chuyển khoản cho chủ xe khi nhận xe)hoặc Xe máy (kèm cà vẹt gốc) giá trị 15 triệu</p>
                                        </div>
                                    </div>
                                    <div class="info-car-desc">
                                        <input type="checkbox" name="readmore" id="readmore">
                                        <div class="content">
                                            <h6 class="df-align-center">Điều khoản</h6>
                                            <div class="des">Quy định khác:<br/>
                                                ◦ Sử dụng xe đúng mục đích.<br/>
                                                ◦ Không sử dụng xe thuê vào mục đích phi pháp, trái pháp luật.<br/>
                                                ◦ Không sửa dụng xe thuê để cầm cố, thế chấp.<br/>
                                                ◦ Không hút thuốc, nhả kẹo cao su, xả rác trong xe.<br/>
                                                ◦ Không chở hàng quốc cấm dễ cháy nổ.<br/>
                                                ◦ Không chở hoa quả, thực phẩm nặng mùi trong xe.<br/>
                                                ◦ Khi trả xe, nếu xe bẩn hoặc có mùi trong xe, khách hàng vui lòng vệ sinh <br/>
                                                xe sạch sẽ hoặc gửi phụ thu phí vệ sinh xe.<br/>
                                                Trân trọng cảm ơn, chúc quý khách hàng có những chuyến đi tuyệt vời!<br/>
                                            </div>
                                            <div class"button">
                                                <label for="readmore"
                                                       data-more="Đọc Thêm"
                                                       data-less="Lượt bớt"
                                                       ></label>       
                                            </div>
                                        </div>
                                    </div><div class="info-car-desc">
                                        <h6 class="df-align-center">Chính sách huỷ chuyến</h6>
                                        <div class="cancel-policy">
                                            <div class="column title">
                                                <div class="column__item case">Thời điểm hủy chuyến</div>
                                                <div class="column__item">Khách thuê hủy chuyến</div>
                                            </div>
                                            <div class="column">
                                                <div class="column__item case">Trong vòng 1h sau đặt cọc</div>
                                                <div class="column__item">
                                                    <div class="wrap-svg">
                                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M12.25 2C6.74 2 2.25 6.49 2.25 12C2.25 17.51 6.74 22 12.25 22C17.76 22 22.25 17.51 22.25 12C22.25 6.49 17.76 2 12.25 2ZM15.84 10.59L12.32 14.11C12.17 14.26 11.98 14.33 11.79 14.33C11.6 14.33 11.4 14.26 11.26 14.11L9.5 12.35C9.2 12.06 9.2 11.58 9.5 11.29C9.79 11 10.27 11 10.56 11.29L11.79 12.52L14.78 9.53C15.07 9.24 15.54 9.24 15.84 9.53C16.13 9.82 16.13 10.3 15.84 10.59Z" fill="#12B76A"></path>
                                                        </svg>
                                                    </div>Hoàn tiền 100%
                                                </div>
                                            </div>
                                            <div class="column">
                                                <div class="column__item case">Trước chuyến đi &gt;7 ngày</div>
                                                <div class="column__item">
                                                    <div class="wrap-svg">
                                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M12.25 2C6.74 2 2.25 6.49 2.25 12C2.25 17.51 6.74 22 12.25 22C17.76 22 22.25 17.51 22.25 12C22.25 6.49 17.76 2 12.25 2ZM15.84 10.59L12.32 14.11C12.17 14.26 11.98 14.33 11.79 14.33C11.6 14.33 11.4 14.26 11.26 14.11L9.5 12.35C9.2 12.06 9.2 11.58 9.5 11.29C9.79 11 10.27 11 10.56 11.29L11.79 12.52L14.78 9.53C15.07 9.24 15.54 9.24 15.84 9.53C16.13 9.82 16.13 10.3 15.84 10.59Z" fill="#12B76A"></path>
                                                        </svg>
                                                    </div>Hoàn tiền 70%
                                                </div>
                                            </div>
                                            <div class="column">
                                                <div class="column__item case">Trong vòng 7 ngày trước chuyến đi</div>
                                                <div class="column__item">
                                                    <div class="wrap-svg">
                                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M12.25 2C6.74 2 2.25 6.49 2.25 12C2.25 17.51 6.74 22 12.25 22C17.76 22 22.25 17.51 22.25 12C22.25 6.49 17.76 2 12.25 2ZM14.67 13.39C14.97 13.69 14.96 14.16 14.67 14.45C14.52 14.59 14.33 14.67 14.14 14.67C13.95 14.67 13.75 14.59 13.61 14.44L12.25 13.07L10.9 14.44C10.75 14.59 10.56 14.67 10.36 14.67C10.17 14.67 9.98 14.59 9.84 14.45C9.54 14.16 9.53999 13.69 9.82999 13.39L11.2 12L9.82999 10.61C9.53999 10.31 9.54 9.84 9.84 9.55C10.13 9.26 10.61 9.26 10.9 9.56L12.25 10.93L13.61 9.56C13.9 9.26 14.37 9.26 14.67 9.55C14.96 9.84 14.97 10.31 14.67 10.61L13.3 12L14.67 13.39Z" fill="#F04438"></path>
                                                        </svg>
                                                    </div>Không hoàn tiền
                                                </div>
                                            </div>
                                            <div class="desc-note">
                                                <p class="df-align-center">* Khách thuê không nhận xe sẽ không được hoàn cọc</p>
                                                <p class="df-align-center">* Tiền cọc sẽ được hoàn trả trong vòng 1-3 ngày làm việc
                                                    <span class="tooltip tooltip--m ">
                                                        <span class="wrap-svg">
                                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                            <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                            <path d="M9.08984 9.00008C9.32495 8.33175 9.789 7.76819 10.3998 7.40921C11.0106 7.05024 11.7287 6.91902 12.427 7.03879C13.1253 7.15857 13.7587 7.52161 14.2149 8.06361C14.6712 8.60561 14.9209 9.2916 14.9198 10.0001C14.9198 12.0001 11.9198 13.0001 11.9198 13.0001" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                            <path d="M12 17H12.01" stroke="black" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                            </svg>
                                                        </span>
                                                        <span class="tooltip-text">
                                                            <b>Thủ tục hoàn tiền &amp; đền cọc</b>
                                                            Mioto sẽ hoàn lại tiền cọc (&amp; tiền đền cọc do chủ xe hủy chuyến (nếu có) theo chính sách hủy chuyến) qua tài khoản ngân hàng của khách thuê trong vòng 1-3 ngày làm việc kể từ thời điểm hủy chuyến.
                                                            <i>*Nhân viên Mioto sẽ liên hệ khách thuê (qua số điện thoại đã đăng ký trên Mioto) để xin thông tin tài khoản ngân hàng, hoặc Khách thuê có thể chủ động gửi thông tin cho Mioto qua email Contact@mioto.vn hoặc nhắn tin tại Mioto Fanpage</i>
                                                        </span>
                                                    </span>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="line-page"></div>
                                    <div class="info-car-desc">                                                  
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