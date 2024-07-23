package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	/*
	 * baseSql:データ取得用のSQL
	 */
//	private String baseSql = "";
	/*
	 * postFilter:フィルター後のリストへの格納処理
	 */
	private List<TestListSubject> postFilter(
			ResultSet rSet,
			int entYear,
			String classNum
	) throws Exception {
		// list:格納リスト
		List<TestListSubject> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				/*
				 * student_no:学生番号
				 * varchar(10), primary key, not null
				 */
				String studentNo = rSet.getString("student_no");
				/*
				 * no:回数
				 * integer(10), primary key, not null
				 * point:得点
				 * intger(10), value = null
				 */
				int no = rSet.getInt("no");
				int point = rSet.getInt("point");
				// newStudent:学生番号が既存の時false
				boolean newStudent = true;
				TestListSubject test = new TestListSubject();
				if (list.size() > 0) {
					for (TestListSubject old : list) {
						if (studentNo == old.getStudentNo()) {
							newStudent = false;
							// 新規データを作成
							test = old;
							test.putPoint(no, point);
							// 既存のデータを削除
							list.remove(old);
							break;
						}
					}
				}
				if (newStudent) {
					/*
					 * ent_year:入学年度
					 * integer(10), value = null
					 */
					test.setEntYear(entYear);
					/*
					 * student_no:学生番号
					 * varchar(10), primary key, not null
					 */
					test.setStudentNo(studentNo);
					/*
					 * student_name:学生名
					 * varchar(10), value = null
					 */
					StudentDao studentDao = new StudentDao();
					Student student = studentDao.get(studentNo);
					String studentName = student.getName();
					test.setStudentName(studentName);
					/*
					 * class_num:クラス番号
					 * varchar(5), value = null
					 */
					test.setClassNum(classNum);
					/*
					 * no:回数
					 * integer(10), primary key, not null
					 * point:得点
					 * intger(10), value = null
					 */
					Map<Integer, Integer> points = new HashMap<Integer, Integer>();
					points.put(no, point);
					test.setPoints(points);
				}
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
	public List<TestListSubject> filter(
			int entYear,
			String classNum,
			Subject subject,
			School school
	) throws Exception {
		System.out.println("入力入学年度:'"+entYear+"'");
		System.out.println("入力クラス番号:'"+classNum+"'");
		System.out.println("入力科目情報:");
		subject.getSchool();
		String subjectCd = subject.getCd();
		subject.getName();
		System.out.println("入力学校情報:");
		String schoolCd = school.getCd();
		school.getName();
		// list:検索結果
		List<TestListSubject> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		// rSet:検索結果
		ResultSet rSet;
		try {
			// SQL文を準備
			String sql = "select t.student_no as student_no, t.no as no, coalesce(t.point,101) as point"
					+ " from test as t join student as s"
					+ " on t.student_no = s.no"
					+ " where s.ent_year = ? and s.class_num = ?"
					+ " and t.subject_cd = ? and t.school_cd = ?"
					+ " order by t.student_no, t.no;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subjectCd);
			statement.setString(4, schoolCd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql
					.replaceFirst("\\?", String.valueOf(entYear))
					.replaceFirst("\\?", classNum)
					.replaceFirst("\\?", subjectCd)
					.replaceFirst("\\?", schoolCd));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			rSet = statement.executeQuery();
			list = this.postFilter(rSet, entYear, classNum);
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