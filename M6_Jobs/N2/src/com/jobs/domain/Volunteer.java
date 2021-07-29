package com.jobs.domain;

public class Volunteer extends AbsStaffMember {

	String description;

	public Volunteer(String name, String address, String phone, String description) throws Exception {
		super(name, address, phone);
		this.description = description;

	}

	@Override
	public void pay() {
		totalPaid = 0;

	}

	@Override
	public String toString() {
		return "Volunteer [id=" + id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", description=" + description + ", totalPaid=" + totalPaid + "]\n";
	}
	
	

}
