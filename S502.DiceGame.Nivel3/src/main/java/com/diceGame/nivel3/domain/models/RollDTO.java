package com.diceGame.nivel3.domain.models;

import org.springframework.stereotype.Component;

@Component
public class RollDTO {

	private Integer valueDice1;
	private Integer valueDice2;
	private boolean won;
	
	private Integer playerId;

	public RollDTO() {
		super();
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

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
