<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部品一覧</title>
    <link rel="stylesheet" href="<c:url value='/css/Main.css' />">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,500,700">
</head>
<body>
	<%@include file="header.jsp"%>
    <div class="ichiran-nav">
        <h1>部品一覧</h1>
    </div>
    <div class="card-container">
        <c:forEach var="s" items="${Shohins}">
            <div class="product-card">
                <div class="product-tumb">
                    <img src="<c:url value='/img/${s.img}' />" alt="${s.shohin_name}">
                </div>
                <div class="product-details">
                    <span class="product-catagory">パーツ: 
                        <c:choose>
                            <c:when test="${s.part == 1}">CPU</c:when>
                            <c:when test="${s.part == 2}">GPU</c:when>
                            <c:when test="${s.part == 3}">RAM</c:when>
                            <c:when test="${s.part == 4}">パワー</c:when>
                            <c:when test="${s.part == 5}">ケース</c:when>
                        </c:choose>
                    </span>
                    <h4><a href="#">${s.shohin_name}</a></h4>
                    <p>商品ID: ${s.shohin_id}</p>
                    <p>単価: <fmt:formatNumber type="number" value="${s.tanka}"></fmt:formatNumber></p>
                    <div class="product-bottom-details">
                        <div class="product-price"><fmt:formatDate value="${s.createdate}" type="DATE" pattern="yyyy/MM/dd HH:mm:ss" /></div>
                        <div class="product-links">
                            <button type="button" class="btn" onclick="location.href='InsertCart?Shohin_ID=${s.shohin_id}'">カート</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
