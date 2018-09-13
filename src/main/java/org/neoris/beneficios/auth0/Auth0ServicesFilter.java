package org.neoris.beneficios.auth0;

import com.auth0.SessionUtils;
import com.auth0.json.auth.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.neoris.beneficios.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class Auth0ServicesFilter implements Filter {

    
	@Autowired
	private TokenUtils utils;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
			throws IOException, ServletException {
		
		Boolean isValid= false;
		
		HttpServletRequest req = (HttpServletRequest) request;
		

		String token = req.getHeader("Authorization");
		if (null == token) {
			token = (String) SessionUtils.get(req, "accessToken");
		}		
					
		UserInfo userInfo = utils.validateToken(token);

		isValid = utils.infoUservalidate(userInfo);

		if (!isValid) {
			System.out.println("error");
		} else {
			next.doFilter(request, response);
		}

	}


	@Override
	public void destroy() {
	}

}
