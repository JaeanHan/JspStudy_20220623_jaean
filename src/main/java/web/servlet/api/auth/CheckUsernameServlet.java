package web.servlet.api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/check/username")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService; 
	
	public CheckUsernameServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		String username = request.getParameter("username");
		response.setContentType("text/plain; charsetUTF-8");
		
		try {
			response.getWriter().print(userService.checkUsername(username)); // Ajax 요청에 대한 응답으로 데이터 전송
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
