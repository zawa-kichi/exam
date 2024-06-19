package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class ClassNumDao extends Dao{
	public List<Student> filter( School school)throws Exception{
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet= null;
		// SQL文の条件
		String order  = "order by no asc";
		try{
			// プリペアードステートメントにSQL文を実行
			statement = connection.prepareStatement(order);
			// プリペアードステートメントに学生コードをバインド
			statement.setString(1,school.getCd());
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = filter(school);
		}catch(Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (statement != null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}
}