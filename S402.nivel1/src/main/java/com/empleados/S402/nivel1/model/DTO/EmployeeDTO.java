package com.empleados.S402.nivel1.model.DTO;

import com.empleados.S402.nivel1.model.domain.Job;

public class EmployeeDTO {

	private Integer pk_ID;
	private String name;
	private Job job;
	private Double salary;
	
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
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	

	public Integer getPk_ID() {
		return pk_ID;
	}


	public void setPk_ID(Integer pk_ID) {
		this.pk_ID = pk_ID;
	}

	
}
