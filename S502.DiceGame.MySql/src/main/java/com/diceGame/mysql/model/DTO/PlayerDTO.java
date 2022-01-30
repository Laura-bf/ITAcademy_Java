package com.diceGame.mysql.model.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.diceGame.mysql.model.domain.Roll;

public class PlayerDTO {
	
	private Integer playerId;
	private String name;
	private String visibleName;
	private String password;
//	@Temporal(TemporalType.TIMESTAMP)
//	private final Date registerOn;
	private Double rate;
	private List<Roll> rollList;
//	private String token;
	
//	private Boolean authenticated;
	
	public PlayerDTO() {
//		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<Roll>();
//		this.authenticated = false;
	}
	
	public PlayerDTO(String name, String password) {
		this.name = name;
		this.visibleName = name;
		this.password = password;
//		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<Roll>();
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibleName() {
		return visibleName;
	}

	public void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Date getRegisterOn() {
//		return registerOn;
//	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<Roll> getRollList() {
		return rollList;
	}

	public void setRollList(List<Roll> rollList) {
		this.rollList = rollList;
	}

	public void addRoll() {
		Roll roll = new Roll();
		roll.playRoll();
		List<Roll> rollList = this.rollList;
		rollList.add(roll);
		this.setRollList(rollList);
		this.calculateRate();
	}
	
	public void deleteAllRollsFromList() {
		this.rollList.clear();
		this.rate = 0d;
	}
	
	public void calculateRate() {
		double totalWins = 0;
		double size = this.rollList.size();
		for (Roll roll : this.rollList) {
			if(roll.isWon())
				totalWins+=1;
		}
		double result = (totalWins/size)*100;
		this.rate = result;
	}

//	public Boolean getAuthenticated() {
//		return authenticated;
//	}
//
//	public void setAuthenticated(Boolean authenticated) {
//		this.authenticated = authenticated;
//	}

//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
}
