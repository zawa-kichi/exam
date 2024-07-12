<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="contents">
		<section>
			<!-- 画面タイトル -->
			<h2>学生管理</h2>
			<!-- /画面タイトル -->
			<!-- 新規登録リンク -->
			<div>
				<a href="StudentCreate.action">新規登録</a>
			</div>
			<!-- /新規登録リンク -->
			<form method="post">
				<!-- 入学年度セレクトボックス -->
				<label>入学年度</label>
				<select name="f1">
					<option value="0">--------</option>
					<c:forEach items="${entYearSet}" var="entYear">
						<option
							value="${entYear}"
							<c:if test="${f1 == entYear}">selected</c:if>
						>
							${entYear}
						</option>
					</c:forEach>
				</select>
				<!-- /入学年度セレクトボックス -->
				<!-- クラスセレクトボックス -->
				<label>クラス</label>
				<select name="f2">
					<option value="0">--------</option>
					<c:forEach items="${classNumSet}" var="classNum">
						<option
							value="${classNum}"
							<c:if test="${f2.equals(classNum)}">selected</c:if>
						>
							${classNum}
						</option>
					</c:forEach>
				</select>
				<!-- /クラスセレクトボックス -->
				<!-- 在学中のON/OFF -->
				<label>
					<input
						type="checkbox"
						name="f3"
						<c:if test="${f3}">checked="checked"</c:if>
					>
					在学中
				</label>
				<!-- /在学中のON/OFF -->
				<!-- 絞込みボタン -->
				<button>絞込み</button>
				<!-- /絞込みボタン -->
				<div>
					${errors.get("f1")}
				</div>
			</form>
			<c:choose>
				<c:when test="${students.size()>0}">
					<!-- 検索結果件数 -->
					<div>検索結果：${students.size()}件</div>
					<!-- /検索結果件数 -->
					<table>
						<tr>
							<!-- 入学年度（ヘッダー） -->
							<th>入学年度</th>
							<!-- /入学年度（ヘッダー） -->
							<!-- 学生番号（ヘッダー） -->
							<th>学生番号</th>
							<!-- /学生番号（ヘッダー） -->
							<!-- 氏名（ヘッダー） -->
							<th>氏名</th>
							<!-- /氏名（ヘッダー） -->
							<!-- クラス（ヘッダー） -->
							<th>クラス</th>
							<!-- /クラス（ヘッダー） -->
							<!-- 在学中（ヘッダー） -->
							<th>在学中</th>
							<!-- /在学中（ヘッダー） -->
							<th></th>
						</tr>
						<c:forEach items="${students}" var="student">
							<tr>
								<!-- 入学年度（学生情報） -->
								<td>${student.entYear}</td>
								<!-- /入学年度（学生情報） -->
								<!-- 学生番号（学生情報） -->
								<td>${student.no}</td>
								<!-- /学生番号（学生情報） -->
								<!-- 氏名（学生情報） -->
								<td>${student.name}</td>
								<!-- /氏名（学生情報） -->
								<!-- クラス（学生情報） -->
								<td>${student.classNum}</td>
								<!-- /クラス（学生情報） -->
								<!-- 在学中（学生情報） -->
								<td>
									<c:choose>
										<c:when test="${student.isAttend()}">
											○
										</c:when>
										<c:otherwise>
											×
										</c:otherwise>
									</c:choose>
								</td>
								<!-- /在学中（学生情報） -->
								<!-- 学生情報変更リンク -->
								<td>
									<!--
									仕様書ではaタグの指定であったが、
									想定通りの動作にならないため、formタグを用いている
									-->
									<form action="StudentUpdate.action">
										<input type="hidden" name="student_no" value="${student.no}">
										<button>変更</button>
									</form>
								</td>
								<!-- /学生情報変更リンク -->
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<!-- 学生情報無しメッセージ -->
					<div>学生情報が存在しませんでした</div>
					<!-- /学生情報無しメッセージ -->
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>