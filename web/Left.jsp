<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i>Loại xe</div>
        <ul class="list-group category_block">
            <li class="list-group-item text-white ${tag == o.cid ? "active":""}"><a href="home">Tất cả</a></li>
            <c:forEach items="${listCategory}" var="o">
                <li class="list-group-item text-white ${tag == o.cid ? "active":""}"><a href="category?cid=${o.cid}">${o.cname}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>