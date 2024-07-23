<!-- 科目管理一覧JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@include file="base.jsp" %>

		<section>
			<!-- 画面タイトル -->
			<h2>成績管理</h2>
			<!-- /画面タイトル -->
			<!-- 完了メッセージ -->
			<div>
				<p>登録が完了しました</p>
			</div>
			<!-- /完了メッセージ -->
			<!-- 戻るリンク -->
			<div>
				<a href="TestRegist.action">戻る</a>
			</div>
			<!-- /戻るリンク -->
			<!-- 学生番号一覧リンク -->
			<div>
				<a href="TestList.action">成績参照</a>
			</div>
			<!-- /学生番号一覧リンク -->
		</section>