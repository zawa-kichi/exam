<!-- 科目管理一覧JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectList.action">
	<h2>科目管理</h2>
	<div>
		<a href="SubjectCreate.action">新規登録</a>
	</div>
	<table>
		<tr>
			<th>科目コード</th>
			<th>科目名</th>
		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>${subject.cd}</td>
				<td>${subject.name}</td>
				<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
				<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
			</tr>
		</c:forEach>
	</table>
</form>