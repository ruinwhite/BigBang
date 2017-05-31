package cn.com.ruin.common.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.ruin.common.bean.Article;
import cn.com.ruin.common.utils.DBConnection;

public class ArticleDao {
	private Connection conn;
	
	/**
	 * 通过页面起始码和结束码查询文章列表
	 * @param startNum
	 * @param endNum
	 * @return
	 */
	public List<Article> queryArticleDaoByPageStartNum(int startNum, int pageSize){
		List<Article> arts = null;
		Article art = null;
		String sql = "select id,user_id,title,content,category,tag,state,"
				+ "create_time,update_time,sikm_count from article "
				+ "where state!=3 limit "+startNum+","+pageSize;
		try {
			conn = DBConnection.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			arts = new ArrayList<Article>();
			while(rs.next()){
				art = new Article();
				art.setId(rs.getLong(1));
				art.setUserId(rs.getLong(2));
				art.setTitle(rs.getString(3));
				art.setContent(rs.getString(4));
				art.setCategory(rs.getInt(5));
				art.setTag(rs.getString(6));
				art.setState(rs.getInt(7));
				art.setCreateTime(rs.getTimestamp(8));
				art.setUpdateTime(rs.getTimestamp(9));
				art.setSikmCount(rs.getInt(10));
				arts.add(art);
			}
		} catch (SQLException | PropertyVetoException e) {
			e.printStackTrace();
		}
		return arts;
	}
	
	/**
	 * 查询文章数量
	 * @return
	 */
	public int queryTotalArticleCount(){
		String sql = "select count(1) from article where state!=3";
		int count = 0;
		try {
			conn = DBConnection.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException | PropertyVetoException e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}
}
