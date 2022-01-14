package com.diceGame.model.domain;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rolls")
public class Roll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rollId")
	private Integer rollId;
	
	@ManyToOne()
	@JoinColumn(name = "playerId")
	private Player player;
	
	@Column(name = "dice1")
	private Integer valueDice1;
	
	@Column(name = "dice2")
	private Integer valueDice2;
	
	private boolean isWon;

	public Roll() {
	}
	
	public Roll(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
		return isWon;
	}

	public void setWon(boolean isWon) {
		this.isWon = isWon;
	}

	public void playRoll(){
		this.valueDice1 = new Random().nextInt(6)+1;
		this.valueDice2 = new Random().nextInt(6)+1;
		if(this.valueDice1+this.valueDice2==7) 
			this.isWon = true;
		else 
			this.isWon = false;
	}

	@Override
	public String toString() {
		return "Roll [valueDice1=" + valueDice1 + ", valueDice2="
				+ valueDice2 + ", isWon=" + isWon + "]";
	}

}
