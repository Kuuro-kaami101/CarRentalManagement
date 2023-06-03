<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detail Car</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>   
    </head>   
    <style>
        .content{
            overflow-x: hidden;
            width: 1340px;
            height: 800px;
            margin: 20px auto;
            list-style: none ;
            display: grid;
            grid-template-columns: 300px 300px 300px 300px 300px ;
            grid-template-row:300px 300px; 
            grid-column-gap:1px;
            grid-row-gap: 1px;
        }
        .item img{
            width: 300px;
            height: 300px;
            border-radius: 10px;
        }
        .item1{
            grid-column-start: 1;
            grid-column-end: 4;
            
        }
        .item2{
            grid-column-start: 4;
            grid-column-end: 5;  
            grid-row-start: 1;
            grid-row-end: 1;
        }
        .item3{
            grid-column-start: 4;
            grid-column-end: 5;
            grid-row-start: 2;
            grid-row-end: 2;
            
        }
        .item1 img{
            width: 850px;
            height: 600px;
        }
    </style>
    <body>
        <jsp:include page="Menu.jsp"  ></jsp:include>
        <div class="content">
            <div class="item item1">
                <img src="images/car1.3.jpg">
            </div>
            <div class="item item2" style="height:300px">
                <img src="images/car1.2.jpg">
            </div>
            <div class="item item3">
                <img src="images/car1.jpg">
            </div>
        </div>
    </body>
</html>
