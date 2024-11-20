<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja">
<head>
	<link rel="stylesheet" href="<c:url value='/css/InsertShohinPage.css' />">
	<script>
        function displayValue() {
            var selectedValue = document.getElementById("numberSelect").value;
			document.getElementById('part').value=selectedValue;
        }
    </script>
<meta charset="UTF-8">
<title>新規商品</title>
<link rel="stylesheet" href="<c:url value='/css/InsertShohinPage.css' />">
</head>
<body>
	<form action="/InsertShohin" method="POST">
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
			<label for ="part">パーツ</label>
			<select id="numberSelect" onchange="displayValue()">
		        <option value="1">CPU</option>
		        <option value="2">GPU</option>
		        <option value="3">RAM</option>
		        <option value="4">パワー</option>
		        <option value="5">ケース</option>
		    </select>
			<input type ="text" name = "part" id = "part" hidden value="1">
		</div>
		<div>
			<label for = "img">イメージ</label>
			<input type = "text" id ="img" name ="img" required>
		</div>
	  <div>
	    <button type="submit">新規登録</button>
	  </div>
	  	<div>
			<button type = "button" onclick="location.href='/Master'">Cancel</button>
		</div>
	</form>

</body>
</html>