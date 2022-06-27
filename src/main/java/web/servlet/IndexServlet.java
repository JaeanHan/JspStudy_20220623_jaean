package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("서블릿 생성");
	}
	
	public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청에대한 응답");
//		PrintWriter out = response.getWriter();
//		out.println("<h1>this is index page</h1>");
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void destroy() {
		System.out.println("서블릿 소멸");
	}
}
