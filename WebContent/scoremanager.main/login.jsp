<!-- ログインJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="base.jsp" %>
<link rel="stylesheet" href="style.css">

<h2>ログイン</h2>
<form action="Login.action">
	<div>
		<label for="id">ID</label>
		<input type="text" id="id" name="id" required>
		<c:forEach var="id" items="${id}">
			<option value="${id}">${id}<option>
		</c:forEach>
	</div>
	<div>
		<label for="password">パスワード</label>
		<input type="password" id="password" name="password" required>
		<c:forEach var="password" items="${password}">
			<option value="${password}">${password}<option>
		</c:forEach>
	</div>
	<div>
		<input type="checkbox" name="password">
		<label>パスワードを表示</label>
	</div>
	<div>
		<button type="submit">ログイン</button>
	</div>
</form>