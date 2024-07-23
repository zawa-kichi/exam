package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	/*
	 * baseSql:データ取得用のSQL
	 */
//	private String baseSql = "";
	/*
	 * postFilter:フィルター後のリストへの格納処理
	 */
	private List<TestListStudent> postFilter(
			ResultSet rSet,
			School school
	) throws Exception {
		// list:格納リスト
		List<TestListStudent> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListStudent test = new TestListStudent();
				/*
				 * subject_cd:科目コード
				 * char(3), primary key, not null
				 */
				String subjectCd = rSet.getString("subject_cd");
				test.setSubjectCd(subjectCd);
				/*
				 * subject_name:科目名
				 * varchar(20), value = null
				 */
				SubjectDao subjectDao = new SubjectDao();
				Subject subject = subjectDao.get(subjectCd, school);
				String subjectName = subject.getName();
				test.setSubjectName(subjectName);
				/*
				 * no:回数
				 * integer(10), primary key, not null
				 */
				int no = rSet.getInt("no");
				test.setNo(no);
				/*
				 * point:得点
				 * intger(10), value = null
				 */
				int point = rSet.getInt("point");
				test.setPoint(point);
				list.add(test);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return list;
	}
	/*
	 * filter:学生番号を指定して教科別成績一覧を取得
	 */
	public List<TestListStudent> filter(
			Student student
	) throws Exception {
		System.out.println("入力学生情報:");
		String studentNo = student.getNo();
		student.getName();
		student.getEntYear();
		student.getClassNum();
		student.isAttend();
		School school = student.getSchool();
		String schoolCd = school.getCd();
		// list:検索結果
		List<TestListStudent> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// SQL文を準備
			String sql = "select subject_cd, no, point from test"
					+ " where student_no = ? and school_cd = ?"
					+ " and point is not null"
					+ " order by subject_cd, no";
			statement = connection.prepareStatement(sql);
			statement.setString(1, studentNo);
			statement.setString(2, schoolCd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql
					.replaceFirst("\\?", studentNo)
					.replaceFirst("\\?", schoolCd));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			ResultSet rSet = statement.executeQuery();
			list = this.postFilter(rSet, school);
		} catch (Exception e) {
			 throw e;
		} finally {
			if (Objects.nonNull(statement)) {
				try {
					statement.close();
				} catch (SQLException sqlException) {
					 throw sqlException;
				}
			}
			if (Objects.nonNull(connection)) {
				try {
					connection.close();
				} catch (SQLException sqlException) {
					 throw sqlException;
				}
			}
		}
		return list;
	}
}