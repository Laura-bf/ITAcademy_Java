package com.nivel3.model.domain;

import java.util.Date;

public class Ticket {
	private static int countId = 1;
	private int ticketId;
	private Date date;
	
	public Ticket() {
		this.ticketId = countId;
		countId++;
		this.date = new Date();
		
	}

}
