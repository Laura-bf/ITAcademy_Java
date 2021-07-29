package com.jobs.domain;

public class Senior extends Employee {
	
	public Senior(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);
		this.paymentRate = paymentRate;
		
		pay();
		if(super.totalPaid < 2700 || totalPaid > 4000)
			throw new Exception("Senior salary must be between 2700€ and 4000€, both included");
	}

	@Override
	public void pay(){
		super.pay();
	}
	
	@Override
	public String toString() {
		return "Senior: " + super.toString();
	}

}
