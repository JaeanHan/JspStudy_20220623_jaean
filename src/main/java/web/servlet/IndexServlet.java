package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 요청의 종류
 * 1. view (HTML) 요청 -> 무조건 get 요청 (주소창)
 * 2. api(application programming interface) 요청 -> CRUD(post, get, put, delete) : Javascript (AJAX 동기, 비동기)
 * 3. 
 * 
 */

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		
	}

}
