package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao{
	static DataSource ds;
	public Connection getConnection() throws Exception{
		if (ds == null){
			InitialContext ic = new InitialContext();
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/Javasystem");
		}
		return ds.getConnection();
	}
}