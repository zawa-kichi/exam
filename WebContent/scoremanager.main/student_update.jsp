<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="contents">
		<section>
			<!-- 画面タイトル -->
			<h2>学生情報変更</h2>
			<!-- /画面タイトル -->
			<form action="StudentUpdateExecute.action" method="post">
				<!-- 項目タイトル・入学年度 -->
				<label>入学年度</label>
				<!-- /項目タイトル・入学年度 -->
				<!-- 入学年度 -->
				<input
					type="text"
					name="ent_year"
					value="${ent_year}"
					readonly
				>
				<!-- /入学年度セレクトボックス -->
				<!-- 項目タイトル・学生番号 -->
				<label>学生番号</label>
				<!-- /項目タイトル・学生番号 -->
				<!-- 学生番号 -->
				<input
					type="text"
					name="no"
					value="${no}"
					readonly
				>
				<!-- /学生番号 -->
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
				<!-- 在学中のON/OFF -->
				<label>
					在学中
					<input
						type="checkbox"
						name="is_attend"
						<c:if test="${is_attend}">checked="checked"</c:if>
					>
				</label>
				<!-- /在学中のON/OFF -->
				<!-- 変更ボタン -->
				<button>変更</button>
				<!-- /変更ボタン -->
			</form>
			<!-- 戻るリンク -->
			<div>
				<a href="StudentList.action">戻る</a>
			</div>
			<!-- /戻るリンク -->
		</section>
	</c:param>
</c:import>