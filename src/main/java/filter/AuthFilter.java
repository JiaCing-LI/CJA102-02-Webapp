package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JWTUtil;

@WebServlet("api/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String authHeader = request.getHeader("Authoruzation");
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("{error}");
			return;
		}
		String token = authHeader.substring(7);
		try {
			Integer memId = JWTUtil.
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

}
