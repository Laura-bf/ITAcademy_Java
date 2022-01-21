package com.diceGame.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Entity
@Document(collection = "players")
public class Player implements Comparable<Player> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@MongoId
	private Integer playerId;
	private String name;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private final Date registerOn;
	private Double rate;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@DocumentReference(lazy = true, collection ="rolls")
	private List<Roll> rollList;
	
	public Player() {
		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<Roll>();
	}
	
	public Player(String name, String password) {
		this.name = name;
		this.password = password;
		this.registerOn = new Date();
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

	@Override
	public String toString() {
		return playerId + "\t" + name + "\t" + rate + "%success";
	}
	//para ordenar los jugadores según su rate de mayor a menor
	@Override
    public int compareTo(Player player){
        if(player.getRate()>this.rate){
            return 1;
        }else if(player.getRate()==this.rate){
            return 0;
        }else{
            return -1;
        }
    }
}
