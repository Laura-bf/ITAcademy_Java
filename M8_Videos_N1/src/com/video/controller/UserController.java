package com.video.controller;

import com.video.model.domain.User;
import com.video.model.persistance.UserDTO;
import com.video.model.persistance.UserRepository;

public class UserController {
	
	private static UserRepository userRep = UserRepository.getUserRepository();
	private static UserDTO activeUser;
	
	public static void setActiveUser(String name, String surname, String password) {
		activeUser = UserDTO.getActiveUser(name, surname, password);
	}
	
	public static UserDTO getActiveUSer() {
		return activeUser;
	}
	
	public static UserRepository getUserRep() {
		return userRep;
	}
	
	public static int searchUser() {
		int idExists = 0;
		for(int i = userRep.getAllUsers().size()-1; i>=0; i--) {
			if((activeUser.getName().equalsIgnoreCase(userRep.getAllUsers().get(i).getName()) 
					&& activeUser.getSurname().equalsIgnoreCase(userRep.getAllUsers().get(i).getSurname()))) {
				idExists = userRep.getAllUsers().get(i).getUserId();
			}
		}
		return idExists;
	}
	
	public static User searchUserById(int userId) {
		User user = null;
		for(int i = userRep.getAllUsers().size()-1; i>=0; i--) {
			if(userId==userRep.getAllUsers().get(i).getUserId()) {
				user = userRep.getAllUsers().get(i);
				i=-1;
			}
		}
		return user;
	}
	
	public static void createNewUser() {
		User newUser = new User(activeUser.getName(), activeUser.getSurname(), activeUser.getPassword());
		userRep.addUsers(newUser);
	}
	
	public static int getUserId() {
		int userId = 0;
		for(User user : userRep.getAllUsers()) {
			if(activeUser.getUserId()==user.getUserId()) {
				userId = user.getUserId();
			}
		}
		return userId;
	}
	
	public static void setPassWord(String password) {
		activeUser.setPassword(password);
	}
	
	public static String infoActiveUser() {
		return "Nombre: " + activeUser.getName().toUpperCase() +" Apellido: "+ activeUser.getSurname().toUpperCase();
	}
	//PRUEBAS: añadir usuarios al repositorio
	public static void addTestUsers() {
		userRep.testInitRep(1);
		userRep.testInitRep(2);
		userRep.testInitRep(3);
/*TEST*/System.out.println(userRep);	
	}
}
