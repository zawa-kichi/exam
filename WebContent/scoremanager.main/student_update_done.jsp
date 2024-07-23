<!-- 科目登録成功JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectCreateDone.action">

	<!-- 画面タイトル -->
	<h2>学生情報変更</h2>

	<!-- 変更完了メッセージ -->
	<p>変更が完了しました。</p>

	<a href="StudentList.action">学生一覧</a>
</form>