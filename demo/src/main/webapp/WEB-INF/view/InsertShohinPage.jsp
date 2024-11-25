<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.dto.Com_ShohinDTO"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>

<!DOCTYPE html>
<html lang="ja" xml:lang="ja">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value='/css/InsertShohinPage.css' />">
    <script src="<c:url value='/js/InsertShohinPage.js' />" defer>
	</script>
	<%
			List<Com_ShohinDTO>shohins = (List<Com_ShohinDTO>)session.getAttribute("Shohins");
		    String shohinsJson = "";
		    if (shohins != null) {
		        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		        shohinsJson = objectMapper.writeValueAsString(shohins);
		    }
		%>
		<script>
			const shohins = <%= shohinsJson %>;
			document.addEventListener("DOMContentLoaded", function () {
			    document.getElementById('shohin_id').addEventListener('change', function () {
			        const shohinId = document.getElementById('shohin_id').value;

			        // 상품 ID 중복 검사
			        for (let i = 0; i < shohins.length; i++) {
			            if (shohins[i].shohin_id == shohinId) {
			                alert("商品IDが存在します。");
			                document.getElementById('shohin_id').value = '';
			                break;
			            }
			        }
			    });
			});
			</script>
    <title>新規商品</title>
</head>
<body>
    <form id="InsertShohin" action="/InsertShohin" method="POST">
        <h1>商品マスタ新規登録</h1>
        <div>
            <label for="Shohin_id">商品ID:</label>
            <input type="text" id="shohin_id" name="shohin_id" required>
        </div>
        <div>
            <label for="shohin_name">商品名:</label>
            <input type="text" id="shohin_name" name="shohin_name" required>
        </div>
        <div>
            <label for="tanka">単価:</label>
            <input type="text" id="tanka" name="tanka" required>
        </div>
        <div>
            <label for="part">パーツ</label>
            <select id="numberSelect" onchange="displayValue()">
                <option value="1">CPU</option>
                <option value="2">GPU</option>
                <option value="3">RAM</option>
                <option value="4">パワー</option>
                <option value="5">ケース</option>
            </select>
            <input type="text" name="part" id="part" hidden value="1">
        </div>
        <div>
            <label for="img">イメージ</label>
            <input type="text" id="img" name="img" required>
        </div>
        <div>
            <button type="submit">新規登録</button>
        </div>
        <div>
            <button type="button" onclick="location.href='/Master'">Cancel</button>
        </div>
    </form>
</body>
</html>
