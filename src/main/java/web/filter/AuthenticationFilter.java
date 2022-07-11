package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public AuthenticationFilter() {
    
    }

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().contains("signin") || req.getRequestURI().contains("signup")) { // URI는 요청주소
			HttpSession session = req.getSession();			
			if(session.getAttribute("principal") != null) {
				((HttpServletResponse)response).sendRedirect("/index");
				return;
			}
			//메타 데이터 코어가 실제경로
		}
		request.getServletContext().;
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext();
	}

}
