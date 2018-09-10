package indi.dpl.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import indi.dpl.commons.CommonUtils;
import indi.dpl.domain.User;
import indi.dpl.service.UserException;
import indi.dpl.service.UserService;

/**
 * Servlet implementation class RegistServlet
 * 
 * 负责注册的servlet，调用service中的regist方法
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userservice = new UserService();
		
		//先获取表单内容
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		//校验用户名、密码和验证码,使用map装错误信息，若map长度为0，表示校验通过，再进行提交给Service
		Map<String, String> errors = new HashMap<>();
		//用户名校验
		if(form.getUsername() == null || form.getUsername().trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		}else if(form.getUsername().length() < 3 || form.getUsername().length() > 20) {
			errors.put("username", "用户名长度必须在3~20个字节内!");
		}
		//密码校验
		if(form.getPassword() == null || form.getPassword().trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		}else if(form.getPassword().length() < 3 || form.getPassword().length() > 20) {
			errors.put("password", "密码长度必须在3~20个字节内!");
		}
		//验证码校验
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		if(form.getVerifyCode() == null || form.getVerifyCode().trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空");
		}else if(form.getVerifyCode().length() != 4) {
			errors.put("verifyCode", "验证码长度必须为4");
		}else if(!sessionVerifyCode.equalsIgnoreCase(form.getVerifyCode())) {
			errors.put("verifyCode", "验证码错误");
		}
		//判断map长度
		if(errors != null && errors.size()>0) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
		//通过校验后，将表单内容提交给Service,未捕捉到异常说明用户名未被注册，可以顺利注册，若捕捉到异常，说明已经被注册过了填写错误信息，保存在request域中，返回给regist.jsp
		try {
			userservice.regist(form);
			response.getWriter().println("<h1>注册成功!</h1>");
			request.getSession().setAttribute("session_user",form);
			response.getWriter().println("页面即将跳转");
			response.setHeader("Refresh", "5;URL=/user/welcome.jsp");
		} catch (UserException e) {
			//设置回显
			request.setAttribute("user", form);
			//设置错误信息
			request.setAttribute("msg", e.getMessage());
			//请求转发
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}
}
