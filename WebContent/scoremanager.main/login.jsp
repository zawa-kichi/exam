<!-- ログインJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@ include file="base.jsp"%>
<div class ="login">
	<h2>ログイン</h2>
	<form action="LoginExecute.action">
		<div class="login-id">
			<label for="id">ID</label>
			<!-- IDを入力するテキストボックス -->
			<input type="text"
					id="id"
					name="id"
					value="${id}"
					required>
			<!-- /IDを入力するテキストボックス -->
		</div>
		<div class="login-password">
			<label for="password">パスワード</label>
			<input type="password"
					id="password"
					name="password"
					value="${password}"
					maxlength="20"
					required>
		</div>
		<div class="login-check">
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
		<div class="login-button">
			<button type="submit">ログイン</button>
		</div>
		   <footer class="mt-4 text-center">
	        <p>©2023 THC</p>
	        <p>大原学園</p>
	    </footer>
	</form>
</div>

<style>
	.login{

	}

	.login h2{
		margin: 0 auto;
		margin-left: 25%;
		margin-right: 25%;
	}

	.login-id{
		text-align: center;
	}

	.login-password{
		text-align: center;
	}

	.login-check{
		text-align: center;
	}

	.login-button{
		text-align: center;
	}

</style>