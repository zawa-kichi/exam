<!-- ベースJSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">

<div class="base">
<h1>得点管理システム</h1>
	<a href="Logout.action">ログアウト</a>
	<span>${teacher.name}様</span>
</div>

<nav>
	<div class="sidebar">
		<a href="menu.jsp">メニュー</a><br>
		<a href="StudentList.action">学生管理</a><br>
		<div>成績管理</div>
		<a href="#">成績登録</a><br>
		<a href="#">成績参照</a><br>
		<a href="#">科目管理</a><br>
	</div>
</nav>