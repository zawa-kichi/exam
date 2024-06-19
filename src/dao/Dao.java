package dao;

import java.sql.Connection;

import javax.sql.DataSource;

public class Dao{
	static DataSource ds;
	public Connection getConnection() throws Exception{
		return ds.getConnection();
	}
}