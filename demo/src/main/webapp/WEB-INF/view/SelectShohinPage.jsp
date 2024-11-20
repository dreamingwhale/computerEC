<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja">
<head>
    <script>
        function displayValue() {
            var selectedValue = document.getElementById("numberSelect").value;
            document.getElementById('part').value = selectedValue;
        }
    </script>
    <meta charset="UTF-8">
    <title>商品詳細</title>
	<link rel="stylesheet" href="<c:url value='/css/SelectShohinPage.css' />">
</head>
<body>
<form action="/UpdateShohin" method="POST">
	<h1>商品マスタ詳細</h1>
    <div>
        <label for="Shohin_id">商品ID:</label>
        <input type="text" id="shohin_id" name="shohin_id" required readonly value="${shousaiShohin.shohin_id}">
    </div>
    <div>
        <label for="shohin_name">商品名:</label>
        <input type="text" id="shohin_name" name="shohin_name" value="${shousaiShohin.shohin_name}" required>
    </div>
    <div>
        <label for="tanka">単価:</label>
        <input type="text" id="tanka" name="tanka" value="${shousaiShohin.tanka}" required>
    </div>
	<div>
		<label for="part">パーツ</label>
	    <select id="numberSelect" onchange="displayValue()">
	        <option value="1" ${shousaiShohin.part == 1 ? 'selected' : ''}>CPU</option>
	        <option value="2" ${shousaiShohin.part == 2 ? 'selected' : ''}>GPU</option>
	        <option value="3" ${shousaiShohin.part == 3 ? 'selected' : ''}>RAM</option>
	        <option value="4" ${shousaiShohin.part == 4 ? 'selected' : ''}>パワー</option>
	        <option value="5" ${shousaiShohin.part == 5 ? 'selected' : ''}>ケース</option>
	    </select>
    </div>
    <input type="text" id="part" name="part" hidden value="${shousaiShohin.part}">
	
    <div>
        <label for="img">イメージ</label>
        <input type="text" id="img" name="img" value="${shousaiShohin.img}" required>
    </div>
    <div>
        <button type="submit">修正</button>
    </div>
	<c:choose>
		
		<c:when test="${shousaiShohin.delete_flg == false }">
			<div>
			<button type="button" class="btn"
			onclick="location.href='DeleteShohin?Shohin_ID=${shousaiShohin.shohin_id }'">削除</button>
			</div>
		</c:when>
		<c:when test="${shousaiShohin.delete_flg == true }">
			<div>
			<button type="button" class="btn"
			onclick="location.href='DeleteShohin?Shohin_ID=${shousaiShohin.shohin_id }'">削除取り消し</button>
			</div>
		</c:when>
	</c:choose>
    <div>
        <button type="button" onclick="location.href='/Master'">Cancel</button>
    </div>
</form>
</body>
</html>
