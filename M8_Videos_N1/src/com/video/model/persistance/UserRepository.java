package com.video.model.persistance;

import java.util.ArrayList;
import java.util.List;

import com.video.model.domain.User;
import com.video.model.domain.Video;//sólo es necesaria para el método de pruebas/rellenar registros video

public class UserRepository {
	private static UserRepository userRep = new UserRepository();
	private static List<User> allUsers;

	private UserRepository() {
		allUsers = new ArrayList<User>();
	}

	public static UserRepository getUserRepository() {
		if (userRep == null) {
			userRep = new UserRepository();
		}
		return userRep;
	}

	// obtener el listado de usuarios de la instancia repository
	public List<User> getAllUsers() {
		return allUsers;
	}

	// añadir usuarios al registro
	public void addUsers(User user) {
		userRep.getAllUsers().add(user);
	}

	// PRUEBAS: para añadir automáticamente algunos registros
	public void testInitRep(int i) {
		User user = User.getTestUser("name" + i, "surname" + i, "pw" + i);
		user.addUserVideo(Video.getTestVideo("url" + i, "title" + i));
		userRep.getAllUsers().add(user);
	}

	@Override
	public String toString() {
		return "Listado Usuarios:\n" + userRep.getAllUsers().toString();
	}

}
