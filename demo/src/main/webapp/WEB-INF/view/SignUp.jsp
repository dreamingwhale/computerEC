<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ja" xml:lang="ja">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/SignUp.css' />">

</head>
<body>
	<%@include file="header.jsp"%>
	<form action="/KaiinToroku" method="post">
	    <div>
	        <label for="userID">ID:</label>
	        <input type="text" id="userID" name="userID" required>
	    </div>
	    <div>
	        <label for="userPW">Password:</label>
	        <input type="password" id="userPW" name="userPW" required>
	    </div>
		<div>
			<label for ="userPWConfirm">Password 確認:</label>
			<input type="password" id="userPWConfirm" name ="userPWConfirm" required>
		</div>
	    <div>
	        <label for="userAddress">住所:</label>
	        <input type="text" id="userAddress" name="userAddress" required>
	    </div>
		<div>
	    	<button type="submit">登録</button>
		</div>
		<div>
			<button  onClick ="location.href='/'">Cancel</button>
		</div>
	</form>
<script src="<c:url value='/js/SignUp.js' />" />
</body>
</html>