package org.neoris.beneficios.auth0;

import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.auth.UserInfo;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.net.AuthRequest;
import com.auth0.net.CustomRequest;
import com.auth0.net.Request;
import com.auth0.net.TokenRequest;
import com.auth0.utils.Asserts;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Response;
import okhttp3.ResponseBody;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.neoris.beneficios.services.UserService;
import org.neoris.beneficios.utils.AppConfig;
import org.neoris.beneficios.utils.HeaderRequestInterceptor;
import org.neoris.beneficios.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class CallbackController {

    @Autowired
    private AuthController controller;
    private final String redirectOnFail;
    private final String redirectOnSuccess;
   // private final AuthAPI client;
    private static final String KEY_CODE = "code";
    private final ObjectMapper mapper;
    private final TypeReference<TokenHolder> tType;
    private AppConfig config;
    private final TypeReference<UserInfo> tTType;
    
    @Autowired(required=true)
	@Qualifier("userService")
    private UserService userService;

	
	@Autowired
	private TokenUtils utils;
    
    public CallbackController(AppConfig config) {
        this.redirectOnFail = "/app-beneficios/login";
        this.redirectOnSuccess = "/app-beneficios/home";
       // this.client = new AuthAPI(config.getDomain(), config.getClientId(), config.getClientSecret());
        this.mapper = new ObjectMapper();
        this.tType = new TypeReference<TokenHolder>() {
		};
		
		  this.tTType = new TypeReference<UserInfo>() {
			};
		this.config = config;
		
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
        	Boolean isValid= false;
        	Tokens tokens = null;
        	String userName = null;
        	String redirectUri = req.getRequestURL().toString();
        	String authorizationCode = req.getParameter(KEY_CODE);

        	tokens = exchangeCodeForTokens(authorizationCode,redirectUri);
     	        	      	
        	SessionUtils.set(req, "accessToken", tokens.getAccessToken());
        	res.sendRedirect("/app-beneficios/home");
        	
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }
    


	private Tokens exchangeCodeForTokens(String authorizationCode, String redirectUri) throws Auth0Exception {
        TokenHolder holder = exchangeCode(authorizationCode, redirectUri);
        return new Tokens(holder.getAccessToken(), holder.getIdToken(), holder.getRefreshToken(), holder.getTokenType(), holder.getExpiresIn());
    }

    private Tokens mergeTokens(Tokens tokens, Tokens latestTokens) {
        String accessToken = latestTokens.getAccessToken() != null ? latestTokens.getAccessToken() : tokens.getAccessToken();
        String idToken = latestTokens.getIdToken() != null ? latestTokens.getIdToken() : tokens.getIdToken();
        String refreshToken = latestTokens.getRefreshToken() != null ? latestTokens.getRefreshToken() : tokens.getRefreshToken();
        String type = latestTokens.getType() != null ? latestTokens.getType() : tokens.getType();
        Long expiresIn = latestTokens.getExpiresIn() != null ? latestTokens.getExpiresIn() : tokens.getExpiresIn();
        return new Tokens(accessToken, idToken, refreshToken, type, expiresIn);
    }


    private TokenHolder exchangeCode(String code, String redirectUri) {
    	String url = "https://portal.neoris.net/f5-oauth2/v1/token";
    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    	nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));

    	nameValuePairs.add(new BasicNameValuePair("client_id",config.getClientId()));
    	nameValuePairs.add(new BasicNameValuePair("client_secret", config.getClientSecret()));
    	nameValuePairs.add(new BasicNameValuePair("redirect_uri", redirectUri));
    	nameValuePairs.add(new BasicNameValuePair("code", code));

    	String postResult = null;
    	try {
			postResult =  utils.sendPost(url, nameValuePairs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return parseResponse(postResult);
    }
    
    
    private TokenHolder parseResponse(String response) {
    	TokenHolder tokenHolder = null;
        try {
           
        	tokenHolder = mapper.readValue(response, tType);
        } catch (IOException e) {
            //throw new APIException("Failed to parse json body", response, e);
        }
        
        return tokenHolder;
    }
}
