package com.video.view.application;

import com.video.controller.UserController;
import com.video.controller.VideoController;
import com.video.view.UserWindow;
import com.video.view.VideoWindow;

public class VideosApp {

	public static void main(String[] args) {

		UserWindow window = UserWindow.getWindow();
		VideoWindow videoWindow = VideoWindow.getWindow();
		UserController.addTestUsers();

		boolean end = false;
		boolean end2 = false;
		int sesionOpt = 0;
		int userId = 0;
		int videoId = 0;
		do {
			int startOpt = window.startMenu();
			window.infoUser();
			while (UserController.getActiveUSer() == null) {
				window.regUserCancelled();
				startOpt = window.startMenu();
				window.infoUser();
			}

			// busca en repositorio usuarios para comprobar si ya existe este registro
			int idExists = UserController.searchUser();
/* TEST */ 	System.out.println(idExists != 0);

			// registra o inicia sesión según corresponda
			switch (startOpt) {
			case 0:
				if (idExists == 0) {
					UserController.createNewUser();
					userId = UserController.getUserId();
					window.regUser();
				} else {
					window.regUserExist(UserController.getActiveUSer().getName());
					userId = idExists;
				}
/* TEST */ 		System.out.println(UserController.getUserRep().getAllUsers().toString());
				sesionOpt = window.askStartSesion(UserController.getActiveUSer().getName());
				if (sesionOpt == 0) {
					sesionOpt = videoWindow.startSesion(UserController.getActiveUSer().getName());
				} else {
					sesionOpt = 2;
				}
				break;
			case 1:
				if (idExists == 0) {
					int noReg = window.noRegUser();
					if (noReg == 0) {
						end = false;
						end2 = true;
					} else if (noReg == 1) {
						if (window.checkInfoUser(UserController.infoActiveUser())) {
							window.setPassword();
							UserController.createNewUser();
							window.regUser();
							sesionOpt = videoWindow.startSesion(UserController.getActiveUSer().getName());
							userId = UserController.getUserId();
							end = true;
/* TEST */ 				System.out.println(UserController.getUserRep().getAllUsers().toString());
						} else {
							end = false;
						}
					}
				} else {
					sesionOpt = videoWindow.startSesion(UserController.getActiveUSer().getName());
					userId = idExists;
				}
				break;
			}

			while(!end2) {
				switch (sesionOpt) {
				case 0:
					videoWindow.showVideos(userId);
					if (videoWindow.optMenuVideo() == 0) {
						sesionOpt = videoWindow.startSesion(UserController.getActiveUSer().getName());
					} else {
						window.closeApp();
						end2 = true;
						end = true;
					}
					break;
				case 1:// Crear nuevo video
					videoId = videoWindow.infoVideo(userId);
					if (videoId == -1) {
						videoWindow.regVideoCancelled();
					} else if (videoId == 0) {
						videoWindow.regVideoExist();
					} else {
						videoWindow.regVideo(VideoController.searchVideoById(videoId, userId).getTitle());
						if (videoWindow.askForTags()) {
							do {
								videoWindow.infoTags(videoId, userId);
							} while (videoWindow.askMoreTags());
						}
					}
					sesionOpt = videoWindow.startSesion(UserController.getActiveUSer().getName());
/* TEST */ 			System.out.println(VideoController.showPostedVideos(userId));
					break;
				case 2:
					window.closeApp();
					end2 = true;
					end = true;
					break;
				}
			}
		} while (!end);
	}

}
