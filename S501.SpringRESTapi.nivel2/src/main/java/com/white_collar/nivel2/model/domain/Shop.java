package com.white_collar.nivel2.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Shop_id", nullable=false)
	private Integer id;
	@Column(name = "Shop_name", nullable=false)
	private String name;
	@Column(name = "Capacity", nullable=false)
	private int maxCapacity;
	@OneToMany(cascade = CascadeType.ALL) //como al añadir cuadros estos aún no están guardados en la base de datos con cascadetype.alllo guarda y luego lo añade
	private List<Picture> pictures;
	
	public Shop() {}
	
	public Shop(String name, int capacity) {
		this.name = name;
		this.maxCapacity = capacity;
		this.pictures = new ArrayList<Picture>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void addPicture(Picture picture) {
		pictures.add(picture);
	}
	
	public void deletePictures() {
		pictures.clear();
	}

}
