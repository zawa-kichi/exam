<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectUpdateExecute.action">
	<h2>科目情報変更</h2>
	<c:if test="${not empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>
	<div class="subject-update">
		<div>
			<label for="cd">科目コード</label>
			<!-- /項目タイトル・科目コード -->
			<!-- 科目コード -->
			<input type="text"
					name="cd"
				value="${cd}"
				readonly>
		</div>
		<div>
			${errors.get("cd")}
		</div>
		<div>
			<label for="name">科目名</label>
			<input type="text"
				   id="name"
				   name="name"
				   placeholder="科目名を入力してください。"
				   maxlength="20"
				   value="${name}"
				   required>
		</div>
		<div>
			<button type="submit">変更</button>
		</div>
		<div>
			<a href="SubjectList.action">戻る</a>
		</div>
	</div>
</form>
