package com.diceGame.mysql.model.domain;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class Roll {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer rollId;
	private Integer valueDice1;
	private Integer valueDice2;
	private boolean won;

	public Roll() {
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
