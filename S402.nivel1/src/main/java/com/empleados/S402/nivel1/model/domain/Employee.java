package com.empleados.S402.nivel1.model.domain;

import javax.persistence.*;

@Entity  					
@Table(name = "Empleados")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer pk_ID;
	@Column(name="Nombre", length = 50, nullable = false)
	private String name;
	@Column(name="Categoría", nullable = false)
	private Job job;
	
	public Employee() {
		
	}
	
	public Employee(String name, Job job) {
		this.name = name;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Integer getPk_ID() {
		return pk_ID;
	}

	public void setPk_ID(Integer pk_ID) {
		this.pk_ID = pk_ID;
	}

}
