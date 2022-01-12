package com.diceGame.model.domain;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playerId")
	private Integer playerId;
	@Column(name = "name", length = 15, nullable = false, unique = true)
	private String name;
	@Column(name = "password", length = 10, nullable = false)
	private String password;
	@Column (name = "registerOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerOn;
	@Column (name = "rate")
	private Double rate;
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Roll> rollList;
	
	public Player() {
	}
	
	public Player(String name, String password) {
		this.name = name;
		this.password = password;
		this.registerOn = new Date();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterOn() {
		return registerOn;
	}

	public void setRegisterOn(Date registerOn) {
		this.registerOn = registerOn;
	}

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

	public void addRoll(Roll roll) {
		this.rollList.add(roll);
		roll.setPlayer(this);
	}
	
	public void deleteAllRollsFromList() {
		this.rollList.clear();
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

}
