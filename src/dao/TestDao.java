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
import bean.Test;

public class TestDao extends Dao {
	/*
	 * get:primary key(no)を用いたstudentテーブルの検索
	 */
	public Test get(
			Student student,
			Subject subject,
			School school,
			int no) throws Exception {
		System.out.println("入力学生情報:");
		String studentNo = student.getNo();
		student.getName();
		student.getEntYear();
		student.getClassNum();
		student.isAttend();
		student.getSchool();
		System.out.println("入力科目情報:");
		subject.getSchool();
		String subjectCd = subject.getCd();
		subject.getName();
		System.out.println("入力学校情報:");
		String schoolCd = school.getCd();
		school.getName();
		System.out.println("入力試験回数:'"+no+"'");
		// test:検索結果
		Test test = new Test();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// データベースを検索
			String sql = "select * from test"
					+ " where student_no = ? and subject_cd = ?"
					+ " and school_cd = ? and no = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, studentNo);
			statement.setString(2, subjectCd);
			statement.setString(3, schoolCd);
			statement.setInt(4, no);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql
					.replaceFirst("\\?", studentNo)
					.replaceFirst("\\?", subjectCd)
					.replaceFirst("\\?", schoolCd)
					.replaceFirst("\\?", String.valueOf(no)));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			ResultSet rSet = statement.executeQuery();
			if (rSet.next()) {
				/*
				 * student_no:学生番号
				 * varchar(10), primary key, not null
				 */
				test.setStudent(student);
				/*
				 * subject_cd:科目コード
				 * char(3), primary key, not null
				 */
				test.setSubject(subject);
				/*
				 * school_cd:学校コード
				 * char(3), primary key, not null
				 */
				test.setSchool(school);
				/*
				 * no:回数
				 * integer(10), primary key, not null
				 */
				test.setNo(no);
				/*
				 * point:得点
				 * intger(10), value = null
				 */
				int point = rSet.getInt("point");
				test.setPoint(point);
				/*
				 * class_num:クラス番号
				 * varchar(5), value = null
				 */
				String classNum = rSet.getString("class_num");
				test.setClassNum(classNum);
			} else {
				test = null;
			}
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
		return test;
	}
	/*
	 * postFilter:フィルター後のリストへの格納処理
	 */
	private List<Test> postFilter(
		ResultSet rSet,
		String classNum,
		Subject subject,
		int no,
		School school
	) throws Exception {
		// list:格納リスト
		List<Test> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				Test test = new Test();
				/*
				 * student_no:学生番号
				 * varchar(10), primary key, not null
				 */
				StudentDao studentDao = new StudentDao();
				String studentNo = rSet.getString("no");
				Student student = studentDao.get(studentNo);
				test.setStudent(student);
				/*
				 * subject_cd:科目コード
				 * char(3), primary key, not null
				 */
				test.setSubject(subject);
				/*
				 * school_cd:学校コード
				 * char(3), primary key, not null
				 */
				test.setSchool(school);
				/*
				 * no:回数
				 * integer(10), primary key, not null
				 */
				test.setNo(no);
				/*
				 * point:得点
				 * intger(10), value = null
				 */
				int point = rSet.getInt("point");
				test.setPoint(point);
				/*
				 * class_num:クラス番号
				 * varchar(5), value = null
				 */
				test.setClassNum(classNum);
				list.add(test);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return list;
	}
	/*
	 * filter:入学年度、クラス番号、強化、試験回数、学校を指定して学生一覧を取得
	 */
	public List<Test> filter(
			int entYear,
			String classNum,
			Subject subject,
			int no,
			School school
	) throws Exception {
		System.out.println("入力入学年度:'"+entYear+"'");
		System.out.println("入力クラス番号:'"+classNum+"'");
		System.out.println("入力科目情報:");
		subject.getSchool();
		String subjectCd = subject.getCd();
		subject.getName();
		System.out.println("入力試験回数:'"+no+"'");
		System.out.println("入力学校情報:");
		String schoolCd = school.getCd();
		school.getName();
		// list:検索結果
		List<Test> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		// rSet:検索結果
		ResultSet rSet;
		try {
			// SQL文を準備
			String sql = "select s.no as no, coalesce(t.point, 101) as point"
					+ " from (select * from test where subject_cd = ? and no = ?) as t"
					+ " right join student as s"
					+ " on t.student_no = s.no"
					+ " where s.ent_year = ? and s.class_num = ? and s.school_cd = ?"
					+ " order by no;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, subjectCd);
			statement.setInt(2, no);
			statement.setInt(3, entYear);
			statement.setString(4, classNum);
			statement.setString(5, schoolCd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql
					.replaceFirst("\\?", subjectCd)
					.replaceFirst("\\?", String.valueOf(no))
					.replaceFirst("\\?", String.valueOf(entYear))
					.replaceFirst("\\?", classNum)
					.replaceFirst("\\?", schoolCd));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			rSet = statement.executeQuery();
			list = this.postFilter(rSet, classNum, subject, no, school);
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
	/*
	 * save:データベースに学生情報を追加
	 * 学生情報が存在する場合は更新
	 */
	public boolean save(List<Test> list) throws Exception {
		// データベースと接続
		Connection connection = getConnection();
		int count = 0;
		try {
			for (Test test : list) {
				boolean saveResult = this.save(test, connection);
				if (saveResult) {
					count++;
				}
			}
		} catch (Exception e) {
			 throw e;
		} finally {
			if (Objects.nonNull(connection)) {
				try {
					connection.close();
				} catch (SQLException sqlException) {
					 throw sqlException;
				}
			}
		}
		return count > 0;
	}
	private boolean save(Test test, Connection connection) throws Exception {
		// SQL文を準備
		PreparedStatement statement = null;
		int count = 0;
		try {
			/*
			 * student_no:学生番号
			 * varchar(10), primary key, not null
			 */
			Student student = test.getStudent();
			String studentNo = student.getNo();
			/*
			 * subject_cd:科目コード
			 * char(3), primary key, not null
			 */
			Subject subject = test.getSubject();
			String subjectCd = subject.getCd();
			/*
			 * school_cd:学校コード
			 * char(3), primary key, not null
			 */
			School school = test.getSchool();
			String schoolCd = school.getCd();
			/*
			 * no:回数
			 * integer(10), primary key, not null
			 */
			int no = test.getNo();
			/*
			 * point:得点
			 * intger(10), value = null
			 */
			int point = test.getPoint();
			/*
			 * class_num:クラス番号
			 * varchar(5), value = null
			 */
			String classNum = test.getClassNum();
			// old:旧学生データ
			Test old = this.get(student, subject, school, no);
			String sql;
			if (Objects.isNull(old)) {
				// 学生データが存在しない時、情報追加SQLを作成
				sql = "insert into test"
						+ "(student_no, subject_cd, school_cd, no, point, class_num)"
						+ " values(?, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, studentNo);
				statement.setString(2, subjectCd);
				statement.setString(3, schoolCd);
				statement.setInt(4, no);
				statement.setInt(5, point);
				statement.setString(6, classNum);
				StringBuilder printSql = new StringBuilder();
				printSql.append("追加用SQL文:'");
				printSql.append(sql
						.replaceFirst("\\?", studentNo)
						.replaceFirst("\\?", subjectCd)
						.replaceFirst("\\?", schoolCd)
						.replaceFirst("\\?", String.valueOf(no))
						.replaceFirst("\\?", String.valueOf(point))
						.replaceFirst("\\?", classNum));
				printSql.append("'");
				System.out.println(printSql);
			} else {
				// 学生データが存在する時、情報更新SQLを作成
				sql = "update test set point = ?"
						+ " where student_no = ? and subject_cd = ? and school_cd = ? and no = ?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, point);
				statement.setString(2, studentNo);
				statement.setString(3, subjectCd);
				statement.setString(4, schoolCd);
				statement.setInt(5, no);
				StringBuilder printSql = new StringBuilder();
				printSql.append("更新用SQL文:'");
				printSql.append(sql
						.replaceFirst("\\?", String.valueOf(point))
						.replaceFirst("\\?", studentNo)
						.replaceFirst("\\?", subjectCd)
						.replaceFirst("\\?", schoolCd)
						.replaceFirst("\\?", String.valueOf(no)));
				printSql.append("'");
				System.out.println(printSql);
			}
			// SQLを実行
			count = statement.executeUpdate();
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
		}
		return count > 0;
	}
//	public boolean delete(List<Test> list) throws Exception{
//		return true;
//	}
//	private boolean delete(Test test, Connection connection) throws Exception{
//		return true;
//	}
}