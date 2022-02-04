package com.diceGame.nivel3.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player implements Comparable<Player> {
	
	@Id
	@GeneratedValue(generator = "idGenerator") 
	private Integer playerId;
	private String name;
	private String visibleName;
	private String password;
	private final Date registerOn;
	private Double rate;
	
	public Player() {
		this.registerOn = new Date();
		this.rate = 0d;
	}
	
	public Player(String name, String password) {
		this.name = name;
		this.visibleName = name;
		this.password = password;
		this.registerOn = new Date();
		this.rate = 0d;
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
