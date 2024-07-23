<!-- 科目管理一覧JSP -->
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
<%@include file="base.jsp" %>

<section>
		<!-- 画面タイトル -->
		<h2>成績参照</h2>
		<!-- /画面タイトル -->
		<div>
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
			<div>
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
	<div>
		<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
	</div>
</section>
