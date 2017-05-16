package cn.com.ruin.common.service;

import java.util.List;

import cn.com.ruin.common.bean.Article;
import cn.com.ruin.common.dao.ArticleDao;

public class ArticleService {
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Article> getArticleListByPage(int pageNum,int pageSize){
		List<Article> arts = null;
		try{
			ArticleDao dao = new ArticleDao();
			int startNum = (pageNum - 1) * pageSize;
			int endNum = pageNum * pageSize;
			arts= dao.queryArticleDaoByPageStartNum(startNum, endNum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arts;
	}
}
