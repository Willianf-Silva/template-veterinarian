package br.com.wnfasolutions.veterinarian.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.wnfasolutions.veterinarian.config.property.WnfaApiProperty;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private WnfaApiProperty wnfaApiProperty;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
				.withClient(wnfaApiProperty.getSecurity().getClientWeb())
				.secret(passwordEncoder.encode(wnfaApiProperty.getSecurity().getSecretKeyClientWeb()))
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(wnfaApiProperty.getSecurity().getAccessTokenValidity())
				.refreshTokenValiditySeconds(wnfaApiProperty.getSecurity().getRefreshTokenValidity())
			.and()
				.withClient(wnfaApiProperty.getSecurity().getClientMobile())
				.secret(passwordEncoder.encode(wnfaApiProperty.getSecurity().getSecretKeyClientMobile()))
				.scopes("read")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(wnfaApiProperty.getSecurity().getAccessTokenValidity())
				.refreshTokenValiditySeconds(wnfaApiProperty.getSecurity().getRefreshTokenValidity());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.reuseRefreshTokens(false)
			.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey(wnfaApiProperty.getSecurity().getSecretKeyApp());
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter());
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
	
	private CorsFilter corsFilter() {
		final List<String> ORIGINS_ALLOWED = wnfaApiProperty.getSecurity().getOrigins();
		
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    
	    for (String origin : ORIGINS_ALLOWED) {
        	config.addAllowedOrigin(origin);			
		}
	    	
	    config.setAllowCredentials(true);
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}
