package cn.com.ruin.common.utils;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourcePool {
	private static ComboPooledDataSource cds;
	private DataSourcePool() throws PropertyVetoException{
		cds = new ComboPooledDataSource();
		cds.setDriverClass("com.mysql.jdbc.Driver");
		cds.setJdbcUrl("jdbc:mysql://localhost:3306/husky");
		cds.setUser("root");
		cds.setPassword("root");
		cds.setMaxPoolSize(200);
		cds.setInitialPoolSize(3);
		cds.setMaxIdleTime(2000);
	}
	
	public synchronized static ComboPooledDataSource getDS() throws PropertyVetoException{
		if(cds == null){
			new DataSourcePool();
		}
		return cds;
	}
}
