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

public class StudentDao extends Dao {
	/*
	 * baseSql:データ取得用のSQL
	 */
	private String baseSql = "select * from student where school_cd = ?";
	/*
	 * get:primary key(no)を用いたstudentテーブルの検索
	 */
	public Student get(String no) throws Exception {
		System.out.println("入力学生番号:'"+no+"'");
		// student:検索結果
		Student student = new Student();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// データベースを検索
			String sql = "select * from student where no = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, no);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", no));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			ResultSet rSet = statement.executeQuery();
			if (rSet.next()) {
				// 学生番号
				student.setNo(no);
				// 学生名
				String name = rSet.getString("name");
				student.setName(name);
				// 入学年度
				int entYear = rSet.getInt("ent_year");
				student.setEntYear(entYear);
				// クラス番号
				String classNum = rSet.getString("class_num");
				student.setClassNum(classNum);
				// 在学中フラグ
				boolean isAttend = rSet.getBoolean("is_attend");
				student.setAttend(isAttend);
				// 所属学校(学校コードから検索)
				String cd = rSet.getString("School_cd");
				SchoolDao dao = new SchoolDao();
				School school = dao.get(cd);
				student.setSchool(school);
			} else {
				student = null;
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
		return student;
	}
	/*
	 * postFilter:フィルター後のリストへの格納処理
	 */
	private List<Student> postFilter(
		ResultSet rSet,
		School school
	) throws Exception {
		// list:格納リスト
		List<Student> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				Student student = new Student();
				// 学生番号
				String no = rSet.getString("no");
				student.setNo(no);
				// 学生名
				String name = rSet.getString("name");
				student.setName(name);
				// 入学年度
				int entYear = rSet.getInt("ent_year");
				student.setEntYear(entYear);
				// クラス番号
				String classNum = rSet.getString("class_num");
				student.setClassNum(classNum);
				// 在学中フラグ
				boolean isAttend = rSet.getBoolean("is_attend");
				student.setAttend(isAttend);
				// 所属学校
				student.setSchool(school);
				list.add(student);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return list;
	}
	/*
	 * filter:学校、入学年度、クラス番号、在学フラグを指定して学生一覧を取得
	 */
	public List<Student> filter(
		School school,
		int entYear,
		String classNum,
		boolean isAttend
	) throws Exception {
		// list:検索結果
		List<Student> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		String condition = " and ent_year = ? and class_num = ?";
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}
		String order = " order by no";
		// rSet:検索結果
		ResultSet rSet;
		try {
			// SQL文を準備
			String sql = baseSql + condition + conditionIsAttend + order;
			statement = connection.prepareStatement(sql);
			String cd = school.getCd();
			statement.setString(1, cd);
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", cd)
					.replaceFirst("\\?", String.valueOf(entYear)).replaceFirst("\\?", classNum));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);
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
	 * filter:学校、入学年度、在学フラグを指定して学生一覧を取得
	 */
	public List<Student> filter(
			School school,
			int entYear,
			boolean isAttend
		) throws Exception {
		// list:検索結果
		List<Student> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		String condition = " and ent_year = ?";
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}
		String order = " order by no";
		// rSet:検索結果
		ResultSet rSet;
		try {
			// SQL文を準備
			String sql = baseSql + condition + conditionIsAttend + order;
			statement = connection.prepareStatement(sql);
			String cd = school.getCd();
			statement.setString(1, cd);
			statement.setInt(2, entYear);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", cd).replaceFirst("\\?", String.valueOf(entYear)));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);
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
	 * filter:学校、在学フラグを指定して学生一覧を取得
	 */
	public List<Student> filter(
			School school,
			boolean isAttend
		) throws Exception {
		// list:検索結果
		List<Student> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = " and is_attend = true";
		}
		String order = " order by no";
		// rSet:検索結果
		ResultSet rSet;
		try {
			// SQL文を準備
			String sql = baseSql + conditionIsAttend + order;
			statement = connection.prepareStatement(sql);
			String cd = school.getCd();
			statement.setString(1, cd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", cd));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);
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
	public boolean save(Student student) throws Exception {
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		int count = 0;
		try {
			// 学生番号
			String no = student.getNo();
			// 学生名
			String name = student.getName();
			// 入学年度
			int entYear = student.getEntYear();
			// クラス番号
			String classNum = student.getClassNum();
			// 在学中フラグ
			boolean isAttend = student.isAttend();
			// 学校コード
			String schoolCd = student.getSchool().getCd();
			// old:旧学生データ
			Student old = this.get(no);
			String sql;
			if (Objects.isNull(old)) {
				// 学生データが存在しない時、情報追加SQLを作成
				sql = "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				// 学生番号
				statement.setString(1, no);
				// 学生名
				statement.setString(2, name);
				// 入学年度
				statement.setInt(3, entYear);
				// クラス番号
				statement.setString(4, classNum);
				// 在学中フラグ
				statement.setBoolean(5, isAttend);
				// 学校コード
				statement.setString(6, schoolCd);
				StringBuilder printSql = new StringBuilder();
				printSql.append("追加用SQL文:'");
				printSql.append(sql.replaceFirst("\\?", no).replaceFirst("\\?", name)
						.replaceFirst("\\?", String.valueOf(entYear)).replaceFirst("\\?", classNum)
						.replaceFirst("\\?", String.valueOf(isAttend)).replaceFirst("\\?", schoolCd));
				printSql.append("'");
				System.out.println(printSql);
			} else {
				// 学生データが存在する時、情報更新SQLを作成
				sql = "update student set name = ?, ent_year = ?, class_num = ?, is_attend = ? where no = ?";
				statement = connection.prepareStatement(sql);
				// 学生名
				statement.setString(1, name);
				// 入学年度
				statement.setInt(2, entYear);
				// クラス番号
				statement.setString(3, classNum);
				// 在学中フラグ
				statement.setBoolean(4, isAttend);
				// 学生番号
				statement.setString(5, no);
				StringBuilder printSql = new StringBuilder();
				printSql.append("更新用SQL文:'");
				printSql.append(sql.replaceFirst("\\?", name)
						.replaceFirst("\\?", String.valueOf(entYear)).replaceFirst("\\?", classNum)
						.replaceFirst("\\?", String.valueOf(isAttend)).replaceFirst("\\?", no));
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
}