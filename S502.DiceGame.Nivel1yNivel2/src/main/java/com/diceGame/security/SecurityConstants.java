package com.diceGame.security;

public class SecurityConstants {

	// Spring Security
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	// la secret key puede ser cualquiera pero lo suyo es que sea tan larga com el hash (si HS256 => 256bits/32chars)
//	public static final String SECRET = "SECRET_KEY-mejor-si-es-de-32-chars"; 
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 900_000; // 15 min: best practice agains secret key brute-forcing atacks
	public static final String SIGN_UP_URL = "/players";
}
