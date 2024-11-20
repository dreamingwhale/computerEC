<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ja" xml:lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/Login.css' />">
</head>
<body>
<div class="login-container">
    <h2>ログイン</h2>
    <form action="/Main" method="POST" id="loginForm">
        <div class="form-group">
            <label for="userID">ユーザー名:</label>
            <input type="text" id="userID" name="userID" required>
        </div>
        <div class="form-group">
            <label for="password">パスワード:</label>
            <input type="password" id="userPW" name="userPW" required>
        </div>
        <div class="form-group">
            <button type="submit">ログイン</button>
        </div>
        <div class="form-group">
            <button type="button" onclick="location.href='/SignUp'">会員登録</button>
        </div>
    </form>
</div>

<script src="<c:url value='/js/Login.js' />"></script>
</body>
</html>
