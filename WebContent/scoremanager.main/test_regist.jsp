<!-- 科目管理一覧JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@include file="base.jsp" %>

		<section>
		<div class="test_regist-list">
			<!-- 画面タイトル -->
			<h2>成績管理</h2>
			<!-- /画面タイトル -->
			<form action="TestRegist.action">
				<table>
					<!-- ヘッダー -->
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>科目</th>
						<th>回数</th>
					</tr>
					<!-- /ヘッダー -->
					<tr>
						<td>
							<!-- 入学年度セレクトボックス -->
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
						</td>
						<td>
							<!-- クラスセレクトボックス -->
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
						</td>
						<td>
							<!-- 科目セレクトボックス -->
							<select name="f3">
								<option value="0">--------</option>
								<c:forEach items="${subjectSet}" var="subject">
									<option
										value="${subject.cd}"
										<c:if test="${f3.equals(subject.cd)}">selected</c:if>
									>
										${subject.name}
									</option>
								</c:forEach>
							</select>
							<!-- /科目セレクトボックス -->
						</td>
						<td>
							<!-- 回数セレクトボックス -->
							<select name="f4">
								<option value="0">--------</option>
								<c:forEach items="${noSet}" var="no">
									<option
										value="${no}"
										<c:if test="${f4 == no}">selected</c:if>
									>
										${no}
									</option>
								</c:forEach>
							</select>
							<!-- /回数セレクトボックス -->
						</td>
					</tr>
				</table>
				<!-- 検索ボタン -->
				<button>検索</button>
				<!-- /検索ボタン -->
			</form>
			</div>
			<div>
				${errors.get("f1")}
			</div>
			<c:if test="${tests.size() > 0}">
				<!-- 科目 -->
				<div class="subject">科目：${subject.name}（${f4}回）</div>
				<!-- /科目 -->
				<form action="TestRegistExecute.action">
					<!-- 成績一覧テーブル -->
					<div class="test_regist-table">
						<table>
							<tr>
								<!-- 入学年度（ヘッダー） -->
								<th>入学年度</th>
								<!-- /入学年度（ヘッダー） -->
								<!-- クラス（ヘッダー） -->
								<th>クラス</th>
								<!-- /クラス（ヘッダー） -->
								<!-- 学生番号（ヘッダー） -->
								<th>学生番号</th>
								<!-- /学生番号（ヘッダー） -->
								<!-- 氏名（ヘッダー） -->
								<th>氏名</th>
								<!-- /氏名（ヘッダー） -->
								<!-- 点数（ヘッダー） -->
								<th>点数</th>
								<!-- /点数（ヘッダー） -->
							</tr>
							<c:forEach items="${tests}" var="test">
								<tr>
									<!-- 入学年度（成績情報） -->
									<td>
										${f1}
									</td>
									<!-- /入学年度（成績情報） -->
									<!-- クラス（成績情報） -->
									<td>
										${f2}
									</td>
									<!-- /クラス（成績情報） -->
									<!-- 学生番号（成績情報） -->
									<td>
										${test.student.no}
									</td>
									<!-- /学生番号（成績情報） -->
									<!-- 氏名（成績情報） -->
									<td>
										${test.student.name}
									</td>
									<!-- /氏名（成績情報） -->
									<!-- 点数（成績情報） -->
									<td>
										<input
											type="text"
											name="point_${test.student.no}"
											<c:if test="${test.point <= 100}">
												value="${test.point}"
											</c:if>
										>
										<div>
											${errors.get("f2_" + test.student.no)}
										</div>
									</td>
									<!-- /点数（成績情報） -->
								</tr>
							</c:forEach>
						</table>
					<!-- /成績一覧テーブル -->
					<!-- 登録して終了ボタン -->
					<button>登録して終了</button>
					<!-- /登録して終了ボタン -->
					</div>
				</form>
			</c:if>
		</section>
<style>
	.test_regist-list{
		margin-left :15%;
		margin-right: 10%;
		}

	.test_regist-list h2{
		margin: 0 auto;
		}

	.test_regist-table{
		margin-left :14%;
		margin-right: 10%;
		}
	.subject{
		margin-left :14%;
		margin-right: 10%;
		}

</style>