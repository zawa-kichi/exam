<!-- 科目情報削除JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectDeleteExecute.action">
	<h2>科目情報削除</h2>
	<p><label>
	<!-- /項目タイトル・科目コード -->
	<!-- 科目コード -->
	<input type="text"
				name="cd"
				value="${cd}"
				readonly>
				を削除してもよろしいですか？</label>
	<button type="submit">削除</button>
	<a href="SubjectList.action">戻る</a>
</form>