package org.neoris.beneficios.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.SessionUtils;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public ModelAndView welcome(final HttpServletRequest req) {

		String accessToken = (String) SessionUtils.get(req, "accessToken");
		String idToken = (String) SessionUtils.get(req, "idToken");

		ModelAndView model = new ModelAndView("home");

		if (accessToken != null) {
			model.getModelMap().put("userId", accessToken);
		} else if (idToken != null) {
			model.getModelMap().put("userId", idToken);
		}

		return model;
	}
}
