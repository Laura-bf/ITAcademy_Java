package com.empleados.S402.nivel2.model.DTO;

import java.io.File;

import com.empleados.S402.nivel2.model.domain.Job;

public class EmployeeDTO {

	private Integer pk_ID;
	private String name;
	private Job job;
	private Double salary;
	private File photo;
	
	public EmployeeDTO() {
	
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
	
	public File getPhoto() {
		return this.photo;
	}

	public void setPhoto() {
		String s = "src/main/resources/static/uploads/empPhotos/"+this.pk_ID+".jpg";
		File file = new File(s);
		if(file.exists()) {
			this.photo = new File("/uploads/empPhotos/"+this.pk_ID+".jpg");
		}else
			this.photo = new File("/images/blank-profile.png");
	}
	public void setPhoto(String photoPath) {
		this.photo = new File(photoPath);
	}

	
}
