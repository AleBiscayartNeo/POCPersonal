package org.neoris.beneficios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auth0.SessionUtils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
@Controller
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PATH = "/error";

    @RequestMapping("/error")
	public ModelAndView welcome(final HttpServletRequest req) {

		String accessToken = (String) SessionUtils.get(req, "accessToken");
		String idToken = (String) SessionUtils.get(req, "idToken");

		ModelAndView model = new ModelAndView("error");

		return model;
	}
    
    public String getErrorPath() {
        return PATH;
    }

}