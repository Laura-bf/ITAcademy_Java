package com.video.model.DTO;

import com.video.model.domain.User;

public class UserDTO extends User {
	private static UserDTO activeUser;

	private UserDTO(String name, String surname, String password) {
		super(name, surname, password);
	}

	// para obtener activeUser �nicamente por este m�todo **SINGLETON**
	public static UserDTO getActiveUser(String name, String surname, String password) {
		if (activeUser == null) {
			activeUser = new UserDTO(name, surname, password);
			counterId--;
		}
		return activeUser;
	}
}
