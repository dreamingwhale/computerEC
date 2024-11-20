<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<c:url value='/css/Master.css' />">

</head>
<body>
	<div class="ichiran-nav">
		<h1>商品マスタ一覧</h1>
		<form action="InsertShohinPage" method ="get">
			<button>新規登録</button>
		</form>
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
				<td>削除フラグ</td>
				<td>イメージ</td>
				<td>詳細</td>

			</tr>
		</thead>
		<c:forEach var="s" items="${Shohins}">
			<tbody>
				<tr>
					<td>${s.shohin_id }</td>
					<td>${s.shohin_name }</td>
					<td class="ichiran-tanka"><fmt:formatNumber type="number"
							value="${s.tanka }">
						</fmt:formatNumber></td>
					<!-- <input type="checkbox" value="true"> -->
					
					<td><fmt:formatDate value="${s.createdate }" type="DATE"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${s.updatedate }" type="DATE"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td>
						<c:choose>
							<c:when test="${s.part == 1}">
								CPU
							</c:when>
							<c:when test="${s.part == 2}">
								GPU
							</c:when>
							<c:when test="${s.part == 3}">
								RAM
							</c:when>
							<c:when test="${s.part == 4}">
								パワー
							</c:when>
							<c:when test="${s.part == 5}">
								ケース
							</c:when>
						</c:choose>
					</td>
					<c:choose>
						<c:when test="${s.delete_flg == false }">
							<td><input type="checkbox" disabled></td>
						</c:when>
						<c:when test="${s.delete_flg == true }">
							<td><input type="checkbox" checked disabled></td>
						</c:when>
					</c:choose>
					<td><img src="<c:url value='/img/${s.img}' />" width="50px" height="50px"></td>
					<td>
						<button onclick="location.href='SelectShohinPage?searchShohin_id=${s.shohin_id }'">詳細</button>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</body>
</html>