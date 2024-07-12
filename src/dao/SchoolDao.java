package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {
	/*
	 * get:
	 * primary keyの学校コードを用いてデータベース内の一致するデータを検索
	 * 見つからない場合、nullを返す
	 */
	public School get(String cd) throws Exception {
		System.out.println("入力学校コード:'"+cd+"'");
		// school:検索結果
		School school = new School();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			// データベースを検索
			String sql = "select * from school where cd = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, cd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", cd));
			printSql.append("'");
			System.out.println(printSql);
			ResultSet resultSet = statement.executeQuery();
			// 検索結果を格納
			if (resultSet.next()) {
				// 学校コード
				school.setCd(cd);
				// 学校名
				String name = resultSet.getString("name");
				school.setName(name);
			} else {
				school = null;
			}
		} catch (Exception e) {
			 throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqlException) {
					 throw sqlException;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqlException) {
					 throw sqlException;
				}
			}
		}
		return school;
	}

	public String getString(String cd) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}