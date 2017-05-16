package cn.com.ruin.common.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.com.ruin.common.bean.User;
import cn.com.ruin.common.utils.DBConnection;

public class LoginDao {
	private Connection conn;
	
	/**
	 * 通过用户名查询用户信息
	 * @param loginName
	 * @return
	 */
	public User queryUserPasswordByLoginName(
			String loginName){
		User user = null;
		String sql = "select user_id,user_name,password,nick_name,"
				+ " name,id_card,country,province,city,"
				+ " age,phone,email,signed,state,"
				+ " register_time,last_update_time"
				+ " from user where user_name = '"+loginName+"'";
		try {
			conn = DBConnection.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNickName(rs.getString(4));
				user.setName(rs.getString(5));
				user.setIdCard(rs.getString(6));
				user.setCountry(rs.getString(7));
				user.setProvince(rs.getString(8));
				user.setCity(rs.getString(9));
				user.setAge(rs.getInt(10));
				user.setPhone(rs.getString(11));
				user.setEmail(rs.getString(12));
				user.setSigned(rs.getString(13));
				user.setState(rs.getInt(14));
				user.setRegisterTime(rs.getTimestamp(15));
				user.setLastUpdateTime(rs.getTimestamp(16));
			}
		} catch (SQLException | PropertyVetoException e) {
			e.printStackTrace();
		}
		return user;
		
	}
}
