package com.diceGame.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.diceGame.model.domain.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Es el LOGIN FILTER: se encargará de interceptar las peticiones que provengan de /login y obtener el username y password que vienen en el body de la petición.
 * Authentication Filter se usa junto con un Authorization Filter (esto se establece en una WebSecurityConfig class)
 * 	-Authentication para saber si el usuario es realment quien dice ser
 * 	-Authorization verifica la existencia y validez del access token del Authorization header
 */
public class JWTAuthenticationLoginFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationLoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		/**
         *  SetFilterProcessesUrl("uri") es un método que establece la ruta de la url para el login,
         *  si se comenta la linea 42 y no se especifica una ruta distinta, spring security crea el endpoint /login por defecto 
         *  (es por esto que no se define un endpoint para el login de forma explícita en el controller)
         */
//        setFilterProcessesUrl("/login"); 
	}
	
	/**
     * Método heredado de UsernamePasswordAuthenticationFilter, se ejecuta cuando el cliente intenta el log in a la applicación
     * 	- lee las credenciales
     * 	- crea un userPOJO a partir de éstas
     * 	- verifica que las credenciales son correctas para autenticar al usuario
     * Devuelve, si la autenticación es correcta, un Authentication Object que contiene las autorities que se le hayan pasado (linea 55)
     */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
		try {
			Player credenciales = new ObjectMapper().readValue(request.getInputStream(), Player.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getName(), 
																								credenciales.getPassword(), 
																								new ArrayList<>())); //como en la aplicación no hay roles definidos la lista de authorities está vacía.
			
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * Si la autenticación es correcta en attemptAuthenticatin(), entonces se ejecuta(successfulAuthentication)
     * De esto y de los parámetros que recibe se encarga Spring Security
     * (el parámetro Authentication auth, es el obtenido después de ok en attemptAuthentication())
     */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException{
//		String token = Jwts.builder()
//							.setIssuedAt(new Date())
//							.setSubject(((User)auth.getPrincipal()).getUsername())
//							.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
//							.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
//							.compact();
//		response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX +" "+ token);
		
		// Si la autenticacion fue exitosa, agregamos el token a la respuesta
        JwtUtil.addAuthentication(response, auth.getName());
	}

}
