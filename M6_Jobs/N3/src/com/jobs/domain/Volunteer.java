package com.jobs.domain;

public class Volunteer extends AbsStaffMember {

	protected boolean aid;
	protected double aidAmount;

	public Volunteer(String name, String address, String phone, boolean aid, double aidAmount) throws Exception {
		super(name, address, phone);
		this.aid = aid;
		this.aidAmount = aidAmount;
		if(!aid) {
			if(aidAmount != 0) throw new Exception("This volunteer has no aid");
		} else if(aidAmount <= 0 || aidAmount > 300)  throw new RuntimeException("No negative values allowed in this field. Maximum aid=300€");
		
	}

	@Override
	public void pay() {
		if(aid) {
			super.extraPay = "Has an aid of " + aidAmount + "€.";
		} else {
			super.extraPay ="Has no aid";
		}
	}

	@Override
	public String toString() {
		return "Volunteer: id=" + id + ", Name: " + name + ", Address: " + address
				+ ", Phone: " + phone + " / " + super.extraPay + "\n";
	}
	
	

}
