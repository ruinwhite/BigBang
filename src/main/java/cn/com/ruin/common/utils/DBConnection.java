package cn.com.ruin.common.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	private DBConnection() throws SQLException, PropertyVetoException{
		if(conn == null){
			conn = DataSourcePool.getDS().getConnection();
		}
	}
	
	public synchronized static Connection getConn() throws SQLException, PropertyVetoException{
		if(conn == null){
			new DBConnection();
		}
		return conn;
	}
}
