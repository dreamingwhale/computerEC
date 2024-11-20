<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import="com.example.demo.classes.Cart"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/Cart.css' />">
<title>Insert title here</title>
<%
    // 세션에서 Cart 리스트를 가져옴
    List<Cart> CartList = (List<Cart>) session.getAttribute("CartList");

    // 리스트가 null인 경우 새로 생성
    if (CartList == null) {
        CartList = new ArrayList<>();
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
                <td>商品名</td>
                <td>単価</td>
                <td>登録日時</td>
                <td>更新日時</td>
                <td>パーツ</td>
                <td>イメージ</td>
                <td>詳細</td>
            </tr>
        </thead>
        <c:forEach var="c" items="${CartList}">
            <tbody>
                <tr>
                    <td>${c.shohinDTO.shohin_id}</td>
                    <td>${c.shohinDTO.shohin_name}</td>
                    <td class="ichiran-tanka">
                        <fmt:formatNumber type="number" value="${c.shohinDTO.tanka}" />
                    </td>
                    <td>
                        <fmt:formatDate value="${c.shohinDTO.createdate}" type="DATE"
                            pattern="yyyy/MM/dd HH:mm:ss" />
                    </td>
                    <td>
                        <fmt:formatDate value="${c.shohinDTO.updatedate}" type="DATE"
                            pattern="yyyy/MM/dd HH:mm:ss" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${c.shohinDTO.part == 1}">CPU</c:when>
                            <c:when test="${c.shohinDTO.part == 2}">GPU</c:when>
                            <c:when test="${c.shohinDTO.part == 3}">RAM</c:when>
                            <c:when test="${c.shohinDTO.part == 4}">パワー</c:when>
                            <c:when test="${c.shohinDTO.part == 5}">ケース</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <img src="<c:url value='/img/${c.shohinDTO.img}' />" width="50px" height="50px">
                    </td>
                    <td>
                        <button type="button" class="btn"
                            onclick="location.href='DeleteCart?Shohin_ID=${c.shohinDTO.shohin_id}'">
                            削除
                        </button>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
    </table>
	<div>
		<button onClick ="location.href =`Order`">購入</button>
	</div>
</body>
</html>
