package web.servlet.api;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/username")
public class GetUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초 1회만 실행");
	}

	public void destroy() {
		System.out.println("서블릿 객체가 소멸될 때 실행");
	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("서비스 호출");
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("test");
		String name = request.getParameter("name");
		System.out.println("GET요청 들어옴");
		System.out.println("Read");

		System.out.println("test: " + t + " name: " + name);
		request.setAttribute("name", name);

		request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST요청 들어옴");
		System.out.println("Create");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PUT요청 들어옴");
		System.out.println("Update");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DELETE요청 들어옴");
		System.out.println("Delete");
	}
}
