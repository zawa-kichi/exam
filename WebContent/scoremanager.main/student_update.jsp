<!-- 学生更新JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="StudentUpdate.action">
<h2>学生情報変更</h2>
<div>
	<label for="entYear">入学年度</label>
		<input type="text" id="entYear" name="entYear" value="${ent_year}" required>
</div>
<div>
	<label for="no">学生番号</label>
		<input type="text" id="no" name="no" value="${no}" required>
</div>
<div>
	<label for="name">氏名</label>
		<input type="text" id="no" name="name" value="${name}" required>
</div>
<div>
	<label for="classNum">クラス</label>
	<select id="classNum" name="classNum" required>
		<c:forEach var="classNum" items="${classNum }">
			<option value="${classNum }">${classNum }<option>
		</c:forEach>
	</select>
</div>
<div>
	<button type="submit">変更</button>
	<a href="StudentList.action">戻る</a>
</div>




