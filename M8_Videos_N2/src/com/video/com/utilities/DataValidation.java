package com.video.com.utilities;

public class DataValidation {

	public static void checkInfoUser(String name, String surname, String password) throws NullPointerException {
		if (name.equals("") || surname.equals("") || password.equals("")) {
			throw new NullPointerException("Uno o varios campos en blanco (nombre, apellido y password obligatorios)");
		}
	}

	public static void checkInfoVideo(String url, String title) throws NullPointerException {
		if (url.equals("") || title.equals("")) {
			throw new NullPointerException("Uno o varios campos en blanco (URL y título del vídeo obligatorios");
		}
	}
	
	public static void checkData(String data) throws NullPointerException {
		if (data.equals("")) {
			throw new NullPointerException("Campo obligatorio en blanco");
		}
	}
}
