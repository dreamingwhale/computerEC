<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.example.demo.dto.Com_KaiinDTO"%>

<%-- 店舗名・ユーザIDの表示 --%>
<%
Com_KaiinDTO kaiin = (Com_KaiinDTO) session.getAttribute("Kaiin");
if (kaiin == null ) {
	request.setAttribute("errorMsg", "再ログインをお願いします。");
	request.getRequestDispatcher("login.jsp").forward(request, response);
} else {
%>

<link rel="stylesheet" href="<c:url value='/css/header.css' />">


<hr>

<%-- ナビ表示 --%>
<div id="container">
	<div id="nav">
		<ul class="menu">
			<li class="list"><a class="a" href="/Header_ichiran">商品一覧</a></li>
			<li class="list"><a class="a" href="/Header_Cart">カート内一覧</a></li>
			<li class="list"><a class="a" href="/">ログアウト</a></li>
		</ul>
	</div><%-- nav --%>
	<div class="nav_name">
		ようこそ、<%=kaiin.getUserID()%>様
	</div>
</div><%-- container --%>
<hr>
<%
}
%>
