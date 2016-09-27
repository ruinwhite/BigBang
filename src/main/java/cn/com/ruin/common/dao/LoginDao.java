package cn.com.ruin.common.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.com.ruin.common.bean.Account;
import cn.com.ruin.common.utils.DBConnection;

public class LoginDao {
	private Connection conn;
	public Account queryAccountInfoByLoginNameAndPassword(
			String loginName,String userPassword){
		Account account = null;
		String sql = "select account_no,user_id,role_id,login_name,"
				+ " password,level,xp,create_time,last_update_time"
				+ " from account where login_name = '"+loginName+"'"
				+ " and password = '"+userPassword+"'";
		try {
			conn = DBConnection.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				account = new Account();
				account.setAccountNo(rs.getLong(1));
				account.setUserId(rs.getLong(2));
				account.setRoleId(rs.getInt(3));
				account.setLoginName(rs.getString(4));
				account.setPassword(rs.getString(5));
				account.setLevel(rs.getInt(6));
				account.setXp(rs.getLong(7));
				account.setCreateTime(rs.getTimestamp(8));
				account.setLastUpdateTime(rs.getTimestamp(9));
			}
		} catch (SQLException | PropertyVetoException e) {
			e.printStackTrace();
		}
		return account;
		
	}
}
