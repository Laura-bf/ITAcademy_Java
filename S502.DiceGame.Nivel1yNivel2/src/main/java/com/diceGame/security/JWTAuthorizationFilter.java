package com.diceGame.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
	
	/**
	 * Este método intercepta los requests y verifica el Authorization header
	 * Si éste no está presente o no empieza con BEARER continúa con la cadena de filtros
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
		String header = req.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		//si el header ok, se llama a getAuthentication() del jwtUtil
		Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)req);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
}
