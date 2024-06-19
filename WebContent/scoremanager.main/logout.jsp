<!-- ログアウトJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">

		<form action="StudentCreateAction">
			<div class="container">
				<%@include file="base.jsp" %>
					<div class="main">
					<h2>ログアウト</h2>
					<label for="logout">ログアウトしました</label>
					<a href="login.jsp">ログイン</a>
					</div>
			</div>
		</form>