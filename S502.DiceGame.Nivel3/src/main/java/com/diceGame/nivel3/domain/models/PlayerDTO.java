package com.diceGame.nivel3.domain.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
public class PlayerDTO {
	
	private Integer playerId;
	private String name;
	private String visibleName;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private final Date registerOn;
	private Double rate;
	private List<RollDTO> rollList;
	
	public PlayerDTO() {
		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<RollDTO>();
	}
	
	public PlayerDTO(String name, String password) {
		this.name = name;
		this.visibleName = name;
		this.password = password;
		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<RollDTO>();
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

	public Date getRegisterOn() {
		return registerOn;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<RollDTO> getRollList() {
		return rollList;
	}

	public void setRollList(List<RollDTO> rollList) {
		this.rollList = rollList;
	}
	
	public void calculateRate() {
		double totalWins = 0;
		double size = this.rollList.size();
		for (RollDTO roll : this.rollList) {
			if(roll.isWon())
				totalWins+=1;
		}
		double result = (totalWins/size)*100;
		this.rate = result;
	}
	
}
