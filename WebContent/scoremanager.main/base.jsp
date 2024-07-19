<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>得点管理システム</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<div class="base">
  <h1 style="text-align:center">得点管理システム</h1>
  <%@page import="java.util.Objects"%>
  <%@page import="bean.Teacher"%>

  <!-- TeacherのセッションをJSPに適用 -->
  <%Teacher user = (Teacher)session.getAttribute("user");
  boolean userIsNotNull = Objects.nonNull(user);
  if (userIsNotNull){
	  String userId = user.getId();
	  String userName = user.getName();
	  request.setAttribute("userName", userName);
	  request.setAttribute("userIsNotNull", userIsNotNull);
	}%>
  <c:if test="${userIsNotNull}">
  <!-- ログインユーザー名 -->
  <span>
	${userName}様
  </span>
  <!-- /ログインユーザー名 -->
  <!-- ログアウト -->
  <a href="Logout.action">ログアウト</a>
  <!-- /ログアウト -->
  	</c:if>
</div>


<nav>
	<!-- ログインしてるときにサイドバーを表示する -->
	<c:if test="${userIsNotNull}">
	<div class="sidebar">
		<a href="Menu.action">メニュー</a><br>
		<a href="StudentList.action">学生管理</a><br>
		<label>成績管理</label>
		<a href="TestRegist.action">成績登録</a><br>
		<a href="TestList.action">成績参照</a><br>
		<a href="SubjectList.action">科目管理</a><br>
	</div>
  	</c:if>
</nav>