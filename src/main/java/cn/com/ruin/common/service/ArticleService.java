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
			arts= dao.queryArticleDaoByPageStartNum(startNum, pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arts;
	}
	
	/**
	 * 查询文章总数
	 * @return
	 */
	public int getTotalArticleCount(){
		try{
			ArticleDao dao = new ArticleDao();
			return dao.queryTotalArticleCount();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 查询热点文章
	 * @return
	 */
	public List<Article> getHotArticles(){
		try{
			ArticleDao dao = new ArticleDao();
			return dao.queryHotArticles();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
