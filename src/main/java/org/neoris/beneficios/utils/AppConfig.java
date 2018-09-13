package org.neoris.beneficios.utils;

import javax.servlet.Filter;

import org.neoris.beneficios.auth0.Auth0Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SuppressWarnings("unused")
@Component
@Configuration
@EnableCaching
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
public class AppConfig {
    /**
     * This is your auth0 domain (tenant you have created when registering with auth0 - account name)
     */
    @Value(value = "${com.auth0.domain}")
    private String domain;

    /**
     * This is the client id of your auth0 application (see Settings page on auth0 dashboard)
     */
    @Value(value = "${com.auth0.clientId}")
    private String clientId;

    /**
     * This is the client secret of your auth0 application (see Settings page on auth0 dashboard)
     */
    @Value(value = "${com.auth0.clientSecret}")
    private String clientSecret;
    
    @Value("${mail.user}")
	private String mailUser;

	@Value("${mail.user.pass}")
	private String mailPassword;

	@Value("${mail.to}")
	private String to;

	@Value("${mail.subject}")
	private String subject;
	
	@Value("${encriptacion.key}")
	private String encriptacionKey;
	
	@Value("${encriptacion.iv}")
	private String encriptacionIv;
	
	@Value("${com.auth0.url}")
	private String url;

//    @Bean
//    public FilterRegistrationBean filterRegistration() {
//        final FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new Auth0Filter());
//        registration.setName(Auth0Filter.class.getSimpleName());
//        return registration;
//    }

    public String getDomain() {
        return domain;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
    
	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the encriptacionKey
	 */
	public String getEncriptacionKey() {
		return encriptacionKey;
	}

	/**
	 * @param encriptacionKey the encriptacionKey to set
	 */
	public void setEncriptacionKey(String encriptacionKey) {
		this.encriptacionKey = encriptacionKey;
	}

	/**
	 * @return the encriptacionIv
	 */
	public String getEncriptacionIv() {
		return encriptacionIv;
	}

	/**
	 * @param encriptacionIv the encriptacionIv to set
	 */
	public void setEncriptacionIv(String encriptacionIv) {
		this.encriptacionIv = encriptacionIv;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
