package com.diceGame.model.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "players")
@Profile({"mongodb"})
public class MongoPlayer implements Comparable<MongoPlayer>{
	
	@MongoId(FieldType.OBJECT_ID)
	private String playerId;
	@Indexed//para crear un index de este campo y que se filtren los players por esta propiedad
	private String name;
	private String visibleName;
	private String password;
	private String role;
	private Date registerOn;
	private Double rate;
	private List<Roll> rollList;
	
	public MongoPlayer() {
		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<Roll>();
	}
	
	public MongoPlayer(String name, String password) {
		this.name = name;
		this.visibleName = name;
		this.password = password;
		this.registerOn = new Date();
		this.rate = 0d;
		this.rollList = new ArrayList<Roll>();
	}
	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String id) {
		this.playerId = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	
	//para ordenar los jugadores según su rate de mayor a menor
	@Override
    public int compareTo(MongoPlayer player){
        if(player.getRate()>this.rate){
            return 1;
        }else if(player.getRate()==this.rate){
            return 0;
        }else{
            return -1;
        }
    }
}
