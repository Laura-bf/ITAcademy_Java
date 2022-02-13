package com.diceGame.nivel3.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import com.diceGame.nivel3.domain.entities.Player;

/**
 * Es el Authentication Filter: se encargará de interceptar las peticiones que provengan de /login y obtener el username y password que vienen en el body de la petición.
 * Cuando llega una petición /login nuestro filtro LoginFilter se encargará de validar las credenciales, 
 * en caso de ser válidas, creará un JWT y se enviará de regreso al cliente.
 * A partir de aquí el cliente deberá enviar este mismo token al servidor cada vez que solicite recursos protegidos. 
 * Podemos observar que tenemos una clase de utilidad llamada JwtUtil la cuál usamos para crear el token.
 */
public class JWTAuthenticationLoginFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationLoginFilter(AuthenticationManager authManager) {
        this.authenticationManager=authManager;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{

		try {
		
			//obtenemos el body de la peticion (inputstream, que asumimos viene en formato JSON
			//el body tendrá un JSON que será Player.class {"username": "nombre", "password":"password"}
			//se realiza un mapeo a la clase user para tener ahi los datos
			Player user = new ObjectMapper().readValue(req.getInputStream(),Player.class);
        
			// Finalmente autenticamos
			// Spring comparará el user/password recibidos
			// contra el que definimos en la clase SecurityConfig
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getName(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
        JwtUtil.addAuthentication(res, auth.getName());
    }
}
