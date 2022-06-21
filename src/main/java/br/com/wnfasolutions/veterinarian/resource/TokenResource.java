package br.com.wnfasolutions.veterinarian.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.veterinarian.config.property.WnfaApiProperty;
import br.com.wnfasolutions.veterinarian.resource.swagger.TokenResourceSwagger;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenResource implements TokenResourceSwagger{
	
	@Autowired
	private WnfaApiProperty wnfaApiProperty;
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(wnfaApiProperty.getSecurity().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
