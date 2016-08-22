package cn.com.ruin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends HttpServlet{
	private static final long serialVersionUID = -2608656495042290857L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String name = req.getParameter("name");
		String pwd = req.getParameter("password");
		//TODO service接口完成
		resp.sendRedirect("/husky/essay/public/html/public.html");
	}

}
