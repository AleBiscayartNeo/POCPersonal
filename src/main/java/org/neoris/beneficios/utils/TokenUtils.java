package org.neoris.beneficios.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.neoris.beneficios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class TokenUtils {
	
    @Autowired(required=true)
	@Qualifier("userService")
    private UserService userService;
	
    
    private final ObjectMapper mapper;
    private AppConfig config;
    private final TypeReference<UserInfo> tTType;
    

    public TokenUtils(AppConfig config) {
        this.mapper = new ObjectMapper();		
		  this.tTType = new TypeReference<UserInfo>() {
			};
		this.config = config;
		
    }
	
    public UserInfo validateToken(String token) {
    	String url = "https://portal.neoris.net/f5-oauth2/v1/introspect";

    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);    	
    	
    	nameValuePairs.add(new BasicNameValuePair("token_type_hint", "Bearer"));

    	nameValuePairs.add(new BasicNameValuePair("client_id",config.getClientId()));
    	nameValuePairs.add(new BasicNameValuePair("client_secret", config.getClientSecret()));
    	nameValuePairs.add(new BasicNameValuePair("token", token));


    	String postResult = null;
    	try {
			postResult =  sendPost(url, nameValuePairs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return parseValidate(postResult);
    }
    
    
    public boolean validarUser(String userName) {
		
		return userService.isUserAdminWeb(userName);
	}
    
	public Boolean infoUservalidateWeb(UserInfo holder) throws Auth0Exception {
		
		String user = null;		
		Boolean active = false;
		Boolean isValid = false;
		
		Map<String, Object> userInfoValues = holder.getValues();

		if (null != userInfoValues ) {
			
			active = (Boolean) userInfoValues.get("active");
			
			if(null !=userInfoValues.get("username")) {
				user = splitUserName(userInfoValues.get("username").toString());
			}				
			
		}
		
		if(active && validarUser(user)) {
			isValid = true;
		}
		
		return isValid;
	}
	
	public Boolean infoUservalidate(UserInfo holder) throws Auth0Exception {
			
		Boolean active = false;
		
		Map<String, Object> userInfoValues = holder.getValues();

		if (null != userInfoValues ) {
			
			active = (Boolean) userInfoValues.get("active");
			
		}

		return active;
	}
	
	
	private String splitUserName(String userName) {

		String user = null;

		if (null != userName) {
			String[] parts = userName.split("/");

			if (null != parts && null != parts[2]) {

				int inicio = parts[2].indexOf(".");

				user = parts[2].substring(inicio + 1);

			}

		}

		return user;

	}
	
    public String sendPost(String url, List<NameValuePair> postParams) 
            throws Exception {

        HttpPost post = new HttpPost(url);
        HttpClient client = new DefaultHttpClient();

        // add header
        post.setHeader("Origin", "J6W4EFQ6cw6JMYszkxhneS6HZhAYuGVWVeNn6ch4KnWwE");
        post.setHeader("Accept", 
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.setHeader("Accept-Language", "en-US,en;q=0.5");
        post.setHeader("Connection", "keep-alive");
        //post.setHeader("Referer", "https://portal.neoris.net/f5-oauth2/v1/token");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        post.setEntity(new UrlEncodedFormEntity(postParams));

        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
    

    private UserInfo parseValidate(String response) {
    	UserInfo tokenHolder = null;
        try {
           
        	tokenHolder = mapper.readValue(response, tTType);
        } catch (IOException e) {
            //throw new APIException("Failed to parse json body", response, e);
        }
        
        return tokenHolder;
    }
    
    

}
