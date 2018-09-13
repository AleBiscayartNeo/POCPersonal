package org.neoris.beneficios.auth0;

import com.auth0.SessionUtils;
import com.auth0.json.auth.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.neoris.beneficios.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Auth0Filter implements Filter {

	@Autowired
	private TokenUtils utils;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String accessToken = (String) SessionUtils.get(req, "accessToken");
		String idToken = (String) SessionUtils.get(req, "idToken");
		Boolean isValid = false;

		String path = ((HttpServletRequest) request).getRequestURI();

		if (null != accessToken && path.contains("/app-beneficios/")) {

			UserInfo userInfo = utils.validateToken(accessToken);

			isValid = utils.infoUservalidateWeb(userInfo);

			if (path.equals("/app-beneficios/")) {

				if (isValid) {

					res.sendRedirect("/app-beneficios/home");
					return;
				} else {
					res.sendRedirect("/app-beneficios/login");
					return;
				}
			}

			if (path.equals("/app-beneficios/home")) {

				if (!isValid) {

					res.sendRedirect("/app-beneficios/error");
					return;
				}

			}
		} else if (path.contains("resources") || path.contains("error") || path.contains("login")
				|| path.contains("callback") || path.contains("services")) {
			// Do nothing
		} else if (path.contains("/app-beneficios/") && !path.equals("/app-beneficios/")) {
			res.sendRedirect("/app-beneficios/");
			return;
		}

		next.doFilter(request, response);
	}

    @Override
    public void destroy() {
    }
}
