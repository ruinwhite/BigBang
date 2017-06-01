package cn.com.ruin.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.com.ruin.common.bean.User;
import cn.com.ruin.common.service.LoginService;

public class LoginAction extends HttpServlet{
	private static final long serialVersionUID = -2608656495042290857L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=UTF-8");
		String parameter = "";
		Map<String,String> result = new HashMap<String,String>();
		BufferedReader br = null;
		try{
			req.setCharacterEncoding("utf-8");
			br = new BufferedReader(new InputStreamReader(
					(ServletInputStream)req.getInputStream(),"utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while((temp = br.readLine()) != null){
				sb.append(temp);
			}
			parameter = sb.toString();
			if(parameter != ""){
				String[] params = parameter.split("&");
				String loginName = "";
				String userPassword = "";
				if(params.length==2){
					for(String param : params){
						String[] keyVal = param.split("=");
						if("loginName".equals(keyVal[0])){
							loginName = keyVal[1];
						}
						if("userPassword".equals(keyVal[0])){
							userPassword = keyVal[1];
						}
					}
				}
				if(loginName.equals("") || userPassword.equals("")){
					result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
					result.put("errorInfo", "用户名或密码为空");
					log("用户名或密码为空");
				}else{
					LoginService loginService = new LoginService();
					User user = new User();
					if((user = loginService.login(loginName, userPassword)) != null){
						result.put("loginName", user.getUserName());
						result.put("userPassword", user.getPassword());
						result.put("status", ""+HttpServletResponse.SC_OK);
						log("登录成功");
					}else{
						result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
						result.put("errorInfo", "用户名或密码错误");
						log("用户名或密码错误");
					}
				}
			}else{
				result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
				result.put("errorInfo", "用户名或密码为空");
				log("用户名或密码为空");
			}
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			resp.getWriter().write(json.toJSONString());
		}catch(Exception e){
			result.put("status", ""+HttpServletResponse.SC_BAD_REQUEST);
			result.put("errorInfo", "登录异常");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			try {
				resp.getWriter().write(json.toJSONString());
			} catch (IOException e1) {
				log("系统异常", e);
			}
			log("登录异常", e);
		}finally{
			if(br != null){
				br.close();
			}
		}
	}

}
