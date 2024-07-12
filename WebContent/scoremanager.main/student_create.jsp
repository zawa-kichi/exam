<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="contents">
		<section>
			<!-- 画面タイトル -->
			<h2>学生情報登録</h2>
			<!-- /画面タイトル -->
			<form action="StudentCreateExecite.action" method="post">
				<!-- 入学年度 -->
				<label>入学年度</label>
				<!-- /入学年度 -->
				<!-- 入学年度セレクトボックス -->
				<select name="ent_year">
					<option value="0">--------</option>
					<c:forEach items="${entYearSet}" var="entYear">
						<option
							value="${entYear}"
							<c:if test="${ent_year == entYear}">selected</c:if>
						>
							${entYear}
						</option>
					</c:forEach>
				</select>
				<!-- /入学年度セレクトボックス -->
				<!-- エラーメッセージ -->
				<div>
					${errors.get("ent_year")}
				</div>
				<!-- /エラーメッセージ -->
				<!-- 学生番号 -->
				<label>学生番号</label>
				<!-- /学生番号 -->
				<!-- 学生番号入力テキスト -->
				<input
					type="text"
					name="no"
					placeholder="学生番号を入力してください"
					maxlength="10"
					value="${no}"
					required
				>
				<!-- /学生番号入力テキスト -->
				<!-- エラーメッセージ -->
				<div>
					${errors.get("no")}
				</div>
				<!-- /エラーメッセージ -->
				<!-- 氏名 -->
				<label>氏名</label>
				<!-- /氏名 -->
				<!-- 氏名入力テキスト -->
				<input
					type="text"
					name="name"
					placeholder="氏名を入力してください"
					maxlength="30"
					value="${name}"
					required
				>
				<!-- /氏名入力テキスト -->
				<!-- クラス -->
				<label>クラス</label>
				<!-- /クラス -->
				<!-- クラスセレクトボックス -->
				<select name="class_num">
					<c:forEach items="${classNumSet}" var="classNum">
						<option
							value="${classNum}"
							<c:if test="${class_num.equals(classNum)}">selected</c:if>
						>
							${classNum}
						</option>
					</c:forEach>
				</select>
				<!-- /クラスセレクトボックス -->
				<!-- 登録して終了ボタン -->
				<button>登録して終了</button>
				<!-- /登録して終了ボタン -->
			</form>
			<!-- 戻るリンク -->
			<div>
				<a href="StudentList.action">戻る</a>
			</div>
			<!-- /戻るリンク -->
		</section>
	</c:param>
</c:import>