package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {
	/*
	 * filter:学校を指定し、クラス番号リストをデータベースから抽出する
	 */
	public List<String> filter(School school) throws Exception {
		// list:検索結果
		List<String> list = new ArrayList<>();
		// データベースと接続
		Connection connection = getConnection();
		// SQL文を準備
		PreparedStatement statement = null;
		try {
			String sql = "select class_num from class_num where school_cd = ? order by class_num";
			statement = connection.prepareStatement(sql);
			String cd = school.getCd();
			statement.setString(1, cd);
			StringBuilder printSql = new StringBuilder();
			printSql.append("検索用SQL文:'");
			printSql.append(sql.replaceFirst("\\?", cd));
			printSql.append("'");
			System.out.println(printSql);
			// 検索結果を格納
			ResultSet set = statement.executeQuery();
			try {
				while (set.next()) {
					String classNum = set.getString("class_num");
					list.add(classNum);
				}
			} catch (Exception e) {
				 e.printStackTrace();
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
		return list;
	}
}