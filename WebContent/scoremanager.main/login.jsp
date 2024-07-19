<!-- ログインJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@ include file="base.jsp"%>

<h2>ログイン</h2>
<form action="LoginExecute.action">
	<div>
		<label for="id">ID</label>
		<input type="text" id="id" name="id" required>
		<c:forEach var="id" items="${id}">
			<option value="${id}">${id}<option>
		</c:forEach>
	</div>
	<div>
		<label for="password">パスワード</label>
		<input type="password" id="password" name="password" maxlength="20" required>
		<c:forEach var="password" items="${password}">
			<option value="${password}">${password}<option>
		</c:forEach>
	</div>
	<div>
		<input type="checkbox" id="pass_check">
		<label for="checkbox">パスワードを表示</label>
	</div>
	<script>
		document.addEventListener("DOMContentLoaded",function(event){
			const targetElement = document.getElementById("password");
			const triggerElement = document.getElementById("pass_check");

			triggerElement.addEventListener("change",function(event){
				if(this.checked){
					targetElement.setAttribute("type","text");
				}else{
					targetElement.setAttribute("type","password");
				}
			},false);
		},false);


	</script>
	<div>
		<button type="submit">ログイン</button>
	</div>
</form>