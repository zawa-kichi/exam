<!-- 科目情報登録JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../base/style.css">
<%@include file="base.jsp" %>

<form action="SubjectCreateExecute.action">
	<!-- 画面タイトル -->
	<h2>科目情報登録</h2>
	<div class="subject.create">
		<div>
			<label for="cd">科目コード</label>
			<input type="text"
			       id="cd"
			       name="cd"
			       placeholder="科目コードを入力してください"
				   maxlength="3"
				   value="${cd}"
			       required>
			<c:if test="$}"></c:if>
		</div>
		<div>
			<label for="name">科目名</label>
			<input type="text"
				   id="name"
				   name="name"
				   placeholder="科目名を入力してください"
				   maxlength="20"
				   value="${name}"
				   required>
		</div>
		<div>
			<button type="submit">登録</button>
		</div>
		<div>
			<!-- 戻るリンク -->
			<a href="SubjectList.action">戻る</a>
		</div>
	</div>
	<c:if test="${subject.cd.length()< 3}">
		<p>科目コードは3文字以上で入力してください。
	</c:if>
</form>