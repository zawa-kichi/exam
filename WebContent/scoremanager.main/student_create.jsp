<!-- 学生登録JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../style.css">
<%@include file="base.jsp" %>

		<h2>学生情報登録</h2>
		<form action="StudentCreate.action">
			<div class="create">
					<div>
					<label for="entYear">入学年度</label>
						<select id="entYear" name="entYear" required>
							<c:forEach var="entTear" items="${entYear }">
								<option value="${entYear}">${entYear}<option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label for="no">学生番号</label>
						<input type="text" id="no" name="no" required></input>
					</div>
					<div>
							<label for="name">氏名</label>
							<input type="text" id="name" name="name" required></input>
					</div>
					<div>
						<label for="classNum">クラス</label>
						<select id="classNum" name="classNum" required>
							<c:forEach var="classNum" items="${classNum }">
								<option value="${classNum }">${classNum }<option>
							</c:forEach>
						</select>
					</div>
				<button type="submit">登録して終了</button><br>
				<a href="menu.jsp">戻る</a>
			</div>
	</form>
