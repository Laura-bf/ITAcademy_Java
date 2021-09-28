package com.video.view;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.video.com.utilities.DataValidation;
import com.video.controller.VideoController;

public class VideoWindow {

	private static VideoWindow videoWindow;

	private VideoWindow() {
	}

	// inicia una ventana de comunicaci�n sobre videos con el user
	public static VideoWindow getWindow() {
		if (videoWindow == null) {
			videoWindow = new VideoWindow();
		}
		return videoWindow;
	}

	// Menu inicio sesi�n - opciones video
	public int startSesion(String name) {
		return JOptionPane.showOptionDialog(null, "�Qu� hacemos " + name.toUpperCase() + "?", null, JOptionPane.DEFAULT_OPTION,
				3, null, new Object[] { "Ver V�deos Publicados", "Reproducir V�deo", "Crear Nuevo V�deo", "Salir"  }, null);
	}
	
	public void showVideos(int userId) {
		String allVideos = VideoController.showPostedVideos(userId);
		JTextArea msg = new JTextArea(allVideos);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setRows(25);
		msg.setSize(900, 300);

		JScrollPane scrollPane = new JScrollPane(msg);

		JOptionPane.showMessageDialog(null, scrollPane);
	}
	
	public String chooseVideo(int userId) {
		JOptionPane.showInternalMessageDialog(null, "Indica el ID del video que quieres reproducir");
		
		String allVideos = VideoController.showPostedVideos(userId);
		JTextArea msg = new JTextArea(allVideos);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setRows(25);
		msg.setSize(900, 300);

		JScrollPane scrollPane = new JScrollPane(msg);
		boolean error = true;
		String idOption = null;
		while(error) {
			idOption = JOptionPane.showInputDialog(null, scrollPane);
			if(idOption!=null) {
				try {
					DataValidation.checkData(idOption);
					error = false;
				}catch (NullPointerException wrongId) {
					wrongId();
					error = true;
				}
			}else {
				error = false;
				JOptionPane.showMessageDialog(null, "Saliendo del reproductor de v�deo...");
			}
		}		
		return idOption;
	}
	
	public int videoPlayerOptions() {
		return JOptionPane.showOptionDialog(null, null, null, JOptionPane.DEFAULT_OPTION,
				3, null, new Object[] { "PLAY", "PAUSE", "STOP"  }, null);
	}
//	public void openVideoPlayer(int videoId, int userId) {
//		String runningVideo = "Reproduciendo "+ VideoController.searchVideoById(videoId, userId).getTitle() + "\nTiempo transcurrido: "
//	}

	// Videos: volver atr�s o salir de aplicacion
	public int optMenuVideo() {
		int option = JOptionPane.showOptionDialog(null, "�Y ahora qu�?", null, JOptionPane.YES_NO_OPTION, 3, null,
				new Object[] { "Opciones de Video", "Salir" }, null);
		return option;
	}

	public int infoVideo(int userId) {
		int videoId = 0;
		String url = null;
		String title = null;
		boolean error = true;
		while (error) {
			url = JOptionPane.showInputDialog(null, "URL del v�deo", "DATOS DEL V�DEO", JOptionPane.QUESTION_MESSAGE);
			if (url == null) {
				videoId = -1;
				error = false;
			} else if (url != null) {
				title = JOptionPane.showInputDialog(null, "T�tulo del v�deo", "DATOS DEL V�DEO",
						JOptionPane.QUESTION_MESSAGE);
				if (title == null) {
					videoId = -1;
					error = false;
				}
			}
			if (videoId != -1) {
				try {
					DataValidation.checkInfoVideo(url, title);
					videoId = VideoController.createNewVideo(url, title, userId);
					error = false;
				} catch (NullPointerException videoDataEx) {
					System.err.println(videoDataEx.getMessage());
					wrongVideoData();
					error = true;
				}
			}
		}
		return videoId;
	}

	public void wrongVideoData() {
		JOptionPane.showInternalMessageDialog(null, "URL y T�tulo del v�deo son obligatorios");
	}

	public void regVideo(String title) {
		JOptionPane.showMessageDialog(null, title + " se ha a�adido a tu lista de v�deos!");
	}

	public void regVideoExist() {
		JOptionPane.showMessageDialog(null, "Ya tienes un v�deo con esta url");
	}

	public void regVideoCancelled() {
		JOptionPane.showMessageDialog(null, "Registro v�deo cancelado");
	}

	public boolean askForTags() {
		if (JOptionPane.showOptionDialog(null, "�A�adimos etiquetas al v�deo?", null, JOptionPane.YES_NO_OPTION, 3,
				null, null, null) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean askMoreTags() {
		if (JOptionPane.showOptionDialog(null, "�Quieres a�adir m�s etiquetas al v�deo?", null,
				JOptionPane.YES_NO_OPTION, 3, null, null, null) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean infoTags(int videoId, int userId) {
		boolean error = true;
		boolean infoTag = false;
		String videoTag = null;
		while(error) {
			videoTag = JOptionPane.showInputDialog(null, "Nueva Etiqueta", "ETIQUETAS V�DEO", 3);
			if (videoTag != null) {
				try {
					DataValidation.checkData(videoTag);
					VideoController.addVideoTag(videoTag, videoId, userId);
					infoTag = true;
					error = false;
				}catch (NullPointerException wrongTag) {
					System.err.println(wrongTag.getMessage());
					wrongTag();
					error = true;
				}
			} else {
				infoTag = false;
				error = false;
			}
		}
		return infoTag;
	}
	
	public void wrongTag() {
		JOptionPane.showInternalMessageDialog(null, "No indicaste ninguna etiqueta");
	}
	
	public void wrongId() {
		JOptionPane.showInternalMessageDialog(null, "No indicaste ning�n ID");
	}
}
