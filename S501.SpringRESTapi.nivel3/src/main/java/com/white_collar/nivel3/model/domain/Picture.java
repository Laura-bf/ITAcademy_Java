package com.white_collar.nivel3.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="picture_id")
	private Integer picture_id;
	
	private String pictureName;
	private String author;
	private double price;
	private LocalDate regDate;
	
	public Picture() {
		
	}
	
	public Picture(String pictureName, String author) {
		this.pictureName = pictureName;
		if(author.equals(null)||author.equals(""))
			this.author = "anonymous";
		else
			this.author = author;
		this.regDate=LocalDate.now();
	}
	
	public Integer getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(Integer picture_id) {
		this.picture_id = picture_id;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	
}
