package com.diceGame.nivel3.domain.documents;

import java.util.Random;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "rolls")
public class Roll {
	@MongoId
	private String rollId;
	private Integer valueDice1;
	private Integer valueDice2;
	private boolean won;
	
	private Integer playerId;

	public Roll() {
	}

	public String getRollId() {
		return rollId;
	}

	public void setRollId(String rollId) {
		this.rollId = rollId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getValueDice1() {
		return valueDice1;
	}

	public void setValueDice1(Integer valueDice1) {
		this.valueDice1 = valueDice1;
	}

	public Integer getValueDice2() {
		return valueDice2;
	}

	public void setValueDice2(Integer valueDice2) {
		this.valueDice2 = valueDice2;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public void playRoll(){
		this.valueDice1 = new Random().nextInt(6)+1;
		this.valueDice2 = new Random().nextInt(6)+1;
		if(this.valueDice1+this.valueDice2==7) 
			this.won = true;
		else 
			this.won = false;
	}

	@Override
	public String toString() {
		return "Roll [valueDice1=" + valueDice1 + ", valueDice2="
				+ valueDice2 + ", isWon=" + won + "]";
	}
}
