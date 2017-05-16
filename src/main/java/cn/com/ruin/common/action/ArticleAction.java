package cn.com.ruin.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import cn.com.ruin.common.bean.Article;
import cn.com.ruin.common.service.ArticleService;

import com.alibaba.fastjson.JSONObject;

public class ArticleAction extends HttpServlet{

	private static final long serialVersionUID = -5329363942994443105L;
	
	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String parameter = "";
		Map<String,Object> result = new HashMap<String,Object>();;
		try{
			req.setCharacterEncoding("utf-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream)req.getInputStream(),"utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while((temp = br.readLine()) != null){
				sb.append(temp);
			}
			br.close();
			parameter = sb.toString();
			if(parameter != ""){
				String[] params = parameter.split("&");
				int pageNum = -1;
				int pageSize = -1;
				if(params.length==2){
					for(String param : params){
						String[] keyVal = param.split("=");
						if("pageNum".equals(keyVal[0])){
							pageNum = Integer.valueOf(keyVal[1]).intValue();
						}
						if("pageSize".equals(keyVal[0])){
							pageSize = Integer.valueOf(keyVal[1]).intValue();
						}
					}
				}
				if(pageNum < 1 || pageSize < 1){
					result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
					result.put("errorInfo", "页码或页长无效");
					log("页码或页长无效");
				}else{
					ArticleService articleService = new ArticleService();
					List<Article> arts = null;
					if((arts = articleService.getArticleListByPage(pageNum, pageSize)) != null){
						result.put("arts", arts);
						result.put("status", ""+HttpServletResponse.SC_OK);
						log("登录成功");
					}else{
						result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
						result.put("errorInfo", "无文章列表或分页查询失败");
						log("无文章列表或分页查询失败");
					}
				}
			}else{
				result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
				result.put("errorInfo", "分页查询参数异常");
				log("分页查询参数异常");
			}
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			resp.getWriter().write(json.toJSONString());
		}catch(Exception e){
			result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
			result.put("errorInfo", "获取文章列表失败");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			try {
				resp.getWriter().write(json.toJSONString());
			} catch (IOException e1) {
				log("系统异常", e);
			}
			log("获取文章列表失败", e);
		}
	}
}
