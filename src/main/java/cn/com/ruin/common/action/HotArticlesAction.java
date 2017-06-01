package cn.com.ruin.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ruin.common.bean.Article;
import cn.com.ruin.common.service.ArticleService;

import com.alibaba.fastjson.JSONObject;

public class HotArticlesAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			ArticleService articleService = new ArticleService();
			List<Article> arts = null;
			if((arts = articleService.getHotArticles()) != null){
				result.put("arts", arts);
				result.put("status", ""+HttpServletResponse.SC_OK);
				log("热点文章查询成功");
			}
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			resp.getWriter().write(json.toJSONString());
		}catch(Exception e){
			result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
			result.put("errorInfo", "获取热点文章列表失败");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			try {
				resp.getWriter().write(json.toJSONString());
			} catch (IOException e1) {
				log("系统异常", e);
			}
			log("获取热点文章列表失败", e);
		}
	}
}
