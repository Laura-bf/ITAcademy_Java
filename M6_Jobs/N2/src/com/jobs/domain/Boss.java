package com.jobs.domain;

public class Boss extends Employee {

	public Boss(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);
		
		this.paymentRate = paymentRate;
	}
	
	@Override
	public void pay() throws Exception {
		super.pay();
		if (super.totalPaid< 8000)
			throw new Exception("Boss salary must be at least 8000€");
	}

	@Override
	public String toString() {
		return "Boss: " + super.toString();
	}

}
