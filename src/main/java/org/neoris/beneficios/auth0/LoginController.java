package org.neoris.beneficios.auth0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
@Controller
public class LoginController {

    @Autowired
    private AuthController controller;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(final HttpServletRequest req) {
        logger.debug("Performing login");
        String serverName = req.getServerName();
        String redirectUri = "";
        if (null != serverName && serverName.contains("localhost")) {
        	redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/app-beneficios/callback";
        } else {
        	redirectUri = req.getScheme() + "://" + req.getServerName() + "/app-beneficios/callback";
        }
        
        String authorizeUrl = controller.buildAuthorizeUrl(req, redirectUri);
        return "redirect:" + authorizeUrl;
    }

}
