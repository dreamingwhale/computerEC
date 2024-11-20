<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import="com.example.demo.classes.OrderedProducts"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	document.getElementByID()
</script>
<link rel="stylesheet" href="<c:url value='/css/OrderConfirm.css' />">
<title>Insert title here</title>
<%
    // 세션에서 Cart 리스트를 가져옴
    List<OrderedProducts> OrderedProducts = (List<OrderedProducts>) session.getAttribute("OrderedProducts");

    // 리스트가 null인 경우 새로 생성
    if (OrderedProducts == null) {
        OrderedProducts = new ArrayList<>();
    }
%>
</head>
<body>
	<%@include file="header.jsp"%>
    <div class="ichiran-nav">
        <h1>部品一覧</h1>
    </div>
    <table>
        <thead>
            <tr>
                <td>商品ID</td>
                <td>単価</td>
                <td>登録日時</td>
                <td>パーツ</td>
                <td>イメージ</td>

            </tr>
        </thead>
		<c:set var = "total" value = "0"/>
        <c:forEach var="c" items="${OrderedProducts}">
            <tbody>
                <tr>
                    <td>${c.shohin_id}</td>
                    <td class="ichiran-tanka">
                        <fmt:formatNumber type="number" value="${c.tanka}" />
                    </td>
                    <td>
                        <fmt:formatDate value="${c.createdate}" type="DATE"
                            pattern="yyyy/MM/dd HH:mm:ss" />
                    </td>
                    
                    <td>
                        <c:choose>
                            <c:when test="${c.part == 1}">CPU</c:when>
                            <c:when test="${c.part == 2}">GPU</c:when>
                            <c:when test="${c.part == 3}">RAM</c:when>
                            <c:when test="${c.part == 4}">パワー</c:when>
                            <c:when test="${c.part == 5}">ケース</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <img src="<c:url value='/img/${c.img}' />" width="50px" height="50px">
                    </td>
                    
                </tr>
            </tbody>
			<c:set var= "total" value="${total + c.tanka}"/>
        </c:forEach>
			<h2>合計：<c:out value="${total}"/></h2>
    </table>
	<div>
		<button onClick ="location.href =`/`">Loginに戻る</button>
	</div>
</body>
</html>
