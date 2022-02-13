package com.diceGame.nivel3.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.diceGame.nivel3.model.services.PlayerService;

//esta clase define las reglas de seguridad
//se ponen a funcionar los filtros (se implementa la lógica de seguridad personalizada extendiendo WebSecurityConfigurerAdapter)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private UserDetailsService userDetailsService;
	
	public SecurityConfig(PlayerService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el login no requiere autenticación
		 * 5. Se indica que el registro no requiere autenticación
		 * 6. Se indica que el resto de URLs esten securizadas
		 */
        httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.cors()
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_URL).permitAll()  //permitimos el acceso a /login a cualquiera
			.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll() //permitimos el acceso a /players a cualquiera (=cualquiera puede registrarse para jugar)
			.anyRequest().authenticated()
			.and()
			.addFilter(new JWTAuthenticationLoginFilter(authenticationManager())) // Las peticiones /login pasaran previamente por este filtro
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));  // Las demás peticiones pasarán por este filtro para validar el token
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios 
		auth.userDetailsService(userDetailsService);
	}
}
