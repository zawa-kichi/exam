package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao{
	public Teacher get(String id)throws Exception{
		// 教師インスタンスを初期化
		Teacher teacher = new Teacher();
		// データベースのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try{
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from teacher where id = ?");
			// プリペアードステートメントに学生番号をセット
			statement.setString(1, id);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
		}catch(Exception e){
			throw e;
		}finally{
			// プリペアードステートメントを閉じる
			if (statement != null){
				try{
					statement.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null){
				try{
					connection.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return teacher;
	}

	public Teacher login(String id,String password)throws Exception{
		Teacher teacher = null;

		Connection con = getConnection();

		PreparedStatement st;
		st=con.prepareStatement(
			"select * from teacher where id=? and password=?");
		st.setString(1,id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while(rs.next()){
			teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setPassword(rs.getString("password"));
		}
		// プリペアードステートメントを閉じる
		st.close();
		// コネクションを閉じる
		con.close();
		// 教師インスタンスに返す
		return teacher;
	}
}