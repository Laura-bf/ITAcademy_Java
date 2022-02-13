package com.diceGame.nivel3.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 */
/**
 * Authorization Filter se usa junto con un Authentication Filter (esto se establece en una WebSecurityConfig class)
 * 	-Authentication para saber si el usuario es realment quien dice ser
 * 	-Authorization verifica la existencia y validez del access token del Authorization header
 * 
 * En la configuration class,WebSecurityConfig, se especificarán qué endpoints están sujetos a este filtro
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}
	
	@Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		String header = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }
}
