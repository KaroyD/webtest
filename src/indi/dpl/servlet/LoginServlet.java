package indi.dpl.servlet;

import java.io.IOException;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService uService=new UserService();
		
		//封装表单
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		try {
			//没有抛出异常则说明登陆成功，将service返回的User对象保存在session中，并重定向到welcome.jsp
			User user=uService.login(form);
			request.getSession().setAttribute("session_user", user);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
			
			
		} catch (UserException e) {
			//捕捉到异常则说明登陆失败，此时将错误信息和登陆信息保存在request域中，然后请求转发至login.jsp
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	}

}
