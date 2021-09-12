package com.video.view;

import javax.swing.JOptionPane;

import com.video.com.utilities.DataValidation;
import com.video.controller.UserController;

public class UserWindow {

	private static UserWindow window;

	private UserWindow() {
	}

	// inicia una ventana de comunicación con el user
	public static UserWindow getWindow() {
		if (window == null) {
			window = new UserWindow();
		}
		return window;
	}

	// Menú inicio
	public int startMenu() {
		int startOption = JOptionPane.showOptionDialog(null, "Bienvenid@! ¿Qué quieres hacer?", "INICIO",
				JOptionPane.YES_NO_OPTION, 0, null,
				new Object[] { "Soy nuev@ - REGISTRARME", "Ya nos conocemos! - INICIAR SESIÓN" }, null);
		return startOption;
	}

	// Pide datos usuario
	public void infoUser() throws NullPointerException {
		boolean error = true;
		String userName = null;
		String userSurname = null;
		String password = null;
		while (error) {
			userName = JOptionPane.showInputDialog(null, "Dinos tu nombre", "TUS DATOS", JOptionPane.QUESTION_MESSAGE);
			if (userName != null) {
				userSurname = JOptionPane.showInputDialog(null, "Ahora tu apellido", "TUS DATOS",
						JOptionPane.QUESTION_MESSAGE);
				if (userSurname != null) {
					password = JOptionPane.showInputDialog(null, "y por último la contraseña", "TUS DATOS",
							JOptionPane.QUESTION_MESSAGE);
					if (password != null) {
						try {
							DataValidation.checkInfoUser(userName, userSurname, password);
							UserController.setActiveUser(userName, userSurname, password);
							error = false;
						} catch (NullPointerException dataExc) {
							System.err.println(dataExc.getMessage());
							wrongData();
							error = true;
						}
					} else
						error = false;
				} else
					error = false;
			} else
				error = false;
		}
	}

	public void wrongData() {
		JOptionPane.showInternalMessageDialog(null, "Nombre, Apellido y Contraseña son obligatorios");
	}

	public void regUserCancelled() {
		JOptionPane.showMessageDialog(null, "Registro cancelado");
	}

	// Informa de usuario registrado correctamente
	public void regUser() {
		JOptionPane.showMessageDialog(null, "REGISTRO OK!");
	}

	// Informa de que el usuario ya está registrado
	public void regUserExist(String name) {
		JOptionPane.showMessageDialog(null, "Ya tenemos un registro con tu nombre, " + name.toUpperCase());
	}

	// Informa de usuario NO registrado
	public int noRegUser() {
		int noReg = JOptionPane.showOptionDialog(null, "No te encontramos por aquí", "UPSS!!",
				JOptionPane.YES_NO_CANCEL_OPTION, 0, null, new Object[] { "Intentarlo de nuevo", "Registrarme" }, null);
		return noReg;
	}

	// Confirma datos nuevo registro
	public boolean checkInfoUser(String infoUser) {
		int checkInfo = JOptionPane.showOptionDialog(null, infoUser, "Confirma tus datos de registro",
				JOptionPane.YES_NO_OPTION, 0, null, new Object[] { "OK!", "Volver al Inicio" }, null);
		if (checkInfo == 0)
			return true;
		else
			return false;
	}

	public void setPassword() {
		String password = JOptionPane.showInputDialog(null, "Confirma tu contraseña", "TUS DATOS",
				JOptionPane.QUESTION_MESSAGE);
		UserController.getActiveUSer().setPassword(password);
	}

	// Pregunta si quiere iniciar sesion
	public int askStartSesion(String name) {
		return JOptionPane.showOptionDialog(null, "¿Iniciamos sesión con tus datos?", null, JOptionPane.YES_NO_OPTION,
				0, null, new Object[] { "OK!", "Salir" }, null);
	}

	// Informa de que se cierra la aplicación
	public void closeApp() {
		JOptionPane.showMessageDialog(null, "Saliendo de la aplicación...");

	}
}
