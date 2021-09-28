package com.video.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	public static int counterId = 0;
	private int userId;
	private String name;
	private String surname;
	private String password;
	private Date regDate;
	private List<Video> postedVideos = new ArrayList<Video>();

	public User(String name, String surname, String password) {
		counterId++;
		this.userId = counterId;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.regDate = new Date();
	}

	// para añadir videos al listado de un usuario
	public void addUserVideo(Video video) {
		postedVideos.add(video);
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public List<Video> getPostedVideos() {
		return postedVideos;
	}
	
	// PRUEBAS: para añadir registros al inicio del repository
	public static User getTestUser(String name, String surname, String password) {
		User testUser = new User(name, surname, password);
		return testUser;
	}
	
	@Override
	public String toString() {
		return "User: userId=" + userId + "\t name=" + name + ",\t surname=" + surname + "\t password=" + password
				+ "\t regDate=" + regDate + "\t postedVideos=" + postedVideos + "\n";
	}
}
