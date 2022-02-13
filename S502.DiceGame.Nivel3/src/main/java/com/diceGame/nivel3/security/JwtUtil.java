package com.diceGame.nivel3.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.diceGame.nivel3.model.services.PlayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

@Component
public class JwtUtil {

	private static PlayerService playerService;
	
	@Autowired
	public JwtUtil(PlayerService playerService) {
		JwtUtil.playerService = playerService;
	}
	 // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public static void addAuthentication(HttpServletResponse res, String username) {
    	String playerId = playerService.getPlayerIdByName(username).toString();
    	
        String token = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
            .claim("playerId", playerId)
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
            .compact();

        //agregamos al encabezado el token
        res.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY, SecurityConstants.TOKEN_BEARER_PREFIX + token);
    }
 // Método para validar el token enviado por el cliente
    public static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
        
        //Obtenemos, si lo hay, el playerId de la url de la petición
        String requestPlayerId = getPlayerIdFromUrlPath(request); 

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET_KEY)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_BEARER_PREFIX, "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            if(requestPlayerId==null) {
            	// Recordamos que para las demás peticiones que no sean /login no requerimos una autenticacion por username/password
            	// por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            	return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()):null;
            
            }else if (requestPlayerId != null) {
            	//esto se puede hacer mejor obteniendo del claims del token el valor de "playerId" pero no me sale...
            	String playerName = playerService.getPlayerNameById(Integer.valueOf(requestPlayerId));
            	
            	return user.equals(playerName) ? new UsernamePasswordAuthenticationToken(user, null, emptyList()):null;
            }
        }
        return null;
    }
    
    //Método para obtener el id del url path si lo hubiera
    private static String getPlayerIdFromUrlPath(HttpServletRequest request) {
    	String urlPath = request.getRequestURI(); // includes leading forward slash
    	if(urlPath.endsWith("/rolls")) {
    		String[] strings = urlPath.split("/rolls"); //divide string en dos (/players/playerId y /rolls)
        	return strings[0].substring(9); //para obetener chars despues de /players/(8chars)
    	} else
    		return null;
    }
}
