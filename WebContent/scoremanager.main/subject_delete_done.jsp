<!-- 科目登録成功JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectDeleteDone.action">
	<h2>科目情報削除</h2>
	<p>削除が完了しました。</p>


	<a href="SubjectList.action">科目一覧</a>
</form>