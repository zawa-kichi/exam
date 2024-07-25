<!-- 科目管理一覧JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@include file="base.jsp" %>

		<section>
			<div class="test-subject">
			<!-- 画面タイトル -->
			<h2>成績参照</h2>
			<!-- /画面タイトル -->
				<!-- 科目情報 -->
				<div>
					<form action="TestListSubjectExecute.action">
						科目情報
						<table>
							<!-- ヘッダー -->
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>科目</th>
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
							</tr>
						</table>
						<!-- 検索ボタン -->
						<button>検索</button>
						<!-- /検索ボタン -->
					</form>
				</div>
				<!-- /科目情報 -->
				<!-- 学生情報 -->
				<div class="test-subject_table">
					<form action="TestListStudentExecute.action">
						学生情報
						<table>
							<!-- ヘッダー -->
							<tr>
								<th>学生番号</th>
							</tr>
							<!-- /ヘッダー -->
							<tr>
								<td>
									<!-- 学生番号テキストボックス -->
									<input
										name="f4"
										type="text"
										value="${f4}"
										placeholder="学生番号を入力してください"
										maxlength="10"
										pattern="^[0-9]+$"
										required>
									<!-- /学生番号テキストボックス -->
								</td>
							</tr>
						</table>
						<!-- 検索ボタン -->
						<button>検索</button>
						<!-- /検索ボタン -->
					</form>
				</div>
				<!-- /学生情報 -->
			</div>
			<c:choose>
				<c:when test="${tests.size() > 0}">
					<div>
						科目：${subject.name}
					</div>
					<div>
						<table>
							<tr>
								<!-- ヘッダー -->
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<c:forEach var="no" items="${noSet}">
									<th>${no}回</th>
								</c:forEach>
								<!-- /ヘッダー -->
							</tr>
							<!-- 成績情報 -->
							<c:forEach items="${tests}" var="test">
								<tr>
									<!-- 入学年度 -->
									<td>
										${f1}
									</td>
									<!-- /入学年度 -->
									<!-- クラス -->
									<td>
										${f2}
									</td>
									<!-- /クラス -->
									<!-- 学生番号 -->
									<td>
										${test.studentNo}
									</td>
									<!-- /学生番号 -->
									<!-- 氏名 -->
									<td>
										${test.studentName}
									</td>
									<!-- /氏名 -->
									<c:forEach var="no" items="${noSet}">
										<!-- 点数 -->
										<td>
											<c:choose>
												<c:when test="${test.points.get(no) <= 100}">
													${test.points.get(no)}
												</c:when>
												<c:otherwise>
													-
												</c:otherwise>
											</c:choose>
										</td>
										<!-- /点数 -->
									</c:forEach>
								</tr>
							</c:forEach>
							<!-- /成績情報 -->
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p>学生情報が存在しませんでした</p>
					</div>
				</c:otherwise>
			</c:choose>
		</section>

<style>
	.test-subject{
		margin-left :15%;
		margin-right: 10%;
		}

	.test-subject_table{
		margin-left :15%;
		margin-right: 10%;
		}
</style>