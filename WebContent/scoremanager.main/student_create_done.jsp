<!-- 科目登録成功JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="StudentCreateDone.action">

	<!-- 画面タイトル -->
	<h2>学生情報登録</h2>

	<!-- 登録完了メッセージ -->
	<p>登録が完了しました。</p>

	<!-- 戻るリンク -->
	<a href="StudentCreate.action">戻る</a>

	<!-- 学生一覧リンク -->
	<a href="StudentList.action">学生一覧</a>
</form>