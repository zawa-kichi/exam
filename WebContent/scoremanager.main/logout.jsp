<!-- ログアウトJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@include file="base.jsp" %>

<form action="Logout.action">
	<div class="container">
		<h2>ログアウト</h2>
			<div class="main">
			<!-- ログアウトメッセージ -->
			<p>ログアウトしました</p>
			<a href="Login.Action">ログイン</a>
			</div>
	</div>
	<footer class="mt-4 text-center">
        <p>©2023 THC</p>
        <p>大原学園</p>
    </footer>
</form>