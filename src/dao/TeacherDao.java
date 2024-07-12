package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {
	/*
	 * get:
	 * primary keyのIDを用いてデータベース内の一致するデータを検索
	 * 見つからない場合、nullを返す
	 */
	public Teacher get(String id) throws Exception {
		System.out.println("入力教員ID:'"+id+"'");
		// teacher:検索結果
		Teacher teacher = new Teacher();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// データベースを検索
			String sql = "select * from teacher where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", id));
			printSql.append("'");
			System.out.println(printSql);
			ResultSet set = statement.executeQuery();
			// 検索結果を格納
			if (set.next()) {
				// ID
				teacher.setId(id);
				// パスワード
				String password = set.getString("password");
				teacher.setPassword(password);
				// 氏名
				String name = set.getString("name");
				teacher.setName(name);
				// 所属学校(学校コードから検索)
				SchoolDao dao = new SchoolDao();
				String cd = set.getString("school_cd");
				School school = dao.get(cd);
				teacher.setSchool(school);
			} else {
				teacher = null;
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
		return teacher;
	}
	/*
	 * login:
	 * IDとパスワードが一致する教員データを検索
	 * 一致しない場合、nullを返す
	 */
	public Teacher login(String id, String password) throws Exception{
		System.out.println("入力教員ID:'"+id+"'");
		System.out.println("入力パスワード:'"+password+"'");
		// teacher:検索結果
		Teacher teacher = new Teacher();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// データベースを検索
			String sql = "select * from teacher where id = ? and password = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, password);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", id).replaceFirst("\\?", password));
			printSql.append("'");
			System.out.println(printSql);
			ResultSet set = statement.executeQuery();
			// 検索結果を格納
			if (set.next()) {
				// ID
				teacher.setId(id);
				// パスワード
				teacher.setPassword(password);
				// 氏名
				String name = set.getString("name");
				teacher.setName(name);
				// 所属学校(学校コードから検索)
				SchoolDao dao = new SchoolDao();
				String cd = set.getString("school_cd");
				School school = dao.get(cd);
				teacher.setSchool(school);
			} else {
				teacher = null;
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
		return teacher;
	}
}