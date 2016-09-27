package cn.com.ruin.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.ruin.common.service.LoginService;

public class LoginAction extends HttpServlet{
	private static final long serialVersionUID = -2608656495042290857L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LoginService loginService = new LoginService();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String loginName = req.getParameter("loginName");
		System.out.println(req.getParameterNames().nextElement());
		String password = req.getParameter("password");
		try {
			if(loginService.login(loginName, password)){
				
			}else{
				
				out.write("用户名或密码错误!");
				out.flush();
				out.close();
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

}
